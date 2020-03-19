package seedu.nova.model.plan.task;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.TimeUtil;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.plan.ImpossibleTaskException;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AbsoluteTask extends Task {
    WeekDayDuration wdd;

    private AbsoluteTask(String name, Duration duration, WeekDayDuration wdd) {
        super(name, duration);
        this.wdd = wdd;
    }

    public static AbsoluteTask create(String name, Duration duration, WeekDayDuration wdd) throws
            ImpossibleTaskException
    {
        if (wdd.getDuration().compareTo(duration) != 0) {
            throw new ImpossibleTaskException();
        } else {
            return new AbsoluteTask(name, duration, wdd);
        }
    }

    public WeekDayDuration getWeekDayDuration() {
        return this.wdd;
    }


    public TreeMap<LocalDate, List<DateTimeDuration>> getPossibleSlot(DateTimeSlotList dsl) {
        TreeMap<LocalDate, List<DateTimeDuration>> map = new TreeMap<>();
        dsl.intersectWith(this.wdd).stream().filter(
                x -> x.getDuration().compareTo(this.duration) >= 0).forEach(x -> {
            LocalDate mon = TimeUtil.getMondayOfWeek(x.getStartDate());
            if(map.containsKey(mon)) {
                map.get(mon).add(x);
            } else {
                List<DateTimeDuration> l = new ArrayList<>();
                l.add(x);
                map.put(mon, l);
            }
        });
        return map;
    }

    public Event generateEvent(DateTimeDuration dtd) {
        return new AdoptedEvent(this.name, dtd, this);
    }
}
