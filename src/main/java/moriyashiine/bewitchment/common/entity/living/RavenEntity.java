package moriyashiine.bewitchment.common.entity.living;

import moriyashiine.bewitchment.common.entity.living.util.BWTameableEntity;
import moriyashiine.bewitchment.common.registry.BWEntityTypes;
import moriyashiine.bewitchment.common.registry.BWTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RavenEntity extends BWTameableEntity {
	public RavenEntity(EntityType<? extends TameableEntity> type, World world) {
		super(type, world);
		moveControl = new FlightMoveControl(this, 180, false);
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6);
	}
	
	@Override
	protected boolean hasShiny() {
		return true;
	}
	
	@Override
	public int getVariants() {
		return 2;
	}
	
	@Override
	protected boolean isTamingItem(ItemStack stack) {
		return stack.getItem() == Items.GOLD_NUGGET || BWTags.SILVER_NUGGETS.contains(stack.getItem());
	}
	
	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		if (entity instanceof RavenEntity)
		{
			RavenEntity child = BWEntityTypes.RAVEN.create(world);
			if (child != null) {
				child.dataTracker.set(VARIANT, random.nextBoolean() ? dataTracker.get(VARIANT) : entity.getDataTracker().get(VARIANT));
			}
		}
		return null;
	}
	
	@Override
	protected EntityNavigation createNavigation(World world) {
		BirdNavigation birdNavigation = new BirdNavigation(this, world);
		birdNavigation.setCanPathThroughDoors(false);
		birdNavigation.setCanSwim(true);
		birdNavigation.setCanEnterOpenDoors(true);
		return birdNavigation;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == Items.WHEAT_SEEDS;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15f, 1);
	}
	
	@Override
	protected float playFlySound(float distance) {
		playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15f, 1);
		return distance;
	}
	
	@Override
	protected boolean hasWings() {
		return true;
	}
	
	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
		return false;
	}
	
	@Override
	protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		EntityAttributeInstance maxHealth = getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
		EntityAttributeInstance attackDamage = getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
		if (tamed) {
			maxHealth.setBaseValue(20);
			attackDamage.setBaseValue(3);
			setHealth(getMaxHealth());
		}
		else {
			maxHealth.setBaseValue(8);
			attackDamage.setBaseValue(1);
		}
	}
	
	@Override
	protected void initGoals() {
		goalSelector.add(1, new SwimGoal(this));
		goalSelector.add(2, new SitGoal(this));
		goalSelector.add(3, new MeleeAttackGoal(this, 1, true));
		goalSelector.add(4, new FollowOwnerGoal(this, 1, 10, 2, false));
		goalSelector.add(5, new AnimalMateGoal(this, 1));
		goalSelector.add(6, new WanderAroundFarGoal(this, 1));
		goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 8));
		goalSelector.add(7, new LookAroundGoal(this));
		targetSelector.add(1, new TrackOwnerAttackerGoal(this));
		targetSelector.add(2, new AttackWithOwnerGoal(this));
		targetSelector.add(3, (new RevengeGoal(this)).setGroupRevenge());
	}
}