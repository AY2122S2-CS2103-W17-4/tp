package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;

public class DeletePositionCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -p : Deletes the position identified by the index number used in the displayed position list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " -p 1";

    public static final String MESSAGE_DELETE_POSITION_SUCCESS = "Deleted Position: %1$s";

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
        model.deletePosition(positionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_POSITION_SUCCESS, positionToDelete),
                getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }
}
