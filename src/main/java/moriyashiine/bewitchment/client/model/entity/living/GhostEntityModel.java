/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.model.entity.living;

import moriyashiine.bewitchment.common.entity.living.GhostEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

public class GhostEntityModel<T extends GhostEntity> extends BipedEntityModel<T> {
	private final ModelPart realBody;
	private final ModelPart realBodyTrail00;
	private final ModelPart BipedLeftArm;
	private final ModelPart BipedRightArm;
	private final ModelPart realHead;

	private boolean realArm = false;

	public GhostEntityModel(ModelPart root) {
		super(root);
		realBody = root.getChild("realBody");
		realBodyTrail00 = realBody.getChild("realBodyTrail00");
		BipedLeftArm = root.getChild("BipedLeftArm");
		BipedRightArm = root.getChild("BipedRightArm");
		realHead = root.getChild("realHead");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = BipedEntityModel.getModelData(Dilation.NONE, 0);
		ModelPartData root = data.getRoot();
		ModelPartData BipedRightArm = root.addChild("BipedRightArm", ModelPartBuilder.create().uv(40, 16).mirrored(true).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 13.0F, 4.0F), ModelTransform.of(-5.0F, 2.0F, 0.0F, -1.3963F, 0.0F, 0.1F));
		BipedRightArm.addChild("rArmWisp", ModelPartBuilder.create().uv(40, 34).mirrored(true).cuboid(-1.5F, -4.5F, 0.1F, 3.0F, 11.0F, 4.0F), ModelTransform.of(-1.0F, 2.7F, 1.7F, 0.0F, 0.0F, 0.0F));
		ModelPartData realHead = root.addChild("realHead", ModelPartBuilder.create().cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData skull = realHead.addChild("skull", ModelPartBuilder.create().uv(34, 0).cuboid(-3.5F, -7.5F, -3.0F, 7.0F, 5.0F, 6.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		skull.addChild("skullJaw", ModelPartBuilder.create().uv(19, 30).cuboid(-2.5F, -1.0F, -3.5F, 5.0F, 2.0F, 5.0F), ModelTransform.of(0.0F, -1.9F, 0.9F, 0.1745F, 0.0F, 0.0F));
		ModelPartData BipedLeftArm = root.addChild("BipedLeftArm", ModelPartBuilder.create().uv(40, 16).mirrored(true).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 13.0F, 4.0F), ModelTransform.of(5.0F, 2.0F, 0.0F, -1.3963F, 0.0F, -0.1F));
		BipedLeftArm.addChild("lArmWisp", ModelPartBuilder.create().uv(40, 34).mirrored(true).cuboid(-1.5F, -4.5F, 0.1F, 3.0F, 11.0F, 4.0F), ModelTransform.of(1.0F, 2.7F, 1.7F, 0.0F, 0.0F, 0.0F));
		ModelPartData realBody = root.addChild("realBody", ModelPartBuilder.create().uv(14, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2094F, 0.0F, 0.0F));
		ModelPartData realBodyTrail00 = realBody.addChild("realBodyTrail00", ModelPartBuilder.create().uv(33, 51).cuboid(-4.5F, 0.0F, -2.5F, 9.0F, 6.0F, 5.0F), ModelTransform.of(0.0F, 7.7F, 0.0F, 0.1047F, 0.0F, 0.0F));
		realBodyTrail00.addChild("realBodyTrail01", ModelPartBuilder.create().uv(0, 39).cuboid(-5.0F, 0.0F, -3.0F, 10.0F, 9.0F, 6.0F), ModelTransform.of(0.0F, 5.8F, 0.1F, 0.1047F, 0.0F, 0.0F));
		return TexturedModelData.of(data, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float realHeadYaw, float realHeadPitch) {
		realArm = false;
		super.setAngles(entity, limbAngle, limbDistance, animationProgress, realHeadYaw, realHeadPitch);
		realArm = true;
		copyRotation(realHead, super.head);
		realBodyTrail00.pitch = MathHelper.sin(animationProgress / 12) / 6;
		CrossbowPosing.meleeAttack(leftArm, rightArm, false, entity.handSwingProgress, animationProgress);
		if (entity.getDataTracker().get(GhostEntity.HAS_TARGET)) {
			rightArm.pitch += 4.5;
			rightArm.roll = MathHelper.sin(animationProgress) / 2;
			leftArm.pitch += 4.5;
			leftArm.roll = -rightArm.roll;
		} else {
			leftArm.roll = -0.1f;
			rightArm.roll = 0.1f;
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		realHead.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		realBody.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		leftArm.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		rightArm.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	protected ModelPart getArm(Arm arm) {
		return realArm ? (arm == Arm.LEFT ? BipedLeftArm : BipedRightArm) : super.getArm(arm);
	}

	private void copyRotation(ModelPart to, ModelPart from) {
		to.pitch = from.pitch;
		to.yaw = from.yaw;
		to.roll = from.roll;
	}
}
