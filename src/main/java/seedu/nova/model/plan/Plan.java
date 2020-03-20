package seedu.nova.model.plan;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.event.Event;
import seedu.nova.model.schedule.timeunit.Week;
import seedu.nova.storage.JsonParsable;

import java.util.List;

public interface Plan extends JsonParsable, Copyable<Plan> {
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
