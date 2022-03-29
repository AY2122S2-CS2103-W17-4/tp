package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Interview> PREDICATE_SHOW_ALL_INTERVIEWS = unused -> true;
    Predicate<Position> PREDICATE_SHOW_ALL_POSITIONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the address book.
     */
    boolean hasPerson(Applicant applicant);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the address book.
     */
    void deletePerson(Applicant target);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the address book.
     */
    void addPerson(Applicant applicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in the address book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant
     * in the address book.
     */
    void setPerson(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);

    /**
     * Returns true if an interview already exists in the address book.
     */
    boolean hasInterview(Interview interview);

    /**
     * Returns true if an applicant already has an interview for that timeslot.
     */
    boolean hasConflictingInterview(Interview interview);

    /**
     * Deletes the given interview.
     * The interview must exist in the address book.
     */
    void deleteInterview(Interview target);

    /**
     * Adds the given interview.
     * {@code interview} must not already exist in HireLah.
     */
    void addInterview(Interview interview);

    /**
     * Replaces the given interview {@code target} with {@code editedInterview}.
     * {@code target} must exist in the address book.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview
     * in the address book.
     */
    void setInterview(Interview target, Interview editedInterview);

    /** Returns an unmodifiable view of the filtered interview list */
    ObservableList<Interview> getFilteredInterviewList();

    /**
     * Updates the filter of the filtered interview list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInterviewList(Predicate<Interview> predicate);

    /**
     * Updates the filter of the filtered position list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPositionList(Predicate<Position> predicate);

    /**
     * Deletes the given position.
     * The position must exist in the address book.
     */
    void deletePosition(Position target);

    /**
     * Returns true if a position already exists in the address book.
     */
    boolean hasPosition(Position position);

    /**
     * Adds the given position.
     * {@code position} must not already exist in the address book.
     */
    void addPosition(Position position);

    /**
     * Replaces the given position {@code target} with {@code editedPosition}.
     * {@code target} must exist in the address book.
     * The position identity of {@code editedPosition} must not be the same as another existing position
     * in the address book.
     */
    void setPosition(Position target, Position editedPosition);

    /** Returns an unmodifiable view of the filtered position list */
    ObservableList<Position> getFilteredPositionList();
}
