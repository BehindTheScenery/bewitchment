/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.common.statuseffect;

import moriyashiine.bewitchment.client.packet.SpawnExplosionParticlesPacket;
import moriyashiine.bewitchment.common.entity.living.ToadEntity;
import moriyashiine.bewitchment.common.registry.BWDamageSources;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class WednesdayStatusEffect extends StatusEffect {
	public WednesdayStatusEffect(StatusEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);
		entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1, 1);
		PlayerLookup.tracking(entity).forEach(trackingPlayer -> SpawnExplosionParticlesPacket.send(trackingPlayer, entity));
		if (entity instanceof ServerPlayerEntity player) {
			SpawnExplosionParticlesPacket.send(player, entity);
		}
		if (entity instanceof ToadEntity toad && toad.isFromWednesdayRitual) {
			entity.remove(Entity.RemovalReason.DISCARDED);
		} else {
			entity.damage(BWDamageSources.create(entity.getWorld(), BWDamageSources.WEDNESDAY), Float.MAX_VALUE);
		}
	}
}
