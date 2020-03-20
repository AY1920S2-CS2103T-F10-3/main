package seedu.nova.model;

import static seedu.nova.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import org.json.simple.JSONObject;
import seedu.nova.commons.core.LogsCenter;
import seedu.nova.model.addressbook.AddressBookModel;
import seedu.nova.model.addressbook.NovaAddressBook;
import seedu.nova.model.plan.Plan;
import seedu.nova.model.tools.NovaScheduler;
import seedu.nova.model.tools.Scheduler;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    AddressBookModel abModel;
    Scheduler scheduler;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(AddressBookModel abModel, Scheduler scheduler) {
        requireAllNonNull(abModel, scheduler);

        logger.fine("Initializing with address book: " + abModel);

        this.abModel = abModel;
        this.scheduler = scheduler;
    }

    public ModelManager() {
        this(new NovaAddressBook(), new NovaScheduler());
    }

    public AddressBookModel getAddressBook() {
        return this.abModel;
    }

    public void setAddressBook(AddressBookModel abModel) {
        this.abModel = abModel;
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public static ModelManager parseFromJson(JSONObject jo) {
        AddressBookModel abModel = (AddressBookModel) jo.get("abModel");
        Plan pModel = (Plan) jo.get("pModel");
        Scheduler scheduler = (Scheduler) jo.get("scheduler");
        return new ModelManager(abModel, scheduler);
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("abModel", this.abModel.toJsonObject());
        jo.put("scheduler", this.scheduler.toJsonObject());
        return jo;
    }

}
