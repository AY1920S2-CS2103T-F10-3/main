package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.schedule.Week;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;


// like a weekly event
public abstract class Task {
    String name;
    Duration duration;

    protected Task(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public abstract List<DateTimeDuration> getPossibleSlot(DateTimeSlotList dsl);

    public abstract Optional<Event> generateEvent(Week week);
}
