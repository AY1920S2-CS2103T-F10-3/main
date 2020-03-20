package seedu.nova.model.util.time.slotlist;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import seedu.nova.model.util.Copyable;
import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.duration.TimedDuration;

/**
 * Container for DateTimeDuration
 */
public class DateTimeSlotList implements SlotList<DateTimeDuration>, Copyable<DateTimeSlotList> {
    private TreeSet<DateTimeDuration> freeSlotSet;
    private TreeMap<LocalDateTime, DateTimeDuration> freeSlotMap;

    public DateTimeSlotList(DateTimeDuration init) {
        this(new TreeSet<>(), new TreeMap<>());
        addDuration(init);
    }

    public DateTimeSlotList(List<DateTimeDuration> init) {
        this(new TreeSet<>(), new TreeMap<>());
        init.forEach(this::addDuration);
    }

    private DateTimeSlotList(TreeSet<DateTimeDuration> freeSlotSet,
                             TreeMap<LocalDateTime, DateTimeDuration> freeSlotMap) {
        this.freeSlotSet = freeSlotSet;
        this.freeSlotMap = freeSlotMap;
    }

    @Override
    public List<DateTimeDuration> getSlotList() {
        return new ArrayList<>(this.freeSlotSet);
    }

    @Override
    public List<DateTimeDuration> getSlotList(Duration greaterThan) {
        DateTimeDuration d = DateTimeDuration.parseDuration(greaterThan);
        return new ArrayList<>(this.freeSlotSet.tailSet(d));
    }

    public List<DateTimeDuration> getSlotContaining(TimedDuration d) {
        return this.freeSlotSet.stream().parallel().filter(x -> x.isSubsetOf(d)).collect(Collectors.toList());
    }

    public boolean isSupersetOf(TimedDuration d) {
        return this.freeSlotSet.stream().parallel().anyMatch(x -> x.isSubsetOf(d));
    }

    public List<DateTimeDuration> getSlotOn(DayOfWeek day) {
        return this.freeSlotSet.stream().parallel().filter(x -> x.getStartDate().getDayOfWeek().equals(day)).collect(
                Collectors.toList());
    }

    /**
     * delete the duration ed from the list of durations.
     * @param ed timed duration to delete
     */
    public void excludeDuration(DateTimeDuration ed) {
        DateTimeDuration lastFreeSlot = this.freeSlotMap.floorEntry(ed.getStartDateTime()).getValue();
        if (lastFreeSlot.isOverlapping(ed)) {
            List<TimedDuration> comp = lastFreeSlot.relativeComplementOf(ed);
            deleteDuration(lastFreeSlot);
            comp.forEach(this::addDuration);
        }
    }

    /**
     * exclude all the durations in another
     * @param another date time slot list
     * @return compliment of another
     */
    public DateTimeSlotList relativeComplimentOf(DateTimeSlotList another) {
        DateTimeSlotList ans = getCopy();
        another.freeSlotSet.forEach(ans::excludeDuration);
        return ans;
    }

    /**
     * intersection with another timed duration
     * @param lst another timed duration
     * @return list of intersection duration
     */
    public List<DateTimeDuration> intersectWith(TimedDuration lst) {
        return this.freeSlotSet.stream()
                .parallel()
                .map(x -> (DateTimeDuration) x.intersectWith(lst))
                .filter(x -> !x.isZero()).collect(Collectors.toList());
    }

    /**
     * add ed back to list
     * @param ed datetimeduration
     */
    public void includeDuration(DateTimeDuration ed) {
        DateTimeDuration lastFreeSlot = this.freeSlotMap.floorEntry(ed.getStartDateTime()).getValue();
        DateTimeDuration nextFreeSlot = this.freeSlotMap.ceilingEntry(ed.getEndDateTime()).getValue();

        if (ed.isConnected(lastFreeSlot)) {
            deleteDuration(lastFreeSlot);
            ed = new DateTimeDuration(lastFreeSlot.getStartDateTime(), ed.getEndDateTime());
        }
        if (nextFreeSlot.isConnected(ed)) {
            deleteDuration(nextFreeSlot);
            ed = new DateTimeDuration(ed.getStartDateTime(), nextFreeSlot.getEndDateTime());
        }
        addDuration(ed);
    }

    private void addDuration(TimedDuration dd) {
        DateTimeDuration d = (DateTimeDuration) dd;
        this.freeSlotMap.put(d.getStartDateTime(), d);
        this.freeSlotSet.add(d);
    }

    private void deleteDuration(TimedDuration dd) {
        DateTimeDuration d = (DateTimeDuration) dd;
        this.freeSlotSet.remove(d);
    }

    public int size() {
        return this.freeSlotSet.size();
    }

    public boolean contains(DateTimeDuration d) {
        return this.freeSlotSet.contains(d);
    }

    @Override
    public DateTimeSlotList getCopy() {
        return new DateTimeSlotList(new TreeSet<>(this.freeSlotSet), new TreeMap<>(this.freeSlotMap));
    }
}
