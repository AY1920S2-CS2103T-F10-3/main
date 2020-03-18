package seedu.nova.logic.parser;

import static seedu.nova.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.abcommands.AbHelpCommand;
import seedu.nova.logic.commands.commoncommands.ExitCommand;
import seedu.nova.logic.commands.commoncommands.NavCommand;
import seedu.nova.logic.parser.abparsers.AddressBookParser;
import seedu.nova.logic.parser.eventparsers.EventParser;
import seedu.nova.logic.parser.exceptions.ParseException;
import seedu.nova.logic.parser.ptparsers.ProgresstrackerParser;
import seedu.nova.model.Model;

/**
 * Class for logic parser
 */
public class LogicParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private final NavCommandParser navCommandParser;
    private final AddressBookParser addressBookParser;
    private final EventParser eventParser;
    private final ScheduleParser scheduleParser;
    private final ProgresstrackerParser progresstrackerParser;
    private Model model;

    public LogicParser(Model model) {
        //Create parser objects for each page/ feature
        navCommandParser = new NavCommandParser();
        addressBookParser = new AddressBookParser();
        eventParser = new EventParser();
        scheduleParser = new ScheduleParser();
        progresstrackerParser = new ProgresstrackerParser();
        this.model = model;
    }

    /**
     * Parses user input into command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        ModeEnum mode = model.getMode().getModeEnum();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            switch (mode) {

            case ADDRESSBOOK:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbHelpCommand.MESSAGE_USAGE));

            case EVENT:
                //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                // EventHelpCommand.MESSAGE_USAGE));

            case SCHEDULER:
                //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                // SchedulerHelpCommand.MESSAGE_USAGE));

            case PROGRESSTRACKER:
                //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,ptbHelpCommand.MESSAGE_USAGE));

            default:
                throw new ParseException("No such mode");
            }
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        if (commandWord.equals(NavCommand.COMMAND_WORD)) {
            return navCommandParser.parse(arguments);
        } else if (commandWord.equals(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        } else {
            //check mode
            switch (mode) {

            case ADDRESSBOOK:
                return addressBookParser.parseCommand(userInput);

            case EVENT:
                return eventParser.parseCommand(userInput);

            case SCHEDULER:
                return scheduleParser.parseCommand(userInput);

            case PROGRESSTRACKER:
                return progresstrackerParser.parseCommand(userInput);

            default:
                throw new ParseException("No such mode");
            }
        }
    }
}
