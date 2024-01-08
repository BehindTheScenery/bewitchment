/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.render.entity.living;

import moriyashiine.bewitchment.client.BewitchmentClient;
import moriyashiine.bewitchment.client.model.entity.living.ToadEntityModel;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.ToadEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ToadEntityRenderer extends MobEntityRenderer<ToadEntity, ToadEntityModel<ToadEntity>> {
	private static Identifier[] TEXTURES;

	public ToadEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new ToadEntityModel<>(context.getPart(BewitchmentClient.TOAD_MODEL_LAYER)), 0.3f);
	}

	@Override
	public Identifier getTexture(ToadEntity entity) {
		if (TEXTURES == null) {
			int variants = entity.getVariants();
			TEXTURES = new Identifier[variants];
			for (int i = 0; i < variants; i++) {
				TEXTURES[i] = Bewitchment.id("textures/entity/living/toad/" + i + ".png");
			}
		}
		return TEXTURES[entity.getVariant()];
	}
}
