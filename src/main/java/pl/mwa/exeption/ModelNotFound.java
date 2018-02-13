package pl.mwa.exeption;

public class ModelNotFound extends RuntimeException{
    private static final String MESSAGE_PATTERN = "Entity %s with id %s was not found";

    public ModelNotFound(String entityName, long id) {
        super(String.format(MESSAGE_PATTERN, entityName, id));
    }
}
