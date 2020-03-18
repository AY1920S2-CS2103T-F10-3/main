package seedu.nova.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.nova.commons.core.GuiSettings;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.logic.parser.exceptions.ParseException;
import seedu.nova.model.Model;
import seedu.nova.model.addressbook.AddressBookModel;
import seedu.nova.model.addressbook.ReadOnlyAddressBook;
import seedu.nova.model.common.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.nova.model.Model#getAddressBook()
     */
    Model getNova();
}
