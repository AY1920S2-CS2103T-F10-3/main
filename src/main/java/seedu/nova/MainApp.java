package seedu.nova;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.nova.commons.core.Config;
import seedu.nova.commons.core.LogsCenter;
import seedu.nova.commons.core.Version;
import seedu.nova.commons.exceptions.DataConversionException;
import seedu.nova.commons.util.ConfigUtil;
import seedu.nova.commons.util.StringUtil;
import seedu.nova.logic.Logic;
import seedu.nova.logic.LogicManager;
import seedu.nova.model.addressbook.NovaAddressBook;
import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;
import seedu.nova.model.addressbook.ReadOnlyAddressBook;
import seedu.nova.model.common.util.SampleDataUtil;
import seedu.nova.storage.JsonStorage;
import seedu.nova.ui.Ui;
import seedu.nova.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Model model;
    protected JsonStorage<Model> storage;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        //model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(JsonStorage<Model> storage) {
        try {
            return storage.readFromJson();
        } catch (Exception e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            return new ModelManager();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
    }
}
