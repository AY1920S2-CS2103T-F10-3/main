package seedu.nova.model.plan;

import org.json.simple.JSONObject;
import seedu.nova.model.common.time.DateTimeDuration;
import seedu.nova.model.common.time.SlotList;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class StudyPlan implements PlanModel {
    DateTimeDuration weekDuration;
    SlotList freeSlotList;
    List<Task> studyTask;

    public StudyPlan() {
        this.weekDuration = DateTimeDuration.parseWeekFromDate(LocalDate.now());
        this.freeSlotList = new SlotList(this.weekDuration);
        this.studyTask = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.studyTask;
    }

    public void excludeDuration(DateTimeDuration d) {
        d.setStartDate(this.weekDuration.getStartDate());
        for (int i = 0; i < 7; i++) {
            this.freeSlotList.excludeDuration(d.plusDays(i));
        }
    }

    public void createAndAddTask(String taskName, Duration duration, DateTimeDuration possibleRange) {
        addTask(createTask(taskName, duration, possibleRange));
    }

    private Task createTask(String taskName, Duration duration, DateTimeDuration possibleRange) {
        SortedSet<DateTimeDuration> set = this.freeSlotList.getFreeSlotList(duration);
        List<DateTimeDuration> lst =
                set.stream().filter(
                        x -> x.intersectionWith(possibleRange).getDuration().compareTo(duration) >= 0).collect(
                        Collectors.toList());
        return new Task(taskName, duration, new SlotList(lst));
    }

    private void addTask(Task task) {
        this.studyTask.add(task);
    }

    @Override
    public JSONObject toJsonObject() {
        return new JSONObject();
    }
}
