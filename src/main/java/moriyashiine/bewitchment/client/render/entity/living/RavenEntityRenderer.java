/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.render.entity.living;

import moriyashiine.bewitchment.client.BewitchmentClient;
import moriyashiine.bewitchment.client.model.entity.living.RavenEntityModel;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.RavenEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class RavenEntityRenderer extends MobEntityRenderer<RavenEntity, RavenEntityModel<RavenEntity>> {
	private static Identifier[] TEXTURES;

	public RavenEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new RavenEntityModel<>(context.getPart(BewitchmentClient.RAVEN_MODEL_LAYER)), 0.3f);
	}

	@Override
	public Identifier getTexture(RavenEntity entity) {
		if (TEXTURES == null) {
			int variants = entity.getVariants();
			TEXTURES = new Identifier[variants];
			for (int i = 0; i < variants; i++) {
				TEXTURES[i] = Bewitchment.id("textures/entity/living/raven/" + i + ".png");
			}
		}
		return TEXTURES[entity.getVariant()];
	}
}
