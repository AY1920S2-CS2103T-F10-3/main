package seedu.nova.model;

import static seedu.nova.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import org.json.simple.JSONObject;
import seedu.nova.commons.core.LogsCenter;
import seedu.nova.model.addressbook.AddressBookModel;
import seedu.nova.model.addressbook.NovaAddressBook;
import seedu.nova.model.plan.PlanModel;
import seedu.nova.model.plan.StudyPlan;
import seedu.nova.model.schedule.Schedule;
import seedu.nova.model.schedule.ScheduleModel;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    public AddressBookModel abModel;
    public PlanModel pModel;
    public ScheduleModel sModel;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(AddressBookModel abModel, PlanModel pModel, ScheduleModel sModel) {
        requireAllNonNull(abModel, pModel, sModel);

        logger.fine("Initializing with address book: " + abModel);

        this.abModel = abModel;
        this.pModel = pModel;
        this.sModel = sModel;
    }

    public ModelManager() {
        this(new NovaAddressBook(), new StudyPlan(), new Schedule());
    }

    public void setAddressBook(AddressBookModel abModel) {
        this.abModel = abModel;
    }

    public AddressBookModel getAddressBook() {
        return this.abModel;
    }

    public PlanModel getPlan() {
        return this.pModel;
    }

    public ScheduleModel getSchedule() {
        return this.sModel;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("abModel", this.abModel.toJsonObject());
        jo.put("pModel", this.pModel.toJsonObject());
        jo.put("sModel", this.sModel.toJsonObject());
        return jo;
    }

    public static ModelManager parseFromJson(JSONObject jo) {
        AddressBookModel abModel = (AddressBookModel) jo.get("abModel");
        PlanModel pModel = (PlanModel) jo.get("pModel");
        ScheduleModel sModel = (ScheduleModel) jo.get("sModel");
        return new ModelManager(abModel, pModel, sModel);
    }

}
