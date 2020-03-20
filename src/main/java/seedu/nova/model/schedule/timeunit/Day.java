package seedu.nova.model.schedule.timeunit;

import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Day implements TimeUnit {
    DayOfWeek type;
    DateTimeDuration dayDuration;
    DateTimeSlotList freeSlotList;
    List<Event> eventList;

    Day(LocalDate lDate) {
        this.dayDuration = DateTimeDuration.parseDayFromDate(lDate);
        this.type = this.dayDuration.getStartDateTime().getDayOfWeek();
        initialise();
    }

    private Day(DayOfWeek type, DateTimeDuration dayDuration, List<Event> eventList) {
        this.type = type;
        this.dayDuration = dayDuration;
        this.eventList = eventList;
    }

    private void initialise() {
        this.eventList = new ArrayList<>();
        this.freeSlotList = new DateTimeSlotList(this.dayDuration);
    }

    public DateTimeDuration getDuration() {
        return this.dayDuration;
    }

    public List<DateTimeDuration> getFreeSlotList(Duration greaterThan) {
        return this.freeSlotList.getSlotList(greaterThan);
    }

    public DateTimeSlotList getFreeSlotList() {
        return this.freeSlotList;
    }

    public boolean addEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        if(this.freeSlotList.isSupersetOf(ed)) {
            this.freeSlotList.excludeDuration(ed);
            return this.eventList.add(event);
        } else {
            return false;
        }
    }

    public boolean deleteEvent(Event event) {
        this.freeSlotList.includeDuration(event.getDateTimeDuration());
        return this.eventList.remove(event);
    }

    @Override
    public List<Event> getEventList() {
        return this.eventList;
    }

    @Override
    public Day getCopy() {
        return new Day(this.type, this.dayDuration.getCopy(), new ArrayList<>(this.eventList));
    }
}
