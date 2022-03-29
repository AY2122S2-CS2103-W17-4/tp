package seedu.address.logic.commands.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.applicant.EditApplicantCommand;
import seedu.address.logic.commands.applicant.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.EditApplicantDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditApplicantCommand.
 */
public class EditApplicantCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new PersonBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editedApplicant).build();
        EditApplicantCommand editCommand = new EditApplicantCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredPersonList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredApplicantList().size());
        Applicant lastApplicant = model.getFilteredApplicantList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastApplicant);
        Applicant editedApplicant = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditApplicantCommand.EditApplicantDescriptor descriptor =
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditApplicantCommand editCommand = new EditApplicantCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastApplicant, editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditApplicantCommand editCommand = new EditApplicantCommand(INDEX_FIRST_PERSON, new EditApplicantDescriptor());
        Applicant editedApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        Applicant editedApplicant = new PersonBuilder(applicantInFilteredList).withName(VALID_NAME_BOB).build();
        EditApplicantCommand editCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant).build();
        EditApplicantCommand editCommand = new EditApplicantCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditApplicantCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit applicant in filtered list into a duplicate in address book
        Applicant applicantInList = model.getAddressBook().getApplicantList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditApplicantCommand editCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditApplicantDescriptorBuilder(applicantInList).build());

        assertCommandFailure(editCommand, model, EditApplicantCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        EditApplicantCommand.EditApplicantDescriptor descriptor =
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditApplicantCommand editCommand = new EditApplicantCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicantList().size());

        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(outOfBoundIndex,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editApplicantCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditApplicantCommand standardCommand = new EditApplicantCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditApplicantDescriptor copyDescriptor = new EditApplicantDescriptor(DESC_AMY);
        EditApplicantCommand commandWithSameValues = new EditApplicantCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditApplicantCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditApplicantCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
