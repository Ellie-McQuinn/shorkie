package dev.compasses.shorkie;

import dev.compasses.shorkie.registration.ShorkieBlocks;
import dev.compasses.shorkie.registration.ShorkieItems;
import dev.compasses.shorkie.registration.ShorkieSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod("shorkie")
public class Shorkie {
   public static final String MODID = "shorkie";

   public Shorkie(IEventBus bus, ModContainer container) {
      bus.addListener(event -> {
         if (event.getRegistryKey() == Registries.BLOCK) {
            ShorkieBlocks.register();
         } else if (event.getRegistryKey() == Registries.ITEM) {
            ShorkieItems.register();
         } else if (event.getRegistryKey() == Registries.CREATIVE_MODE_TAB) {
            ShorkieItems.registerTab(CreativeModeTab.builder());
         } else if (event.getRegistryKey() == Registries.SOUND_EVENT) {
            ShorkieSounds.register();
         } else if (event.getRegistryKey() == Registries.BLOCK_ENTITY_TYPE) {
            ShorkieBlocks.registerBlockEntity();
         }
      });
   }

   public static ResourceLocation id(String name) {
      return ResourceLocation.fromNamespaceAndPath("shorkie", name);
   }
}
