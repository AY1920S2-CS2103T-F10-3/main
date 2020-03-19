package seedu.nova.model.tools;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.plan.AbsolutePlan;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.schedule.Semester;

import java.time.LocalDate;
import java.util.List;

public class NovaScheduler implements Scheduler {
    Plan defaultPlan;
    List<Plan> planList;

    Semester sem;

    public NovaScheduler(LocalDate startDate, LocalDate endDate) {
        this.sem = new Semester(0, startDate, endDate);
    }

    @Override
    public DateTimeDuration getDateTimeDuration() {
        return this.sem.getScheduleDuration();
    }

    @Override
    public List<Plan> getUserDefinedPlanList() {
        return this.planList;
    }

    @Override
    public List<Event> getEventDefaultPlan() {
        return this.defaultPlan.getOrphanEventList();
    }

    @Override
    public Plan createAndAddPlan(String name) {
        Plan newPlan = new AbsolutePlan(getDateTimeDuration());
        this.planList.add(newPlan);
        return newPlan;
    }

    @Override
    public boolean addEvent(Event event) {
        try {
            this.sem.addEvent(event);
            return this.defaultPlan.addOrphanEvent(event);
        } catch(TimeUnitOpException tuoe) {
            return false;
        }
    }

    @Override
    public boolean deleteEvent(Event event) {
        this.sem.deleteEvent(event);
        return this.defaultPlan.removeOrphanEvent(event);
    }

    @Override
    public boolean schedulePlan(Plan plan) {
        return false;
    }
}
