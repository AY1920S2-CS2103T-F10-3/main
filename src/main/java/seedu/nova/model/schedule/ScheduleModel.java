package seedu.nova.model.schedule;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.storage.JsonParsable;

import java.time.Duration;
import java.util.List;

public interface ScheduleModel extends JsonParsable, Copyable<ScheduleModel> {
    boolean addEvent(Event event);
    boolean deleteEvent(Event event);
    List<DateTimeDuration> getStrictFreeSlots(Duration greaterThan);
    List<DateTimeDuration> getAllFreeSlots(Duration greaterThan);
}
