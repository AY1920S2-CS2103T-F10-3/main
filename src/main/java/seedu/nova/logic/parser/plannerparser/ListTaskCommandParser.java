package seedu.nova.logic.parser.plannerparser;

import seedu.nova.logic.commands.plannercommands.PlannerListTaskCommand;
import seedu.nova.logic.parser.Parser;
import seedu.nova.logic.parser.exceptions.ParseException;

/**
 * List command parser
 */
public class ListTaskCommandParser implements Parser<PlannerListTaskCommand> {

    @Override
    public PlannerListTaskCommand parse(String args) throws ParseException {
        return new PlannerListTaskCommand();
    }
}
