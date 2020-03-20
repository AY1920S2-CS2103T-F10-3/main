package seedu.nova.model.plan;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;
import seedu.nova.model.util.time.slotlist.WeekDaySlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.schedule.timeunit.Week;

/**
 * Task with loose restriction on timeslot allowed, event can be rescheduled
 */
public class WeakTask extends Task {
    private WeekDaySlotList wdsl;

    public WeakTask(EventDetails details, Duration duration, WeekDaySlotList wdsl) {
        super(details, duration);
        this.wdsl = wdsl;
    }

    public WeekDaySlotList getWeekDaySlotList() {
        return this.wdsl;
    }


    public List<DateTimeDuration> getPossibleSlot(DateTimeSlotList dsl) {
        List<DateTimeDuration> lst = new LinkedList<>();
        this.wdsl.getSlotList().stream().map(dsl::intersectWith).forEach(lst::addAll);
        return lst;
    }

    @Override
    public Optional<Event> generateEvent(Week week) {
        List<DateTimeDuration> possible = getPossibleSlot(week.getFreeSlotList());
        if (possible.isEmpty()) {
            return Optional.of(new Event(this.details, (DateTimeDuration) DateTimeDuration.ZERO));
        } else {
            Event event = new Event(this.details, getExactDuration(possible.get(0), this.duration));
            if (week.addEvent(event)) {
                return Optional.empty();
            } else {
                return Optional.of(event);
            }
        }
    }

    private DateTimeDuration getExactDuration(DateTimeDuration dtd, Duration d) {
        return DateTimeDuration.parseFromDateTime(dtd.getStartDateTime(), d);
    }
}
