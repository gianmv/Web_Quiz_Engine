package engine.Quiz.model;

public class Feedback {

    public static final Feedback OK = new Feedback(true, "Congratulations, you're right!");
    public static final Feedback NOT_OK = new Feedback(false,"Wrong answer! Please, try again.");

    protected boolean success;
    protected String feedback;

    public Feedback() {}

    public Feedback(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
