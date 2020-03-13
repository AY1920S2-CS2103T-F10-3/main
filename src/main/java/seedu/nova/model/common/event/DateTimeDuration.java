package seedu.nova.model.common.event;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateTimeDuration implements Comparable<DateTimeDuration> {
    private static final DateTimeFormatter[] defaultDateF = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-mm-dd")
    };
    private static final DateTimeFormatter[] defaultTimeF = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("hh:mm a")
    };
    private static LocalTime beginDayTime = LocalTime.of(0, 0, 0);
    private static LocalTime endDayTime = LocalTime.of(23, 59, 59);

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration duration;

    private DateTimeDuration(LocalDateTime start, LocalDateTime stop, Duration duration) {
        this.startDateTime = start;
        this.endDateTime = stop;
        this.duration = duration;
    }

    public static DateTimeDuration parseFromDate(String date) {
        Optional<LocalDate> opDate = parseDate(date);
        assert !opDate.isEmpty() : "cannot parse date";
        DateTimeDuration ans = new DateTimeDuration(
                LocalDateTime.of(opDate.get(), beginDayTime),
                LocalDateTime.of(opDate.get(), endDayTime),
                Duration.ofDays(1)
        );
        return ans;
    }

    public static DateTimeDuration parseFromDateTime(String date, String startTime, long durationInMin) {
        Optional<LocalDate> opDate = parseDate(date);
        Optional<LocalTime> opTime = parseTime(startTime);
        assert !(opDate.isEmpty() || opTime.isEmpty()) : "cannot parse date";
        LocalDateTime ldt = LocalDateTime.of(opDate.get(), opTime.get());
        DateTimeDuration ans = new DateTimeDuration(
                ldt,
                ldt.plusMinutes(durationInMin),
                Duration.ofMinutes(durationInMin)
        );
        return ans;
    }

    private static Optional<LocalDate> parseDate(String date) {
        for (DateTimeFormatter f : DateTimeDuration.defaultDateF) {
            try {
                return Optional.of(LocalDate.parse(date, f));
            } catch (DateTimeParseException dtpe) {
            }
        }
        return Optional.empty();
    }

    private static Optional<LocalTime> parseTime(String time) {
        for (DateTimeFormatter f : DateTimeDuration.defaultTimeF) {
            try {
                return Optional.of(LocalTime.parse(time, f));
            } catch (DateTimeParseException dtpe) {
            }
        }
        return Optional.empty();
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public boolean isOverlapping(DateTimeDuration anotherDateTime) {
        return (anotherDateTime.startDateTime.compareTo(this.startDateTime) >= 0 && anotherDateTime.startDateTime.compareTo(this.endDateTime) < 0)
                || (anotherDateTime.endDateTime.compareTo(this.startDateTime) >= 0 && anotherDateTime.endDateTime.compareTo(this.endDateTime) < 0);
    }

    @Override
    public int compareTo(DateTimeDuration o) {
        return this.duration.compareTo(o.duration);
    }
}
