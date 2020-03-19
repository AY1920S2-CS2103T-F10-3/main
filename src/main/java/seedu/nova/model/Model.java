package seedu.nova.model;

import seedu.nova.model.addressbook.AddressBookModel;
import seedu.nova.model.plan.Plan;
import seedu.nova.storage.JsonParsable;

/**
 * The API of the Model component.
 */
public interface Model extends JsonParsable {
    AddressBookModel getAddressBook();
    ScheduleModel getSchedule();

    void setAddressBook(AddressBookModel addressBook);
}
