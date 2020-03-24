package seedu.nova.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.nova.commons.core.GuiSettings;
import seedu.nova.commons.core.LogsCenter;
import seedu.nova.logic.Logic;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.logic.parser.ModeEnum;
import seedu.nova.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;
    private HelpBox helpBox;

    //private HelpWindow helpWindow;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private VBox resultDisplayPlaceholder;

    @FXML
    private VBox helpHolder;


    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.helpBox = new HelpBox();

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        scrollPane.vvalueProperty().bind(resultDisplayPlaceholder.heightProperty());

        //setAccelerators();

        //helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        helpHolder.getChildren().add(helpBox.getRoot());

        helpBox.setHelp(logic.getModel().getMode().getModeEnum().name());

        //add schedule for the day in homepage
        try {
            //get localdate fof today
            String today = LocalDate.now().toString();

            //set mode to schedule first
            logic.getModel().getMode().setModeEnum(ModeEnum.SCHEDULE);

            CommandResult commandResult = logic.execute("view t\\" + today);
            ResultDisplay r = new ResultDisplay();
            r.setFeedbackToUser(commandResult.getFeedbackToUser());

            resultDisplayPlaceholder.getChildren().add(r.getRoot());

            //set mode back to home
            logic.getModel().getMode().setModeEnum(ModeEnum.HOME);
        } catch (CommandException | ParseException e) {
            String commandText = "view t\\" + LocalDate.now().toString();
            logger.info("Invalid command: " + commandText);

            ResultDisplay r = new ResultDisplay();
            r.setFeedbackToUser(e.getMessage());
            resultDisplayPlaceholder.getChildren().add(r.getRoot());
        }
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }


    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());

            ResultDisplay r = new ResultDisplay();
            r.setFeedbackToUser(commandResult.getFeedbackToUser());

            resultDisplayPlaceholder.getChildren().add(r.getRoot());

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isChangeMode()) {
                helpBox.setHelp(logic.getModel().getMode().getModeEnum().name());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);

            ResultDisplay r = new ResultDisplay();
            r.setFeedbackToUser(e.getMessage());
            resultDisplayPlaceholder.getChildren().add(r.getRoot());
            throw e;
        }
    }
}
