package seedu.nova.model.schedule;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.model.common.time.SlotList;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Day implements Copyable<Day> {
    DayOfWeek type;
    DateTimeDuration dayDuration;
    SlotList freeSlotList;
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
        this.freeSlotList = new SlotList(this.dayDuration);
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

    @Override
    public Day getCopy() {
        return new Day(this.type, this.dayDuration.getCopy(), new TreeSet<>(this.eventList));
    }
}
