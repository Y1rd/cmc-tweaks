package birb.yird.cmctweaks.mixins;

import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(TransfurVariant.Builder.class)
public class VillagerFearPatch {
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/ArrayList;<init>(Ljava/util/Collection;)V"
            ),
            index = 0
    )
    // Return an empty array so villagers are no longer scared.
    private <E> Collection<? extends E> fearlessVillager(Collection<? extends E> c) {
        return new ArrayList<>();
    }
}
