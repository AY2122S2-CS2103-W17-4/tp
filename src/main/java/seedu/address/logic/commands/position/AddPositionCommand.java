package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUM_OPENINGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;

/**
 * Adds a position in the application.
 */
public class AddPositionCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -p: Adds a position to the application. "
            + "Parameters: "
            + PREFIX_POSITION + "POSITION_NAME"
            + PREFIX_NUM_OPENINGS + "NUM_OPENINGS "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_REQUIREMENT + "REQUIREMENT]...\n"
            + "Example: " + COMMAND_WORD + " -p "
            + PREFIX_POSITION + "Junior Back-end Software Engineer "
            + PREFIX_NUM_OPENINGS + "3 "
            + PREFIX_DESCRIPTION + "Must be able to work remotely. Teams are assigned during on-boarding process. "
            + PREFIX_REQUIREMENT + "Computer Science Bachelors "
            + PREFIX_REQUIREMENT + "Experience with SQL";

    public static final String MESSAGE_SUCCESS = "New position added: %1$s";
    public static final String MESSAGE_DUPLICATE_POSITION = "This position already exists in HireLah application";

    private final Position toAdd;

    /**
     * Creates an AddPositionCommand to add the specified {@code position}
     */
    public AddPositionCommand(Position position) {
        requireNonNull(position);
        toAdd = position;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPosition(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POSITION);
        }

        model.addPosition(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPositionCommand // instanceof handles nulls
                && toAdd.equals(((AddPositionCommand) other).toAdd));
    }
}
