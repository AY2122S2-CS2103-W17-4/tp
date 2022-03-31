package seedu.address.logic.commands.applicant;
import java.io.FileNotFoundException;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExportCsvCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class ExportApplicantCsvCommand extends ExportCsvCommand {
    public static final String MESSAGE_SUCCESS = "Applicant CSV is exported";

    @Override
    public CommandResult execute(Model model) throws CommandException, FileNotFoundException {
        model.exportCsvApplicant();
        return new CommandResult(MESSAGE_SUCCESS, getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }

}
