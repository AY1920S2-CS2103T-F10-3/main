package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;

import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * Deletes note in specified task
 */
public class PtDeleteNoteCommand extends Command {
    public static final String COMMAND_WORD = "deleteNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes note in specified task in the "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_TASK + "TASK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2"
            + PREFIX_TASK + "1";

    public static final String MESSAGE_NOWEEK = "No week beyond week 13";
    public static final String MESSAGE_FAILURE = "Command failed. Please check that there is a task "
            + " or note in the specified index";

    private int weekNum;
    private int taskNum;
    private String project;

    public PtDeleteNoteCommand(int weekNum, int taskNum, String project) {
        this.weekNum = weekNum;
        this.taskNum = taskNum;
        this.project = project.trim().toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isOver13 = weekNum > 13;

        if (isOver13) {
            throw new CommandException(MESSAGE_NOWEEK);
        } else {
            boolean isDeleteNoteSuccess = model.deletePtNote(this.project, weekNum, taskNum);

            if (!isDeleteNoteSuccess) {
                throw new CommandException(MESSAGE_FAILURE);
            }

            String projectName = this.project.toUpperCase();
            String result = "Deleted note to task " + taskNum + " in week " + weekNum + " of " + projectName;

            return new CommandResult(result, false, false);
        }
    }
}

