package seedu.nova.model.common.time;

import seedu.nova.model.common.Copyable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class FreeSlotList implements Copyable<FreeSlotList> {
    TreeSet<DateTimeDuration> freeSlotSet;
    TreeMap<LocalDateTime, DateTimeDuration> freeSlotMap;

    public FreeSlotList(DateTimeDuration init) {
        this(new TreeSet<>(), new TreeMap<>());
        addDuration(init);
    }

    private FreeSlotList(TreeSet<DateTimeDuration> freeSlotSet, TreeMap<LocalDateTime, DateTimeDuration> freeSlotMap) {
        this.freeSlotSet = freeSlotSet;
        this.freeSlotMap = freeSlotMap;
    }

    public SortedSet<DateTimeDuration> getFreeSlotList(Duration greaterThan) {
        DateTimeDuration d = new DateTimeDuration(greaterThan);
        return this.freeSlotSet.tailSet(d);
    }

    public void excludeDuration(DateTimeDuration ed) {
        DateTimeDuration lastFreeSlot = this.freeSlotMap.lowerEntry(ed.getStartDateTime()).getValue();
        List<DateTimeDuration> comp = lastFreeSlot.relativeComplementOf(ed);
        deleteDuration(lastFreeSlot);
        comp.forEach(this::addDuration);
    }

    public void includeDuration(DateTimeDuration ed) {
        DateTimeDuration lastFreeSlot = this.freeSlotMap.floorEntry(ed.getStartDateTime()).getValue();
        DateTimeDuration nextFreeSlot = this.freeSlotMap.ceilingEntry(ed.getEndDateTime()).getValue();

        if(ed.isImmidiatelyAfter(lastFreeSlot)) {
            deleteDuration(lastFreeSlot);
            ed = new DateTimeDuration(lastFreeSlot.getStartDateTime(), ed.getEndDateTime());
        }
        if(nextFreeSlot.isImmidiatelyAfter(ed)) {
            deleteDuration(nextFreeSlot);
            ed = new DateTimeDuration(ed.getStartDateTime(), nextFreeSlot.getEndDateTime());
        }
        addDuration(ed);
    }

    private void addDuration(DateTimeDuration d) {
        this.freeSlotMap.put(d.getStartDateTime(), d);
        this.freeSlotSet.add(d);
    }

    private void deleteDuration(DateTimeDuration d) {
        this.freeSlotMap.remove(d.getStartDateTime());
        this.freeSlotSet.remove(d);
    }

    public boolean contains(DateTimeDuration d) {
        return this.freeSlotSet.contains(d);
    }

    @Override
    public FreeSlotList getCopy() {
        return new FreeSlotList(new TreeSet<>(this.freeSlotSet), new TreeMap<>(this.freeSlotMap));
    }
}
