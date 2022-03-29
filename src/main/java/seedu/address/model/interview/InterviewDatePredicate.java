package seedu.address.model.interview;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Interview}'s {@code date} matches the given date.
 */
public class InterviewDatePredicate implements Predicate<Interview> {
    private final LocalDate date;

    public InterviewDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Interview interview) {
        return date.isEqual(interview.getDate().toLocalDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewDatePredicate // instanceof handles nulls
                && date.equals(((InterviewDatePredicate) other).date)); // state check
    }
}
