package seedu.nova.model.schedule;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;

import java.time.Duration;
import java.util.List;

public interface TimeUnit extends Copyable<TimeUnit> {
    boolean addEvent(Event event) throws TimeUnitOpException;
    boolean deleteEvent(Event event);
    List<Event> getEventList();
    List<DateTimeDuration> getFreeSlotList(Duration greaterThan);
    DateTimeSlotList getFreeSlotList();
}
