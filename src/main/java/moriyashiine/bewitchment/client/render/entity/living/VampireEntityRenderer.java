/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.render.entity.living;

import moriyashiine.bewitchment.client.BewitchmentClient;
import moriyashiine.bewitchment.client.model.entity.living.VampireEntityModel;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.VampireEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class VampireEntityRenderer extends MobEntityRenderer<VampireEntity, VampireEntityModel<VampireEntity>> {
	private static Identifier[] TEXTURES;

	public VampireEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new VampireEntityModel<>(context.getPart(BewitchmentClient.VAMPIRE_MODEL_LAYER)), 0.3f);
	}

	@Override
	public Identifier getTexture(VampireEntity entity) {
		if (TEXTURES == null) {
			int variants = entity.getVariants();
			TEXTURES = new Identifier[variants];
			for (int i = 0; i < variants; i++) {
				TEXTURES[i] = Bewitchment.id("textures/entity/living/vampire/" + i + ".png");
			}
		}
		return TEXTURES[entity.getVariant()];
	}
}
