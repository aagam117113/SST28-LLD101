public class FakeEligibilityStore implements EvaluationStore {
    public void save(String roll, String status) {
        System.out.println("Saved evaluation for roll=" + roll);
    }
}
