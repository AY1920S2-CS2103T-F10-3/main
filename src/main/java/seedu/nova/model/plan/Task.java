package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.WeekDayDuration;

import java.util.List;

//read blabla twice 2 hr..
public class Task {
    // events to be rescheduled
    List<Event> eventList;
    WeekDayDuration wdd;

    Task(List<WeakEvent> eventList, WeekDayDuration wdd) {
        this.eventList = eventList;
        this.wdd = wdd;
    }
}
