package dev.compasses.shorkie.registration;

import dev.compasses.shorkie.Shorkie;
import java.util.List;
import java.util.stream.IntStream;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;

public class ShorkieSounds {
   public static final SoundEvent PLUSHIE_HIT = register("block.shorkie.plushie.hit");
   private static final List<SoundEvent> PLUSHIE_CUDDLED = IntStream.range(1, 6).mapToObj(i -> register("block.shorkie.plushie.use." + i)).toList();

   private static SoundEvent register(String name) {
      ResourceLocation id = Shorkie.id(name);
      return (SoundEvent)Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
   }

   public static SoundEvent getPlushieCuddledSound(RandomSource random) {
      return PLUSHIE_CUDDLED.get(random.nextInt(0, PLUSHIE_CUDDLED.size()));
   }

   public static void register() {
   }
}
