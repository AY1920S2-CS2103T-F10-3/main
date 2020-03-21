package seedu.nova.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.duration.TimedDuration;
import seedu.nova.model.util.time.duration.WeekDayDuration;

/**
 * The type Event stub.
 */
public class Event {

    private String description;
    private String venue;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private TimedDuration dtd;

    /**
     * Instantiates a new Event stub.
     *
     * @param description the description
     * @param venue       the venue
     * @param startTime   the start time
     * @param endTime     the end time
     * @param date        the date
     */
    public Event(String description, String venue, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dtd = new DateTimeDuration(date, startTime, endTime);
    }

    /**
     * Instantiates a new Event stub.
     *
     * @param description the description
     * @param venue       the venue
     * @param startTime   the start time
     * @param endTime     the end time
     * @param date        the date
     */
    public Event(String description, String venue, LocalTime startTime, LocalTime endTime, DayOfWeek dow) {
        this.description = description;
        this.date = null;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dtd = new WeekDayDuration(dow, startTime, endTime);
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets venue.
     *
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Gets TimedDuration.
     *
     * @return the TimedDuration
     */
    public TimedDuration getDtd() {
        return dtd;
    }

    @Override
    public String toString() {

        return "["
                + description + ", "
                + venue + ", "
                + date + ", "
                + startTime + " - "
                + endTime
                + "]";
    }

}
