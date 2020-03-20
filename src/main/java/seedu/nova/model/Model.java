package seedu.nova.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import seedu.nova.commons.core.GuiSettings;
import seedu.nova.model.addressbook.AddressBookManager;
import seedu.nova.model.addressbook.AddressBookModel;
import seedu.nova.model.common.time.duration.WeekDayDuration;
import seedu.nova.model.event.Event;
import seedu.nova.model.addressbook.person.Person;
import seedu.nova.model.event.EventDetails;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.schedule.SchedulerModel;
import seedu.nova.model.schedule.timeunit.Day;
import seedu.nova.model.schedule.timeunit.Week;
import seedu.nova.model.userpref.ReadOnlyUserPrefs;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' nova book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' nova book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    AddressBookModel getAddressBookManager();

    SchedulerModel getSchedulerManager();

    Mode getMode();

    ProgressTracker getProgressTracker();


}
