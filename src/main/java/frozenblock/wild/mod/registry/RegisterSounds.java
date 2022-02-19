package frozenblock.wild.mod.registry;

import frozenblock.wild.mod.WildMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterSounds { //Register sound definitions

    /** BLOCKS **/
    //Deepslate Frame
        public static SoundEvent BLOCK_REINFORCED_DEEPSLATE_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.reinforced_deepslate.step"));
        public static SoundEvent BLOCK_REINFORCED_DEEPSLATE_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.reinforced_deepslate.break"));
        public static SoundEvent BLOCK_REINFORCED_DEEPSLATE_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.reinforced_deepslate.place"));
        public static SoundEvent BLOCK_REINFORCED_DEEPSLATE_HIT = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.reinforced_deepslate.hit"));
    //Mud
        public static SoundEvent BLOCK_MUD_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud.step"));
        public static SoundEvent BLOCK_MUD_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud.break"));
        public static SoundEvent BLOCK_MUD_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud.place"));
        public static SoundEvent BLOCK_MUD_HIT = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud.hit"));
    //Mud Bricks
        public static SoundEvent BLOCK_MUD_BRICKS_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud_bricks.step"));
        public static SoundEvent BLOCK_MUD_BRICKS_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud_bricks.break"));
        public static SoundEvent BLOCK_MUD_BRICKS_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud_bricks.place"));
        public static SoundEvent BLOCK_MUD_BRICKS_HIT = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud_bricks.hit"));
        public static SoundEvent BLOCK_MUD_BRICKS_FALL = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mud_bricks.fall"));
    //Mangrove Propagule
        public static SoundEvent BLOCK_MANGROVE_PROPAGULE_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.propagule.break"));
        public static SoundEvent BLOCK_MANGROVE_PROPAGULE_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.propagule.place"));
        public static SoundEvent BLOCK_MANGROVE_PROPAGULE_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.propagule.step"));
        public static SoundEvent BLOCK_MANGROVE_PROPAGULE_HIT = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.propagule.hit"));
        public static SoundEvent BLOCK_MANGROVE_PROPAGULE_FALL = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.propagule.fall"));
    //Mangrove Roots
        public static SoundEvent BLOCK_MANGROVE_ROOTS_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mangrove_roots.break"));
        public static SoundEvent BLOCK_MANGROVE_ROOTS_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mangrove_roots.place"));
        public static SoundEvent BLOCK_MANGROVE_ROOTS_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mangrove_roots.step"));
        public static SoundEvent BLOCK_MANGROVE_ROOTS_HIT = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mangrove_roots.hit"));
        public static SoundEvent BLOCK_MANGROVE_ROOTS_FALL = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.mangrove_roots.fall"));
    //Sculk
        public static SoundEvent BLOCK_SCULK_STEP = new SoundEvent(new Identifier("accuratesculk", "block.sculk.step"));
        public static SoundEvent BLOCK_SCULK_HIT = new SoundEvent(new Identifier("accuratesculk", "block.sculk.hit"));
        public static SoundEvent BLOCK_SCULK_FALL = new SoundEvent(new Identifier("accuratesculk", "block.sculk.fall"));
        public static SoundEvent BLOCK_SCULK_BREAK = new SoundEvent(new Identifier("accuratesculk", "block.sculk.break"));
        public static SoundEvent BLOCK_SCULK_PLACE = new SoundEvent(new Identifier("accuratesculk", "block.sculk.place"));
    //Sculk Catalyst
        public static SoundEvent BLOCK_SCULK_CATALYST_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_catalyst.break"));
        public static SoundEvent BLOCK_SCULK_CATALYST_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_catalyst.place"));
        public static SoundEvent BLOCK_SCULK_CATALYST_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_catalyst.step"));
        public static SoundEvent BLOCK_SCULK_CATALYST_BLOOM = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_catalyst.bloom"));
    //Sculk Sensor
        public static SoundEvent BLOCK_SCULK_SENSOR_RECEIVE_RF = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_sensor.receive_rf"));
    //Sculk Shrieker
        public static SoundEvent BLOCK_SCULK_SHRIEKER_BREAK = new SoundEvent(new Identifier("accuratesculk", "block.sculk_shrieker.break"));
        public static SoundEvent BLOCK_SCULK_SHRIEKER_PLACE = new SoundEvent(new Identifier("accuratesculk", "block.sculk_shrieker.place"));
        public static SoundEvent BLOCK_SCULK_SHRIEKER_SHRIEK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_shrieker.shriek"));
    //Sculk Vein
        public static SoundEvent BLOCK_SCULK_VEIN_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_vein.place"));
        public static SoundEvent BLOCK_SCULK_VEIN_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk_vein.break"));

    /** ENTITIES **/
    //Chest Boat
        public static SoundEvent ENTITY_CHEST_BOAT_PADDLE_WATER = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.chest_boat.paddle_water"));
        public static SoundEvent ENTITY_CHEST_BOAT_PADDLE_LAND = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.chest_boat.paddle_land"));
    
    //Firefly
        public static SoundEvent ENTITY_FIREFLY_LOOP = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.firefly.loop"));
        public static SoundEvent ENTITY_FIREFLY_HURT = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.firefly.hurt"));
    //Frog
        public static SoundEvent ENTITY_FROG_AMBIENT = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.frog.ambient"));
        public static SoundEvent ENTITY_FROG_DEATH = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.frog.death"));
        public static SoundEvent ENTITY_FROG_EAT = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.frog.eat"));
        public static SoundEvent ENTITY_FROG_HURT = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.frog.hurt"));
        public static SoundEvent ENTITY_FROG_LONG_JUMP = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.frog.long_jump"));
    public static SoundEvent ENTITY_FROG_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.frog.step"));
        public static SoundEvent ENTITY_FROG_TONGUE = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.frog.tongue"));
    //Warden
        public static SoundEvent ENTITY_WARDEN_AMBIENT = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.ambient"));
        public static SoundEvent ENTITY_WARDEN_AMBIENT_UNDERGROUND = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.ambient_underground"));
        public static SoundEvent ENTITY_WARDEN_ANGRY = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.warden.angry"));
        public static SoundEvent ENTITY_WARDEN_ATTACK = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.attack"));
        public static SoundEvent ENTITY_WARDEN_CLOSE = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.close"));
        public static SoundEvent ENTITY_WARDEN_CLOSER = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.closer"));
        public static SoundEvent ENTITY_WARDEN_CLOSEST = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.closest"));
        public static SoundEvent ENTITY_WARDEN_DEATH = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.death"));
        public static SoundEvent ENTITY_WARDEN_DIG = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.dig"));
        public static SoundEvent ENTITY_WARDEN_EMERGE = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.emerge"));
        public static SoundEvent ENTITY_WARDEN_HEARTBEAT = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.heartbeat"));
        public static SoundEvent ENTITY_WARDEN_HURT = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.hurt"));
        public static SoundEvent ENTITY_WARDEN_LISTENING = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.warden.listening"));
        public static SoundEvent ENTITY_WARDEN_LISTENING_ANGRY = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.warden.listening_angry"));
        public static SoundEvent ENTITY_WARDEN_ROAR = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.roar"));
        public static SoundEvent ENTITY_WARDEN_SLIGHTLY_ANGRY = new SoundEvent(new Identifier(WildMod.MOD_ID, "entity.warden.slightly_angry"));
        public static SoundEvent ENTITY_WARDEN_SNIFF = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.sniff"));
        public static SoundEvent ENTITY_WARDEN_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.step"));
        public static SoundEvent ENTITY_WARDEN_VIBRATION = new SoundEvent(new Identifier(WildMod.MOD_ID,"entity.warden.vibration"));

    /** MUSIC **/
        public static SoundEvent MUSIC_OVERWORLD_DEEP_DARK = new SoundEvent(new Identifier(WildMod.MOD_ID,"music.overworld.deep_dark"));



    public static void RegisterSounds() { //Register sounds as variables

    /** BLOCKS **/

        //Deepslate Frame
        BLOCK_REINFORCED_DEEPSLATE_STEP = register(BLOCK_REINFORCED_DEEPSLATE_STEP.getId());
        BLOCK_REINFORCED_DEEPSLATE_HIT = register(BLOCK_REINFORCED_DEEPSLATE_HIT.getId());
        BLOCK_REINFORCED_DEEPSLATE_BREAK = register(BLOCK_REINFORCED_DEEPSLATE_BREAK.getId());
        BLOCK_REINFORCED_DEEPSLATE_PLACE = register(BLOCK_REINFORCED_DEEPSLATE_PLACE.getId());

        //Mud
        BLOCK_MUD_STEP = register(BLOCK_MUD_STEP.getId());
        BLOCK_MUD_HIT = register(BLOCK_MUD_HIT.getId());
        BLOCK_MUD_BREAK = register(BLOCK_MUD_BREAK.getId());
        BLOCK_MUD_PLACE = register(BLOCK_MUD_PLACE.getId());

        //Mud Bricks
        BLOCK_MUD_BRICKS_STEP = register(BLOCK_MUD_BRICKS_STEP.getId());
        BLOCK_MUD_BRICKS_HIT = register(BLOCK_MUD_BRICKS_HIT.getId());
        BLOCK_MUD_BRICKS_BREAK = register(BLOCK_MUD_BRICKS_BREAK.getId());
        BLOCK_MUD_BRICKS_PLACE = register(BLOCK_MUD_BRICKS_PLACE.getId());
        BLOCK_MUD_BRICKS_FALL = register(BLOCK_MUD_BRICKS_FALL.getId());

        //Sculk Catalyst
        BLOCK_SCULK_CATALYST_BREAK = register(BLOCK_SCULK_CATALYST_BREAK.getId());
        BLOCK_SCULK_CATALYST_PLACE = register(BLOCK_SCULK_CATALYST_PLACE.getId());
        BLOCK_SCULK_CATALYST_STEP = register(BLOCK_SCULK_CATALYST_STEP.getId());
        BLOCK_SCULK_CATALYST_BLOOM = register(BLOCK_SCULK_CATALYST_BLOOM.getId());

         //Sculk
        BLOCK_SCULK_STEP = register(BLOCK_SCULK_STEP.getId());
        BLOCK_SCULK_HIT = register(BLOCK_SCULK_HIT.getId());
        BLOCK_SCULK_FALL = register(BLOCK_SCULK_FALL.getId());
        BLOCK_SCULK_BREAK = register(BLOCK_SCULK_BREAK.getId());
        BLOCK_SCULK_PLACE = register(BLOCK_SCULK_PLACE.getId());

        //Sculk Sensor
        BLOCK_SCULK_SENSOR_RECEIVE_RF = register(BLOCK_SCULK_SENSOR_RECEIVE_RF.getId());

        //Sculk Shrieker
        BLOCK_SCULK_SHRIEKER_BREAK = register(BLOCK_SCULK_SHRIEKER_BREAK.getId());
        BLOCK_SCULK_SHRIEKER_PLACE = register(BLOCK_SCULK_SHRIEKER_PLACE.getId());
        BLOCK_SCULK_SHRIEKER_SHRIEK = register(BLOCK_SCULK_SHRIEKER_SHRIEK.getId());

        //Sculk Vein
        BLOCK_SCULK_VEIN_BREAK = register(BLOCK_SCULK_VEIN_BREAK.getId());
        BLOCK_SCULK_VEIN_PLACE = register(BLOCK_SCULK_VEIN_PLACE.getId());

        //Mangrove Propagule
        BLOCK_MANGROVE_PROPAGULE_BREAK = register(BLOCK_MANGROVE_PROPAGULE_BREAK.getId());
        BLOCK_MANGROVE_PROPAGULE_PLACE = register(BLOCK_MANGROVE_PROPAGULE_PLACE.getId());
        BLOCK_MANGROVE_PROPAGULE_STEP = register(BLOCK_MANGROVE_PROPAGULE_STEP.getId());
        BLOCK_MANGROVE_PROPAGULE_HIT = register(BLOCK_MANGROVE_PROPAGULE_HIT.getId());
        BLOCK_MANGROVE_PROPAGULE_FALL = register(BLOCK_MANGROVE_PROPAGULE_FALL.getId());

        //Mangrove Roots
        BLOCK_MANGROVE_ROOTS_BREAK = register(BLOCK_MANGROVE_ROOTS_BREAK.getId());
        BLOCK_MANGROVE_ROOTS_PLACE = register(BLOCK_MANGROVE_ROOTS_PLACE.getId());
        BLOCK_MANGROVE_ROOTS_STEP = register(BLOCK_MANGROVE_ROOTS_STEP.getId());
        BLOCK_MANGROVE_ROOTS_HIT = register(BLOCK_MANGROVE_ROOTS_HIT.getId());
        BLOCK_MANGROVE_ROOTS_FALL = register(BLOCK_MANGROVE_ROOTS_FALL.getId());

    /** ENTITIES **/
        //Chest Boat
        ENTITY_CHEST_BOAT_PADDLE_WATER = register(ENTITY_CHEST_BOAT_PADDLE_WATER.getId());
        ENTITY_CHEST_BOAT_PADDLE_LAND = register(ENTITY_CHEST_BOAT_PADDLE_LAND.getId());
        
        //Firefly
        ENTITY_FIREFLY_LOOP = register(ENTITY_FIREFLY_LOOP.getId());
        ENTITY_FIREFLY_HURT = register(ENTITY_FIREFLY_HURT.getId());

        //Frog
        ENTITY_FROG_AMBIENT = register(ENTITY_FROG_AMBIENT.getId());
        ENTITY_FROG_DEATH = register(ENTITY_FROG_DEATH.getId());
        ENTITY_FROG_EAT = register(ENTITY_FROG_EAT.getId());
        ENTITY_FROG_HURT = register(ENTITY_FROG_HURT.getId());
        ENTITY_FROG_LONG_JUMP = register(ENTITY_FROG_LONG_JUMP.getId());
        ENTITY_FROG_STEP = register(ENTITY_FROG_STEP.getId());
        ENTITY_FROG_TONGUE = register(ENTITY_FROG_TONGUE.getId());

        //Warden
        ENTITY_WARDEN_AMBIENT = register(ENTITY_WARDEN_AMBIENT.getId());
        ENTITY_WARDEN_AMBIENT_UNDERGROUND = register(ENTITY_WARDEN_AMBIENT_UNDERGROUND.getId());
        ENTITY_WARDEN_ANGRY = register(ENTITY_WARDEN_ANGRY.getId());
        ENTITY_WARDEN_ATTACK = register(ENTITY_WARDEN_ATTACK.getId());
        ENTITY_WARDEN_CLOSE = register(ENTITY_WARDEN_CLOSE.getId());
        ENTITY_WARDEN_CLOSER = register(ENTITY_WARDEN_CLOSER.getId());
        ENTITY_WARDEN_CLOSEST = register(ENTITY_WARDEN_CLOSEST.getId());
        ENTITY_WARDEN_DEATH = register(ENTITY_WARDEN_DEATH.getId());
        ENTITY_WARDEN_DIG = register(ENTITY_WARDEN_DIG.getId());
        ENTITY_WARDEN_EMERGE = register(ENTITY_WARDEN_EMERGE.getId());
        ENTITY_WARDEN_HEARTBEAT = register(ENTITY_WARDEN_HEARTBEAT.getId());
        ENTITY_WARDEN_HURT = register(ENTITY_WARDEN_HURT.getId());
        ENTITY_WARDEN_LISTENING = register(ENTITY_WARDEN_LISTENING.getId());
        ENTITY_WARDEN_LISTENING_ANGRY = register(ENTITY_WARDEN_LISTENING_ANGRY.getId());
        ENTITY_WARDEN_ROAR = register(ENTITY_WARDEN_ROAR.getId());
        ENTITY_WARDEN_SLIGHTLY_ANGRY = register(ENTITY_WARDEN_SLIGHTLY_ANGRY.getId());
        ENTITY_WARDEN_SNIFF = register(ENTITY_WARDEN_SNIFF.getId());
        ENTITY_WARDEN_STEP = register(ENTITY_WARDEN_STEP.getId());
        ENTITY_WARDEN_VIBRATION = register(ENTITY_WARDEN_VIBRATION.getId());

    /** MUSIC **/
        MUSIC_OVERWORLD_DEEP_DARK = register(MUSIC_OVERWORLD_DEEP_DARK.getId());

    }

    private static SoundEvent register(Identifier id) {
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}

