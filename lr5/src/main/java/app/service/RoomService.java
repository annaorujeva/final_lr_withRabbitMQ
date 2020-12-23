package app.service;

import app.domain.Computer;
import app.exception.*;

import java.util.ArrayList;

public interface RoomService {
    ArrayList<Computer> findAllPC(int id_room) throws UnknownRoomException;
    ArrayList<Computer> findWriteOffPC(int id_room)throws UnknownRoomException;
    //int countOfEquipment(int id_room)throws UnknownRoomException;
    //void moveEquipment(int from_room, int to_room, int id_pc)throws UnknownRoomException, UnknownComputerException, UnknownPlace;
}
