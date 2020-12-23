package app.exception;

public class UnknownComputerException extends Exception{
    public UnknownComputerException(){
        System.out.println("Данный компьютер не найден!");
    }
}
