package dev.compasses.shorkie.content;

import dev.compasses.shorkie.registration.ShorkieBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlushieBlockEntity extends BlockEntity implements Nameable {
   public PlushieBlockEntity(BlockPos pos, BlockState state) {
      super(ShorkieBlocks.ENTITY_TYPE, pos, state);
   }

   @NotNull
   public Component getName() {
      Component customName = this.getCustomName();
      return (Component)(customName == null ? this.getBlockState().getBlock().getName() : customName);
   }

   @Nullable
   public Component getCustomName() {
      return (Component)this.components().get(DataComponents.CUSTOM_NAME);
   }
}
