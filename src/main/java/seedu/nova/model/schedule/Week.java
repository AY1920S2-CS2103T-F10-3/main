package seedu.nova.model.schedule;

import java.time.LocalDate;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;

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
     * Instantiates a new Week.
     *
     * @param date the date
     */
    public Week(LocalDate date) {

        days = new Day[7];
        startDate = date;
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {

        LocalDate date = event.getDate();
        int day = date.getDayOfWeek().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(date);
        }

        days[day].addEvent(event);
    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     */
    public void addLesson(Lesson lesson) {

        int day = lesson.getDay().getValue() - 1;

        if (days[day] == null) {
            days[day] = new Day(startDate.plusDays(day));
        }

        days[day].addLesson(lesson);
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

}
