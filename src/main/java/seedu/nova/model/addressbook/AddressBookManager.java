package seedu.nova.model.addressbook;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.nova.model.addressbook.person.Person;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.nova.commons.util.CollectionUtil.requireAllNonNull;

public class AddressBookManager implements AddressBookModel {
    private final AddressBook addressBook;
    private final FilteredList<Person> filteredPersons;

    public AddressBookManager(ReadOnlyAddressBook addressBook) {
        this.addressBook = new AddressBook(addressBook);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AddressBookManager)) {
            return false;
        }

        // state check
        AddressBookManager other = (AddressBookManager) obj;
        return addressBook.equals(other.addressBook)
                && filteredPersons.equals(other.filteredPersons);
    }

}
