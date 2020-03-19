package seedu.nova.model.schedule;

import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Semester implements TimeUnit {
    int id;
    DateTimeDuration scheduleDuration;
    List<Week> weekList;
    TreeSet<LocalDate> weekStartDateSet;
    List<Event> eventList;
    DateTimeSlotList freeSlotList;

    public Semester(int id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        LocalDateTime realStartDate = DateTimeDuration.parseWeekFromDate(startDate).getStartDateTime();
        LocalDateTime realEndDate = DateTimeDuration.parseWeekFromDate(endDate).getEndDateTime();
        this.scheduleDuration = new DateTimeDuration(realStartDate, realEndDate);
        initialise();
    }

    private void initialise() {
        LocalDate d = this.scheduleDuration.getStartDateTime().toLocalDate();
        LocalDate dd = this.scheduleDuration.getEndDateTime().toLocalDate();
        this.weekList = new ArrayList<>();
        while(d.compareTo(dd) < 0) {
            this.weekStartDateSet.add(d);
            this.weekList.add(new Week(d));
            d = d.plusDays(7);
        }

        this.eventList = new ArrayList<>();
        this.freeSlotList = new DateTimeSlotList(this.scheduleDuration);
    }

    private Semester(int id, List<Week> weekList, List<Event> eventList,
                     TreeSet<LocalDate> weekStartDateSet, DateTimeSlotList freeSlotList) {
        this.id = id;
        this.weekList = weekList;
        this.eventList = eventList;
        this.weekStartDateSet = weekStartDateSet;
        this.freeSlotList = freeSlotList;
    }

    public int getId() {
        return this.id;
    }

    public List<Event> getEventList() {
        return this.eventList;
    }

    public DateTimeDuration getDuration() {
        return this.scheduleDuration;
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
            int startWeek = this.weekStartDateSet.headSet(ed.getStartDateTime().toLocalDate()).size();
            int endWeek = this.weekStartDateSet.headSet(ed.getEndDateTime().toLocalDate()).size();
            for (int i = startWeek; i <= endWeek; i++) {
                this.weekList.get(i).addEvent(event);
            }

            this.freeSlotList.excludeDuration(ed);
            return this.eventList.add(event);
        } else {
            return false;
        }
    }

    public boolean deleteEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();

        for(Week wk : this.weekList) {
            wk.deleteEvent(event);
        }

        this.freeSlotList.includeDuration(ed);
        return this.eventList.remove(event);
    }

    public boolean contains(Event event) {
        return this.eventList.contains(event);
    }

    @Override
    public Semester getCopy() {
        return new Semester(this.id, new ArrayList<>(this.weekList), new ArrayList<>(this.eventList),
                new TreeSet<>(this.weekStartDateSet), this.freeSlotList.getCopy());
    }
}
