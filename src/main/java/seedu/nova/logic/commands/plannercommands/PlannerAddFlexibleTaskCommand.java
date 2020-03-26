package seedu.nova.logic.commands.plannercommands;

import static java.util.Objects.requireNonNull;

import java.time.Duration;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * Add flexible task command
 */
public class PlannerAddFlexibleTaskCommand extends Command {
    public static final String COMMAND_WORD = "flexible";
    private static final String MESSAGE_TASK_ADY_EXIST = "The task already exist in your study plan";
    private static final String MESSAGE_TASK_ADD_SUCCESS = "Task added!";
    private static final String MESSAGE_TASK_ADD_FAILED = "Task NOT added :(";

    private String name;
    private Duration max;
    private Duration min;
    private Duration total;

    public PlannerAddFlexibleTaskCommand(String name, Duration min, Duration max, Duration total) {
        this.name = name;
        this.max = max;
        this.min = min;
        this.total = total;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.searchTask(name) != null) {
            return new CommandResult(MESSAGE_TASK_ADY_EXIST);
        }
        if (model.addFlexibleTask(name, min, max, total)) {
            return new CommandResult(MESSAGE_TASK_ADD_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_TASK_ADD_FAILED);
        }
    }
}
