package es.eukariotas.apiservice.exceptions;

public class CustomExceptions extends Exception{
    public CustomExceptions(String message) {
        super(message);
    }

    public CustomExceptions() {
        super("Error en la peticion");
    }

    public CustomExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomExceptions(Throwable cause) {
        super(cause);
    }

}
