/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.model.entity.living;

import moriyashiine.bewitchment.common.entity.living.VampireEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;

public class VampireEntityModel<T extends VampireEntity> extends BipedEntityModel<T> {
	private final ModelPart realBody;
	private final ModelPart robe;
	private final ModelPart downArms;
	private final ModelPart lArm;
	private final ModelPart rArm;
	private final ModelPart realHead;
	private final ModelPart lLeg;
	private final ModelPart rLeg;
	private final ModelPart crossedArms;

	private boolean realArm = false;

	public VampireEntityModel(ModelPart root) {
		super(root);
		realBody = root.getChild("realBody");
		robe = root.getChild("robe");
		downArms = root.getChild("downArms");
		lArm = downArms.getChild("lArm");
		rArm = lArm.getChild("rArm");
		realHead = root.getChild("realHead");
		lLeg = root.getChild("lLeg");
		rLeg = root.getChild("rLeg");
		crossedArms = root.getChild("crossedArms");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = BipedEntityModel.getModelData(Dilation.NONE, 0);
		ModelPartData root = data.getRoot();
		root.addChild("rLeg", ModelPartBuilder.create().uv(0, 22).mirrored(true).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), ModelTransform.of(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		root.addChild("crossedArms", ModelPartBuilder.create().uv(44, 22).cuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).mirrored(true).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).uv(40, 38).mirrored(false).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F), ModelTransform.of(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
		root.addChild("realBody", ModelPartBuilder.create().uv(16, 20).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData realHead = root.addChild("realHead", ModelPartBuilder.create().cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		realHead.addChild("nose", ModelPartBuilder.create().uv(24, 0).cuboid(-1.0F, -1.3F, -1.0F, 2.0F, 3.0F, 2.0F), ModelTransform.of(0.0F, -3.2F, -4.1F, -0.7854F, 0.0F, 0.0F));
		root.addChild("lLeg", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), ModelTransform.of(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		root.addChild("robe", ModelPartBuilder.create().uv(0, 39).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.25F, 0.25F, 0.25F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData downArms = root.addChild("downArms", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData lArm = downArms.addChild("lArm", ModelPartBuilder.create().uv(40, 46).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), ModelTransform.of(5.0F, -22.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		lArm.addChild("lClaw01", ModelPartBuilder.create().cuboid(-1.1F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F), ModelTransform.of(2.3F, 8.6F, -1.4F, 0.0F, 0.0F, 0.1745F));
		lArm.addChild("lClaw02", ModelPartBuilder.create().cuboid(-1.1F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F), ModelTransform.of(2.3F, 8.8F, -0.1F, 0.0F, 0.0F, 0.1745F));
		lArm.addChild("lClaw03", ModelPartBuilder.create().cuboid(-1.1F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F), ModelTransform.of(2.3F, 8.6F, 1.2F, 0.0F, 0.0F, 0.1745F));
		ModelPartData rArm = lArm.addChild("rArm", ModelPartBuilder.create().uv(40, 46).mirrored(true).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), ModelTransform.of(-10.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		rArm.addChild("rClaw01", ModelPartBuilder.create().mirrored(true).cuboid(-1.1F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F), ModelTransform.of(-2.3F, 8.6F, -1.4F, 0.0F, 0.0F, -0.1745F));
		rArm.addChild("rClaw02", ModelPartBuilder.create().mirrored(true).cuboid(-1.1F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F), ModelTransform.of(-2.3F, 8.8F, -0.1F, 0.0F, 0.0F, -0.1745F));
		rArm.addChild("rClaw03", ModelPartBuilder.create().mirrored(true).cuboid(-1.1F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F), ModelTransform.of(-2.3F, 8.6F, 1.2F, 0.0F, 0.0F, -0.1745F));
		return TexturedModelData.of(data, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float realHeadYaw, float realHeadPitch) {
		realArm = false;
		super.setAngles(entity, limbAngle, limbDistance, animationProgress, realHeadYaw, realHeadPitch);
		realArm = true;
		copyRotation(realHead, super.head);
		copyRotation(realBody, super.body);
		copyRotation(lArm, super.leftArm);
		copyRotation(rArm, super.rightArm);
		copyRotation(lLeg, super.leftLeg);
		copyRotation(rLeg, super.rightLeg);
		boolean hasTarget = entity.getDataTracker().get(VampireEntity.HAS_TARGET);
		crossedArms.visible = !hasTarget;
		downArms.visible = hasTarget;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		realHead.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		realBody.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		robe.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		crossedArms.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		downArms.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		lLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		rLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	protected ModelPart getArm(Arm arm) {
		return realArm ? (arm == Arm.LEFT ? lArm : rArm) : super.getArm(arm);
	}

	private void copyRotation(ModelPart to, ModelPart from) {
		to.pitch = from.pitch;
		to.yaw = from.yaw;
		to.roll = from.roll;
	}
}
