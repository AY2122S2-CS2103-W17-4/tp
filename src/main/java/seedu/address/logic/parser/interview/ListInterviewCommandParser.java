package seedu.address.logic.parser.interview;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.commands.interview.ListInterviewCommand;
import seedu.address.logic.commands.position.ListPositionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListInterviewCommandParser implements Parser<ListInterviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListInterviewCommand
     * and returns an ListInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListInterviewCommand parse(String args) throws ParseException {
        if (args.equals("")) {
            return new ListInterviewCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILTER_TYPE, PREFIX_FILTER_ARGUMENT);

        if (argMultimap.getValue(PREFIX_FILTER_TYPE).isPresent()
                && argMultimap.getValue(PREFIX_FILTER_ARGUMENT).isPresent()) {

            FilterType filterType =
                    ParserUtil.parseFilterType(DataType.INTERVIEW, argMultimap.getValue(PREFIX_FILTER_TYPE).get());
            FilterArgument filterArgument =
                    ParserUtil.parseFilterArgument(argMultimap.getValue(PREFIX_FILTER_ARGUMENT).get());

            if (filterType.type.equals("date")) {
                try {
                    LocalDate.parse(filterArgument.toString());
                } catch (DateTimeParseException e) {
                    throw new ParseException(Messages.MESSAGE_INVALID_DATE);
                }
            }

            return new ListInterviewCommand(filterType, filterArgument);
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListPositionCommand.MESSAGE_USAGE));
        }
    }
}
