package study.invoice.service.error;

public class NotFoundUserName extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundUserName() {
        super("Login cannot be null or empty!");
    }
}
