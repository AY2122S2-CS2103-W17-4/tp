package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    // Applicant parser
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_AGE = new Prefix("ag/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    // Interview parser
    public static final Prefix PREFIX_APPLICANT = new Prefix("a/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_POSITION = new Prefix("p/");
    // Position parser
    public static final Prefix PREFIX_NUM_OPENINGS = new Prefix("o/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_REQUIREMENT = new Prefix("r/");
    // List parser
    public static final Prefix PREFIX_FILTER_TYPE = new Prefix("f/");
    public static final Prefix PREFIX_FILTER_ARGUMENT = new Prefix("a/");
}
