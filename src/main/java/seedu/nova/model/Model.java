package seedu.nova.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import org.json.simple.JSONObject;
import seedu.nova.commons.core.GuiSettings;
import seedu.nova.model.addressbook.AddressBookModel;
import seedu.nova.model.addressbook.ReadOnlyAddressBook;
import seedu.nova.model.common.person.Person;
import seedu.nova.model.plan.PlanModel;
import seedu.nova.model.schedule.ScheduleModel;
import seedu.nova.storage.JsonParsable;

/**
 * The API of the Model component.
 */
public interface Model extends JsonParsable {
    AddressBookModel getAddressBook();
    PlanModel getPlan();
    ScheduleModel getSchedule();

    void setAddressBook(AddressBookModel addressBook);
}
