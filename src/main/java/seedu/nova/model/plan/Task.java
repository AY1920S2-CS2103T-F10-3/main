package seedu.nova.model.plan;

import seedu.nova.model.common.time.TimeUtil;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.schedule.timeunit.Week;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;


// like a weekly event
public abstract class Task {
    EventDetails details;
    Duration duration;
    TreeSet<LocalDate> weekDone;

    protected Task(EventDetails details, Duration duration) {
        this.details = details;
        this.duration = duration;
    }

    public boolean isCompletedOnWeek(LocalDate sameWeekAs) {
        return this.weekDone.contains(TimeUtil.getMondayOfWeek(sameWeekAs));
    }

    public boolean markAsCompleteOnWeek(LocalDate sameWeekAs) {
        return this.weekDone.add(TimeUtil.getMondayOfWeek(sameWeekAs));
    }

    public abstract List<DateTimeDuration> getPossibleSlot(DateTimeSlotList dsl);

    public abstract Optional<Event> generateEvent(Week week);
}
