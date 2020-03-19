package seedu.nova.model.common.time.slotlist;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.TimedDuration;

import java.time.Duration;
import java.util.List;

public interface SlotList<T extends TimedDuration> extends Copyable<SlotList<T>> {
    List<T> getSlotList();
    List<T> getSlotList(Duration greaterThan);
    List<T> getSlotContaining(TimedDuration d);

    List<T> intersectWith(TimedDuration td);
    boolean isSupersetOf(TimedDuration td);

    void includeDuration(T ed);
    void excludeDuration(T ed);
}
