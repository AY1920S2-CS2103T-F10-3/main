package seedu.NOVA.logic.parser.abparsers;

import static seedu.NOVA.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.NOVA.commons.core.index.Index;
import seedu.NOVA.logic.commands.abcommands.AbDeleteCommand;
import seedu.NOVA.logic.parser.Parser;
import seedu.NOVA.logic.parser.ParserUtil;
import seedu.NOVA.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AbDeleteCommand object
 */
public class AbDeleteCommandParser implements Parser<AbDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AbDeleteCommand
     * and returns a AbDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AbDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AbDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AbDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
