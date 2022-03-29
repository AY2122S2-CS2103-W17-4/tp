package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.DeleteApplicantCommand;
import seedu.address.logic.commands.interview.DeleteInterviewCommand;
import seedu.address.logic.commands.position.DeletePositionCommand;

public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_positionFlag_returnsDeletePositionCommand() {
        assertParseSuccess(parser, "-p 1", new DeletePositionCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_applicantFlag_returnsDeleteApplicantCommand() {
        assertParseSuccess(parser, "-a 1", new DeleteApplicantCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_interviewFlag_returnsDeleteInterviewCommand() {
        assertParseSuccess(parser, "-i 1", new DeleteInterviewCommand(INDEX_FIRST_PERSON));
    }
}
