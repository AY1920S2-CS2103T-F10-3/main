package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.schedule.Week;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbsoluteTask extends Task {
    WeekDayDuration wdd;

    public AbsoluteTask(String name, WeekDayDuration wdd) {
        super(name, wdd.getDuration());
        this.wdd = wdd;
    }

    public WeekDayDuration getWeekDayDuration() {
        return this.wdd;
    }


    public List<DateTimeDuration> getPossibleSlot(DateTimeSlotList dsl) {
        return dsl.intersectWith(this.wdd).stream().filter(x -> x.getDuration().compareTo(this.duration) >= 0).collect(
                Collectors.toList());
    }

    @Override
    public Optional<Event> generateEvent(Week week) {
        Event event = new Event(this.name, this.wdd.toDateTimeDuration(week.getDuration().getStartDate()));
        if(week.addEvent(event)) {
            return Optional.empty();
        } else {
            return Optional.of(event);
        }
    }

}
