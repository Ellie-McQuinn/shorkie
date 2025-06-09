package dev.compasses.shorkie.content;

import dev.compasses.shorkie.registration.ShorkieBlocks;
import dev.compasses.shorkie.registration.ShorkieSounds;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlushieBlock extends Block implements EntityBlock {
   private static final VoxelShape SHARKIE_NORTH = box(5.0, 0.0, 0.0, 11.0, 5.0, 17.0);
   private static final VoxelShape SHARKIE_SOUTH = SHARKIE_NORTH.move(0.0, 0.0, -0.0625);
   private static final VoxelShape SHARKIE_EAST = box(-1.0, 0.0, 5.0, 16.0, 5.0, 11.0);
   private static final VoxelShape SHARKIE_WEST = SHARKIE_EAST.move(0.0625, 0.0, 0.0);
   private static final VoxelShape BREADSTICK_SOUTH = box(5.0, 0.0, 0.0, 11.0, 6.0, 17.0);
   private static final VoxelShape BREADSTICK_NORTH = BREADSTICK_SOUTH.move(0.0, 0.0, -0.0625);
   private static final VoxelShape BREADSTICK_WEST = box(-1.0, 0.0, 5.0, 16.0, 6.0, 11.0);
   private static final VoxelShape BREADSTICK_EAST = BREADSTICK_WEST.move(0.0625, 0.0, 0.0);
   private static final VoxelShape WHALE_Z = box(4.0, 0.0, 0.0, 12.0, 5.0, 16.0);
   private static final VoxelShape WHALE_X = box(0.0, 0.0, 4.0, 16.0, 5.0, 12.0);
   private final PlushieType type;
   private Component description;

   public PlushieBlock(Properties properties, PlushieType type, String descriptionKey) {
      super(properties);
      this.type = type;
      if (descriptionKey != null) {
         this.description = Component.translatable(descriptionKey).withStyle(ChatFormatting.GRAY);
      }

      this.registerDefaultState((BlockState)this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
   }

   public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
      if (this.description != null) {
         tooltip.add(this.description);
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{BlockStateProperties.HORIZONTAL_FACING});
   }

   protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
      Direction facing = (Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING);
      if (this.type == PlushieType.WHALE) {
         return facing.getAxis() == Axis.Z ? WHALE_Z : WHALE_X;
      } else if (this.type == PlushieType.SHARKIE) {
         return switch (facing) {
            case NORTH -> SHARKIE_NORTH;
            case SOUTH -> SHARKIE_SOUTH;
            case EAST -> SHARKIE_EAST;
            case WEST -> SHARKIE_WEST;
            default -> throw new IllegalStateException("Plushie cannot be placed vertically.");
         };
      } else if (this.type == PlushieType.BREADSTICK) {
         return switch (facing) {
            case NORTH -> BREADSTICK_NORTH;
            case SOUTH -> BREADSTICK_SOUTH;
            case EAST -> BREADSTICK_EAST;
            case WEST -> BREADSTICK_WEST;
            default -> throw new IllegalStateException("Plushie cannot be placed vertically.");
         };
      } else {
         throw new IllegalStateException("Unknown plushie type: " + this.type);
      }
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
   }

   @NotNull
   protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
      if (!level.isClientSide()) {
         level.playSound(null, pos, ShorkieSounds.getPlushieCuddledSound(level.getRandom()), SoundSource.BLOCKS, 0.5F, 1.0F);
      }

      return InteractionResult.SUCCESS;
   }

   protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
      if (!level.isClientSide()) {
         level.playSound(null, hit.getBlockPos(), ShorkieSounds.PLUSHIE_HIT, SoundSource.BLOCKS, 0.5F, 1.0F);
      }
   }

   @Nullable
   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
      return ShorkieBlocks.ENTITY_TYPE.create(pos, state);
   }
}
