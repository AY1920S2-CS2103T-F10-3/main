package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.logging.Logger;

import seedu.nova.commons.core.LogsCenter;
import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * Edits task to specified week
 */
public class PtEditNoteCommand extends Command {
    public static final String COMMAND_WORD = "editNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits note in specified task in the "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_TASK + "TASK "
            + PREFIX_DESC + "NOTE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2 "
            + PREFIX_TASK + "1 "
            + PREFIX_DESC + "take note to do by 2359 Friday";

    public static final String MESSAGE_NOWEEK = "No week beyond week 13";

    public static final String MESSAGE_FAILURE = "Command failed. Please check that there is a task "
            + "or note in the specified index";

    public static final String MESSAGE_SUCCESS = "Edited note to task %d in week %d of %s";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private int weekNum;
    private int taskNum;
    private String project;
    private String note;

    /**
     * Creates PtEditNoteCommand object
     * @param weekNum week of PtTask with note to be edited
     * @param taskNum task number of PtTask with note to be edited
     * @param project project of PtTask with note to be edited
     * @param note note to be edited
     */
    public PtEditNoteCommand(int weekNum, int taskNum, String project, String note) {
        requireNonNull(project);
        requireNonNull(note);

        this.weekNum = weekNum;
        this.taskNum = taskNum;
        this.project = project.trim().toLowerCase();
        this.note = note;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public String getProject() {
        return project;
    }

    public String getNote() {
        return note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("executing editNote command for: project " + project + ", week " + weekNum + ", task " + taskNum);

        requireNonNull(model);
        boolean isOver13 = weekNum > 13;

        //if week is over 13, throw no week error message
        if (isOver13) {
            throw new CommandException(MESSAGE_NOWEEK);
        } else {
            boolean isEditNoteSuccess = model.editPtNote(this.project, weekNum, taskNum, note);

            if (!isEditNoteSuccess) {
                throw new CommandException(MESSAGE_FAILURE);
            }

            String projectName = this.project.toUpperCase();
            String result = String.format(MESSAGE_SUCCESS, taskNum, weekNum, projectName);

            return new CommandResult(result, false, false);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PtEditNoteCommand)) {
            return false;
        } else {
            boolean isSameProject = ((PtEditNoteCommand) obj).getProject().equals(this.getProject());
            boolean isSameWeek = ((PtEditNoteCommand) obj).getWeekNum() == this.getWeekNum();
            boolean isSameTaskNum = ((PtEditNoteCommand) obj).getTaskNum() == (this.getTaskNum());
            boolean isSameNote = ((PtEditNoteCommand) obj).getNote().equals(this.getNote());

            return isSameProject && isSameWeek && isSameNote && isSameTaskNum;
        }
    }
}
