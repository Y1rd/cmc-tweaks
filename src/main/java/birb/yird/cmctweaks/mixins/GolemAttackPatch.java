package birb.yird.cmctweaks.mixins;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolem.class)
public class GolemAttackPatch {
    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void overrideRegisterGoals(CallbackInfo ci) {
        IronGolem self = (IronGolem) (Object) this;
        // Remove Goals, then add backs the one we need. A bit silly, but it works.
        self.targetSelector.getAvailableGoals().removeIf(goal -> self.targetSelector.getAvailableGoals().contains(goal));
        self.targetSelector.addGoal(1, new DefendVillageTargetGoal(self));
        self.targetSelector.addGoal(2, new HurtByTargetGoal(self));
        self.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(self, Mob.class, 5, false, false, (p_28879_) -> p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper)));
    }
}
