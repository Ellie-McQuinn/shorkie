package dev.compasses.shorkie.registration;

import dev.compasses.shorkie.Shorkie;
import dev.compasses.shorkie.content.PlushieBlock;
import dev.compasses.shorkie.content.PlushieItem;
import java.util.List;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab.Builder;
import net.minecraft.world.item.Item.Properties;

public class ShorkieItems {
   public static final PlushieItem SHARKIE = registerPlushieItem(ShorkieBlocks.SHARKIE);
   public static final PlushieItem GRAY_SHARKIE = registerPlushieItem(ShorkieBlocks.GRAY_SHARKIE);
   public static final PlushieItem WHALE = registerPlushieItem(ShorkieBlocks.WHALE);
   public static final PlushieItem BREADSTICK = registerPlushieItem(ShorkieBlocks.BREADSTICK);
   public static final List<PlushieItem> PRIDE_SHARKIES = ShorkieBlocks.PRIDE_SHARKIES.stream().map(ShorkieItems::registerPlushieItem).toList();

   private static PlushieItem registerPlushieItem(PlushieBlock block) {
      ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, BuiltInRegistries.BLOCK.getKey(block));
      return (PlushieItem)Registry.register(BuiltInRegistries.ITEM, registryKey, new PlushieItem(block, new Properties().stacksTo(1)));
   }

   public static void registerTab(Builder builder) {
      builder.title(Component.translatable("itemGroup.shorkie.tab"))
         .withTabsBefore(new ResourceKey[]{CreativeModeTabs.COMBAT})
         .icon(SHARKIE::getDefaultInstance)
         .displayItems((parameters, output) -> {
            output.accept(SHARKIE);
            output.accept(GRAY_SHARKIE);
            output.accept(WHALE);
            output.accept(BREADSTICK);

            for (PlushieItem sharkie : PRIDE_SHARKIES) {
               output.accept(sharkie);
            }
         });
      Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Shorkie.id("tab"), builder.build());
   }

   public static void register() {
   }
}
