package seedu.nova.model.plan;

import org.json.simple.JSONObject;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.TimeUtil;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.WeekDaySlotList;
import seedu.nova.model.plan.task.AbsoluteTask;
import seedu.nova.model.plan.task.AdoptedEvent;
import seedu.nova.model.plan.task.Task;
import seedu.nova.model.schedule.TimeUnit;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class AbsolutePlan implements Plan {
    DateTimeDuration dtd;
    List<LocalDate> weekStartList;

    List<Task> taskList;
    WeekDaySlotList freeSlotList;
    Map<Task, List<AdoptedEvent>> mapTaskToItsEvent;

    List<Event> orphanEventList;

    public AbsolutePlan(DateTimeDuration dtd) {
        this.taskList = new ArrayList<>();
        this.orphanEventList = new ArrayList<>();
        this.mapTaskToItsEvent = new HashMap<>();
        this.freeSlotList = new WeekDaySlotList();
        this.dtd = dtd;
        this.weekStartList = dtd.getWeekStartList();
    }

    @Override
    public List<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public Task createAndAddTask(String taskName, Duration duration, WeekDayDuration onExactDuration) throws
            ImpossibleTaskException
    {
        if(!this.freeSlotList.isSupersetOf(onExactDuration)) {
            throw new ImpossibleTaskException();
        }
        Task newTask = AbsoluteTask.create(taskName, duration, onExactDuration);
        this.taskList.add(newTask);
        this.freeSlotList.excludeDuration(onExactDuration);
        return newTask;
    }

    @Override
    public boolean deleteTask(Task task) throws ImpossibleTaskException {
        if(task instanceof AbsoluteTask) {
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
    public List<AdoptedEvent> getEventsOfTask(Task task) {
        return this.mapTaskToItsEvent.get(task);
    }

    @Override
    public boolean addOrphanEvent(Event event) {
        return this.orphanEventList.add(event);
    }

    @Override
    public List<AdoptedEvent> scheduleEvents(Task task, TimeUnit timetable) {
        List<AdoptedEvent> lst = new ArrayList<>();
        TreeMap<LocalDate, List<DateTimeDuration>> map = task.getPossibleSlot(timetable.getFreeSlotList());
        for(Map.Entry<LocalDate, List<DateTimeDuration>> e : map.entrySet()) {
            if(!e.getValue().isEmpty()) {
                lst.add(task.generateEvent(e.getValue().get(0)));
            }
        }
        return lst;
    }

    @Override
    public JSONObject toJsonObject() {
        return null;
    }
}
