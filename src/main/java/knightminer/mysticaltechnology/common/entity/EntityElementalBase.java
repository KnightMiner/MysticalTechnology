package knightminer.mysticaltechnology.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityElementalBase extends EntityBlaze {

	public EntityElementalBase(World worldIn) {
		super(worldIn);
		// water is still removed from the list, elementals don't like water
		this.setPathPriority(PathNodeType.LAVA, -1.0F);
		this.setPathPriority(PathNodeType.DANGER_FIRE, 8.0F);
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, 16.0F);
		this.isImmuneToFire = false;
		this.experienceValue = 10;
	}

	// uses custom implementation for fireball attacking
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(4, new AIFireballAttack(this));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
	}

	/**
	 * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
	 */
	@Override
	public boolean isBurning() {
		boolean flag = this.worldObj != null && this.worldObj.isRemote;
		return !this.isImmuneToFire && (this.fire > 0 || flag && this.getFlag(0));
	}

	/**
	 * Returns an new instance of the fireball entity to be summoned when an elemental fires a fireball
	 */
	protected abstract EntitySmallFireball getFireball(double x, double y, double z);

	@Override
	protected abstract SoundEvent getAmbientSound();

	@Override
	protected abstract SoundEvent getHurtSound();

	@Override
	protected abstract SoundEvent getDeathSound();

	// Copied from EntityBlaze since I need to use custom fireballs to change the effects
	static class AIFireballAttack extends EntityAIBase {
		private final EntityElementalBase elemental;
		private int attackStep;
		private int attackTime;

		public AIFireballAttack(EntityElementalBase elemental) {
			this.elemental = elemental;
			this.setMutexBits(3);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = this.elemental.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		@Override
		public void startExecuting() {
			this.attackStep = 0;
		}

		/**
		 * Resets the task
		 */
		@Override
		public void resetTask() {
			// TODO
			//this.elemental.setOnFire(false);
		}

		/**
		 * Updates the task
		 */
		@Override
		public void updateTask() {
			--this.attackTime;
			EntityLivingBase entitylivingbase = this.elemental.getAttackTarget();
			double distanceSq = this.elemental.getDistanceSqToEntity(entitylivingbase);

			if (distanceSq < 4.0D) {
				if (this.attackTime <= 0) {
					this.attackTime = 20;
					this.elemental.attackEntityAsMob(entitylivingbase);
				}

				this.elemental.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}
			else if (distanceSq < 256.0D) {
				double x = entitylivingbase.posX - this.elemental.posX;
				double y = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.elemental.posY + this.elemental.height / 2.0F);
				double z = entitylivingbase.posZ - this.elemental.posZ;

				if (this.attackTime <= 0) {
					++this.attackStep;

					if (this.attackStep == 1) {
						this.attackTime = 60;
						this.elemental.setOnFire(true);
					}
					else if (this.attackStep <= 4) {
						this.attackTime = 6;
					}
					else {
						this.attackTime = 100;
						this.attackStep = 0;
						this.elemental.setOnFire(false);
					}

					if (this.attackStep > 1) {
						float f = MathHelper.sqrt_float(MathHelper.sqrt_double(distanceSq)) * 0.5F;
						this.elemental.worldObj.playEvent((EntityPlayer)null, 1018, new BlockPos((int)this.elemental.posX, (int)this.elemental.posY, (int)this.elemental.posZ), 0);

						for (int i = 0; i < 1; ++i) {
							EntitySmallFireball entitysmallfireball = elemental.getFireball(x + this.elemental.getRNG().nextGaussian() * f, y, z + this.elemental.getRNG().nextGaussian() * f);
							entitysmallfireball.posY = this.elemental.posY + this.elemental.height / 2.0F + 0.5D;
							this.elemental.worldObj.spawnEntityInWorld(entitysmallfireball);
						}
					}
				}

				this.elemental.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
			}
			else {
				this.elemental.getNavigator().clearPathEntity();
				this.elemental.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}

			super.updateTask();
		}
	}
}
