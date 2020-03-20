package seedu.nova.model;

import static java.util.Objects.requireNonNull;
import static seedu.nova.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Logger;

import seedu.nova.commons.core.GuiSettings;
import seedu.nova.commons.core.LogsCenter;
import seedu.nova.logic.parser.ModeEnum;
import seedu.nova.model.addressbook.AddressBook;
import seedu.nova.model.addressbook.AddressBookManager;
import seedu.nova.model.addressbook.AddressBookModel;
import seedu.nova.model.addressbook.ReadOnlyAddressBook;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.schedule.ReadOnlyScheduler;
import seedu.nova.model.schedule.Scheduler;
import seedu.nova.model.schedule.SchedulerManager;
import seedu.nova.model.schedule.SchedulerModel;
import seedu.nova.model.userpref.ReadOnlyUserPrefs;
import seedu.nova.model.userpref.UserPrefs;

/**
 * Represents the in-memory model of the data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBookManager addressBookManager;
    private final UserPrefs userPrefs;
    private final SchedulerManager schedulerManager;
    private final ProgressTracker progressTracker;
    private Mode mode;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyScheduler scheduler) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBookManager = new AddressBookManager(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.progressTracker = new ProgressTracker();
        this.schedulerManager = new SchedulerManager(scheduler);
        this.mode = new Mode(ModeEnum.ADDRESSBOOK);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new Scheduler(LocalDate.of(2020, 1, 13), LocalDate.of(2020, 5, 3)));
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ===========================================================================

    public AddressBookModel getAddressBookManager() {
        return this.addressBookManager;
    }

    //=========== Mode ==================================================================================
    @Override
    public Mode getMode() {
        return mode;
    }

    //=========== ProgressTracker ==================================================================================
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    //=========== Scheduler Methods =============================================================

    public SchedulerModel getSchedulerManager() {
        return this.schedulerManager;
    }

}
