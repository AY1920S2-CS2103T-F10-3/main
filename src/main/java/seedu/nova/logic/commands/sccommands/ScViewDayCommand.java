package seedu.nova.logic.commands.sccommands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * The type Sc view day command.
 */
public class ScViewDayCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "view";
    private static final String MESSAGE_DATE_OUT_OF_RANGE = "The date is not within the schedule";
    private static final String MESSAGE_NO_EVENT = "You have no event on that day";

    private final LocalDate date;

    /**
     * Instantiates a new Sc view day command.
     *
     * @param date the date
     */
    public ScViewDayCommand(LocalDate date) {

        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        if (!model.isWithinSem(date)) {
            throw new CommandException(MESSAGE_DATE_OUT_OF_RANGE);
        }

        String eventString = model.viewSchedule(date);

        if (eventString.equals("")) {
            return new CommandResult(MESSAGE_NO_EVENT);
        } else {
            return new CommandResult(eventString);
        }
    }

}
