package app.exception;

public class UnknownRoomException extends Exception{
    public UnknownRoomException(){
        System.out.println("Данная аудитория не найдена!");
    }
}
