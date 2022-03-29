package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POSITIONS;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.commons.core.DataType;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionNamePredicate;

/**
 * Lists positions in HireLah to the user.
 */
public class ListPositionCommand extends ListCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -p: List positions with optional parameters."
            + "\nOptional parameters: "
            + PREFIX_FILTER_TYPE + "FILTER_TYPE "
            + PREFIX_FILTER_ARGUMENT + "FILTER_TYPE "
            + "\nExample: " + COMMAND_WORD + " -p "
            + PREFIX_FILTER_TYPE + "name "
            + PREFIX_FILTER_ARGUMENT + "Software Engineer ";

    public static final String MESSAGE_SUCCESS = "Listed %1$d position(s)";

    private FilterType filterType;
    private FilterArgument filterArgument;

    /**
     * Creates an ListPositionCommand to display all {@code Position}
     */
    public ListPositionCommand() {
        filterType = null;
        filterArgument = null;
    }

    /**
     * Creates an ListPositionCommand to filter and display {@code Position}
     */
    public ListPositionCommand(FilterType filterType, FilterArgument filterArgument) {
        this.filterType = filterType;
        this.filterArgument = filterArgument;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (filterType != null && filterArgument != null) {
            if (filterType.type.equals("name")) {
                String[] nameKeywords = filterArgument.toString().split("\\s+");
                Predicate<Position> predicate = new PositionNamePredicate(Arrays.asList(nameKeywords));
                model.updateFilteredPositionList(predicate);
            }
        } else {
            model.updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredPositionList().size()), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }
}
