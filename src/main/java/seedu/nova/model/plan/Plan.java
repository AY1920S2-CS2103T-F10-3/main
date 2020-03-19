package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.plan.timeunit.Day;
import seedu.nova.model.plan.timeunit.Week;
import seedu.nova.model.schedule.Schedule;
import seedu.nova.storage.JsonParsable;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface Plan extends JsonParsable {
    void createAndAddTask(String taskName, Duration duration, DateTimeDuration possibleRange);
    List<Task> getTaskList();
    List<Event> getOrphanEventList();

    Day getDay(LocalDate date) throws OutOfScopeException;
    Week getWeek(LocalDate sameWeekAs) throws OutOfScopeException;

    /**
     * Create an event that associates the task. This is the only way to create weak events.
     * @param task
     * @param dtd
     * @return weak event
     */
    WeakEvent scheduleWeakEvent(Task task, Schedule schedule);
}
