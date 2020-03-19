package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.schedule.Week;
import seedu.nova.storage.JsonParsable;

import java.util.List;

public interface Plan extends JsonParsable {
    String getName();
    List<Task> getTaskList();
    boolean addTask(Task task);
    boolean deleteTask(Task task);

    List<Event> getOrphanEventList();

    boolean addOrphanEvent(Event event);
    boolean removeOrphanEvent(Event event);

    // records every events created in a map
    List<Event> scheduleEvents(Week week);
}
