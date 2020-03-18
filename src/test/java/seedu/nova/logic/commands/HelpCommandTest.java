package seedu.nova.logic.commands;

import static seedu.nova.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nova.logic.commands.addressbook.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.addressbook.HelpCommand;
import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
