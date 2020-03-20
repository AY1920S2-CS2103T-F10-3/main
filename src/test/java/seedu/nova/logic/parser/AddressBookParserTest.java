package seedu.nova.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.nova.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nova.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.nova.testutil.Assert.assertThrows;
import static seedu.nova.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

//import seedu.nova.logic.commands.abcommands.AbAddCommand;

import seedu.nova.logic.commands.abcommands.AbClearCommand;
import seedu.nova.logic.commands.abcommands.AbDeleteCommand;
//import seedu.nova.logic.commands.abcommands.AbEditCommand;
//import seedu.nova.logic.commands.abcommands.AbEditCommand.EditPersonDescriptor;
//import seedu.nova.logic.commands.abcommands.AbFindCommand;
import seedu.nova.logic.commands.abcommands.AbHelpCommand;
import seedu.nova.logic.commands.abcommands.AbListCommand;
//import seedu.nova.logic.commands.commoncommands.ExitCommand;
import seedu.nova.logic.parser.abparsers.AddressBookParser;
import seedu.nova.logic.parser.exceptions.ParseException;
//import seedu.nova.model.addressbook.person.NameContainsKeywordsPredicate;
//import seedu.nova.model.addressbook.person.Person;
//import seedu.nova.testutil.EditPersonDescriptorBuilder;
//import seedu.nova.testutil.PersonBuilder;
//import seedu.nova.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    /*@Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AbAddCommand command = (AbAddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AbAddCommand(person), command);
    }*/

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(AbClearCommand.COMMAND_WORD, "") instanceof AbClearCommand);
        assertTrue(parser.parseCommand(AbClearCommand.COMMAND_WORD, " 3") instanceof AbClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        AbDeleteCommand command = (AbDeleteCommand) parser.parseCommand(
                AbDeleteCommand.COMMAND_WORD, " "
                        + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new AbDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    /*@Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        AbEditCommand command = (AbEditCommand) parser.parseCommand(AbEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new AbEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }*/

    /*@Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }*/

    /*@Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        AbFindCommand command = (AbFindCommand) parser.parseCommand(
                AbFindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new AbFindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    } */

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(AbHelpCommand.COMMAND_WORD, "") instanceof AbHelpCommand);
        assertTrue(parser.parseCommand(AbHelpCommand.COMMAND_WORD, " 3") instanceof AbHelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(AbListCommand.COMMAND_WORD, "") instanceof AbListCommand);
    }

    /*@Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AbHelpCommand.MESSAGE_USAGE), () -> parser.parseCommand("", ""));
    }*/

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand", ""));
    }
}
