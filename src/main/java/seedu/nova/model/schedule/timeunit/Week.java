package seedu.nova.model.schedule.timeunit;

import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Week implements TimeUnit {
    DateTimeDuration weekDuration;
    List<Day> sevenDays;
    List<Event> eventList;
    DateTimeSlotList freeSlotList;

    public Week(LocalDate date) {
        this.weekDuration = DateTimeDuration.parseWeekFromDate(date);
        init();
    }

    private Week(List<Day> sevenDays, List<Event> eventList, DateTimeSlotList freeSlotList) {
        this.sevenDays = sevenDays;
        this.eventList = eventList;
        this.freeSlotList = freeSlotList;
    }

    private void init() {
        LocalDate d = this.weekDuration.getStartDateTime().toLocalDate();
        this.sevenDays = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            sevenDays.add(new Day(d.plusDays(i)));
        }

        this.eventList = new ArrayList<>();
        this.freeSlotList = new DateTimeSlotList(this.weekDuration);
    }

    public DateTimeDuration getDuration() {
        return this.weekDuration;
    }

    public List<DateTimeDuration> getFreeSlotList(Duration greaterThan) {
        return this.freeSlotList.getSlotList(greaterThan);
    }

    public DateTimeSlotList getFreeSlotList() {
        return this.freeSlotList;
    }

    public boolean addEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        if (this.freeSlotList.isSupersetOf(ed)) {
            int startDay = ed.getStartDateTime().getDayOfWeek().getValue();
            int endDay = ed.getEndDateTime().getDayOfWeek().getValue();
            for (int i = startDay; i <= endDay; i++) {
                this.sevenDays.get(i).addEvent(event);
            }

            this.freeSlotList.excludeDuration(ed);
            return this.eventList.add(event);
        } else {
            return false;
        }
    }

    public boolean deleteEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();

        int startDay = ed.getStartDateTime().getDayOfWeek().getValue();
        int endDay = ed.getEndDateTime().getDayOfWeek().getValue();
        for (int i = startDay; i <= endDay; i++) {
            this.sevenDays.get(i).deleteEvent(event);
        }

        this.freeSlotList.includeDuration(ed);
        return this.eventList.remove(event);
    }

    public Day getDay(DayOfWeek dow) {
        return this.sevenDays.get(dow.getValue() - 1);
    }

    @Override
    public List<Event> getEventList() {
        return this.eventList;
    }

    @Override
    public Week getCopy() {
        return new Week(new ArrayList<>(this.sevenDays), new ArrayList<>(this.eventList), this.freeSlotList.getCopy());
    }
}
