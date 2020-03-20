package seedu.nova.model.schedule;

import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.plan.AbsoluteTask;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.schedule.timeunit.Day;
import seedu.nova.model.schedule.timeunit.Week;

import java.time.LocalDate;
import java.util.List;

public class SchedulerManager implements SchedulerModel {
    private final Scheduler scheduler;

    public SchedulerManager(ReadOnlyScheduler readOnlyScheduler) {
        this.scheduler = new Scheduler(readOnlyScheduler);
    }

    @Override
    public void setScheduler(ReadOnlyScheduler scheduler) {
        this.scheduler.resetData(scheduler);
    }

    @Override
    public ReadOnlyScheduler getScheduler() {
        return this.scheduler;
    }

    @Override
    public DateTimeSlotList getFreeSlot(Week week) {
        return week.getFreeSlotList();
    }

    @Override
    public Week getWeek(LocalDate sameWeekAs) {
        return this.scheduler.sem.getWeek(sameWeekAs).getCopy();
    }

    @Override
    public void addEvent(Event e) {
        this.scheduler.addEvent(e);
    }

    @Override
    public void addAbsoluteTask(AbsoluteTask absTask) {
        this.scheduler.addAbsoluteTask(absTask);
    }

    @Override
    public Day getDay(LocalDate date, List<Plan> planInAscendingOrder) {
        Week wk = getWeek(date, planInAscendingOrder);
        return wk.getDay(date.getDayOfWeek());
    }

    @Override
    public Week getWeek(LocalDate sameWeekAs, List<Plan> planList) {
        Week wk = this.scheduler.sem.getWeek(sameWeekAs).getCopy();
        if(wk != null) {
            for(Plan plan : planList) {
                plan.scheduleEvents(wk);
            }
        }
        return wk;
    }

    @Override
    public List<Plan> getPlanList() {
        return this.scheduler.getUserDefinedPlanList();
    }

    @Override
    public void replaceWeek(Week week) {
        this.scheduler.replaceWeek(week);
    }

    @Override
    public boolean isWithinSem(LocalDate date) {
        return this.scheduler.sem.getDuration().getStartDate().compareTo(date) <= 0 && this.scheduler.sem.getDuration().getEndDate().compareTo(date) >= 0;
    }

    @Override
    public Day getDay(LocalDate date) {
        return this.scheduler.sem.getDay(date).getCopy();
    }
}
