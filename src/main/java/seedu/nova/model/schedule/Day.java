package seedu.nova.model.schedule;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * The type Day.
 */
public class Day {

    private static final String MESSAGE_SLOT_CONFLICT = "There is another event during that time";

    private TreeSet<Event> events;
    private LocalDate date;
    private DateTimeSlotList dtsl;

    /**
     * Instantiates a new Day.
     *
     * @param date the date
     */
    public Day(LocalDate date) {

        events = new TreeSet<>();
        this.date = date;
        this.dtsl = DateTimeSlotList.ofDay(date);
    }

    /**
     * Add event.
     *
     * @param event the event
     * @throws SchedulerException the command exception
     */
    public boolean addEvent(Event event) throws SchedulerException {
        boolean hasSlot = dtsl.isSupersetOf(event.getDtd());
        if (hasSlot) {
            dtsl.excludeDuration(event.getDtd());
            System.err.println(event + " has been added to " + date);
            return events.add(event);
        } else {
            throw new SchedulerException(MESSAGE_SLOT_CONFLICT);
        }
    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     * @throws SchedulerException the command exception
     */
    public boolean addLesson(Lesson lesson) throws SchedulerException {
        Lesson l = new Lesson(lesson, date);
        return addEvent(l);
    }

    /**
     * View string.
     *
     * @return the string
     */
    public String view() {

        StringBuilder sb = new StringBuilder();
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Get free slots in the form of DateTimeDuration
     *
     * @return List of DateTimeDuration
     */
    public DateTimeSlotList getFreeSlot() {
        return dtsl;
    }

}
