package response;

public class Response {
    private String type;
    private String message;
    private Object object;

    public Response(String type, String message, Object object) {
        this.type = type;
        this.message = message;
        this.object = object;
    }

    public Response(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }
}
