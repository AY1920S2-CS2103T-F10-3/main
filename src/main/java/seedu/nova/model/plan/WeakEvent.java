package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;

public class WeakEvent extends Event {
    public Task task;

    WeakEvent(String name, DateTimeDuration dateTime, Task task) {
        super(name, dateTime);
        this.task = task;
        task.slotList.excludeDuration(dateTime);
    }
}
