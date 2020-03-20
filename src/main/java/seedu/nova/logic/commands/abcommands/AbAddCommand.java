package seedu.nova.logic.commands.abcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.addressbook.person.Person;


/**
 * Adds a person to the address book.
 */
public class AbAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_CATEGORY + "CATEGORY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_CATEGORY + "classmate ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AbAddCommand to add the specified {@code Person}
     */
    public AbAddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getAddressBookManager().hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.getAddressBookManager().addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AbAddCommand // instanceof handles nulls
                && toAdd.equals(((AbAddCommand) other).toAdd));
    }
}
