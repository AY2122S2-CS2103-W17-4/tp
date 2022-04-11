package seedu.address.logic.commands.interview;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

public class DeleteInterviewCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -i : Deletes the interview identified by the index number used in the displayed interview list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " -i 1";

    public static final String MESSAGE_DELETE_INTERVIEW_SUCCESS = "Deleted Interview: %1$s";

    private final Index targetIndex;

    public DeleteInterviewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }
        Interview interviewToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInterview(interviewToDelete);

        if (interviewToDelete.isPassedStatus()) {
            Position oldPosition = interviewToDelete.getPosition();
            Position newPosition = interviewToDelete.getPosition().rejectOffer();
            model.updatePosition(oldPosition, newPosition);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_INTERVIEW_SUCCESS, interviewToDelete),
                getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.INTERVIEW;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInterviewCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInterviewCommand) other).targetIndex)); // state check
    }
}
