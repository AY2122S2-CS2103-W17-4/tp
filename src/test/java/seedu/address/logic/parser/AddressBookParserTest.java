/*
package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.applicant.EditApplicantCommand;
import seedu.address.logic.commands.applicant.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.add.AddApplicantCommand;
import seedu.address.logic.commands.applicant.DeleteApplicantCommand;
import seedu.address.logic.commands.help.HelpCommand;
import seedu.address.logic.commands.applicant.ListApplicantCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantNamePredicate;
import seedu.address.testutil.EditApplicantDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ApplicantUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Applicant applicant = new PersonBuilder().build();
        AddApplicantCommand command = (AddApplicantCommand) parser.parseCommand(ApplicantUtil.getAddCommand(applicant));
        assertEquals(new AddApplicantCommand(applicant), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteApplicantCommand command = (DeleteApplicantCommand) parser.parseCommand(
                DeleteApplicantCommand.COMMAND_WORD + " -a " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteApplicantCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Applicant applicant = new PersonBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicant).build();
        EditApplicantCommand command =
                (EditApplicantCommand) parser.parseCommand(EditApplicantCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + ApplicantUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditApplicantCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new ApplicantNamePredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListApplicantCommand.COMMAND_WORD) instanceof ListApplicantCommand);
        assertTrue(parser.parseCommand(ListApplicantCommand.COMMAND_WORD + " 3") instanceof ListApplicantCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
 */
