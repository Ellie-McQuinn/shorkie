package dev.compasses.shorkie.content;

import dev.compasses.shorkie.registration.ShorkieSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PlushieItem extends BlockItem {
   public PlushieItem(PlushieBlock block, Properties properties) {
      super(block, properties);
   }

   @NotNull
   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      level.playSound(player, player, ShorkieSounds.getPlushieCuddledSound(level.getRandom()), SoundSource.RECORDS, 0.5F, 1.0F);
      ItemStack heldStack = player.getItemInHand(hand);
      player.getCooldowns().addCooldown(heldStack.getItem(), 100);
      player.awardStat(Stats.ITEM_USED.get(this));
      return InteractionResultHolder.consume(heldStack);
   }

   @NotNull
   public EquipmentSlot getEquipmentSlot(ItemStack stack) {
      return EquipmentSlot.HEAD;
   }
}
