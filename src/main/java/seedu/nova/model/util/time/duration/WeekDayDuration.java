package seedu.nova.model.util.time.duration;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import seedu.nova.model.util.time.TimeUtil;

/**
 * Combination of Day of week, time and duration
 */
public class WeekDayDuration implements TimedDuration {
    private DayOfWeek startDow;
    private LocalTime startTime;
    private DayOfWeek endDow;
    private LocalTime endTime;
    private Duration duration;

    public WeekDayDuration() {
        this.startDow = DayOfWeek.MONDAY;
        this.startTime = LocalTime.of(0, 0, 0);
        this.endDow = DayOfWeek.SUNDAY;
        this.endTime = LocalTime.of(23, 59, 59);
        this.duration = Duration.between(TimeUtil.toDateTime(this.startDow, LocalDate.now(), this.startTime),
                TimeUtil.toDateTime(this.endDow, LocalDate.now(), this.endTime));
    }

    public WeekDayDuration(DayOfWeek startDow, LocalTime startTime, LocalTime endTime) {
        this.startDow = startDow;
        this.startTime = startTime;
        this.endTime = endTime;
        if (startTime.compareTo(endTime) < 0) {
            this.duration = Duration.between(startTime, endTime);
        } else {
            this.duration = Duration.ofDays(1).minus(Duration.between(startTime, endTime));
        }
    }

    public WeekDayDuration(DayOfWeek startDow, LocalTime startTime, DayOfWeek endDow, LocalTime endTime) {
        this.startDow = startDow;
        this.startTime = startTime;
        this.endDow = endDow;
        this.endTime = endTime;
    }

    private WeekDayDuration(DayOfWeek startDow, LocalTime startTime, DayOfWeek endDow, LocalTime endTime,
                            Duration duration) {
        this.startDow = startDow;
        this.startTime = startTime;
        this.endDow = endDow;
        this.endTime = endTime;
        this.duration = duration;
    }

    public static WeekDayDuration parseDuration(Duration duration) {
        return new WeekDayDuration(null, null, null, null, duration);
    }

    public boolean isZero() {
        return this.duration.isZero();
    }

    public int getStartValue() {
        return 86400 * startDow.getValue() + (int) (startTime.toNanoOfDay() / 1000000000);
    }

    public int getEndValue() {
        return 86400 * endDow.getValue() + (int) (endTime.toNanoOfDay() / 1000000000);
    }

    /**
     * get Week of the date, in DateTimeDuration
     *
     * @param sameWeek date
     * @return DateTimeDuration
     */
    public DateTimeDuration toDateTimeDuration(LocalDate sameWeek) {
        LocalDateTime startDate = LocalDateTime.of(TimeUtil.dateOfSameWeek(this.startDow, sameWeek), this.startTime);
        LocalDateTime endDate = LocalDateTime.of(TimeUtil.dateOfSameWeek(this.endDow, sameWeek), this.endTime);
        if (getStartValue() > getEndValue()) {
            endDate = endDate.plusDays(7);
        }
        return new DateTimeDuration(startDate, endDate);
    }

    @Override
    public DayOfWeek getStartDay() {
        return this.startDow;
    }

    @Override
    public LocalTime getStartTime() {
        return this.startTime;
    }

    @Override
    public DayOfWeek getEndDay() {
        return this.endDow;
    }

    @Override
    public LocalTime getEndTime() {
        return this.endTime;
    }

    @Override
    public Duration getDuration() {
        return this.duration;
    }

    @Override
    public boolean isConnected(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.isConnected(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now())
                    .isConnected(((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public boolean isOverlapping(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.isOverlapping(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now())
                    .isOverlapping(((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public boolean isSubsetOf(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.isSubsetOf(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now())
                    .isSubsetOf(((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public List<TimedDuration> relativeComplementOf(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.relativeComplementOf(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now())
                    .relativeComplementOf(((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public TimedDuration intersectWith(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.intersectWith(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now())
                    .intersectWith(((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public int compareTo(TimedDuration another) {
        return getDuration().compareTo(another.getDuration());
    }

    @Override
    public WeekDayDuration getCopy() {
        return new WeekDayDuration(this.startDow, this.startTime, this.endDow, this.endTime, this.duration);
    }
}
