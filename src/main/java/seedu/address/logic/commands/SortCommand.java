package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class SortCommand extends Command{
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sort according to...";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortedPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
