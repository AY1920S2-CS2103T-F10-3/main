package seedu.nova.model.plan;

import org.json.simple.JSONObject;
import seedu.nova.model.common.event.Event;

import java.time.LocalTime;
import java.util.List;

public class StudyPlan implements PlanModel {
    List<Event> studyEvents;
    LocalTime startTime;
    LocalTime endTime;

    @Override
    public JSONObject toJsonObject() {
        return new JSONObject();
    }
}
