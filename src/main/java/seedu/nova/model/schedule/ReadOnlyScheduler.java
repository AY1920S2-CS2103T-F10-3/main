package seedu.nova.model.schedule;

import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.plan.AbsoluteTask;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.plan.Task;
import seedu.nova.model.schedule.timeunit.Week;
import seedu.nova.storage.JsonParsable;

import java.time.LocalDate;
import java.util.List;

public interface ReadOnlyScheduler extends JsonParsable {
    DateTimeDuration getDateTimeDuration();

    // plans
    List<Plan> getUserDefinedPlanList();
    boolean createAndAddPlan(String name);
    boolean removePlan(Plan plan);
    Plan getDefaultPlan();

    // methods for default plan
    boolean addEvent(Event event);
    boolean deleteEvent(Event event);
    boolean addAbsoluteTask(AbsoluteTask absoluteTask);
    boolean deleteTask(Task task);

    // edit schedule
    void replaceWeek(Week week);
}
