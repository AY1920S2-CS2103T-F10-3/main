package seedu.nova.model.tools;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.plan.Plan;

import java.util.List;

public interface Scheduler {
    List<Plan> getUserDefinedPlanList();
    List<Event> getEventDefaultPlan();
    DateTimeDuration getDateTimeDuration();
    Plan createAndAddPlan(String name);
    boolean addEvent(Event event);
    boolean deleteEvent(Event event);
    boolean schedulePlan(Plan plan);
}
