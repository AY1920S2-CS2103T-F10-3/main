package seedu.nova.logic.commands.plannercommands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * Schedule Task command
 */
public class PlannerScheduleTaskCommand extends Command {
    public static final String COMMAND_WORD = "schedule";
    private static final String MESSAGE_EVENT_GEN_SUCCESS = "New event added on ";
    private static final String MESSAGE_EVENT_GEN_FAILED = "Failed to add new event";
    private static final String TASK_NOT_FOUND = "Cannot find task ";

    private String taskName;
    private LocalDate date;

    public PlannerScheduleTaskCommand(String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
    }

    private String successMsg() {
        return MESSAGE_EVENT_GEN_SUCCESS + date.toString();
    }

    private String taskNotFoundMsg() {
        return TASK_NOT_FOUND + taskName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.searchTask(taskName) == null) {
            throw new CommandException(taskNotFoundMsg());
        }
        try {
            model.generateTaskEvent(model.searchTask(taskName), date);
        } catch (Exception e) {
            return new CommandResult(MESSAGE_EVENT_GEN_FAILED);
        }
        return new CommandResult(MESSAGE_EVENT_GEN_SUCCESS);
    }
}
