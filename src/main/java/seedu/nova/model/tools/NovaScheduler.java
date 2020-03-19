package seedu.nova.model.tools;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.plan.AbsolutePlan;
import seedu.nova.model.plan.AbsoluteTask;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.plan.Task;
import seedu.nova.model.schedule.Semester;
import seedu.nova.model.schedule.Week;

import java.time.LocalDate;
import java.util.List;

public class NovaScheduler implements Scheduler {
    Plan defaultPlan;
    List<Plan> planList;

    Semester sem;


    @Override
    public DateTimeDuration getDateTimeDuration() {
        return this.sem.getDuration();
    }

    @Override
    public List<Plan> getUserDefinedPlanList() {
        return this.planList;
    }

    @Override
    public Plan createAndAddPlan(String name) {
        Plan newPlan = new AbsolutePlan(name);
        this.planList.add(newPlan);
        return newPlan;
    }

    @Override
    public boolean removePlan(Plan plan) {
        this.planList.remove(plan);
        return true;
    }

    @Override
    public Plan getDefaultPlan() {
        return this.defaultPlan;
    }

    @Override
    public boolean addEvent(Event event) {
        return this.defaultPlan.addOrphanEvent(event);
    }

    @Override
    public boolean deleteEvent(Event event) {
        return this.defaultPlan.removeOrphanEvent(event);
    }

    @Override
    public DateTimeSlotList getFreeSlot(Week week) {
        return week.getFreeSlotList();
    }

    @Override
    public boolean addAbsoluteTask(String taskName, WeekDayDuration dtd) {
        return this.defaultPlan.addTask(new AbsoluteTask(taskName, dtd));
    }

    @Override
    public boolean deleteTask(Task task) {
        return this.defaultPlan.deleteTask(task);
    }

    @Override
    public Week getWeek(LocalDate sameWeekAs) {
        return this.sem.getWeek(sameWeekAs).getCopy();
    }

    @Override
    public Week getWeek(LocalDate sameWeekAs, List<Plan> planList) {
        Week wk = this.sem.getWeek(sameWeekAs).getCopy();
        if(wk != null) {
            for(Plan plan : planList) {
                plan.scheduleEvents(wk);
            }
        }
        return wk;
    }

    @Override
    public void replaceWeek(Week week) {
        this.sem.replaceWeek(week);
    }
}
