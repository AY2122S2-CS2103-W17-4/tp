package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Edits the details of the specified data type in the application.
 */
public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
