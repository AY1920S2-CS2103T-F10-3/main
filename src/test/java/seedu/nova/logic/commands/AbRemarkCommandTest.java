package seedu.nova.logic.commands;

import static seedu.nova.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nova.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.nova.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;
import seedu.nova.model.UserPrefs;
import seedu.nova.model.addressbook.NovaAddressBook;
import seedu.nova.model.common.person.Person;
import seedu.nova.model.common.person.Remark;
import seedu.nova.testutil.PersonBuilder;

class AbRemarkCommandTest {
    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(REMARK_STUB).build();

        AbRemarkCommand abRemarkCommand = new AbRemarkCommand(INDEX_FIRST_PERSON,
                new Remark(editedPerson.getRemark().value));

        String expectedMessage = String.format(AbRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new NovaAddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(abRemarkCommand, model, expectedMessage, expectedModel);
    }

}
