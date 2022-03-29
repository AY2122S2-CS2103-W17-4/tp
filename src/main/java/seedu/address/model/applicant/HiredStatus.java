package seedu.address.model.applicant;

/**
 * Represents an Applicant's status in the address book.
 * default value is "Available" and is replaced with a position name when Applicant accepts an offer
 */
public class HiredStatus {
    private final String value;

    /**
     * Constructs a {@code HiredStatus}.
     * Initialised as available as every Applicant added to HireLah is applying for a position.
     */
    public HiredStatus() {
        value = "Available";
    }

    public HiredStatus(String positionName) {
        value = "Hired as " + positionName;
    }

    @Override
    public String toString() {
        return value;
    }
}
