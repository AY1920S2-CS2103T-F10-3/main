package seedu.nova.logic.parser.scparser;

import static seedu.nova.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.sccommands.ScViewDayCommand;
import seedu.nova.logic.parser.exceptions.ParseException;

/**
 * The type Schedule parser.
 */
public class ScheduleParser {

    /**
     * Parses command.
     *
     * @param commandWord the command word
     * @param arguments   the arguments
     * @return the command
     * @throws ParseException the parse exception
     */
    public Command parseCommand(String commandWord, String arguments) throws ParseException {

        switch (commandWord) {
        case ScViewDayCommand.COMMAND_WORD:
            return new ScViewDayCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
