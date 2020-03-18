package seedu.NOVA.logic.commands.abcommands;

import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class AbHelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
