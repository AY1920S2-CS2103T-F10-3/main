package seedu.nova.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.duration.TimedDuration;
import seedu.nova.model.util.time.duration.WeekDayDuration;

/**
 * Represents an Event that can be managed.
 */
public class Event implements Comparable<Event> {
    protected String desc;
    protected String venue;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;
    protected TimedDuration dtd;
    protected String note = "";
    protected boolean isDone = false;

    /**
     * Instantiates a new Event stub.
     *
     * @param desc      the description
     * @param venue     the venue
     * @param startTime the start time
     * @param endTime   the end time
     * @param date      the date
     */
    public Event(String desc, String venue, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.desc = desc;
        this.date = date;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dtd = new DateTimeDuration(date, startTime, endTime);
    }

    /**
     * Instantiates a new Event stub.
     *
     * @param desc      the description
     * @param venue     the venue
     * @param startTime the start time
     * @param endTime   the end time
     */
    public Event(String desc, String venue, LocalTime startTime, LocalTime endTime, DayOfWeek dow) {
        this.desc = desc;
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
     * Gets dayOfWeek
     *
     * @return the dayOfWeek
     */
    public DayOfWeek getDayOfWeek() {
        return dtd.getStartDay();
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
     * Sets start time.
     *
     * @param startTime the startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
        return desc;
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

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(getDate(), getStartTime());
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(getDate(), getEndTime());
    }

    @Override
    public String toString() {

        return "["
                + desc + ", "
                + venue + ", "
                + date + ", "
                + startTime + " - "
                + endTime
                + "]";
    }

    @Override
    public int compareTo(Event event) {
        return getStartDateTime().compareTo(event.getStartDateTime());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            return desc.equals(((Event) obj).desc)
                    && venue.equals(((Event) obj).venue)
                    && date.equals(((Event) obj).date)
                    && startTime.equals(((Event) obj).startTime)
                    && endTime.equals(((Event) obj).endTime);
        } else {
            return super.equals(obj);
        }
    }

    public void markDone() {
        isDone = true;
    }

    public void addNote(String note) {
        this.note = note;
    }

}
