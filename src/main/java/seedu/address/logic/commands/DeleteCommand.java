package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes the specified data from the application.
 */
public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
