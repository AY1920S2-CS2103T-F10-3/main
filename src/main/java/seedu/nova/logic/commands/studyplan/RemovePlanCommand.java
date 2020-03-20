package seedu.nova.logic.commands.studyplan;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.plan.Plan;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class RemovePlanCommand extends Command {
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";

    String planName;

    public RemovePlanCommand(String planName) {
        requireNonNull(planName);
        this.planName = planName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> planList = model.getScheduler().getUserDefinedPlanList();
        Optional<Plan> opPlan = planList.stream().filter(x -> x.getName().equals(this.planName)).findFirst();
        if(opPlan.isEmpty()) {
            throw new CommandException("");
        } else {
            planList.remove(opPlan.get());
            return new CommandResult(String.format(MESSAGE_SUCCESS, planName));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemovePlanCommand // instanceof handles nulls
                && planName.equals(((RemovePlanCommand) other).planName));
    }
}
