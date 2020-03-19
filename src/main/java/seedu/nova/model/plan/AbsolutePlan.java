package seedu.nova.model.plan;

import org.json.simple.JSONObject;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.WeekDaySlotList;
import seedu.nova.model.schedule.Week;

import java.util.*;

public class AbsolutePlan implements Plan {
    List<Task> taskList;
    WeekDaySlotList freeSlotList;

    List<Event> orphanEventList;

    public AbsolutePlan() {
        this.taskList = new ArrayList<>();
        this.orphanEventList = new ArrayList<>();
        this.freeSlotList = new WeekDaySlotList();
    }

    @Override
    public List<Task> getTaskList() {
        return this.taskList;
    }

    public Task createAndAddTask(String taskName, WeekDayDuration onExactDuration) {
        Task newTask = new AbsoluteTask(taskName, onExactDuration);
        this.taskList.add(newTask);
        this.freeSlotList.excludeDuration(onExactDuration);
        return newTask;
    }

    @Override
    public boolean deleteTask(Task task) throws ImpossibleTaskException {
        if (task instanceof AbsoluteTask) {
            WeekDayDuration wdd = ((AbsoluteTask) task).getWeekDayDuration();
            this.freeSlotList.includeDuration(wdd);
            return this.taskList.remove(task);
        } else {
            throw new ImpossibleTaskException();
        }
    }

    @Override
    public List<Event> getOrphanEventList() {
        return this.orphanEventList;
    }

    @Override
    public boolean addOrphanEvent(Event event) {
        return this.orphanEventList.add(event);
    }

    @Override
    public boolean removeOrphanEvent(Event event) {
        return this.orphanEventList.remove(event);
    }

    @Override
    public List<Event> scheduleEvents(Week week) {
        List<Event> failedEvent = new ArrayList<>();
        this.orphanEventList.stream().parallel().filter(
                x -> x.getDateTimeDuration().isSubsetOf(week.getDuration())).forEach(x -> {
            if (!week.addEvent(x)) {
                failedEvent.add(x);
            }
        });
        this.taskList.forEach(x -> {
            x.generateEvent(week).ifPresent(failedEvent::add);
        });
        return failedEvent;
    }

    @Override
    public JSONObject toJsonObject() {
        return null;
    }
}
