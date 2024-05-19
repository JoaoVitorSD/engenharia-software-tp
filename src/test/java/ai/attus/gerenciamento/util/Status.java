package ai.attus.gerenciamento.util;

public enum Status {
    CREATED(201),
    OK(200),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    NOT_FOUND(404);
    
    public final int code;

    Status(int code) {
        this.code = code;
    }
}
