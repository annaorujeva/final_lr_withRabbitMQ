package app.service;
import app.domain.Computer;
import app.exception.*;

public interface ComputerService {
    void WriteOffPC(int id) throws UnknownComputerException, UnknownRoomException;
    void ChangePCPlace(int id_pc, int pc_id_to) throws UnknownComputerException, NoPlaceException;
    Computer findPC(int id) throws UnknownComputerException;
}
