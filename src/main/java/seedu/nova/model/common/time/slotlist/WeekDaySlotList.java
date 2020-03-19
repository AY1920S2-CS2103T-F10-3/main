package seedu.nova.model.common.time.slotlist;

import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.duration.TimedDuration;
import seedu.nova.model.common.time.duration.WeekDayDuration;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class WeekDaySlotList implements SlotList<WeekDayDuration> {
    TreeSet<WeekDayDuration> freeSlotSet;
    TreeMap<Integer, WeekDayDuration> freeSlotMap;

    public WeekDaySlotList() {
        this(new TreeSet<>(), new TreeMap<>());
        addDuration(new WeekDayDuration());
    }

    private WeekDaySlotList(TreeSet<WeekDayDuration> freeSlotSet, TreeMap<Integer, WeekDayDuration> freeSlotMap) {
        this.freeSlotSet = freeSlotSet;
        this.freeSlotMap = freeSlotMap;
    }

    @Override
    public List<WeekDayDuration> getSlotList() {
        return new ArrayList<>(this.freeSlotSet);
    }

    @Override
    public List<WeekDayDuration> getSlotList(Duration greaterThan) {
        WeekDayDuration d = WeekDayDuration.parseDuration(greaterThan);
        return new ArrayList<>(this.freeSlotSet.tailSet(d));
    }

    @Override
    public List<WeekDayDuration> getSlotContaining(TimedDuration d) {
        return this.freeSlotSet.stream().parallel().filter(x -> x.isSubsetOf(d)).collect(Collectors.toList());
    }

    @Override
    public void includeDuration(WeekDayDuration ed) {
        WeekDayDuration lastSlot = this.freeSlotMap.floorEntry(ed.getEndValue()).getValue();
        SortedMap<Integer, WeekDayDuration> tailMap = this.freeSlotMap.tailMap(ed.getStartValue());
        Map.Entry<Integer, WeekDayDuration> endSlot = this.freeSlotMap.higherEntry(ed.getEndValue());

        if(ed.isConnected(lastSlot)) {
            deleteDuration(lastSlot);
            ed = new WeekDayDuration(lastSlot.getStartDay(), lastSlot.getStartTime(), ed.getEndDay(),
                    ed.getEndTime());
        }
        for(Map.Entry<Integer, WeekDayDuration> e : tailMap.entrySet()) {
            if(e.equals(endSlot)) {
                break;
            } else {
                deleteDuration(e.getValue());
                ed = new WeekDayDuration(ed.getStartDay(), ed.getStartTime(), e.getValue().getEndDay(),
                        e.getValue().getEndTime());
            }
        }
        addDuration(ed);
    }

    @Override
    public void excludeDuration(WeekDayDuration ed) {
        WeekDayDuration lastSlot = this.freeSlotMap.floorEntry(ed.getEndValue()).getValue();
        SortedMap<Integer, WeekDayDuration> tailMap = this.freeSlotMap.tailMap(ed.getStartValue());
        Map.Entry<Integer, WeekDayDuration> endSlot = this.freeSlotMap.higherEntry(ed.getEndValue());

        if(ed.isOverlapping(lastSlot)) {
            deleteDuration(lastSlot);
            List<TimedDuration> comp = lastSlot.relativeComplementOf(ed);
            comp.forEach(this::addDuration);
        }
        for(Map.Entry<Integer, WeekDayDuration> e : tailMap.entrySet()) {
            if(e.equals(endSlot)) {
                break;
            } else {
                deleteDuration(lastSlot);
                List<TimedDuration> comp = lastSlot.relativeComplementOf(ed);
                comp.forEach(this::addDuration);
            }
        }
    }

    public List<WeekDayDuration> intersectWith(TimedDuration lst) {
        return this.freeSlotSet.stream().parallel().map(x -> (WeekDayDuration) x.intersectWith(lst)).filter(
                x -> !x.isZero()).collect(Collectors.toList());
    }

    private void addDuration(TimedDuration dd) {
        WeekDayDuration d = (WeekDayDuration) dd;
        this.freeSlotMap.put(d.getStartValue(), d);
        this.freeSlotSet.add(d);
    }

    private void deleteDuration(TimedDuration dd) {
        WeekDayDuration d = (WeekDayDuration) dd;
        this.freeSlotMap.remove(d.getStartValue());
        this.freeSlotSet.remove(d);
    }

    @Override
    public SlotList<WeekDayDuration> getCopy() {
        return null;
    }
}
