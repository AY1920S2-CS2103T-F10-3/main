package seedu.nova.logic.commands;

import static seedu.nova.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.nova.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nova.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.abcommands.AbAddCommand;
import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;
import seedu.nova.model.schedule.Scheduler;
import seedu.nova.model.userpref.UserPrefs;
import seedu.nova.model.addressbook.person.Person;
import seedu.nova.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AbAddCommand}.
 */
public class AbAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Scheduler(LocalDate.of(2020, 1, 13),
                LocalDate.of(2020, 5, 3)));
    }

    @Test
    public void execute_newPerson_success() {

        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBookManager().getAddressBook(), new UserPrefs(),
                new Scheduler(LocalDate.of(2020, 1, 13), LocalDate.of(2020, 5, 3)));
        expectedModel.getAddressBookManager().addPerson(validPerson);

        assertCommandSuccess(new AbAddCommand(validPerson), model,
                String.format(AbAddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {

        Person personInList = model.getAddressBookManager().getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AbAddCommand(personInList), model, AbAddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
