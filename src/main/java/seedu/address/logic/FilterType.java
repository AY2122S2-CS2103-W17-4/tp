package seedu.address.logic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import seedu.address.commons.core.DataType;

public class FilterType {

    public static final String MESSAGE_CONSTRAINTS = "Invalid filter type for the data type specified\n"
            + "Refer to help for the list of filter types";

    public static final Map<DataType, HashSet<String>> VALID_FILTER_TYPES = loadFilterTypes();

    public final String type;

    /**
     * Constructs a {@code FilterType}.
     */
    public FilterType(DataType dataType, String type) {
        requireNonNull(dataType, type);
        checkArgument(isValidFilterType(dataType, type), MESSAGE_CONSTRAINTS);
        this.type = type;
    }

    /**
     * Returns a map of data type to a valid filter type.
     */
    public static Map<DataType, HashSet<String>> loadFilterTypes() {
        HashSet<String> applicantTypes = new HashSet<>();
        applicantTypes.add("name");

        HashSet<String> positionTypes = new HashSet<>();
        positionTypes.add("name");

        HashSet<String> interviewTypes = new HashSet<>();
        interviewTypes.add("appl");
        interviewTypes.add("pos");
        interviewTypes.add("date");

        HashMap<DataType, HashSet<String>> filterTypes = new HashMap<>();
        filterTypes.put(DataType.APPLICANT, applicantTypes);
        filterTypes.put(DataType.POSITION, positionTypes);
        filterTypes.put(DataType.INTERVIEW, interviewTypes);

        return filterTypes;
    }

    /**
     * Returns true if a given string is a valid filter type for the given data type.
     */
    public static boolean isValidFilterType(DataType dataType, String filterType) {
        return VALID_FILTER_TYPES.get(dataType).contains(filterType);
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterType // instanceof handles nulls
                && type.equals(((FilterType) other).type)); // state check
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
