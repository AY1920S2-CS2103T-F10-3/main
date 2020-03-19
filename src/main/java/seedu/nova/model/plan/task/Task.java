package seedu.nova.model.plan.task;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;

import java.time.Duration;
import java.util.List;

public abstract class Task {
    String name;
    Duration duration;

    protected Task(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public abstract List<DateTimeDuration> getPossibleSlot(DateTimeSlotList dsl);

    public abstract Event generateEvent(DateTimeDuration dtd);
}
