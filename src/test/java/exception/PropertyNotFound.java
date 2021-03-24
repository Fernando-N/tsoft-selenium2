package exception;

public class PropertyNotFound extends RuntimeException{

    public PropertyNotFound(String property) {
        super(String.format("Property '%s' no encontrada", property));
    }

}
