public interface EligibilityRule {
    /**
     * Returns null if the student passes this rule, or a reason string if they
     * fail.
     */
    String check(StudentProfile s);
}
