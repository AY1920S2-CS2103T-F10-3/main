package seedu.nova.model.common.time;

import seedu.nova.model.common.Copyable;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateTimeDuration implements Comparable<DateTimeDuration>, Copyable<DateTimeDuration> {
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

    public DateTimeDuration(LocalDateTime start, LocalDateTime stop) {
        this.startDateTime = start;
        this.endDateTime = stop;
        if(start.compareTo(stop) < 0) {
            this.duration = Duration.between(start, stop);
        } else {
            this.duration = Duration.ZERO;
        }
    }

    public DateTimeDuration(LocalDate date, LocalTime start, LocalTime end) {
        if(start.compareTo(end) < 0) {
            this.startDateTime = LocalDateTime.of(date, start);
            this.endDateTime = LocalDateTime.of(date, end);
            this.duration = Duration.between(this.startDateTime, this.endDateTime);
        } else {
            this.startDateTime = LocalDateTime.of(date, start);
            this.endDateTime = LocalDateTime.of(date.plusDays(1), end);
            this.duration = Duration.between(this.startDateTime, this.endDateTime);
        }
    }

    DateTimeDuration(Duration duration) {
        this.duration = duration;
    }

    private DateTimeDuration(LocalDateTime start, LocalDateTime end, Duration duration) {
        this.startDateTime = start;
        this.endDateTime = end;
        this.duration = duration;
    }

    public static DateTimeDuration parseDayFromDate(String date) throws DurationParseException {
        return parseDayFromDate(parseDate(date));
    }

    public static DateTimeDuration parseDayFromDate(LocalDate lDate) {
        return new DateTimeDuration(
                LocalDateTime.of(LocalDate.of(lDate.getYear(), lDate.getMonth(), lDate.getDayOfMonth()), beginDayTime),
                LocalDateTime.of(lDate, endDayTime)
        );
    }

    public static DateTimeDuration parseWeekFromDate(String date) throws DurationParseException {
        return parseWeekFromDate(parseDate(date));
    }

    public static DateTimeDuration parseWeekFromDate(LocalDate monDate) {
        int offset = monDate.getDayOfWeek().getValue() - 1;
        return new DateTimeDuration(
                LocalDateTime.of(LocalDate.of(monDate.getYear(), monDate.getMonth(),
                        monDate.getDayOfMonth() - offset), beginDayTime),
                LocalDateTime.of(LocalDate.of(monDate.getYear(), monDate.getMonth(),
                        monDate.getDayOfMonth() + 7 - offset), endDayTime)
        );
    }

    public static DateTimeDuration parseFromDateTime(String date, String startTime, long durationInMin) throws
            DurationParseException
    {
        return parseFromDateTime(parseDate(date), parseTime(startTime), durationInMin);
    }

    public static DateTimeDuration parseFromDateTime(LocalDate date, LocalTime startTime, long durationInMin) {
        LocalDateTime dt = LocalDateTime.of(date, startTime);
        return new DateTimeDuration(
                dt,
                dt.plusMinutes(durationInMin)
        );
    }

    public static DateTimeDuration parseFromDateTime(LocalDateTime startDateTime, Duration duration) {
        return new DateTimeDuration(
                startDateTime,
                startDateTime.plusMinutes(duration.toMinutes())
        );
    }

    public static LocalDate parseDate(String date) throws DurationParseException {
        for (DateTimeFormatter f : DateTimeDuration.defaultDateF) {
            try {
                return LocalDate.parse(date, f);
            } catch (DateTimeParseException dtpe) {
            }
        }
        throw new DurationParseException();
    }

    public static LocalTime parseTime(String time) throws DurationParseException {
        for (DateTimeFormatter f : DateTimeDuration.defaultTimeF) {
            try {
                return LocalTime.parse(time, f);
            } catch (DateTimeParseException dtpe) {
            }
        }
        throw new DurationParseException();
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDate(LocalDate date) {
        this.startDateTime = LocalDateTime.of(date, this.startDateTime.toLocalTime());
        this.endDateTime = this.startDateTime.plus(this.duration);
    }

    public LocalDate getStartDate() {
        return this.startDateTime.toLocalDate();
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public long toDays() {
        return this.duration.toDays();
    }

    public long toWeeks() {
        return (long) Math.ceil((toDays() + 0.0) / 7);
    }

    public DateTimeDuration plusDays(long days) {
        DateTimeDuration d = getCopy();
        d.startDateTime = d.startDateTime.plusDays(days);
        d.endDateTime = d.endDateTime.plusDays(days);
        return d;
    }

    public boolean isOverlapping(DateTimeDuration another) {
        return this.startDateTime.compareTo(another.endDateTime) < 0 &&
                this.endDateTime.compareTo(another.startDateTime) > 0;
    }

    public boolean isSubsetOf(DateTimeDuration another) {
        return this.startDateTime.compareTo(another.startDateTime) >= 0 &&
                this.endDateTime.compareTo(another.endDateTime) <= 0;
    }

    public boolean isImmidiatelyAfter(DateTimeDuration another) {
        return this.startDateTime.equals(another.endDateTime);
    }

    public boolean isConnected(DateTimeDuration another) {
        return this.startDateTime.compareTo(another.endDateTime) <= 0 &&
                this.endDateTime.compareTo(another.startDateTime) >= 0;
    }

    public List<DateTimeDuration> relativeComplementOf(DateTimeDuration another) {
        List<DateTimeDuration> lst = new ArrayList<>();
        if (isOverlapping(another)) {
            if (another.startDateTime.compareTo(this.startDateTime) > 0) {
                lst.add(DateTimeDuration.parseFromDateTime(this.startDateTime,
                        Duration.between(this.startDateTime, another.startDateTime)));
            }
            if (another.endDateTime.compareTo(this.endDateTime) < 0) {
                lst.add(DateTimeDuration.parseFromDateTime(another.endDateTime,
                        Duration.between(this.endDateTime, another.endDateTime)));
            }
        } else {
            lst.add(this);
        }
        return lst;
    }

    public DateTimeDuration intersectionWith(DateTimeDuration another) {
        LocalDateTime start;
        LocalDateTime end;
        if(another.startDateTime.compareTo(this.startDateTime) > 0) {
            start = another.startDateTime;
        } else {
            start = this.startDateTime;
        }
        if(another.endDateTime.compareTo(this.endDateTime) < 0) {
            end = another.endDateTime;
        } else {
            end = this.endDateTime;
        }
        return new DateTimeDuration(start, end);
    }

    @Override
    public int compareTo(DateTimeDuration o) {
        return this.duration.compareTo(o.duration);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DateTimeDuration) {
            return this.duration.equals(((DateTimeDuration) obj).duration) &&
                    this.startDateTime.equals(((DateTimeDuration) obj).startDateTime);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public DateTimeDuration getCopy() {
        return new DateTimeDuration(this.startDateTime, this.endDateTime, this.duration);
    }
}
