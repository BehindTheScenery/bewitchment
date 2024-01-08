/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.render.entity.living;

import moriyashiine.bewitchment.client.BewitchmentClient;
import moriyashiine.bewitchment.client.model.entity.living.SnakeEntityModel;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.SnakeEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SnakeEntityRenderer extends MobEntityRenderer<SnakeEntity, SnakeEntityModel<SnakeEntity>> {
	private static Identifier[] TEXTURES;

	public SnakeEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new SnakeEntityModel<>(context.getPart(BewitchmentClient.SNAKE_MODEL_LAYER)), 0.3f);
	}

	@Override
	public Identifier getTexture(SnakeEntity entity) {
		if (TEXTURES == null) {
			int variants = entity.getVariants();
			TEXTURES = new Identifier[variants];
			for (int i = 0; i < variants; i++) {
				TEXTURES[i] = Bewitchment.id("textures/entity/living/snake/" + i + ".png");
			}
		}
		return TEXTURES[entity.getVariant()];
	}
}
