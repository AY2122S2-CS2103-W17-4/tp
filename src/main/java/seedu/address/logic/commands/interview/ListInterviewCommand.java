package seedu.address.logic.commands.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.commons.core.DataType;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.InterviewApplicantPredicate;
import seedu.address.model.interview.InterviewDatePredicate;
import seedu.address.model.interview.InterviewPositionPredicate;

/**
 * Lists interviews in HireLah to the user.
 */
public class ListInterviewCommand extends ListCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -i: List interviews with optional parameters."
            + "\nOptional parameters: "
            + PREFIX_FILTER_TYPE + "FILTER_TYPE "
            + PREFIX_FILTER_ARGUMENT + "FILTER_TYPE "
            + "\nExample: " + COMMAND_WORD + " -i "
            + PREFIX_FILTER_TYPE + "name "
            + PREFIX_FILTER_ARGUMENT + "John Doe ";

    public static final String MESSAGE_SUCCESS = "Listed %1$d interviews";

    private FilterType filterType;
    private FilterArgument filterArgument;

    /**
     * Creates an ListInterviewCommand to display all {@code Interview}
     */
    public ListInterviewCommand() {
        filterType = null;
        filterArgument = null;
    }

    /**
     * Creates an ListInterviewCommand to filter and display {@code Interview}
     */
    public ListInterviewCommand(FilterType filterType, FilterArgument filterArgument) {
        this.filterType = filterType;
        this.filterArgument = filterArgument;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (filterType != null && filterArgument != null) {
            if (filterType.type.equals("appl")) {
                String[] applicantNameKeywords = filterArgument.toString().split("\\s+");
                Predicate<Interview> predicateApplicantName =
                        new InterviewApplicantPredicate(Arrays.asList(applicantNameKeywords));
                model.updateFilteredInterviewList(predicateApplicantName);
            } else if (filterType.type.equals("pos")) {
                String[] positionNameKeywords = filterArgument.toString().split("\\s+");
                Predicate<Interview> predicatePositionName =
                        new InterviewPositionPredicate(Arrays.asList(positionNameKeywords));
                model.updateFilteredInterviewList(predicatePositionName);
            } else if (filterType.type.equals("date")) {
                Predicate<Interview> predicateDate =
                        new InterviewDatePredicate(LocalDate.parse(filterArgument.toString()));
                model.updateFilteredInterviewList(predicateDate);
            }
        } else {
            model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredInterviewList().size()), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.INTERVIEW;
    }
}
