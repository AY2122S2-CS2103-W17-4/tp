package seedu.address.logic.parser.applicants;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.commands.applicant.ListApplicantCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListApplicantCommandParser implements Parser<ListApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListApplicantCommand
     * and returns an ListApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListApplicantCommand parse(String args) throws ParseException {
        if (args.equals("")) {
            return new ListApplicantCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILTER_TYPE, PREFIX_FILTER_ARGUMENT);

        if (argMultimap.getValue(PREFIX_FILTER_TYPE).isPresent()
                && argMultimap.getValue(PREFIX_FILTER_ARGUMENT).isPresent()) {

            FilterType filterType =
                    ParserUtil.parseFilterType(DataType.APPLICANT, argMultimap.getValue(PREFIX_FILTER_TYPE).get());
            FilterArgument filterArgument =
                    ParserUtil.parseFilterArgument(argMultimap.getValue(PREFIX_FILTER_ARGUMENT).get());

            return new ListApplicantCommand(filterType, filterArgument);
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListApplicantCommand.MESSAGE_USAGE));
        }
    }
}
