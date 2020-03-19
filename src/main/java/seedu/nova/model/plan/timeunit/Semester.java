package seedu.nova.model.plan.timeunit;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.event.Event;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.TimedDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Semester implements Copyable<Semester> {
    int id;
    DateTimeDuration scheduleDuration;
    List<Week> weekList;
    TreeSet<LocalDate> weekStartDateSet;
    TreeSet<Event> eventList;
    DateTimeSlotList freeSlotList;

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

        this.eventList = new TreeSet<>();
        this.freeSlotList = new DateTimeSlotList(this.scheduleDuration);
    }

    private Semester(int id, List<Week> weekList, TreeSet<Event> eventList, DateTimeSlotList freeSlotList) {
        this.id = id;
        this.weekList = weekList;
        this.eventList = eventList;
        this.freeSlotList = freeSlotList;
    }

    public int getId() {
        return this.id;
    }

    public TreeSet<Event> getEventList() {
        return this.eventList;
    }

    List<TimedDuration> getFreeSlots(Duration greaterThan) {
        return this.freeSlotList.getSlotList(greaterThan);
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

    public void deleteEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        this.eventList.remove(event);
        this.freeSlotList.includeDuration(ed);

        int startWeek = this.weekStartDateSet.headSet(ed.getStartDateTime().toLocalDate()).size();
        int endWeek = this.weekStartDateSet.headSet(ed.getEndDateTime().toLocalDate()).size();
        for(int i = startWeek; i <= endWeek; i++) {
            this.weekList.get(i).deleteEvent(event);
        }
    }

    public boolean contains(Event event) {
        return this.eventList.contains(event);
    }

    @Override
    public Semester getCopy() {
        return new Semester(this.id, new ArrayList<>(this.weekList), new TreeSet<>(this.eventList),
                this.freeSlotList.getCopy());
    }
}
