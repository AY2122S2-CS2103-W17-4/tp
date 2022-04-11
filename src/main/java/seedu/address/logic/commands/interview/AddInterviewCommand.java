package seedu.address.logic.commands.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

public class AddInterviewCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " -i: Adds an interview to the HireLah application. "
            + "Parameters: APPLICANT_INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE "
            + PREFIX_POSITION + "POSITION_INDEX" + "\n"
            + "Example: " + COMMAND_WORD + " -i " + "1 "
            + PREFIX_DATE + "2022-01-01 12:00 "
            + PREFIX_POSITION + "1";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_POSITION_NO_OPENING = "The position the applicant is interviewing for "
            + "has no openings, so an interview cannot be scheduled.";
    public static final String MESSAGE_APPLICANT_HAS_JOB = "The applicant already has a job, so an interview cannot "
            + "be scheduled.";

    private final Index applicantIndex;
    private final LocalDateTime date;
    private final Index positionIndex;

    /**
     * Creates an AddApplicantCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Index applicantIndex, LocalDateTime date, Index positionIndex) {
        requireNonNull(applicantIndex);
        requireNonNull(date);
        requireNonNull(positionIndex);
        this.applicantIndex = applicantIndex;
        this.date = date;
        this.positionIndex = positionIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownApplicantList = model.getFilteredApplicantList();

        if (applicantIndex.getZeroBased() >= lastShownApplicantList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }
        Applicant applicantInInterview = lastShownApplicantList.get(applicantIndex.getZeroBased());
        if (applicantInInterview.isHired()) {
            throw new CommandException(MESSAGE_APPLICANT_HAS_JOB);
        }
        List<Position> lastShownPositionList = model.getFilteredPositionList();
        if (positionIndex.getZeroBased() >= lastShownPositionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
        }
        Position positionInInterview = lastShownPositionList.get(positionIndex.getZeroBased());
        if (model.isSameApplicantPosition(applicantInInterview, positionInInterview)) {
            throw new CommandException(String.format(Messages.MESSAGE_APPLICANT_SAME_POSITION,
                    applicantInInterview.getName().fullName, positionInInterview.getPositionName().positionName));
        }
        if (!positionInInterview.canScheduleInterview()) {
            throw new CommandException(MESSAGE_POSITION_NO_OPENING);
        }
        Interview interviewToAdd = new Interview(applicantInInterview, date, positionInInterview);
        if (model.hasConflictingInterview(interviewToAdd)) {
            throw new CommandException(Messages.MESSAGE_CONFLICTING_INTERVIEW);
        }
        model.addInterview(interviewToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, interviewToAdd), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.INTERVIEW;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && applicantIndex.equals(((AddInterviewCommand) other).applicantIndex)
                && date.equals(((AddInterviewCommand) other).date)
                && positionIndex.equals(((AddInterviewCommand) other).positionIndex));
    }
}
