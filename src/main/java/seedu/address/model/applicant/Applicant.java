package seedu.address.model.applicant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.position.Position;
import seedu.address.model.tag.Tag;

/**
 * Represents a Applicant in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Age age;
    private final Address address;
    private final Gender gender;
    private final HiredStatus hiredStatus;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     * For initializing an Applicant object with compulsory fields and default values
     */
    public Applicant(Name name, Phone phone, Email email, Age age, Address address, Gender gender, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, age, address, gender, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.hiredStatus = new HiredStatus();
        this.tags.addAll(tags);
    }

    /**
     * Overloaded constructor used to edit applicant with changed values
     */
    public Applicant(Name name, Phone phone, Email email, Age age, Address address, Gender gender,
                     HiredStatus hiredStatus, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, age, address, gender, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.hiredStatus = hiredStatus;
        this.tags.addAll(tags);
    }
    /**
     * Changes the hiredStatus of an applicant to the name of the Position provided
     */
    public Applicant setStatus(Applicant applicant, Position position) {
        return new Applicant(applicant.getName(),
                applicant.getPhone(),
                applicant.getEmail(),
                applicant.getAge(),
                applicant.getAddress(),
                applicant.getGender(),
                new HiredStatus(position.getPositionName().toString()),
                applicant.getTags());
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Age getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public HiredStatus getStatus() {
        return hiredStatus;
    }

    public boolean isHired() {
        return hiredStatus.isHired();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getAge().equals(getAge())
                && otherApplicant.getAddress().equals(getAddress())
                && otherApplicant.getGender().equals(getGender())
                && otherApplicant.getTags().equals(getTags())
                && otherApplicant.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, age, address, gender, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Age: ")
                .append(getAge())
                .append("; Address: ")
                .append(getAddress())
                .append("; Gender: ")
                .append(getGender())
                .append("; HiredStatus: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Creates csv output for applicant
     */
    public String convertToCsv() {
        StringBuilder tagString = new StringBuilder();
        for (Tag tag : tags) {
            tagString.append(tag.tagName);
            tagString.append(" | ");
        }
        String trimmedTagString = tagString.substring(0, tagString.length() - 3);
        return name.fullName + "," + phone.value + "," + email.value + "," + age.value + ","
                + escapeSpecialCharacters(address.value) + ","
                + gender.value + "," + hiredStatus.toString() + "," + trimmedTagString;
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
