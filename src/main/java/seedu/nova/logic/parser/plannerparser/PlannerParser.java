package seedu.nova.logic.parser.plannerparser;

import static seedu.nova.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.plannercommands.PlannerAddFlexibleTaskCommand;
import seedu.nova.logic.commands.plannercommands.PlannerAddRoutineTaskCommand;
import seedu.nova.logic.commands.plannercommands.PlannerListTaskCommand;
import seedu.nova.logic.commands.plannercommands.PlannerScheduleTaskCommand;
import seedu.nova.logic.parser.exceptions.ParseException;

/**
 * Parser for planner related commands
 */
public class PlannerParser {
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
        case PlannerAddFlexibleTaskCommand.COMMAND_WORD:
            return new AddFlexibleTaskCommandParser().parse(arguments);
        case PlannerAddRoutineTaskCommand.COMMAND_WORD:
            return new AddRoutineTaskCommandParser().parse(arguments);
        case PlannerListTaskCommand.COMMAND_WORD:
            return new ListTaskCommandParser().parse(arguments);
        case PlannerScheduleTaskCommand.COMMAND_WORD:
            return new ScheduleTaskCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
