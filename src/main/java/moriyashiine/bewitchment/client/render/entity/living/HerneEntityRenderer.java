/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.render.entity.living;

import moriyashiine.bewitchment.client.BewitchmentClient;
import moriyashiine.bewitchment.client.model.entity.living.HerneEntityModel;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.HerneEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.util.Identifier;

public class HerneEntityRenderer extends MobEntityRenderer<HerneEntity, HerneEntityModel<HerneEntity>> {
	private static final Identifier TEXTURE = Bewitchment.id("textures/entity/living/herne.png");

	public HerneEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new HerneEntityModel<>(context.getPart(BewitchmentClient.HERNE_MODEL_LAYER)), 0.5f);
		addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
	}

	@Override
	public Identifier getTexture(HerneEntity entity) {
		return TEXTURE;
	}
}
