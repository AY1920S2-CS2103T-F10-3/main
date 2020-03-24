package seedu.nova.logic.parser.ptparsers;

import static seedu.nova.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Pattern;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.ptcommands.PtListCommand;
import seedu.nova.logic.parser.exceptions.ParseException;

/**
 * Parses user input for progress tracker feature.
 */
public class ProgresstrackerParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case PtListCommand.COMMAND_WORD:
            return new PtListCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}


