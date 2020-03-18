package seedu.nova.model.tools;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.schedule.Schedule;

import java.util.List;
import java.util.stream.Collectors;

public interface Scheduler {
    static List<Event> getOverlappingEvents(Schedule schedule, Event event) {
        return schedule.getAllEvents().stream().parallel().filter(x -> x.isCrashed(event)).collect(Collectors.toList());
    }

    static List<Event> getOverlappingWeakEvents(Schedule schedule, Event event) {
        return schedule.getAllWeakEvents().stream().parallel().filter(x -> x.isCrashed(event)).collect(Collectors.toList());
    }
}
