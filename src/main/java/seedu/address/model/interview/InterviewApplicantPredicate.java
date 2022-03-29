package seedu.address.model.interview;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Interview}'s {@code Applicant}'s {@code name} matches any of the keywords given.
 */
public class InterviewApplicantPredicate implements Predicate<Interview> {
    private final List<String> keywords;

    public InterviewApplicantPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Interview interview) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(interview.getApplicant().getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewApplicantPredicate // instanceof handles nulls
                && keywords.equals(((InterviewApplicantPredicate) other).keywords)); // state check
    }
}
