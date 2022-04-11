package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.DataType;
import seedu.address.model.HireLah;
import seedu.address.model.Model;

/**
 * Clears the application.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All data in HireLah has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHireLah(new HireLah());
        return new CommandResult(MESSAGE_SUCCESS, getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return null;
    }
}
