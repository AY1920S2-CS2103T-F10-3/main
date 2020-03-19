package seedu.nova.model.common.time.duration;

import seedu.nova.model.common.time.TimeUtil;

import java.time.*;
import java.util.List;

public class WeekDayDuration implements TimedDuration {
    DayOfWeek startDow;
    LocalTime startTime;
    DayOfWeek endDow;
    LocalTime endTime;
    Duration duration;

    public boolean isZero() {
        return this.duration.isZero();
    }

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
                            Duration duration)
    {
        this.startDow = startDow;
        this.startTime = startTime;
        this.endDow = endDow;
        this.endTime = endTime;
        this.duration = duration;
    }

    public int getStartValue() {
        return 86400 * startDow.getValue() + (int) (startTime.toNanoOfDay() / 1000000000);
    }

    public int getEndValue() {
        return 86400 * endDow.getValue() + (int) (endTime.toNanoOfDay() / 1000000000);
    }

    DateTimeDuration toDateTimeDuration(LocalDate sameWeek) {
        LocalDate startDate =
                sameWeek.plusDays(this.startDow.getValue() - sameWeek.getDayOfWeek().getValue());
        LocalDate endDate = sameWeek.plusDays(this.endDow.getValue() - sameWeek.getDayOfWeek().getValue());
        if (this.startDow.getValue() > this.endDow.getValue()) {
            endDate = endDate.plusDays(7);
        }
        return new DateTimeDuration(LocalDateTime.of(startDate, this.startTime), LocalDateTime.of(endDate,
                this.endTime));
    }

    public static WeekDayDuration parseDuration(Duration duration) {
        return new WeekDayDuration(null, null, null, null, duration);
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
    public LocalDate getEndDate() {
        return null;
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
            return toDateTimeDuration(LocalDate.now()).isConnected(
                    ((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public boolean isOverlapping(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.isOverlapping(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now()).isOverlapping(
                    ((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public boolean isSubsetOf(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.isSubsetOf(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now()).isSubsetOf(
                    ((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public List<TimedDuration> relativeComplementOf(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.relativeComplementOf(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now()).relativeComplementOf(
                    ((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
        }
    }

    @Override
    public TimedDuration intersectWith(TimedDuration another) {
        if (another instanceof DateTimeDuration) {
            return another.intersectWith(toDateTimeDuration(((DateTimeDuration) another).getStartDate()));
        } else {
            return toDateTimeDuration(LocalDate.now()).intersectWith(
                    ((WeekDayDuration) another).toDateTimeDuration(LocalDate.now()));
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
