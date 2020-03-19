package seedu.nova.model.plan.task;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.common.time.slotlist.WeekDaySlotList;
import seedu.nova.model.plan.ImpossibleTaskException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//read blabla twice 2 hr..
public class WeakTask extends Task {
    List<WeekDayDuration> available;

    private WeakTask(String name, Duration duration, List<WeekDayDuration> available) {
        super(name, duration);
        this.available = available;
    }

    public static WeakTask create(String name, Duration duration, WeekDaySlotList availableSlot) throws
            ImpossibleTaskException
    {
        List<WeekDayDuration> lst = availableSlot.getSlotList(duration);
        if(lst.isEmpty()) {
            throw new ImpossibleTaskException();
        } else {
            return new WeakTask(name, duration, lst);
        }
    }

    public List<DateTimeDuration> getPossibleSlot(DateTimeSlotList dsl) {
        List<DateTimeDuration> lst = new ArrayList<>();
        for(WeekDayDuration d : this.available) {
            lst.addAll(dsl.intersectWith(d).parallelStream().filter(x -> x.getDuration().compareTo(this.duration) >= 0).collect(
                    Collectors.toList()));
        }
        return lst;
    }

    public Event generateEvent(DateTimeDuration dtd) {
        return new AdoptedEvent(this.name, dtd, this);
    }
}
