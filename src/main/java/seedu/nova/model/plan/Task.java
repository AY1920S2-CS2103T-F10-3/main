package seedu.nova.model.plan;

import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.model.common.time.SlotList;

import java.time.Duration;
import java.util.List;

public class Task {
    String taskName;
    Duration duration;
    SlotList slotList;

    Task(String taskName, Duration duration, SlotList lst) {
        this.taskName = taskName;
        this.duration = duration;
        this.slotList = lst;
    }
}
