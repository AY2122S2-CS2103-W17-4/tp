package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

/**
 * Adds an applicant to the application.
 */
public class AddApplicantCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -a: Adds an applicant to HireLah. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_AGE + "AGE "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_GENDER + "GENDER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " -a "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_AGE + "23 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_GENDER + "M "
            + PREFIX_TAG + "AWS Certified "
            + PREFIX_TAG + "Ex-Facebook";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in HireLah application";
    private static final String MESSAGE_DUPLICATE_EMAIL = "The email is already used by %1$s";
    private static final String MESSAGE_DUPLICATE_PHONE = "The phone number is already used by %1$s";

    private final Applicant toAdd;

    /**
     * Creates an AddApplicantCommand to add the specified {@code Applicant}
     */
    public AddApplicantCommand(Applicant applicant) {
        requireNonNull(applicant);
        toAdd = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplicant(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        Applicant applicantWithEmail = model.getApplicantWithEmail(toAdd.getEmail());
        if (applicantWithEmail != null) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EMAIL, applicantWithEmail.getName().fullName));
        }

        Applicant applicantWithPhone = model.getApplicantWithPhone(toAdd.getPhone());
        if (applicantWithPhone != null) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PHONE, applicantWithPhone.getName().fullName));
        }

        model.addApplicant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddApplicantCommand // instanceof handles nulls
                && toAdd.equals(((AddApplicantCommand) other).toAdd));
    }
}
