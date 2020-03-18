package seedu.nova.model.schedule;

import org.json.simple.JSONObject;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.model.plan.WeakEvent;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;

public class Schedule implements ScheduleModel {
    Semester absoluteSchedule;
    Semester schedulableSchedule;
    Semester wholeSchedule;

    public Schedule() {
        LocalDate now = LocalDate.now();
        LocalDate start = LocalDate.of(now.getYear(), now.getMonth(), 1);
        LocalDate end = LocalDate.of(now.getYear(), 12, 31);
        this.absoluteSchedule = new Semester(0, start, end);
        this.schedulableSchedule = new Semester(1, start, end);
        this.wholeSchedule = new Semester(2, start, end);
    }

    public Schedule(LocalDate start, LocalDate end) {
        this.absoluteSchedule = new Semester(0, start, end);
        this.schedulableSchedule = new Semester(1, start, end);
        this.wholeSchedule = new Semester(2, start, end);
    }

    public List<Event> getAllEvents() {
        return this.wholeSchedule.getEventList();
    }

    public SortedSet<DateTimeDuration> getAllFreeSlots(Duration greaterThan) {
        return this.absoluteSchedule.getFreeSlots(greaterThan);
    }

    public SortedSet<DateTimeDuration> getStrictFreeSlots(Duration greaterThan) {
        return this.wholeSchedule.getFreeSlots(greaterThan);
    }

    public boolean addEvent(Event event) {
        DateTimeDuration d = event.getDateTimeDuration();
        SortedSet<DateTimeDuration> freeSlotSet = getStrictFreeSlots(d.getDuration());
        for(DateTimeDuration freeSlot : freeSlotSet) {
            if(d.isSubsetOf(freeSlot)) {
                addEvent(event, freeSlot);
                return true;
            }
        }
        return false;
    }

    public void addEvent(Event event, DateTimeDuration freeSlot) {
        this.absoluteSchedule.addEvent(event, freeSlot);
        this.wholeSchedule.addEvent(event, freeSlot);
    }

    public void deleteEvent(Event event) {
        this.absoluteSchedule.deleteEvent(event);
        this.wholeSchedule.deleteEvent(event);
    }

    // For scheduler use only
    public List<Event> getAllWeakEvents() {
        return this.schedulableSchedule.getEventList();
    }

    void addWeekEvent(Event event, DateTimeDuration freeSlot) {
        this.schedulableSchedule.addEvent(event, freeSlot);
        this.wholeSchedule.addEvent(event, freeSlot);
    }

    void deleteWeakEvent(Event event) {
        this.schedulableSchedule.deleteEvent(event);
        this.wholeSchedule.deleteEvent(event);
    }

    @Override
    public JSONObject toJsonObject() {
        return new JSONObject();
    }
}
