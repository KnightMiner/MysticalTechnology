package knightminer.mysticaltechnology.common.entity;

import knightminer.mysticaltechnology.MysticalTechnology;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityElementalFireball extends EntitySmallFireball {

	public EntityElementalFireball(World worldIn) {
		super(worldIn);
	}

	public EntityElementalFireball(World worldIn, EntityLivingBase shooter, double accelX, double accelY,
			double accelZ) {
		super(worldIn, shooter, accelX, accelY, accelZ);
	}

	public EntityElementalFireball(World worldIn, double x, double y, double z, double accelX, double accelY,
			double accelZ) {
		super(worldIn, x, y, z, accelX, accelY, accelZ);
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult result) {
		if (!this.worldObj.isRemote) {
			if (result.entityHit != null) {
				onEntityHit(result.entityHit);
			}
			else {
				boolean canGrief = true;

				if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving) {
					canGrief = this.worldObj.getGameRules().getBoolean("mobGriefing");
				}

				if (canGrief) {
					onBlockHit(result.getBlockPos().offset(result.sideHit));
				}
			}

			this.setDead();
		}
	}

	@Override
	protected boolean isFireballFiery() {
		return false;
	}

	private void onEntityHit(Entity entityHit) {
		// TODO: more types
		boolean success = entityHit.attackEntityFrom(new EntityDamageSourceIndirect("onFire", this, this.shootingEntity).setProjectile(), 5.0F);

		if (success) {
			this.applyEnchantments(this.shootingEntity, entityHit);
			if(entityHit instanceof EntityLivingBase) {
				((EntityLivingBase) entityHit).addPotionEffect(new PotionEffect(MobEffects.POISON, 100));
			}
		}
	}

	private void onBlockHit(BlockPos pos) {
		/*

		if (this.worldObj.isAirBlock(pos)) {
			this.worldObj.setBlockState(pos, Blocks.FIRE.getDefaultState());
		}*/
	}

}
