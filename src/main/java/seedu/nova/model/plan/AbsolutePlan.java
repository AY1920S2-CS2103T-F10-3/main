package seedu.nova.model.plan;

import java.util.ArrayList;
import java.util.List;

import seedu.nova.model.util.time.duration.WeekDayDuration;
import seedu.nova.model.util.time.slotlist.WeekDaySlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.schedule.timeunit.Week;

/**
 * Plan with definite tasks
 */
public class AbsolutePlan implements Plan {
    private String name;
    private List<Task> taskList;
    private WeekDaySlotList freeSlotList;

    private List<Event> orphanEventList;

    public AbsolutePlan(String name) {
        this.name = name;
        this.taskList = new ArrayList<>();
        this.orphanEventList = new ArrayList<>();
        this.freeSlotList = new WeekDaySlotList();
    }

    private AbsolutePlan(String name, List<Task> taskList, List<Event> orphanEventList, WeekDaySlotList freeSlotList) {
        this.name = name;
        this.taskList = taskList;
        this.orphanEventList = orphanEventList;
        this.freeSlotList = freeSlotList;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * add task to plan
     * @param task task
     * @return added?
     */
    public boolean addTask(Task task) {
        AbsoluteTask at = (AbsoluteTask) task;
        if (this.freeSlotList.isSupersetOf(at.getWeekDayDuration())) {
            this.freeSlotList.excludeDuration(at.getWeekDayDuration());
            return this.taskList.add(at);
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteTask(Task task) {
        if (task instanceof AbsoluteTask) {
            WeekDayDuration wdd = ((AbsoluteTask) task).getWeekDayDuration();
            this.freeSlotList.includeDuration(wdd);
            return this.taskList.remove(task);
        } else {
            return false;
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
        this.orphanEventList.stream()
                .parallel()
                .filter(x -> x.getDateTimeDuration().isSubsetOf(week.getDuration()))
                .forEach(x -> {
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
    public Plan getCopy() {
        return new AbsolutePlan(this.name, new ArrayList<>(this.taskList), new ArrayList<>(this.orphanEventList),
                this.freeSlotList.getCopy());
    }
}
