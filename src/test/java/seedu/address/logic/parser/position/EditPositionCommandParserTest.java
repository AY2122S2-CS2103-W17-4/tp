package seedu.address.logic.parser.position;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPENING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REQUIREMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_EXPERIENCE;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_SKILL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_NAME_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_NAME_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_OPENINGS_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.position.EditPositionCommand;
import seedu.address.model.position.Description;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;
import seedu.address.testutil.EditPositionDescriptorBuilder;

public class EditPositionCommandParserTest {
    private static final String REQUIREMENT_EMPTY = " " + PREFIX_REQUIREMENT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPositionCommand.MESSAGE_USAGE);

    private EditPositionCommandParser parser = new EditPositionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, POSITION_DESC_JR_SWE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPositionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + POSITION_DESC_JR_SWE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + POSITION_DESC_JR_SWE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid position name
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC, PositionName.MESSAGE_CONSTRAINTS);
        // invalid openings
        assertParseFailure(parser, "1" + INVALID_OPENING_DESC, PositionOpenings.MESSAGE_CONSTRAINTS);
        // invalid description
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        // invalid requirement
        assertParseFailure(parser, "1" + INVALID_REQUIREMENT_DESC, Requirement.MESSAGE_CONSTRAINTS);

        // invalid position name followed by valid position openings
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC + OPENING_DESC_JR_SWE,
                PositionName.MESSAGE_CONSTRAINTS);

        // valid description followed by invalid description. The test case for invalid description followed by
        // valid description is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_JR_SWE + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_REQUIREMENT} alone will reset the requirements of the {@code Position} being
        // edited, parsing it together with a valid requirement results in error
        assertParseFailure(parser, "1" + REQUIREMENT_DESC_EXPERIENCE + REQUIREMENT_DESC_SKILL
                + REQUIREMENT_EMPTY, Requirement.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + REQUIREMENT_DESC_EXPERIENCE + REQUIREMENT_EMPTY
                + REQUIREMENT_DESC_SKILL, Requirement.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + REQUIREMENT_EMPTY + REQUIREMENT_DESC_EXPERIENCE
                + REQUIREMENT_DESC_SKILL, Requirement.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + INVALID_OPENING_DESC,
                Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_JR_SWE + REQUIREMENT_DESC_EXPERIENCE
                + DESCRIPTION_DESC_JR_SWE + OPENING_DESC_JR_SWE;

        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withPositionName(VALID_POSITION_NAME_JR_SWE)
                        .withDescription(VALID_DESCRIPTION_NAME_JR_SWE).withNumOpenings(VALID_POSITION_OPENINGS_JR_SWE)
                        .withRequirements(VALID_REQUIREMENT_EXPERIENCE).build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_JR_SWE + REQUIREMENT_DESC_EXPERIENCE;

        EditPositionCommand.EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder()
                .withPositionName(VALID_POSITION_NAME_JR_SWE)
                .withRequirements(VALID_REQUIREMENT_EXPERIENCE).build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
