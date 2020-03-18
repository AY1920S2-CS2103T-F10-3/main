package seedu.nova.model.schedule;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.model.common.time.SlotList;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Semester {
    int id;
    DateTimeDuration scheduleDuration;
    List<Week> weekList;
    TreeSet<LocalDate> weekStartDateSet;
    List<Event> eventList;
    SlotList freeSlotList;

    public Semester(int id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        LocalDateTime realStartDate = DateTimeDuration.parseWeekFromDate(startDate).getStartDateTime();
        LocalDateTime realEndDate = DateTimeDuration.parseWeekFromDate(endDate).getStartDateTime();
        this.scheduleDuration = new DateTimeDuration(realStartDate, realEndDate);
        initialise();
    }

    private void initialise() {
        LocalDate d = this.scheduleDuration.getStartDateTime().toLocalDate();
        long num = this.scheduleDuration.toWeeks();
        this.weekList = new ArrayList<>();
        for(long i = 0; i < num; i++) {
            LocalDate dd = d.plusDays(7 * i);
            this.weekStartDateSet.add(dd);
            weekList.add(new Week(dd));
        }

        this.eventList = new ArrayList<>();
        this.freeSlotList = new SlotList(this.scheduleDuration);
    }

    private Semester(int id, List<Week> weekList, List<Event> eventList, SlotList freeSlotList) {
        this.id = id;
        this.weekList = weekList;
        this.eventList = eventList;
        this.freeSlotList = freeSlotList;
    }

    public int getId() {
        return this.id;
    }

    public List<Event> getEventList() {
        return this.eventList;
    }

    SortedSet<DateTimeDuration> getFreeSlots(Duration greaterThan) {
        return this.freeSlotList.getFreeSlotList(greaterThan);
    }

    public void addEvent(Event event, DateTimeDuration freeSlot) {
        assert this.freeSlotList.contains(freeSlot) : "free slot not exist";
        assert event.getDateTimeDuration().isSubsetOf(freeSlot) : "event out of scope";
        addEvent(event);
    }

    private void addEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        this.eventList.add(event);
        this.freeSlotList.excludeDuration(ed);

        int startWeek = this.weekStartDateSet.headSet(ed.getStartDateTime().toLocalDate()).size();
        int endWeek = this.weekStartDateSet.headSet(ed.getEndDateTime().toLocalDate()).size();
        for(int i = startWeek; i <= endWeek; i++) {
            this.weekList.get(i).addEvent(event);
        }
    }

    void deleteEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        this.eventList.remove(event);
        this.freeSlotList.includeDuration(ed);

        int startWeek = this.weekStartDateSet.headSet(ed.getStartDateTime().toLocalDate()).size();
        int endWeek = this.weekStartDateSet.headSet(ed.getEndDateTime().toLocalDate()).size();
        for(int i = startWeek; i <= endWeek; i++) {
            this.weekList.get(i).deleteEvent(event);
        }
    }

    boolean contains(Event event) {
        return this.eventList.contains(event);
    }
}
