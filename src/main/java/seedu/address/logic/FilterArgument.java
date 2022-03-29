package seedu.address.logic;

import static java.util.Objects.requireNonNull;

public class FilterArgument {

    public static final String MESSAGE_CONSTRAINTS = "Filter arguments should not be blank";

    public final String argument;

    /**
     * Constructs a {@code FilterArgument}.
     *
     * @param argument A valid argument.
     */
    public FilterArgument(String argument) {
        requireNonNull(argument);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return argument;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterArgument // instanceof handles nulls
                && argument.equals(((FilterArgument) other).argument)); // state check
    }

    @Override
    public int hashCode() {
        return argument.hashCode();
    }
}
