package seedu.nova.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;

/**
 * adds a Lesson into the Schedule.
 */
public class EventAddLessonCommand extends Command {
    public static final String COMMAND_WORD = "lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the schedule. \n"
            + "Parameters: "
            + PREFIX_DESC + "[description] "
            + PREFIX_VENUE + "[venue] "
            + PREFIX_TIME + "[day] [Start time (HH:MM)] [End time (HH:MM)] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESC + "CS2103T Tutorial "
            + PREFIX_VENUE + "COM1 B1-03 "
            + PREFIX_TIME + "Friday 10:00 11:00 ";

    public static final String MESSAGE_SUCCESS = "New lesson has been added: \n%1$s";
    private Event toAdd;

    public EventAddLessonCommand(Event lesson) {
        requireNonNull(lesson);
        this.toAdd = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addLesson((Lesson) toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


}
