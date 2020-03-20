package seedu.nova.model.plan;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import seedu.nova.model.util.time.TimeUtil;
import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.schedule.timeunit.Week;


/**
 * Weekly event
 */
public abstract class Task {
    protected EventDetails details;
    protected Duration duration;
    protected TreeSet<LocalDate> weekDone;

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
