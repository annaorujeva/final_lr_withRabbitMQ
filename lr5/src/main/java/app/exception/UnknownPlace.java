package app.exception;

public class UnknownPlace extends Exception{
    public UnknownPlace(){
        System.out.println("Данное место не найдено или недоступно!");
    }
}
