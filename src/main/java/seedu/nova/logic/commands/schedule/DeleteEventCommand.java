package seedu.nova.logic.commands.schedule;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.common.event.Event;

import static java.util.Objects.requireNonNull;

public class DeleteEventCommand extends Command {
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Event toDelete;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public DeleteEventCommand(Event event) {
        requireNonNull(event);
        toDelete = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.getScheduler().deleteEvent(toDelete)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventCommand // instanceof handles nulls
                && toDelete.equals(((DeleteEventCommand) other).toDelete));
    }
}
