package seedu.nova.logic.commands.studyplan;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

import static java.util.Objects.requireNonNull;

public class GeneratePlanCommand extends Command {
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";

    String newPlanName;

    public GeneratePlanCommand(String planName) {
        requireNonNull(planName);
        this.newPlanName = planName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.getScheduler().createAndAddPlan(newPlanName)) {
            throw new CommandException("");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, newPlanName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GeneratePlanCommand // instanceof handles nulls
                && newPlanName.equals(((GeneratePlanCommand) other).newPlanName));
    }
}
