package seedu.nova.model.common.time.duration;

import seedu.nova.model.common.Copyable;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public interface TimedDuration extends Comparable<TimedDuration>, Copyable<TimedDuration> {
    boolean isZero();
    DayOfWeek getStartDay();
    DayOfWeek getEndDay();
    LocalTime getStartTime();
    LocalTime getEndTime();
    Duration getDuration();

    boolean isConnected(TimedDuration another); // isOverlapping && !isSubsetOf
    boolean isOverlapping(TimedDuration another);
    boolean isSubsetOf(TimedDuration another);
    List<TimedDuration> relativeComplementOf(TimedDuration another);
    TimedDuration intersectWith(TimedDuration another);
}
