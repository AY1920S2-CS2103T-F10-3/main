package seedu.nova.model.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents the meeting type of Event.
 */
public class Meeting extends Event {

    public Meeting(String desc, String venue, LocalTime startTime, LocalTime endTime, LocalDate date) {
        super(desc, venue, startTime, endTime);
        this.date = date;
    }

    @Override
    public String toString() {
        return "Description: " + desc + "\n"
                + "Venue: " + venue + "\n"
                + "Date/Time: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + startTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
                + " - " + endTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
    }
}
