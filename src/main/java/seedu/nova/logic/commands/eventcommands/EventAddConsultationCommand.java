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
 * adds a Consultation into the Schedule.
 */
public class EventAddConsultationCommand extends Command {
    public static final String COMMAND_WORD = "consultation";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation to the schedule. \n"
            + "Parameters: "
            + PREFIX_DESC + "[description] "
            + PREFIX_VENUE + "[venue] "
            + PREFIX_TIME + "[YYYY-MM-DD] [Start time (HH:MM)] [End time (HH:MM)] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESC + "clarify UML "
            + PREFIX_VENUE + "COM1 "
            + PREFIX_TIME + "2020-03-20 14:00 16:00 ";

    public static final String MESSAGE_SUCCESS = "New consultation has been added: \n%1$s";
    private Event toAdd;

    public EventAddConsultationCommand(Event consultation) {
        requireNonNull(consultation);
        this.toAdd = consultation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addEvent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
