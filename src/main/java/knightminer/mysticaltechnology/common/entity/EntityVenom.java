package knightminer.mysticaltechnology.common.entity;

import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityVenom extends EntityElementalBase {

	public EntityVenom(World worldIn) {
		super(worldIn);
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(source.isMagicDamage()) {
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected EntitySmallFireball getFireball(double x, double y, double z) {
		return new EntityElementalFireball(this.worldObj, this, x, y, z);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SoundEvent getHurtSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		// TODO Auto-generated method stub
		return null;
	}

}
