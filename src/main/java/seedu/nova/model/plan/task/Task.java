package seedu.nova.model.plan.task;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

public abstract class Task {
    String name;
    Duration duration;

    protected Task(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public abstract TreeMap<LocalDate, List<DateTimeDuration>> getPossibleSlot(DateTimeSlotList dsl);

    public abstract AdoptedEvent generateEvent(DateTimeDuration dtd);
}
