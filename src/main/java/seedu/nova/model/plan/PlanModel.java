package seedu.nova.model.plan;

import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.storage.JsonParsable;

import java.time.Duration;

public interface PlanModel extends JsonParsable {
    void createAndAddTask(String taskName, Duration duration, DateTimeDuration possibleRange);
}
