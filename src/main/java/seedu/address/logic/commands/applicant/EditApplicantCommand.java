package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.DataTypeFlags.FLAG_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.HiredStatus;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing applicant in the application.
 */
public class EditApplicantCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -" + FLAG_APPLICANT
            + ": Edits the details of the applicant " + "identified "
            + "by the index number used in the displayed applicant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INTERVIEW_INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " -" + FLAG_APPLICANT + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited Applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in HireLah.";
    private static final String MESSAGE_DUPLICATE_EMAIL = "The email is already used by %1$s";
    private static final String MESSAGE_DUPLICATE_PHONE = "The phone number is already used by %1$s";

    private final Index index;
    private final EditApplicantDescriptor editApplicantDescriptor;

    /**
     * @param index of the applicant in the filtered applicant list to edit
     * @param editApplicantDescriptor details to edit the applicant with
     */
    public EditApplicantCommand(Index index, EditApplicantDescriptor editApplicantDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicantDescriptor);

        this.index = index;
        this.editApplicantDescriptor = new EditApplicantDescriptor(editApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());
        Applicant editedApplicant = createEditedApplicant(applicantToEdit, editApplicantDescriptor);

        if (!applicantToEdit.isSameApplicant(editedApplicant) && model.hasApplicant(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        boolean emailNotEdited = applicantToEdit.getEmail().equals(editedApplicant.getEmail());
        if (!emailNotEdited) {
            Applicant applicantWithEmail = model.getApplicantWithEmail(editedApplicant.getEmail());
            if (applicantWithEmail != null) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_EMAIL,
                        applicantWithEmail.getName().fullName));
            }
        }

        boolean phoneNotEdited = applicantToEdit.getPhone().equals(editedApplicant.getPhone());
        if (!phoneNotEdited) {
            Applicant applicantWithPhone = model.getApplicantWithPhone(editedApplicant.getPhone());
            if (applicantWithPhone != null) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_PHONE,
                        applicantWithPhone.getName().fullName));
            }
        }

        model.updateApplicant(applicantToEdit, editedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code editApplicantDescriptor}.
     */
    private static Applicant createEditedApplicant(Applicant applicantToEdit,
                                                   EditApplicantDescriptor editApplicantDescriptor) {
        assert applicantToEdit != null;

        Name updatedName = editApplicantDescriptor.getName().orElse(applicantToEdit.getName());
        Phone updatedPhone = editApplicantDescriptor.getPhone().orElse(applicantToEdit.getPhone());
        Email updatedEmail = editApplicantDescriptor.getEmail().orElse(applicantToEdit.getEmail());
        Age updatedAge = editApplicantDescriptor.getAge().orElse(applicantToEdit.getAge());
        Address updatedAddress = editApplicantDescriptor.getAddress().orElse(applicantToEdit.getAddress());
        Gender updatedGender = editApplicantDescriptor.getGender().orElse(applicantToEdit.getGender());
        HiredStatus updatedHiredStatus = editApplicantDescriptor.getStatus().orElse(applicantToEdit.getStatus());
        Set<Tag> updatedTags = editApplicantDescriptor.getTags().orElse(applicantToEdit.getTags());

        return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAge, updatedAddress,
                updatedGender, updatedHiredStatus, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditApplicantCommand)) {
            return false;
        }

        // state check
        EditApplicantCommand e = (EditApplicantCommand) other;
        return index.equals(e.index)
                && editApplicantDescriptor.equals(e.editApplicantDescriptor);
    }

    /**
     * Stores the details to edit the applicant with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class EditApplicantDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Age age;
        private Address address;
        private Gender gender;
        private HiredStatus hiredStatus;
        private Set<Tag> tags;

        public EditApplicantDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicantDescriptor(EditApplicantDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAge(toCopy.age);
            setAddress(toCopy.address);
            setGender(toCopy.gender);
            setStatus(toCopy.hiredStatus);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, age, gender, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setStatus(HiredStatus hiredStatus) {
            this.hiredStatus = hiredStatus;
        }

        public Optional<HiredStatus> getStatus() {
            return Optional.ofNullable(hiredStatus);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicantDescriptor)) {
                return false;
            }

            // state check
            EditApplicantDescriptor e = (EditApplicantDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }


    }
}
