package app.store;

import app.domain.Computer;
import app.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ComputerStore {


    public static ArrayList<Computer> allcomputers = new ArrayList<>();
    public ComputerStore(){}

}
