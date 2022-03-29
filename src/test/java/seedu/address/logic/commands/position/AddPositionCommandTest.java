package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.JR_SOFTWARE_ENGINEER;
import static seedu.address.testutil.TypicalPositions.SR_FE_SOFTWARE_ENGINEER;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.position.Position;
import seedu.address.testutil.PositionBuilder;

public class AddPositionCommandTest {

    @Test
    public void constructor_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPositionCommand(null));
    }

    @Test
    public void execute_positionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPositionAdded modelStub = new ModelStubAcceptingPositionAdded();
        Position validPosition = new PositionBuilder().build();

        CommandResult commandResult = new AddPositionCommand(validPosition).execute(modelStub);

        assertEquals(String.format(AddPositionCommand.MESSAGE_SUCCESS, validPosition),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPosition), modelStub.positionsAdded);
    }

    @Test
    public void execute_duplicatePosition_throwsCommandException() {
        Position validPosition = new PositionBuilder().build();
        AddPositionCommand addPositionCommand = new AddPositionCommand(validPosition);
        ModelStub modelStub = new ModelStubWithPosition(validPosition);

        assertThrows(CommandException.class,
                AddPositionCommand.MESSAGE_DUPLICATE_POSITION, () -> addPositionCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Position jrSwe = new PositionBuilder(JR_SOFTWARE_ENGINEER).build();
        Position srFeSwe = new PositionBuilder(SR_FE_SOFTWARE_ENGINEER).build();
        AddPositionCommand addJrSweCommand = new AddPositionCommand(jrSwe);
        AddPositionCommand addSrFeSweCommand = new AddPositionCommand(srFeSwe);

        // same object -> returns true
        assertTrue(addJrSweCommand.equals(addJrSweCommand));

        // same values -> returns true
        AddPositionCommand addJrSweCommandCopy = new AddPositionCommand(jrSwe);
        assertTrue(addJrSweCommandCopy.equals(addJrSweCommand));

        // different types -> returns false
        assertFalse(addJrSweCommand.equals(1));

        // null -> returns false
        assertFalse(addJrSweCommand.equals(null));

        // different applicant -> returns false
        assertFalse(addJrSweCommand.equals(addSrFeSweCommand));
    }

    /**
     * A Model stub that contains a single Position.
     */
    private class ModelStubWithPosition extends ModelStub {
        private final Position position;

        ModelStubWithPosition(Position position) {
            requireNonNull(position);
            this.position = position;
        }

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return this.position.isSamePosition(position);
        }
    }

    /**
     * A Model stub that always accept the position being added.
     */
    private class ModelStubAcceptingPositionAdded extends ModelStub {
        final ArrayList<Position> positionsAdded = new ArrayList<>();

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return positionsAdded.stream().anyMatch(position::isSamePosition);
        }

        @Override
        public void addPosition(Position position) {
            requireNonNull(position);
            positionsAdded.add(position);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}


