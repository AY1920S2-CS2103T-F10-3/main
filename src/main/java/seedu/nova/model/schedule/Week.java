package seedu.nova.model.schedule;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.model.common.time.FreeSlotList;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class Week {
    DateTimeDuration weekDuration;
    List<Day> sevenDays;
    List<Event> eventList;
    FreeSlotList freeSlotList;

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

        this.eventList = new ArrayList<>();
        this.freeSlotList = new FreeSlotList(this.weekDuration);
    }

    private Week(List<Day> sevenDays, List<Event> eventList, FreeSlotList freeSlotList) {
        this.sevenDays = sevenDays;
        this.eventList = eventList;
        this.freeSlotList = freeSlotList;
    }

    SortedSet<DateTimeDuration> getFreeSlots(Duration greaterThan) {
        return this.freeSlotList.getFreeSlotList(greaterThan);
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
}
