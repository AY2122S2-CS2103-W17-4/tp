package seedu.address.model.position;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.position.exceptions.UnableToAcceptOfferException;
import seedu.address.model.position.exceptions.UnableToExtendOfferException;
import seedu.address.model.position.exceptions.UnableToRejectOfferException;

/**
 * Represent a Position in HireLah.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Position {

    public static final String MESSAGE_CONSTRAINTS =
            "Position cannot have more offers than openings.";

    // Identity fields
    private final PositionName positionName;
    private final Description description;

    // Data fields
    private final PositionOpenings positionOpenings;
    private final PositionOffers positionOffers;
    private final Set<Requirement> requirements = new HashSet<>();

    /**
     * Every field must be present, not null and validated.
     * Number of offers is left out of parameters.
     * Constructor is used for initializing a new position.
     */
    public Position(PositionName positionName, Description description, PositionOpenings positionOpenings,
                    Set<Requirement> requirements) {
        requireAllNonNull(positionName, description, positionOpenings, requirements);
        this.positionName = positionName;
        this.description = description;
        this.positionOpenings = positionOpenings;
        this.requirements.addAll(requirements);
        this.positionOffers = new PositionOffers();
    }

    /**
     * Every field must be present, not null and validated.
     */
    public Position(PositionName positionName, Description description, PositionOpenings positionOpenings,
                    PositionOffers positionOffers, Set<Requirement> requirements) {
        requireAllNonNull(positionName, description, positionOpenings, positionOffers, requirements);
        this.positionName = positionName;
        this.description = description;
        this.positionOpenings = positionOpenings;
        this.requirements.addAll(requirements);
        this.positionOffers = positionOffers;
    }

    public PositionName getPositionName() {
        return positionName;
    }

    public Description getDescription() {
        return description;
    }

    public PositionOpenings getPositionOpenings() {
        return positionOpenings;
    }

    public PositionOffers getPositionOffers() {
        return positionOffers;
    }

    /**
     * Returns an immutable requirements set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Requirement> getRequirements() {
        return Collections.unmodifiableSet(requirements);
    }

    /**
     * Returns true if both positions have the same position name.
     * This defines a weaker notion of equality between two positions,
     * which allows for all positions to have a unique position name.
     */
    public boolean isSamePosition(Position otherPosition) {
        if (otherPosition == this) {
            return true;
        }
        return otherPosition != null
                && otherPosition.positionName.equals(positionName);
    }

    /**
     * Returns true if number of offers is less than number of openings.
     */
    public boolean isValidOpeningsToOffers() {
        return positionOffers.getCount() <= positionOpenings.getCount();
    }

    /**
     * Returns true if number of offers is less than number of openings.
     */
    public boolean canExtendOffer() {
        return positionOffers.getCount() < positionOpenings.getCount();
    }

    /**
     * Returns true if number of openings and number of offers are more than 0.
     */
    public boolean canAcceptOffer() {
        return positionOpenings.getCount() > 0 && positionOffers.getCount() > 0;
    }

    /**
     * Returns true if number of offers is more than 0.
     */
    public boolean canRejectOffer() {
        return positionOffers.getCount() > 0;
    }

    /**
     * Returns true if the number of openings is more than 0.
     */
    public boolean canScheduleInterview() {
        return positionOpenings.getCount() > 0;
    }

    /**
     * Extends an offer for the current Position.
     * An offer can be extended if the current number of offers is less than the current number of openings.
     * The new position will contain a number of offers that is 1 more than the previous value.
     */
    public Position extendOffer() {
        if (!canExtendOffer()) {
            throw new UnableToExtendOfferException();
        }
        PositionOffers newOfferNumber = positionOffers.increment();
        return new Position(getPositionName(), getDescription(), getPositionOpenings(), newOfferNumber,
                getRequirements());
    }

    /**
     * Accepts an offer for the current Position.
     * An offer can be accepted if the current number of offers is more than 0 and there is more than 0 number of
     * openings for the position.
     * The new position will contain a number of offers and openings that is 1 less than the previous values.
     */
    public Position acceptOffer() {
        if (!canAcceptOffer()) {
            throw new UnableToAcceptOfferException();
        }
        PositionOffers newOfferNumber = positionOffers.decrement();
        PositionOpenings newPositionOpenings = positionOpenings.decrement();
        return new Position(getPositionName(), getDescription(), newPositionOpenings, newOfferNumber,
                getRequirements());
    }

    /**
     * Rejects an offer for the current Position.
     * An offer can be rejected if the current number of offers is more than 0.
     * The new position will contain a number of offers that is 1 less than the previous values.
     */
    public Position rejectOffer() {
        if (!canRejectOffer()) {
            throw new UnableToRejectOfferException();
        }
        PositionOffers newOfferNumber = positionOffers.decrement();
        return new Position(getPositionName(), getDescription(), getPositionOpenings(), newOfferNumber,
                getRequirements());
    }

    /**
     * Returns true if both positions have the same identity and data fields.
     * This defines a stronger notion of equality between two positions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;
        return otherPosition.getPositionName().equals(getPositionName())
                && otherPosition.getDescription().equals(getDescription())
                && otherPosition.getPositionOpenings().equals(getPositionOpenings())
                && otherPosition.getRequirements().equals(getRequirements())
                && otherPosition.getPositionOffers().equals(getPositionOffers());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(positionName, description, positionOpenings, requirements);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPositionName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Openings: ")
                .append(getPositionOpenings())
                .append("; Current Offers: ")
                .append(getPositionOffers());

        Set<Requirement> requirements = getRequirements();
        if (!requirements.isEmpty()) {
            builder.append("; Requirements: ");
            requirements.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Creates csv output for position
     */
    public String convertToCsv() {
        StringBuilder requirementString = new StringBuilder();
        for (Requirement requirement : requirements) {
            requirementString.append(requirement.requirementText);
            requirementString.append(" | ");
        }
        String trimmedRequirementString = requirementString.substring(0, requirementString.length() - 3);
        return positionName.positionName + "," + escapeSpecialCharacters(description.descriptionText) + ","
                + positionOpenings.toString() + "," + positionOffers.toString() + "," + trimmedRequirementString;
    }

    /**
     * Eliminates special characters from csv string
     */
    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
