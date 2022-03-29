package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.position.EditPositionCommand.EditPositionDescriptor;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;

/**
 * A utility class to help with building EditPositionDescriptor objects.
 */
public class EditPositionDescriptorBuilder {

    private EditPositionDescriptor descriptor;

    public EditPositionDescriptorBuilder() {
        descriptor = new EditPositionDescriptor();
    }

    public EditPositionDescriptorBuilder(EditPositionDescriptor descriptor) {
        this.descriptor = new EditPositionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPositionDescriptor} with fields containing {@code position}'s details
     */
    public EditPositionDescriptorBuilder(Position position) {
        descriptor = new EditPositionDescriptor();
        descriptor.setPositionName(position.getPositionName());
        descriptor.setPositionOpenings(position.getPositionOpenings());
        descriptor.setDescription(position.getDescription());
        descriptor.setRequirements(position.getRequirements());
    }

    /**
     * Sets the {@code PositionName} of the {@code EditPositionDescriptor} that we are building.
     */
    public EditPositionDescriptorBuilder withPositionName(String name) {
        descriptor.setPositionName(new PositionName(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditPositionDescriptor} that we are building.
     */
    public EditPositionDescriptorBuilder withDescription(String desc) {
        descriptor.setDescription(new Description(desc));
        return this;
    }

    /**
     * Sets the {@code PositionOpenings} of the {@code EditPositionDescriptor} that we are building.
     */
    public EditPositionDescriptorBuilder withNumOpenings(String openings) {
        descriptor.setPositionOpenings(new PositionOpenings(openings));
        return this;
    }

    /**
     * Parses the {@code requirements} into a {@code Set<Requirement>} and set it to the {@code EditPositionDescriptor}
     * that we are building.
     */
    public EditPositionDescriptorBuilder withRequirements(String... requirements) {
        Set<Requirement> reqSet = Stream.of(requirements).map(Requirement::new).collect(Collectors.toSet());
        descriptor.setRequirements(reqSet);
        return this;
    }

    public EditPositionDescriptor build() {
        return descriptor;
    }
}
