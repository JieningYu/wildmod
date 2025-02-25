package frozenblock.wild.mod.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.jetbrains.annotations.Nullable;
import oshi.annotation.concurrent.Immutable;

public class TadpoleBrain {
    private static final float field_37500 = 2.0F;
    private static final float field_37501 = 0.15F;
    private static final float field_37502 = 0.5F;

    public TadpoleBrain() {
    }

    protected static Brain<TadpoleEntity> create(Brain<TadpoleEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<TadpoleEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(new WalkTask(2.0F), new LookAroundTask(45, 90), new WanderAroundTask(), new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)));
    }


    private static void addIdleActivities(Brain<TadpoleEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(Pair.of(0, new TimeLimitedTask(new FollowMobTask(EntityType.PLAYER, 6.0F), UniformIntProvider.create(30, 60))), Pair.of(3, new SeekWaterTask(6, 0.15F)), Pair.of(4, new CompositeTask(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableSet.of(), CompositeTask.Order.ORDERED, CompositeTask.RunMode.TRY_ALL, ImmutableList.of(Pair.of(new AquaticStrollTask(0.5F), 2), Pair.of(new StrollTask(0.15F), 2), Pair.of(new GoTowardsLookTarget(0.5F, 3), 3), Pair.of(new ConditionalTask<>(Entity::isInsideWaterOrBubbleColumn, new WaitTask(30, 60)), 5))))));
    }

    public static void method_41401(TadpoleEntity tadpole) {
        tadpole.getBrain().resetPossibleActivities(ImmutableList.of(Activity.IDLE));
    }
}