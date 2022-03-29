package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Applicant> filteredApplicants;
    private final FilteredList<Interview> filteredInterviews;
    private final FilteredList<Position> filteredPositions;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);
        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<>(this.addressBook.getApplicantList());
        filteredInterviews = new FilteredList<>(this.addressBook.getInterviewList());
        filteredPositions = new FilteredList<>(this.addressBook.getPositionList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Applicant applicant) {
        requireNonNull(applicant);
        return addressBook.hasApplicant(applicant);
    }

    @Override
    public void deletePerson(Applicant target) {
        addressBook.removeApplicant(target);
    }

    @Override
    public void addPerson(Applicant applicant) {
        addressBook.addApplicant(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        addressBook.setApplicant(target, editedApplicant);
    }

    @Override
    public boolean hasInterview(Interview interview) {
        requireNonNull(interview);
        return addressBook.hasInterview(interview);
    }

    @Override
    public boolean hasConflictingInterview(Interview interview) {
        requireAllNonNull(interview);
        return addressBook.hasConflictingInterview(interview);
    }

    @Override
    public void deleteInterview(Interview target) {
        addressBook.removeInterview(target);
    }

    @Override
    public void addInterview(Interview interview) {
        addressBook.addInterview(interview);
        updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
    }

    @Override
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);

        addressBook.setInterview(target, editedInterview);
    }

    @Override
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return addressBook.hasPosition(position);
    }

    @Override
    public void addPosition(Position position) {
        addressBook.addPosition(position);
        updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
    }

    @Override
    public void deletePosition(Position target) {
        addressBook.removePosition(target);
    }

    @Override
    public void setPosition(Position target, Position editedPosition) {
        requireAllNonNull(target, editedPosition);

        addressBook.setPosition(target, editedPosition);
    }

    //=========== Filtered Applicant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Applicant} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return filteredApplicants;
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
    }

    //=========== Filtered Interview List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Interview} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Interview> getFilteredInterviewList() {
        return filteredInterviews;
    }

    @Override
    public void updateFilteredInterviewList(Predicate<Interview> predicate) {
        requireNonNull(predicate);
        filteredInterviews.setPredicate(predicate);
    }

    //=========== Filtered Position List Accessors =============================================================
    @Override
    public ObservableList<Position> getFilteredPositionList() {
        return filteredPositions;
    }

    @Override
    public void updateFilteredPositionList(Predicate<Position> predicate) {
        requireNonNull(predicate);
        filteredPositions.setPredicate(predicate);
    }

    //=========== Utility methods =============================================================
    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredApplicants.equals(other.filteredApplicants);
    }
}
