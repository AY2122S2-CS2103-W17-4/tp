package seedu.address.model.interview;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Interview}'s {@code Position}'s {@code name} matches any of the keywords given.
 */
public class InterviewPositionPredicate implements Predicate<Interview> {
    private final List<String> keywords;

    public InterviewPositionPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Interview interview) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(
                                interview.getPosition().getPositionName().positionName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewPositionPredicate // instanceof handles nulls
                && keywords.equals(((InterviewPositionPredicate) other).keywords)); // state check
    }
}
