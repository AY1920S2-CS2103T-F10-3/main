package seedu.nova.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;

import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.event.Event;

/**
 * adds a Meeting into the Schedule.
 */
public class EventAddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the schedule. \n"
            + "Parameters: "
            + PREFIX_DESC + "[description] "
            + PREFIX_VENUE + "[venue] "
            + PREFIX_TIME + "[YYYY-MM-DD] [Start time (HH:MM)] [End time (HH:MM)] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESC + "CS2103T GUI refactoring "
            + PREFIX_VENUE + "COM1 Basement "
            + PREFIX_TIME + "2020-03-10 14:00 16:00 ";

    public static final String MESSAGE_SUCCESS = "New meeting has been added: \n%1$s";
    private Event toAdd;

    public EventAddMeetingCommand(Event meeting) {
        requireNonNull(meeting);
        this.toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.getSchedulerManager().addEvent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
