package seedu.nova.model.schedule.timeunit;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;

import java.time.Duration;
import java.util.List;

public interface TimeUnit extends Copyable<TimeUnit> {
    boolean addEvent(Event event);
    boolean deleteEvent(Event event);
    List<Event> getEventList();
    List<DateTimeDuration> getFreeSlotList(Duration greaterThan);
    DateTimeDuration getDuration();
    DateTimeSlotList getFreeSlotList();
}
