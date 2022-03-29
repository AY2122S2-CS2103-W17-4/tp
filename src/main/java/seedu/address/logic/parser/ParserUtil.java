package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.position.Description;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String age} into a {@code age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedAge);
    }

    /**
     * Parses a {@code String gender} into a {@code age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String date} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDateTime parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();

        // See whether date is valid
        // consider abstracting into a separate class in Interview
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateParsed = LocalDateTime.parse(date, formatter);
            return dateParsed;
        } catch (DateTimeException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATETIME);
        }

    }

    /**
     * Parses a {@code String positionName} into a {@code PositionName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code positionName} is invalid.
     */
    public static PositionName parsePositionName(String positionName) throws ParseException {
        requireNonNull(positionName);
        String trimmedName = positionName.trim();
        if (!PositionName.isValidPositionName(trimmedName)) {
            throw new ParseException(PositionName.MESSAGE_CONSTRAINTS);
        }
        return new PositionName(trimmedName);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescriptionText(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String openings} into a {@code PositionOpenings}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code openings} is invalid.
     */
    public static PositionOpenings parseOpenings(String openings) throws ParseException {
        requireNonNull(openings);
        String trimmedOpenings = openings.trim();
        if (!PositionOpenings.isValidNumber(openings)) {
            throw new ParseException(PositionOpenings.MESSAGE_CONSTRAINTS);
        }
        return new PositionOpenings(trimmedOpenings);
    }

    /**
     * Parses a {@code String requirement} into a {@code Requirement}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code requirement} is invalid.
     */
    public static Requirement parseRequirement(String requirement) throws ParseException {
        requireNonNull(requirement);
        String trimmedRequirement = requirement.trim();
        if (!Requirement.isValidRequirementText(trimmedRequirement)) {
            throw new ParseException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(trimmedRequirement);
    }

    /**
     * Parses {@code Collection<String> requirements} into a {@code Set<Requirement>}.
     */
    public static Set<Requirement> parseRequirements(Collection<String> requirements) throws ParseException {
        requireNonNull(requirements);
        final Set<Requirement> requirementSet = new HashSet<>();
        for (String requirement : requirements) {
            requirementSet.add(parseRequirement(requirement));
        }
        return requirementSet;
    }

    /**
     * Parses a {@code String filterType} into a {@code FilterType}, along with the corresponding data type.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code filterType} is invalid for the {@code dataType}.
     */
    public static FilterType parseFilterType(DataType dataType, String filterType) throws ParseException {
        requireNonNull(filterType);
        String trimmedFilterType = filterType.trim().toLowerCase();
        if (!FilterType.isValidFilterType(dataType, trimmedFilterType)) {
            throw new ParseException(FilterType.MESSAGE_CONSTRAINTS);
        }
        return new FilterType(dataType, trimmedFilterType);
    }

    /**
     * Parses a {@code String filterArgument} into a {@code FilterArgument}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static FilterArgument parseFilterArgument(String filterArgument) {
        requireNonNull(filterArgument);
        return new FilterArgument(filterArgument.trim());
    }
}
