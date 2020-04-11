package seedu.nova.logic.commands.sccommands.eventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;

import seedu.nova.commons.core.index.Index;
import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;

import seedu.nova.model.Model;
import seedu.nova.model.schedule.event.DateNotFoundException;
import seedu.nova.model.schedule.event.EventNotFoundException;

/**
 * Deletes an Event from the Schedule.
 */
public class EventDeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event from the schedule. \n"
            + "Parameters: "
            + PREFIX_TIME + "[YYYY-MM-DD] "
            + PREFIX_INDEX + "[index] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TIME + "2020-03-20 "
            + PREFIX_INDEX + "2 ";

    public static final String MESSAGE_SUCCESS = "Event has been deleted: \n%1$s";
    private LocalDate date;
    private Index index;

    public EventDeleteCommand(LocalDate date, Index index) {
        requireNonNull(date);

        this.date = date;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            int i = index.getZeroBased();
            String response = model.deleteEvent(date, i);
            return new CommandResult(String.format(MESSAGE_SUCCESS, response));

        } catch (DateNotFoundException e) {
            throw new CommandException("Invalid date - you have no events on that date.");

        } catch (EventNotFoundException e) {
            throw new CommandException("Invalid index.");
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDeleteCommand // instanceof handles nulls
                && index.equals(((EventDeleteCommand) other).index)
                && date.equals(((EventDeleteCommand) other).date)); // state check
    }

}