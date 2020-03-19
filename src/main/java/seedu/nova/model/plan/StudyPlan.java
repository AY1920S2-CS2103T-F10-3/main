package seedu.nova.model.plan;

import org.json.simple.JSONObject;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.plan.task.AdoptedEvent;
import seedu.nova.model.plan.task.Task;
import seedu.nova.model.schedule.TimeUnit;

import java.time.Duration;
import java.util.List;


public class StudyPlan implements Plan {
    DateTimeDuration weekDuration;
    DateTimeSlotList freeSlotList;

    @Override
    public List<Task> getTaskList() {
        return null;
    }

    @Override
    public Task createAndAddTask(String taskName, Duration duration, WeekDayDuration possibleRange) throws
            ImpossibleTaskException
    {
        return null;
    }

    @Override
    public boolean deleteTask(Task task) throws ImpossibleTaskException {
        return false;
    }

    @Override
    public List<Event> getOrphanEventList() {
        return null;
    }

    @Override
    public List<AdoptedEvent> getEventsOfTask(Task task) {
        return null;
    }

    @Override
    public boolean addOrphanEvent(Event event) {
        return false;
    }

    @Override
    public List<AdoptedEvent> scheduleEvents(Task task, TimeUnit timetable) {
        return null;
    }

    @Override
    public JSONObject toJsonObject() {
        return new JSONObject();
    }
}
