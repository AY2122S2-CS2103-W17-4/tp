package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.applicant.DeleteApplicantCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteApplicantCommand}.
 */
public class DeleteApplicantCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(DeleteApplicantCommand.MESSAGE_DELETE_PERSON_SUCCESS, applicantToDelete) + "\n"
                        + String.format(DeleteApplicantCommand.MESSAGE_DELETE_INTERVIEWS, 0);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(applicantToDelete);

        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteApplicantCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(DeleteApplicantCommand.MESSAGE_DELETE_PERSON_SUCCESS, applicantToDelete) + "\n"
                        + String.format(DeleteApplicantCommand.MESSAGE_DELETE_INTERVIEWS, 0);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(applicantToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicantList().size());

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteApplicantCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteApplicantCommand deleteFirstCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);
        DeleteApplicantCommand deleteSecondCommand = new DeleteApplicantCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteApplicantCommand deleteFirstCommandCopy = new DeleteApplicantCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }
}
