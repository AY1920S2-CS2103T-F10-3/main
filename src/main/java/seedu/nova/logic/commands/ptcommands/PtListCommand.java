package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.PtWeek;

/**
 * Executes list command
 */
public class PtListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List tasks in the specified "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2";

    public static final String MESSAGE_NULLWEEK = "Week not added yet";

    private int weekNum;
    private String project;

    public PtListCommand(int weekNum, String project) {
        this.weekNum = weekNum;
        this.project = project.trim().toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ProgressTracker pt = model.getProgressTracker();
        PtWeek week = null;

        if (project.equals("ip")) {
            Ip ip = pt.getIp();
            week = ip.getWeekList().getWeek(weekNum);
        } else {
            //do nothing
        }

        if (week == null) {
            throw new CommandException(MESSAGE_NULLWEEK);
        }

        String result = week.getTaskList().listTasks();

        return new CommandResult(result, false, false);
    }
}
