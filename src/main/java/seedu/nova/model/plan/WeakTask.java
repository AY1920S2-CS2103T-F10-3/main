package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.common.time.slotlist.WeekDaySlotList;
import seedu.nova.model.schedule.Week;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

//read blabla twice 2 hr..
public class WeakTask extends Task {
    WeekDaySlotList wdsl;

    public WeakTask(String name, Duration duration, WeekDaySlotList wdsl) {
        super(name, duration);
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
        if(possible.isEmpty()) {
            return Optional.of(new Event(name, (DateTimeDuration) DateTimeDuration.ZERO));
        } else {
            Event event = new Event(this.name, getExactDuration(possible.get(0), this.duration));
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
