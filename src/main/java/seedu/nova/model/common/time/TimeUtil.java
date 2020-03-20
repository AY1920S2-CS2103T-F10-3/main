package seedu.nova.model.common.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Useful functions for LocalTime
 */
public interface TimeUtil {
    DateTimeFormatter[] defaultDateF = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-mm-dd")
    };
    DateTimeFormatter[] defaultTimeF = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("hh:mm a")
    };
    LocalTime beginDayTime = LocalTime.of(0, 0, 0);
    LocalTime endDayTime = LocalTime.of(23, 59, 59);

    static LocalDate parseDate(String date) throws DateTimeParseException {
        for (DateTimeFormatter f : defaultDateF) {
            try {
                return LocalDate.parse(date, f);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DateTimeParseException("date format wrong", date, 0);
    }

    static LocalTime parseTime(String time) throws DateTimeParseException {
        for (DateTimeFormatter f : defaultTimeF) {
            try {
                return LocalTime.parse(time, f);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DateTimeParseException("time format wrong", time, 0);
    }

    static LocalDate dateOfSameWeek(DayOfWeek dow, LocalDate sameWeekWith) {
        int offset = dow.getValue() - sameWeekWith.getDayOfWeek().getValue();
        return sameWeekWith.plusDays(offset);
    }

    static LocalDate getMondayOfWeek(LocalDate weekOf) {
        return weekOf.minusDays(weekOf.getDayOfWeek().getValue() - 1);
    }

    static LocalDateTime toDateTime(DayOfWeek dow, LocalDate sameWeekWith, LocalTime time) {
        return LocalDateTime.of(dateOfSameWeek(dow, sameWeekWith), time);
    }
}
