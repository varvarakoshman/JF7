package task2;

import lombok.Getter;

public class NoKeyException extends Exception {
    @Getter
    private Exception noKeyEx;

    public NoKeyException(String er) {
        super(er);
    }

    public NoKeyException(String er, Exception e) {
        super(er);
        noKeyEx = e;
    }

    public Exception getnoKeyEx() {
        return noKeyEx;
    }
}


