package seedu.nova.model.plan.timeunit;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Day implements Copyable<Day> {
    DayOfWeek type;
    DateTimeDuration dayDuration;
    DateTimeSlotList freeSlotList;
    TreeSet<Event> eventList;

    Day(LocalDate lDate) {
        this.dayDuration = DateTimeDuration.parseDayFromDate(lDate);
        this.type = this.dayDuration.getStartDateTime().getDayOfWeek();
        initialise();
    }

    private Day(DayOfWeek type, DateTimeDuration dayDuration, TreeSet<Event> eventList) {
        this.type = type;
        this.dayDuration = dayDuration;
        this.eventList = eventList;
    }

    private void initialise() {
        this.eventList = new TreeSet<>();
        this.freeSlotList = new DateTimeSlotList(this.dayDuration);
    }

    List<DateTimeDuration> getFreeSlots(Duration greaterThan) {
        return this.freeSlotList.getSlotList(greaterThan);
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

    @Override
    public Day getCopy() {
        return new Day(this.type, this.dayDuration.getCopy(), new TreeSet<>(this.eventList));
    }
}
