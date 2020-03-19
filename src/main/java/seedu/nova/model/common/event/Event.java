package seedu.nova.model.common.event;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.time.duration.DateTimeDuration;

import static seedu.nova.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event implements Comparable<Event>, Copyable<Event> {
    private final String name;

    private final DateTimeDuration dateTime;

    public Event(String name, DateTimeDuration dateTime) {
        requireAllNonNull(name, dateTime);
        this.name = name;
        this.dateTime = dateTime;
    }

    public DateTimeDuration getDateTimeDuration() {
        return this.dateTime;
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

    public boolean isCrashed(Event anotherEvent) {
        return this.dateTime.isOverlapping(anotherEvent.dateTime);
    }

    @Override
    public int compareTo(Event o) {
        return this.dateTime.getEndDateTime().compareTo(o.dateTime.getEndDateTime());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Event) {
            return this.name.equals(((Event) o).name) && this.dateTime.equals(((Event) o).dateTime);
        } else {
            return super.equals(o);
        }
    }

    @Override
    public Event getCopy() {
        return new Event(this.name, this.dateTime.getCopy());
    }
}
