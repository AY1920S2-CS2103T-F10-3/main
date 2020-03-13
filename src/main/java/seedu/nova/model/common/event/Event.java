package seedu.nova.model.common.event;

import static seedu.nova.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

public class Event implements Comparable<Event> {
    private final Name name;
    private final DateTimeDuration dateTime;

    public Event(Name name, DateTimeDuration dateTime) {
        requireAllNonNull(name, dateTime);
        this.name = name;
        this.dateTime = dateTime;
    }

    public Name getName() {
        return this.name;
    }

    public LocalDateTime getStartDateTime() {
        return this.dateTime.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return this.dateTime.getEndDateTime();
    }

    public boolean isCrashed(Event anotherEvent) {
        return this.dateTime.isOverlapping(anotherEvent.dateTime);
    }

    @Override
    public int compareTo(Event o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}
