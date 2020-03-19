package seedu.nova.model.plan;

import org.json.simple.JSONObject;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;


public class StudyPlan  {
    DateTimeDuration weekDuration;
    DateTimeSlotList freeSlotList;


    public JSONObject toJsonObject() {
        return new JSONObject();
    }
}
