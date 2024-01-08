/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.render.entity.living;

import moriyashiine.bewitchment.client.BewitchmentClient;
import moriyashiine.bewitchment.client.model.entity.living.LeonardEntityModel;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.LeonardEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.util.Identifier;

public class LeonardEntityRenderer extends MobEntityRenderer<LeonardEntity, LeonardEntityModel<LeonardEntity>> {
	private static final Identifier TEXTURE = Bewitchment.id("textures/entity/living/leonard.png");

	public LeonardEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new LeonardEntityModel<>(context.getPart(BewitchmentClient.LEONARD_MODEL_LAYER)), 0.5f);
		addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
	}

	@Override
	public Identifier getTexture(LeonardEntity entity) {
		return TEXTURE;
	}
}
