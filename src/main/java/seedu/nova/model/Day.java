package seedu.nova.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ListIterator;

import seedu.nova.logic.commands.exceptions.CommandException;

/**
 * The type Day.
 */
public class Day {

    private static final String MESSAGE_SLOT_CONFLICT = "There is another event during that time";

    private LinkedList<Event> events;
    private LocalDate date;

    /**
     * Instantiates a new Day.
     *
     * @param date the date
     */
    public Day(LocalDate date) {

        events = new LinkedList<>();
        this.date = date;
    }

    /**
     * Add event.
     *
     * @param event the event
     * @throws CommandException the command exception
     */
    public void addEvent(Event event) throws CommandException {

        ListIterator<Event> iterator = events.listIterator();
        int index = 0;

        if (events.size() == 0) {
            events.add(event);
        } else {
            boolean hasSlot = false;
            while (iterator.hasNext()) {
                //Check to see if startTime is taken
                Event item = iterator.next();
                index++;
                if (event.getStartTime().compareTo(item.getStartTime()) >= 0) {

                    if (iterator.hasNext() && (iterator.next().getStartTime().compareTo(event.getEndTime()) > 0)) {
                        //Slot cannot fit
                        throw new CommandException(MESSAGE_SLOT_CONFLICT);
                    }
                    hasSlot = true;
                    events.add(index, event);
                    System.err.println(events.get(index) + " has been added to " + date);
                    break;
                }
            }

            if (!hasSlot) {
                throw new CommandException(MESSAGE_SLOT_CONFLICT);
            }
        }

    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     * @throws CommandException the command exception
     */
    public void addLesson(Lesson lesson) throws CommandException {

        addEvent(new Lesson(lesson, date));
    }

    /**
     * View string.
     *
     * @return the string
     */
    public String view() {

        StringBuilder sb = new StringBuilder();
        ListIterator<Event> iterator = events.listIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append("\n");
        }
        return sb.toString();
    }

}
