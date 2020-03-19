package seedu.nova.model.plan.timeunit;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Week implements Copyable<Week> {
    DateTimeDuration weekDuration;
    List<Day> sevenDays;
    TreeSet<Event> eventList;
    DateTimeSlotList freeSlotList;

    public Week(LocalDate date) {
        this.weekDuration = DateTimeDuration.parseWeekFromDate(date);
        initialise();
    }

    private void initialise() {
        LocalDate d = this.weekDuration.getStartDateTime().toLocalDate();
        this.sevenDays = new ArrayList<>(7);
        for(int i = 0; i < 7; i++) {
            sevenDays.add(new Day(d.plusDays(i)));
        }

        this.eventList = new TreeSet<>();
        this.freeSlotList = new DateTimeSlotList(this.weekDuration);
    }

    private Week(List<Day> sevenDays, TreeSet<Event> eventList, DateTimeSlotList freeSlotList) {
        this.sevenDays = sevenDays;
        this.eventList = eventList;
        this.freeSlotList = freeSlotList;
    }

    List<DateTimeDuration> getFreeSlots(Duration greaterThan) {
        return this.freeSlotList.getSlotList(greaterThan);
    }

    void addEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        this.eventList.add(event);
        this.freeSlotList.excludeDuration(ed);

        int startDay = ed.getStartDateTime().getDayOfWeek().getValue();
        int endDay = ed.getEndDateTime().getDayOfWeek().getValue();
        for(int i = startDay; i <= endDay; i++) {
            this.sevenDays.get(i).addEvent(event);
        }
    }

    void deleteEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        this.eventList.remove(event);
        this.freeSlotList.includeDuration(ed);

        int startDay = ed.getStartDateTime().getDayOfWeek().getValue();
        int endDay = ed.getEndDateTime().getDayOfWeek().getValue();
        for(int i = startDay; i <= endDay; i++) {
            this.sevenDays.get(i).deleteEvent(event);
        }
    }

    @Override
    public Week getCopy() {
        return new Week(new ArrayList<>(this.sevenDays), new TreeSet<>(this.eventList), this.freeSlotList.getCopy());
    }
}
