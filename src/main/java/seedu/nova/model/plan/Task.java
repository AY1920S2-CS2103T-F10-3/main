package seedu.nova.model.plan;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import seedu.nova.model.event.Event;
import seedu.nova.model.schedule.Day;
import seedu.nova.model.util.time.TimeUtil;


/**
 * task inside plan, represents a soon-to-be-defined event for each day.
 */
public abstract class Task {
    protected TaskDetails details;
    protected TreeMap<LocalDate, Event> dayEventMap;

    protected Task(TaskDetails details) {
        this.details = details;
        this.dayEventMap = new TreeMap<>();
    }

    public String getName() {
        return details.getName();
    }

    public Duration getBaseDuration() {
        return details.getDuration();
    }

    public TaskFreq getTaskFreq() {
        return details.getTaskFreq();
    }

    public TreeMap<LocalDate, Event> getDayEventMap() {
        return dayEventMap;
    }

    public Event getEventOn(LocalDate date) {
        switch (getTaskFreq()) {
        case WEEKLY:
            return dayEventMap.get(TimeUtil.getMondayOfWeek(date));
        case DAILY:
        default:
            return dayEventMap.get(date);
        }
    }

    public List<Event> getEventAfter(LocalDate date) {
        return new ArrayList<>(dayEventMap.tailMap(date).values());
    }

    /**
     * check if it is appropriate to generate an event on the date specified according to the task frequency
     *
     * @param date date
     * @return good?
     */
    public boolean hasEventOn(LocalDate date) {
        switch (getTaskFreq()) {
        case WEEKLY:
            return dayEventMap.containsKey(TimeUtil.getMondayOfWeek(date));
        case DAILY:
        default:
            return dayEventMap.containsKey(date);
        }
    }

    /**
     * Generate an event based on the day's schedule and specification of this task.
     * event is not added into day.
     *
     * @param day day
     * @return generated event
     * @throws ImpossibleTaskException
     */
    public abstract Event generateEventOnDay(Day day) throws ImpossibleTaskException;

    /**
     * Delete event generated by the task.
     *
     * @param event event generated by this task
     * @return deleted?
     */
    public boolean deleteEvent(Event event) {
        switch (getTaskFreq()) {
        case WEEKLY:
            return this.dayEventMap.remove(TimeUtil.getMondayOfWeek(event.getDate()), event);
        case DAILY:
        default:
            return this.dayEventMap.remove(event.getDate(), event);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return details.equals(((Task) obj).details);
        } else {
            return super.equals(obj);
        }
    }
}
