package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * Deletes a position identified using it's displayed index from the address book,
 * and the interviews associated with the position as well.
 */
public class DeletePositionCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -p : Deletes the position identified by the index number used in the displayed position list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " -p 1";

    public static final String MESSAGE_DELETE_POSITION_SUCCESS = "Deleted Position: %1$s";

    public static final String MESSAGE_DELETE_INTERVIEWS = "Deleted %d related interview(s)";

    private final Index targetIndex;

    public DeletePositionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Position> lastShownList = model.getFilteredPositionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
        }

        Position positionToDelete = lastShownList.get(targetIndex.getZeroBased());
        int deleteInterviewCount = deletePositionsInterview(model, positionToDelete);

        model.deletePosition(positionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_POSITION_SUCCESS, positionToDelete) + "\n"
                    + String.format(MESSAGE_DELETE_INTERVIEWS, deleteInterviewCount),
                getCommandDataType());
    }

    /**
     * Deletes interviews which are for the position to delete.
     *
     * @return Number of interviews deleted.
     */
    private int deletePositionsInterview(Model model, Position positionToDelete) {
        ObservableList<Interview> interviewList = model.getAddressBook().getInterviewList();
        ArrayList<Interview> interviewsToDelete = new ArrayList<>();

        for (Interview i : interviewList) {
            if (i.isInterviewForPosition(positionToDelete)) {
                interviewsToDelete.add(i);
            }
        }

        for (Interview i : interviewsToDelete) {
            model.deleteInterview(i);
        }

        return interviewsToDelete.size();
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }
}
