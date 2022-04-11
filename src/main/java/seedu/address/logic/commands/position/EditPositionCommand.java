package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.DataTypeFlags.FLAG_POSITION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUM_OPENINGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;

/**
 * Edits the details of an existing position in the position.
 */
public class EditPositionCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -" + FLAG_POSITION
            + ": Edits the details of the position identified "
            + "by the index number used in the displayed position list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_POSITION + "POSITION_NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_NUM_OPENINGS + "NUM_OPENINGS] "
            + "[" + PREFIX_REQUIREMENT + "REQUIREMENT]...\n"
            + "Example: " + COMMAND_WORD + " -" + FLAG_POSITION + " 1 "
            + PREFIX_NUM_OPENINGS + "2 "
            + PREFIX_REQUIREMENT + "First class honours ";

    public static final String MESSAGE_NOT_EDITED = "At least one field in Position to edit must be provided.";
    public static final String MESSAGE_NOT_VALID_OPENINGS = "New number of openings cannot be less than the current "
            + "number of outstanding offers. Please reject some of the existing candidates before changing the "
            + "number of openings.";
    public static final String MESSAGE_DUPLICATE_POSITION = "This position already exists in HireLah.";
    public static final String MESSAGE_EDIT_POSITION_SUCCESS = "Edited Position: %1$s";

    private static final Logger logger = LogsCenter.getLogger(EditPositionCommand.class);

    private final Index index;
    private final EditPositionDescriptor editPositionDescriptor;

    /**
     * @param index                  of the position in the filtered position list to edit
     * @param editPositionDescriptor details to edit the position with
     */
    public EditPositionCommand(Index index, EditPositionDescriptor editPositionDescriptor) {
        requireNonNull(index);
        requireNonNull(editPositionDescriptor);

        this.index = index;
        this.editPositionDescriptor = new EditPositionDescriptor(editPositionDescriptor);
    }

    /**
     * Creates and returns a {@code Position} with the details of {@code positionToEdit}
     * edited with {@code editPositionDescriptor}.
     */
    private static Position createEditedPosition(Position positionToEdit, EditPositionDescriptor
            editPositionDescriptor) throws CommandException {
        assert positionToEdit != null;

        PositionName updatedPositionName = editPositionDescriptor.getPositionName()
                .orElse(positionToEdit.getPositionName());
        Description updatedDescription = editPositionDescriptor.getDescription()
                .orElse(positionToEdit.getDescription());
        PositionOpenings updatedOpenings = editPositionDescriptor.getPositionOpenings()
                .orElse(positionToEdit.getPositionOpenings());
        Set<Requirement> updatedRequirements = editPositionDescriptor.getRequirements()
                .orElse(positionToEdit.getRequirements());

        Position updatedPosition = new Position(updatedPositionName, updatedDescription,
                updatedOpenings, positionToEdit.getPositionOffers(), updatedRequirements);
        if (!updatedPosition.isValidOpeningsToOffers()) {
            logger.log(Level.WARNING, "Editing position to have less openings than offers is not allowed");
            throw new CommandException(MESSAGE_NOT_VALID_OPENINGS);
        }
        return updatedPosition;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Position> lastShownList = model.getFilteredPositionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
        }

        Position positionToEdit = lastShownList.get(index.getZeroBased());
        Position editedPosition = createEditedPosition(positionToEdit, editPositionDescriptor);

        if (!positionToEdit.isSamePosition(editedPosition) && model.hasPosition(editedPosition)) {
            throw new CommandException(MESSAGE_DUPLICATE_POSITION);
        }

        model.updatePosition(positionToEdit, editedPosition);
        model.updateFilteredPositionList(Model.PREDICATE_SHOW_ALL_POSITIONS);

        return new CommandResult(String.format(MESSAGE_EDIT_POSITION_SUCCESS, editedPosition), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof EditPositionCommand)) {
            return false;
        }

        // state check
        EditPositionCommand e = (EditPositionCommand) other;
        return index.equals(e.index) && editPositionDescriptor.equals(e.editPositionDescriptor);
    }

    /**
     * Store the details to edit the position with. Each non-empty field value will replace the
     * corresponding field value of the position.
     */
    public static class EditPositionDescriptor {
        private PositionName positionName;
        private Description description;
        private PositionOpenings positionOpenings;
        private Set<Requirement> requirements;

        public EditPositionDescriptor() {
        }

        /**
         * Constructs an EditPositionDescriptor.
         *
         * @param toCopy descriptor object to copy
         */
        public EditPositionDescriptor(EditPositionDescriptor toCopy) {
            setPositionName(toCopy.positionName);
            setDescription(toCopy.description);
            setPositionOpenings(toCopy.positionOpenings);
            setRequirements(toCopy.requirements);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(positionName, description, positionOpenings, requirements);
        }

        public Optional<PositionName> getPositionName() {
            return Optional.ofNullable(positionName);
        }

        public void setPositionName(PositionName positionName) {
            this.positionName = positionName;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<PositionOpenings> getPositionOpenings() {
            return Optional.ofNullable(positionOpenings);
        }

        public void setPositionOpenings(PositionOpenings positionOpenings) {
            this.positionOpenings = positionOpenings;
        }

        /**
         * Returns an unmodifiable requirement set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code requirements} is null.
         */
        public Optional<Set<Requirement>> getRequirements() {
            return (requirements != null) ? Optional.of(Collections.unmodifiableSet(requirements)) : Optional.empty();
        }

        /**
         * Sets {@code requirements} to this object's {@code requirements}.
         * A defensive copy of {@code requirements} is used internally.
         */
        public void setRequirements(Set<Requirement> requirements) {
            this.requirements = (requirements != null) ? new HashSet<>(requirements) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPositionDescriptor)) {
                return false;
            }

            // state check
            EditPositionDescriptor e = (EditPositionDescriptor) other;

            return getPositionName().equals(e.getPositionName())
                    && getDescription().equals(e.getDescription())
                    && getPositionOpenings().equals(e.getPositionOpenings())
                    && getRequirements().equals(e.getRequirements());
        }
    }
}
