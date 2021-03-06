package seedu.nova.logic.commands.ptcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nova.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.nova.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nova.testutil.Assert.assertThrows;
import static seedu.nova.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.nova.testutil.TypicalPtTasks.getTypicalProgressTracker;

import org.junit.jupiter.api.Test;

import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;
import seedu.nova.model.Nova;
import seedu.nova.model.UserPrefs;
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.Project;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.Tp;
import seedu.nova.testutil.PtTaskBuilder;

public class PtEditCommandTest {

    private final Nova nova = new Nova();
    private final ProgressTracker pt = getTypicalProgressTracker();
    private final VersionedAddressBook ab = new VersionedAddressBook(getTypicalAddressBook());

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PtEditCommand(1, null, "taskDesc", 1));
    }

    @Test
    public void constructor_nullTaskDesc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PtEditCommand(1, "ip", null, 1));
    }

    @Test
    public void execute_validProjectAndWeekIndex_success() {
        nova.setProgressTrackerNova(pt);
        nova.setAddressBookNova(ab);
        Model model = new ModelManager(nova, new UserPrefs());

        PtEditCommand ptEditCommand = new PtEditCommand(1, "ip", "new taskDesc", 1);

        String expectedMessage = String.format(PtEditCommand.MESSAGE_SUCCESS, 1, 1, "IP");

        ModelManager expectedModel = new ModelManager(model.getNova(), new UserPrefs());

        assertCommandSuccess(ptEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidWeekIndex_throwsCommandException() {
        nova.setProgressTrackerNova(pt);
        nova.setAddressBookNova(ab);
        Model model = new ModelManager(nova, new UserPrefs());

        PtEditCommand ptEditCommand = new PtEditCommand(14, "ip", "new taskDesc", 1);

        assertCommandFailure(ptEditCommand, model, PtEditCommand.MESSAGE_NOWEEK);
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        nova.setProgressTrackerNova(pt);
        nova.setAddressBookNova(ab);
        Model model = new ModelManager(nova, new UserPrefs());

        PtEditCommand ptEditCommand = new PtEditCommand(1, "ip", "new taskDesc", 2);

        assertCommandFailure(ptEditCommand, model, PtEditCommand.MESSAGE_FAILURE);
    }

    @Test
    public void equals() {
        Project ipProject = new Ip();
        Project tpProject = new Tp();
        PtTask ipProjectTask = new PtTaskBuilder().withProject(ipProject).build();
        PtTask tpProjectTask = new PtTaskBuilder().withProject(tpProject).build();

        PtEditCommand editIpProjectTaskCommand = new PtEditCommand(1,
                ipProjectTask.getProjectName(), "new taskDesc", 1);
        PtEditCommand editTpProjectTaskCommand = new PtEditCommand(1,
                tpProjectTask.getProjectName(), "new taskDesc", 1);

        // same object -> returns true
        assertTrue(editIpProjectTaskCommand.equals(editIpProjectTaskCommand));

        // same values -> returns true
        PtEditCommand editIpProjectTaskCommandCopy = new PtEditCommand(1, "ip",
                "new taskDesc", 1);
        assertTrue(editIpProjectTaskCommand.equals(editIpProjectTaskCommandCopy));

        //different types -> returns false
        assertFalse(editIpProjectTaskCommand.equals(1));

        // null -> returns false
        assertFalse(editIpProjectTaskCommand.equals(null));

        // different person -> returns false
        assertFalse(editIpProjectTaskCommand.equals(editTpProjectTaskCommand));
    }

}
