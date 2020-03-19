package seedu.nova.model.plan.task;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.plan.ImpossibleTaskException;

import java.time.Duration;
import java.util.List;

public class AbsoluteTask extends Task {
    WeekDayDuration wdd;

    private AbsoluteTask(String name, Duration duration, WeekDayDuration wdd) {
        super(name, duration);
        this.wdd = wdd;
    }

    public static AbsoluteTask create(String name, Duration duration, WeekDayDuration wdd) throws
            ImpossibleTaskException
    {
        if(wdd.getDuration().compareTo(duration) != 0) {
            throw new ImpossibleTaskException();
        } else {
            return new AbsoluteTask(name, duration, wdd);
        }
    }


    public List<DateTimeDuration> getPossibleSlot(DateTimeSlotList dsl) {
        return dsl.intersectWith(this.wdd);
    }

    public Event generateEvent(DateTimeDuration dtd) {
        return new AdoptedEvent(this.name, dtd, this);
    }
}
