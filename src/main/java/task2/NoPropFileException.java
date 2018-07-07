package task2;

import lombok.Getter;

public class NoPropFileException extends Exception {
    @Getter
    private Exception noPropEx;

    public NoPropFileException(Exception er) {
        super(er);
    }

    public NoPropFileException(String er, Exception e) {
        super(er);
        noPropEx = e;
    }
}