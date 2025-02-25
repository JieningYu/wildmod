package frozenblock.wild.mod;

import frozenblock.wild.mod.entity.FrogBrain;
import frozenblock.wild.mod.liukrastapi.AnimationChannel;
import frozenblock.wild.mod.liukrastapi.AnimationDefinition;
import frozenblock.wild.mod.liukrastapi.FrogAttackablesSensor;
import frozenblock.wild.mod.liukrastapi.IsInWaterSensor;
import frozenblock.wild.mod.mixins.ActivityInvoker;
import frozenblock.wild.mod.mixins.SensorTypeInvoker;
import frozenblock.wild.mod.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.sensor.NearestLivingEntitiesSensor;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.passive.AxolotlBrain;
import net.minecraft.entity.passive.GoatBrain;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Int2ObjectBiMap;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;

import java.util.OptionalInt;
import java.util.function.Supplier;

public class WildMod implements ModInitializer {

    public static final String MOD_ID = "twm";

    @Override
    public void onInitialize() {
        RegisterBlocks.RegisterBlocks();
        RegisterItems.RegisterItems();
        RegisterEntities.RegisterEntities();
        RegisterDispenser.RegisterDispenser();
        RegisterParticles.RegisterParticles();
        RegisterStatusEffects.RegisterStatusEffects();
        RegisterWorldgen.RegisterWorldgen();

        registerData(OPTIONAL_INT);

        TONGUE = ActivityInvoker.callRegister( "tongue");
        SWIM = ActivityInvoker.callRegister( "swim");
        LAY_SPAWN = ActivityInvoker.callRegister( "lay_spawn");

        RegisterAccurateSculk.RegisterAccurateSculk();
    }

    public static void registerData(TrackedDataHandler<?> handler) {
        DATA_HANDLERS.add(handler);
    }

    private static final Int2ObjectBiMap<TrackedDataHandler<?>> DATA_HANDLERS = Int2ObjectBiMap.create(16);

    public static <T> T registerInRegistryVanilla(Registry<T> registry, String name, T idk) {
        return Registry.register(registry, name, idk);
    }

    public static Registry<AnimationDefinition> ANIMATION_DEFINITIONS = FabricRegistryBuilder.createSimple(AnimationDefinition.class, new Identifier( "animation_definitions")).buildAndRegister();
    public static Registry<AnimationChannel.Interpolation> ANIMATION_CHANNEL_INTERPOLATIONS = FabricRegistryBuilder.createSimple(AnimationChannel.Interpolation.class, new Identifier("animation_channel_interpolations")).buildAndRegister();
    public static Registry<AnimationChannel.Target> ANIMATION_CHANNEL_TARGETS = FabricRegistryBuilder.createSimple(AnimationChannel.Target.class, new Identifier("animation_channel_targets")).buildAndRegister();

    public static final SensorType<TemptationsSensor> FROG_TEMPTATIONS = SensorTypeInvoker.callRegister("frog_temptations", () -> new TemptationsSensor(FrogBrain.getTemptItems()));
    public static final SensorType<FrogAttackablesSensor> FROG_ATTACKABLES = SensorTypeInvoker.callRegister("frog_attackables", FrogAttackablesSensor::new);
    public static final SensorType<IsInWaterSensor> IS_IN_WATER = SensorTypeInvoker.callRegister("is_in_water", IsInWaterSensor::new);

    public static Activity TONGUE;
    public static Activity SWIM;
    public static Activity LAY_SPAWN;

    public static final TrackedDataHandler<OptionalInt> OPTIONAL_INT = new TrackedDataHandler<OptionalInt>() {
        public void write(PacketByteBuf packetByteBuf, OptionalInt optionalInt) {
            packetByteBuf.writeVarInt(optionalInt.orElse(-1) + 1);
        }

        public OptionalInt read(PacketByteBuf packetByteBuf) {
            int i = packetByteBuf.readVarInt();
            return i == 0 ? OptionalInt.empty() : OptionalInt.of(i - 1);
        }

        public OptionalInt copy(OptionalInt optionalInt) {
            return optionalInt;
        }
    };

    public static final GameRules.Key<GameRules.BooleanRule> DARKNESS_ENABLED =
            GameRuleRegistry.register("doDarkness", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanRule> SHRIEKER_NEEDS_SCULK =
            GameRuleRegistry.register("shriekerNeedsSculk", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> SHRIEKER_SHRIEKS =
            GameRuleRegistry.register("shriekerShrieks", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanRule> DO_CATALYST_POLLUTION =
            GameRuleRegistry.register("catalystPollution", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanRule> DO_CATALYST_VIBRATIONS =
            GameRuleRegistry.register("catalystVibrations", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> SHRIEKER_GARGLES =
            GameRuleRegistry.register("shriekerGargles", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.IntRule> SCULK_MULTIPLIER =
            GameRuleRegistry.register("spreadMultiplier", GameRules.Category.MISC, GameRuleFactory.createIntRule(1, 1, 20));
    public static final GameRules.Key<GameRules.IntRule> UPWARD_SPREAD =
            GameRuleRegistry.register("upwardSpread", GameRules.Category.MISC, GameRuleFactory.createIntRule(12, 1, 100));
    public static final GameRules.Key<GameRules.IntRule> DOWNWARD_SPREAD =
            GameRuleRegistry.register("downwardSpread", GameRules.Category.MISC, GameRuleFactory.createIntRule(12, 1, 100));
    public static final GameRules.Key<GameRules.BooleanRule> SCULK_STOPS_SCULKCHECK = //PERFORMANCE GAMERULE, STOPS SCULKCHECK SEARCHING IF THE CURRENT BLOCK IS SCULK
            GameRuleRegistry.register("sculkStopsSculkCheck", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

    //TESTING GAMERULES. TODO: DELETE THESE AT 1.0. DO NOT WRITE TOO MUCH CODE THAT DEPENDS ON THESE.
    public static final GameRules.Key<GameRules.BooleanRule> NO_WARDEN_COOLDOWN =
            GameRuleRegistry.register("noWardenCooldown", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> WARDEN_BURNS =
            GameRuleRegistry.register("wardenBurns", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> WARDEN_SPAWNING =
            GameRuleRegistry.register("wardenSpawns", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
}
