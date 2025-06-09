package dev.compasses.shorkie.registration;

import com.google.common.collect.Sets;
import dev.compasses.shorkie.Shorkie;
import dev.compasses.shorkie.content.PlushieBlock;
import dev.compasses.shorkie.content.PlushieBlockEntity;
import dev.compasses.shorkie.content.PlushieType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ShorkieBlocks {
   public static final PlushieBlock SHARKIE = registerPlushieBlock("best_sharkie", PlushieType.SHARKIE, "block.shorkie.best_sharkie.tooltip");
   public static final PlushieBlock GRAY_SHARKIE = registerPlushieBlock("gray_sharkie", PlushieType.SHARKIE, "block.shorkie.gray_sharkie.tooltip");
   public static final PlushieBlock WHALE = registerPlushieBlock("sharkie_but_whale", PlushieType.WHALE, "block.shorkie.sharkie_but_whale.tooltip");
   public static final PlushieBlock BREADSTICK = registerPlushieBlock("breadstick", PlushieType.BREADSTICK, "block.shorkie.breadstick.tooltip");
   public static final List<PlushieBlock> PRIDE_SHARKIES = Stream.of(
         "asexual",
         "agender",
         "aromantic",
         "aroace",
         "bi",
         "gay",
         "lesbian",
         "pan",
         "poly",
         "trans",
         "demiboy",
         "demigirl",
         "demiromantic",
         "demisexual",
         "non_binary",
         "genderfluid",
         "genderqueer",
         "grayrose",
         "grayromantic",
         "graysexual",
         "intersex",
         "queer"
      )
      .map(ShorkieBlocks::registerPridePlushie)
      .toList();
   public static BlockEntityType<PlushieBlockEntity> ENTITY_TYPE = new BlockEntityType(
      PlushieBlockEntity::new,
      Sets.union(Set.of(SHARKIE, GRAY_SHARKIE, WHALE, BREADSTICK), PRIDE_SHARKIES.stream().collect(Collectors.toUnmodifiableSet())),
      null
   );

   public static PlushieBlock registerPridePlushie(String name) {
      return registerPlushieBlock(name + "_pride_sharkie", PlushieType.SHARKIE, null);
   }

   public static PlushieBlock registerPlushieBlock(String name, PlushieType type, String descriptionKey) {
      ResourceKey<Block> registryKey = ResourceKey.create(Registries.BLOCK, Shorkie.id(name));
      return (PlushieBlock)Registry.register(
         BuiltInRegistries.BLOCK, registryKey, new PlushieBlock(Properties.ofFullCopy(Blocks.WHITE_WOOL), type, descriptionKey)
      );
   }

   public static void register() {
   }

   public static void registerBlockEntity() {
      Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Shorkie.id("plushie"), ENTITY_TYPE);
   }
}
