package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.HiredStatus;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Applicant objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_AGE = "77";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GENDER = "F";

    private Name name;
    private Phone phone;
    private Email email;
    private Age age;
    private Address address;
    private Gender gender;
    private HiredStatus hiredStatus;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        age = new Age(DEFAULT_AGE);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        hiredStatus = new HiredStatus();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code applicantToCopy}.
     */
    public PersonBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        age = applicantToCopy.getAge();
        address = applicantToCopy.getAddress();
        gender = applicantToCopy.getGender();
        hiredStatus = applicantToCopy.getStatus();
        tags = new HashSet<>(applicantToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Applicant} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Applicant} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Applicant} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Applicant} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Applicant build() {
        return new Applicant(name, phone, email, age, address, gender, hiredStatus, tags);
    }

}
