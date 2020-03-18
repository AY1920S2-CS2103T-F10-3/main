package seedu.nova.model.schedule;

import seedu.nova.model.common.event.Event;
import seedu.nova.storage.JsonParsable;

public interface ScheduleModel extends JsonParsable {
    boolean addEvent(Event event);
    boolean deleteEvent(Event event);

}
