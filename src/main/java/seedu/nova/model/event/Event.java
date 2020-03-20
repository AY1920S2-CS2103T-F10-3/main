package seedu.nova.model.event;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents an Event that can be managed.
 */
public class Event implements Comparable<Event>, Copyable<Event> {
    protected final EventDetails details;
    protected final DateTimeDuration dateTime;
    protected boolean isDone = false;

    public Event(EventDetails details, DateTimeDuration dateTime) {
        this.details = details;
        this.dateTime = dateTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public DateTimeDuration getDateTimeDuration() {
        return this.dateTime;
    }

    public EventDetails getDetails() {
        return this.details;
    }

    public LocalDateTime getStartDateTime() {
        return this.dateTime.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return this.dateTime.getEndDateTime();
    }

    public Duration getDuration() {
        return this.dateTime.getDuration();
    }

    public void markDone() {
        isDone = true;
    }

    public void addNote(String note) {
        this.details.setNote(note);
    }

    @Override
    public int compareTo(Event o) {
        return this.dateTime.getStartDateTime().compareTo(o.dateTime.getEndDateTime());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Event) {
            return this.details.equals(((Event) o).details) && this.dateTime.equals(((Event) o).dateTime);
        } else {
            return super.equals(o);
        }
    }

    @Override
    public Event getCopy() {
        return new Event(this.details, this.dateTime.getCopy());
    }

}
