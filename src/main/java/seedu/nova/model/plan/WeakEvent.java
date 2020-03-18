package seedu.nova.model.plan;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.event.Name;
import seedu.nova.model.common.time.DateTimeDuration;

public class WeakEvent extends Event {
    public WeakEvent(Name name, DateTimeDuration dateTime) {
        super(name, dateTime);
    }
}
