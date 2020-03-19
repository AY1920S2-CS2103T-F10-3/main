package seedu.nova.logic.commands.studyplan;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.logic.commands.schedule.AddEventCommand;
import seedu.nova.model.Model;
import seedu.nova.model.plan.StudyPlan;

import static java.util.Objects.requireNonNull;

public class GenerateEventCommand extends Command {
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";

    StudyPlan plan;

    public GenerateEventCommand(StudyPlan plan) {
        requireNonNull(plan);
        this.plan = plan;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.getSchedule().addEvent(toAdd)) {
            throw new CommandException("");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
