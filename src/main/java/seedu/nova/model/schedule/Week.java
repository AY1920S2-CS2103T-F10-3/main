package seedu.nova.model.schedule;

import java.time.LocalDate;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * The type Week.
 */
public class Week {

    /**
     * The Days.
     */
    final Day[] days;
    /**
     * The Start date.
     */
    final LocalDate startDate;
    /**
     * DateTimeDuration
     */
    final DateTimeSlotList dtsl;

    /**
     * Instantiates a new Week.
     *
     * @param date the date
     */
    public Week(LocalDate date) {

        days = new Day[7];
        startDate = date;
        dtsl = DateTimeSlotList.ofWeek(date);
    }

    Day getDay(LocalDate date) {
        return days[date.getDayOfWeek().getValue() - 1];
    }

    /**
     * Add event.
     *
     * @param event the event
     * @throws SchedulerException the command exception
     */
    public boolean addEvent(Event event) throws SchedulerException {

        LocalDate date = event.getDate();
        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(date);
        }

        dtsl.excludeDuration(event.getDtd());
        return days[day].addEvent(event);
    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     * @throws SchedulerException the command exception
     */
    public void addLesson(Lesson lesson) throws SchedulerException {

        int day = lesson.getDay().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(startDate.plusDays(day));
        }

        days[day].addLesson(lesson);
        dtsl.excludeDuration(lesson.getDtd());
    }

    /**
     * View string.
     *
     * @param date the date
     * @return the string
     */
    public String view(LocalDate date) {

        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(startDate.plusDays(day));
        }

        return days[day].view();
    }

    /**
     * Get free slots in the form of DateTimeDuration
     *
     * @return list of free slots
     */
    public DateTimeSlotList getFreeSlot() {
        return dtsl;
    }
}
