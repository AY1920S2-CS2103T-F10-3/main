package seedu.nova.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.nova.model.util.time.duration.WeekDayDuration;

/**
 * The type Lesson.
 */
public class Lesson extends Event {

    private DayOfWeek day;
    private WeekDayDuration wdd;

    /**
     * Instantiates a new Lesson.
     *
     * @param description the description
     * @param venue       the venue
     * @param startTime   the start time
     * @param endTime     the end time
     * @param day         the day
     */
    public Lesson(String description, String venue, LocalTime startTime, LocalTime endTime,
                  DayOfWeek day) {
        super(description, venue, startTime, endTime, day);
        this.day = day;
        this.wdd = new WeekDayDuration(day, startTime, endTime);
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param lesson the lesson
     * @param date   the date
     */
    public Lesson(Lesson lesson, LocalDate date) {
        super(lesson.getDescription(), lesson.getVenue(), lesson.getStartTime(), lesson.getEndTime(), date);
        day = lesson.day;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * get WeekDayDuration
     */
    public WeekDayDuration getWdd() {
        return wdd;
    }

}
