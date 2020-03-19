package seedu.nova.model.plan.task;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;

public class AdoptedEvent extends Event {
    public Task task;

    AdoptedEvent(String name, DateTimeDuration dateTime, Task task) {
        super(name, dateTime);
        this.task = task;
    }
}
