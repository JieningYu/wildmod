package frozenblock.wild.mod.liukrastapi;

import com.google.common.collect.ImmutableMap;
import frozenblock.wild.mod.entity.FrogEntity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;

public class CroakTask extends Task<FrogEntity> {
    private static final int field_37424 = 40;
    private static final int field_37425 = 100;
    private int runningTicks;

    public CroakTask() {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), 100);
    }

    protected boolean shouldKeepRunning(ServerWorld serverWorld, FrogEntity frogEntity, long l) {
        return this.runningTicks < 40;
    }

    protected void run(ServerWorld serverWorld, FrogEntity frogEntity, long l) {
        if (!frogEntity.isInsideWaterOrBubbleColumn() && !frogEntity.isInLava()) {
            //frogEntity.setPose(EntityPose.CROAKING);
            this.runningTicks = 0;
        }
    }

    protected void finishRunning(ServerWorld serverWorld, FrogEntity frogEntity, long l) {
        frogEntity.setPose(EntityPose.STANDING);
    }

    protected void keepRunning(ServerWorld serverWorld, FrogEntity frogEntity, long l) {
        ++this.runningTicks;
    }
}

