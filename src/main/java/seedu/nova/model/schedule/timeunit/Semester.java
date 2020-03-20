package seedu.nova.model.schedule.timeunit;

import seedu.nova.model.common.time.TimeUtil;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Semester implements TimeUnit {
    int id;
    DateTimeDuration scheduleDuration;
    TreeMap<LocalDate, Week> weekMap;
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
        this.weekMap = new TreeMap<>();
        while(d.compareTo(dd) < 0) {
            this.weekMap.put(d, new Week(d));
            d = d.plusDays(7);
        }

        this.eventList = new ArrayList<>();
        this.freeSlotList = new DateTimeSlotList(this.scheduleDuration);
    }

    private Semester(int id, TreeMap<LocalDate, Week> weekMap, List<Event> eventList, DateTimeSlotList freeSlotList) {
        this.id = id;
        this.weekMap = weekMap;
        this.eventList = eventList;
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

    public Week getWeek(LocalDate sameWeekAs) {
        LocalDate d = TimeUtil.getMondayOfWeek(sameWeekAs);
        return this.weekMap.getOrDefault(d, null);
    }

    public Day getDay(LocalDate date) {
        Week wk = getWeek(date);
        if(wk == null) {
            return null;
        } else {
            return wk.getDay(date.getDayOfWeek());
        }
    }

    public void replaceWeek(Week week) {
        this.weekMap.put(week.weekDuration.getStartDate(), week);
    }

    public boolean addEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();
        if (this.freeSlotList.isSupersetOf(ed)) {
            SortedMap<LocalDate, Week> startWeekMap = this.weekMap.headMap(ed.getStartDateTime().toLocalDate());
            LocalDate endDate = this.weekMap.ceilingEntry(ed.getEndDateTime().toLocalDate()).getKey();
            for (Map.Entry<LocalDate, Week> e : startWeekMap.entrySet()) {
                e.getValue().addEvent(event);
                if(e.getKey().compareTo(endDate) > 0) {
                    break;
                }
            }

            this.freeSlotList.excludeDuration(ed);
            return this.eventList.add(event);
        } else {
            return false;
        }
    }

    public boolean deleteEvent(Event event) {
        DateTimeDuration ed = event.getDateTimeDuration();

        for(Map.Entry<LocalDate, Week> e : this.weekMap.entrySet()) {
            e.getValue().deleteEvent(event);
        }

        this.freeSlotList.includeDuration(ed);
        return this.eventList.remove(event);
    }

    public boolean contains(Event event) {
        return this.eventList.contains(event);
    }

    @Override
    public Semester getCopy() {
        return new Semester(this.id, new TreeMap<>(this.weekMap), new ArrayList<>(this.eventList),
                this.freeSlotList.getCopy());
    }
}
