package seedu.nova.model.common.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtil {
    private static final DateTimeFormatter[] defaultDateF = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-mm-dd")
    };
    private static final DateTimeFormatter[] defaultTimeF = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("hh:mm a")
    };
    public static LocalTime beginDayTime = LocalTime.of(0, 0, 0);
    public static LocalTime endDayTime = LocalTime.of(23, 59, 59);

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        for (DateTimeFormatter f : defaultDateF) {
            try {
                return LocalDate.parse(date, f);
            } catch (DateTimeParseException dtpe) {
            }
        }
        throw new DateTimeParseException("date format wrong", date, 0);
    }

    public static LocalTime parseTime(String time) throws DateTimeParseException {
        for (DateTimeFormatter f : defaultTimeF) {
            try {
                return LocalTime.parse(time, f);
            } catch (DateTimeParseException dtpe) {
            }
        }
        throw new DateTimeParseException("time format wrong", time, 0);
    }

    public static LocalDate dateOfSameWeek(DayOfWeek dow, LocalDate sameWeekWith) {
        int offset = dow.getValue() - sameWeekWith.getDayOfWeek().getValue();
        return sameWeekWith.plusDays(offset);
    }

    public static LocalDateTime toDateTime(DayOfWeek dow, LocalDate sameWeekWith, LocalTime time) {
        return LocalDateTime.of(dateOfSameWeek(dow, sameWeekWith), time);
    }
}
