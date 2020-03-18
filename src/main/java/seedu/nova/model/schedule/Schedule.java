package seedu.nova.model.schedule;

import org.json.simple.JSONObject;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.DateTimeDuration;

import java.time.Duration;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

public class Schedule implements ScheduleModel {
    Semester absoluteSchedule;
    Semester schedulableSchedule;
    Semester wholeSchedule;

    public Schedule(LocalDate start, LocalDate end) {
        if(start.compareTo(end) >= 0) {
            start = LocalDate.of(start.getYear(), start.getMonth(), 1);
            end = LocalDate.of(end.getYear(), 12, 31);
        }
        init(start, end);
    }

    public Schedule() {
        LocalDate now = LocalDate.now();
        LocalDate start = LocalDate.of(now.getYear(), now.getMonth(), 1);
        LocalDate end = LocalDate.of(now.getYear(), 12, 31);
        init(start, end);
    }

    private void init(LocalDate start, LocalDate end) {
        this.absoluteSchedule = new Semester(0, start, end);
        this.schedulableSchedule = new Semester(1, start, end);
        this.wholeSchedule = new Semester(2, start, end);
    }

    private Schedule(Semester absoluteSchedule, Semester schedulableSchedule, Semester wholeSchedule) {
        this.absoluteSchedule = absoluteSchedule;
        this.schedulableSchedule = schedulableSchedule;
        this.wholeSchedule = wholeSchedule;
    }

    public TreeSet<Event> getAllEvents() {
        return this.wholeSchedule.getEventList();
    }

    public SortedSet<DateTimeDuration> getAllFreeSlots(Duration greaterThan) {
        return this.absoluteSchedule.getFreeSlots(greaterThan);
    }

    public SortedSet<DateTimeDuration> getStrictFreeSlots(Duration greaterThan) {
        return this.wholeSchedule.getFreeSlots(greaterThan);
    }

    @Override
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

    @Override
    public boolean deleteEvent(Event event) {
        if(this.absoluteSchedule.contains(event)) {
            this.absoluteSchedule.deleteEvent(event);
            this.wholeSchedule.deleteEvent(event);
            return true;
        } else {
            return false;
        }
    }

    // For scheduler use only
    public TreeSet<Event> getAllWeakEvents() {
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

    @Override
    public Schedule getCopy() {
        return new Schedule(this.absoluteSchedule.getCopy(), this.schedulableSchedule.getCopy(),
                this.wholeSchedule.getCopy());
    }
}
