package seedu.nova.model.schedule;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;

/**
 * The type Schedule.
 */
public class Schedule {

    private static final int DAYS_IN_WEEK = 7;
    private static final int ACTUAL_RECESS_WEEK = 7;
    private static final int SCHEDULE_RECESS_WEEK = 16;

    private final LocalDate startDate;
    private final LocalDate endDate;

    private Week[] weeks;

    /**
     * Instantiates a new Schedule.
     *
     * @param startDate the start date
     * @param endDate   the end date
     */
    public Schedule(LocalDate startDate, LocalDate endDate) {

        this.startDate = startDate;
        this.endDate = endDate;

        weeks = new Week[17];

        try {
            this.addLesson(new Lesson("CS2103 Tutorial", "COM1-B103", LocalTime.of(10, 0),
                    LocalTime.of(11, 0), DayOfWeek.FRIDAY));
        } catch (SchedulerException e) {
            //Do nothing
        }
    }

    public Day getDay(LocalDate date) {
        int weekNumber = calWeekNumber(date);
        return weeks[weekNumber].getDay(date);
    }

    /**
     * Add event.
     *
     * @param event the event
     * @throws SchedulerException the command exception
     */
    public boolean addEvent(Event event) throws SchedulerException {

        LocalDate date = event.getDate();
        int weekNumber = calWeekNumber(date);

        if (weeks[weekNumber] == null) {
            weeks[weekNumber] = new Week(startDate.plusWeeks(weekNumber));
        }

        return weeks[weekNumber].addEvent(event);

    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     * @throws SchedulerException the command exception
     */
    public boolean addLesson(Lesson lesson) throws SchedulerException {
        boolean ans = true;
        for (int i = 0; i < 14; i++) {

            if (i == ACTUAL_RECESS_WEEK) {
                //No lesson on recess week
                continue;
            }

            if (weeks[i] == null) {
                weeks[i] = new Week(startDate.plusWeeks(i));
            }
            ans &= weeks[i].addLesson(lesson);
        }
        return ans;
    }

    /**
     * View string.
     *
     * @param date the date
     * @return the string
     */
    public String view(LocalDate date) {

        int weekNumber = calWeekNumber(date);

        if (weeks[weekNumber] == null) {
            weeks[weekNumber] = new Week(startDate.plusWeeks(weekNumber));
        }

        return weeks[weekNumber].view(date);
    }

    /**
     * Check date validity boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public boolean checkDateValidity(LocalDate date) {

        return !isBeforeStart(date) && !isAfterEnd(date);
    }

    private boolean isBeforeStart(LocalDate date) {

        return date.compareTo(startDate) < 0;
    }

    private boolean isAfterEnd(LocalDate date) {

        return date.compareTo(endDate) > 0;
    }

    private int calWeekNumber(LocalDate date) {

        return (int) (DAYS.between(startDate, date) / DAYS_IN_WEEK);
    }

}
