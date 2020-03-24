package seedu.nova.model.plan;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.nova.model.event.Event;
import seedu.nova.model.schedule.Day;
import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * Task which generates definite events
 */
public class WeakTask extends Task {
    private Duration maxDuration;

    private WeakTask(TaskDetails details, Duration maxDuration) {
        super(details);
        this.maxDuration = maxDuration;
    }

    public static WeakTask get(String name, Duration mind, Duration maxd, TaskFreq freq) {
        return new WeakTask(new TaskDetails(name, mind, freq), maxd);
    }

    public Duration getMaxDuration() {
        return maxDuration;
    }

    @Override
    public Event generateEventOnDay(Day day) throws ImpossibleTaskException {
        DateTimeSlotList dtsl = day.getFreeSlot();
        List<DateTimeDuration> possibleSlot = dtsl.getSlotList(getBaseDuration());
        if (possibleSlot.isEmpty()) {
            throw new ImpossibleTaskException();
        } else {
            DateTimeDuration dtd = getBestTimeframe(possibleSlot);
            Event newEvent = new Event(
                    getName(),
                    null,
                    dtd.getStartTime(),
                    dtd.getEndTime(),
                    dtd.getStartDate());
            this.dayEventMap.put(dtd.getStartDate(), newEvent);
            return newEvent;
        }
    }

    private DateTimeDuration getBestTimeframe(List<DateTimeDuration> freeSlots) {
        assert !freeSlots.isEmpty() : "no free slot to choose";
        List<DateTimeDuration> lst = new ArrayList<>(freeSlots);
        Collections.sort(lst);
        DateTimeDuration dtd = lst.get(lst.size() - 1);
        if (dtd.getDuration().compareTo(getMaxDuration()) < 0) {
            return dtd;
        } else {
            return new DateTimeDuration(dtd.getStartDateTime(), dtd.getStartDateTime().plus(getMaxDuration()));
        }
    }
}
