package seedu.address.logic.commands.position;

import java.io.FileNotFoundException;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExportCsvCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ExportPositionCsvCommand extends ExportCsvCommand {

    public static final String MESSAGE_SUCCESS = "Position CSV is exported";

    @Override
    public CommandResult execute(Model model) throws CommandException, FileNotFoundException {
        model.exportCsvPosition();
        return new CommandResult(MESSAGE_SUCCESS, getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }
}
