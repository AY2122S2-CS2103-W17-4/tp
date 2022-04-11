package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the specified data to the application.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
