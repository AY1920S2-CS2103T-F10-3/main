package seedu.nova.model.schedule;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.plan.AbsolutePlan;
import seedu.nova.model.plan.AbsoluteTask;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.plan.Task;
import seedu.nova.model.schedule.timeunit.Semester;
import seedu.nova.model.schedule.timeunit.Week;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Scheduler implements ReadOnlyScheduler {
    Plan defaultPlan;
    List<Plan> planList;

    Semester sem;

    public Scheduler(ReadOnlyScheduler toBeCopied) {
        resetData(toBeCopied);
    }

    public void resetData(ReadOnlyScheduler newData) {
        requireNonNull(newData);
        this.defaultPlan = newData.getDefaultPlan().getCopy();
        this.planList = newData.getUserDefinedPlanList().stream().map(Copyable::getCopy).collect(Collectors.toList());
    }

    public Scheduler(LocalDate startDate, LocalDate endDate) {
        this.sem = new Semester(0, startDate, endDate);
        this.defaultPlan = new AbsolutePlan("<default>");
        this.planList = new ArrayList<>();
    }


    @Override
    public DateTimeDuration getDateTimeDuration() {
        return this.sem.getDuration();
    }

    @Override
    public List<Plan> getUserDefinedPlanList() {
        return this.planList;
    }

    @Override
    public boolean createAndAddPlan(String name) {
        Plan newPlan = new AbsolutePlan(name);
        return this.planList.add(newPlan);
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
    public boolean addAbsoluteTask(AbsoluteTask absTask) {
        return this.defaultPlan.addTask(absTask);
    }

    @Override
    public boolean deleteTask(Task task) {
        return this.defaultPlan.deleteTask(task);
    }

    @Override
    public void replaceWeek(Week week) {
        this.sem.replaceWeek(week);
    }

}
