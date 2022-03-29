package seedu.address.model.position;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Position}'s {@code Name} matches any of the keywords given.
 */
public class PositionNamePredicate implements Predicate<Position> {
    private final List<String> keywords;

    public PositionNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Position pos) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(pos.getPositionName().positionName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionNamePredicate // instanceof handles nulls
                && keywords.equals(((PositionNamePredicate) other).keywords)); // state check
    }
}
