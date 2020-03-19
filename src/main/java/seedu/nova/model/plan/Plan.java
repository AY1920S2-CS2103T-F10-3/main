package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.plan.task.AdoptedEvent;
import seedu.nova.model.plan.task.Task;
import seedu.nova.model.schedule.Day;
import seedu.nova.model.schedule.TimeUnit;
import seedu.nova.model.schedule.Week;
import seedu.nova.storage.JsonParsable;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface Plan extends JsonParsable {
    List<Task> getTaskList();
    Task createAndAddTask(String taskName, Duration duration, WeekDayDuration possibleRange) throws
            ImpossibleTaskException;
    boolean deleteTask(Task task) throws ImpossibleTaskException;

    List<Event> getOrphanEventList();
    List<AdoptedEvent> getEventsOfTask(Task task);

    boolean addOrphanEvent(Event event);
    boolean removeOrphanEvent(Event event);

    // records every events created in a map
    AdoptedEvent scheduleEvents(Task task, DateTimeSlotList freeSlotForAWeek);
}
