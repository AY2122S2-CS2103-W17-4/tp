package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * Deletes an applicant identified using it's displayed index from the application,
 * and the interviews associated with the applicant as well.
 */
public class DeleteApplicantCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -a : Deletes the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " -a 1";

    public static final String MESSAGE_DELETE_APPLICANT_SUCCESS = "Deleted Applicant: %1$s";

    public static final String MESSAGE_DELETE_INTERVIEWS = "Deleted %d related interview(s)";

    private final Index targetIndex;

    private final Logger logger = LogsCenter.getLogger(DeleteApplicantCommand.class);

    public DeleteApplicantCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToDelete = lastShownList.get(targetIndex.getZeroBased());

        ArrayList<Interview> interviewsToDelete = model.getApplicantsInterviews(applicantToDelete);
        for (Interview i : interviewsToDelete) {
            model.deleteInterview(i);

            if (i.isPassedStatus()) {
                Position oldPosition = i.getPosition();
                Position newPosition = i.getPosition().rejectOffer();
                model.updatePosition(oldPosition, newPosition);
            }

            logger.log(Level.INFO, String.format("Deleted interview: %1$s", i));
        }

        model.deleteApplicant(applicantToDelete);
        return new CommandResult(
                String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete) + "\n"
                        + String.format(MESSAGE_DELETE_INTERVIEWS, interviewsToDelete.size()),
                getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicantCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicantCommand) other).targetIndex)); // state check
    }
}
