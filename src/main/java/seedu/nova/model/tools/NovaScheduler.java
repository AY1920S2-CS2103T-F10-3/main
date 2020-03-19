package seedu.nova.model.tools;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.plan.WeakEvent;
import seedu.nova.model.schedule.ScheduleModel;

import java.util.List;

public class NovaScheduler implements Scheduler {
    ScheduleModel schedule;

    public NovaScheduler(ScheduleModel schedule) {
        this.schedule = schedule;
    }

    public List<DateTimeDuration> getAvailableSlotForEvent(Event event) {
        if(event instanceof WeakEvent) {
            return this.schedule.getStrictFreeSlots(event.getDuration());
        } else {
            return this.schedule.getAllFreeSlots(event.getDuration());
        }
    }

    public List<Event> generateEventFromPlan(Plan plan) {
        for(Task task : plan.getTaskList()) {

        }
    }
}
