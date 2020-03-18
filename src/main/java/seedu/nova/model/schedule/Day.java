package seedu.nova.model.schedule;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.model.common.time.FreeSlotList;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Day {
    DayOfWeek type;
    DateTimeDuration dayDuration;
    FreeSlotList freeSlotList;
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
        this.eventList = new LinkedList<>();
        this.freeSlotList = new FreeSlotList(this.dayDuration);
    }

    SortedSet<DateTimeDuration> getFreeSlots(Duration greaterThan) {
        return this.freeSlotList.getFreeSlotList(greaterThan);
    }

    void addEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();

        this.eventList.add(event);
        this.freeSlotList.excludeDuration(ed);
    }

    void deleteEvent(Event event) {
        this.eventList.remove(event);
        this.freeSlotList.includeDuration(event.getDateTimeDuration());
    }
}
