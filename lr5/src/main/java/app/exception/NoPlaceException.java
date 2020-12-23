package app.exception;

public class NoPlaceException extends Exception{
    public NoPlaceException() {
        System.out.println("Недостаточно места в комнате!");
    }
}
