package seedu.nova.model.tools;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.plan.Task;
import seedu.nova.model.schedule.Week;
import seedu.nova.storage.JsonParsable;

import java.time.LocalDate;
import java.util.List;

public interface Scheduler extends JsonParsable {
    DateTimeDuration getDateTimeDuration();

    List<Plan> getUserDefinedPlanList();
    boolean createAndAddPlan(String name);
    boolean removePlan(Plan plan);

    Plan getDefaultPlan();
    boolean addEvent(Event event);
    boolean deleteEvent(Event event);

    DateTimeSlotList getFreeSlot(Week week);
    boolean addAbsoluteTask(String taskName, WeekDayDuration dtd);
    boolean deleteTask(Task task);

    Week getWeek(LocalDate sameWeekAs);
    Week getWeek(LocalDate sameWeekAs, List<Plan> planList);
    void replaceWeek(Week week);
}
