package app.service;

import app.domain.*;
import app.exception.*;
import app.repository.*;
import app.store.ComputerStore;
import app.store.RoomStore;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;


@EnableRabbit
public class FileComputerService implements ComputerService {

    public FileComputerService() {
    }

    @Autowired
    ComputerT CompTable;

    @Autowired
    RoomT RoomTable;

    public Computer findPC(int id) throws UnknownComputerException {
        Computer computer = null;
        for(Computer comp: ComputerStore.allcomputers){
            if (comp.getId() == id){
                computer = comp;
                break;
            }
        }
        if (computer == null) {
            throw new UnknownComputerException();
        }
        else return computer;
    }

    @Override
    public void WriteOffPC(int id) throws UnknownComputerException, UnknownRoomException {
        Computer computer = null;
        Room room = null;
        for (Computer comp : ComputerStore.allcomputers) {
            if ((comp.getId() == id) & (comp.getStatus().equals("work"))) {
                computer = comp;
                break;
            }
        }
        for (Room rm:RoomStore.allrooms){
            if((rm.getId()==computer.getRoom())){
                room = rm;
                break;
            }
        }
        if (computer == null) {
            throw new UnknownComputerException();
        }
        if (room == null){
            throw new UnknownRoomException();
        }
        computer.setStatus("writeoff");
        computer.setWriteoff_date(room.getNext_inventory_date());
        room.getPlace(computer.getId_place()).setStatus("free");
        room.setCount_equipment(room.getCount_equipment()-1);
        CompTable.saveAndFlush(computer);
        RoomTable.saveAndFlush(room);
    }

    @Override
    public void ChangePCPlace(int id_pc, int place_id_to) throws UnknownComputerException, NoPlaceException {
        Computer computer = null;
        Room room = null;
        for (Computer comp : ComputerStore.allcomputers) {
            if (comp.getId() == id_pc) {
                computer = comp;
                break;
            }
        }
        for (Room rm : RoomStore.allrooms) {
            if ((rm.getId() == computer.getRoom())) {
                room = rm;
                break;
            }
        }
        if (computer == null) {
            throw new UnknownComputerException();
        }
        if (room.getPlace(place_id_to)==null){
            if(room.getPlaces_in_room().size()<20){
                Place new_place = new Place(place_id_to);
                room.getPlaces_in_room().add(new_place);
                room.getPlace(computer.getId_place()).setStatus("free");
                computer.setId_place(place_id_to);
                room.getPlace(place_id_to).setStatus("used");
                CompTable.saveAndFlush(computer);
            }
            else throw new NoPlaceException();
        }
        else if (room.getPlace(place_id_to).getStatus().equals("free")){
            room.getPlace(computer.getId_place()).setStatus("free");
            computer.setId_place(place_id_to);
            room.getPlace(place_id_to).setStatus("used");
            CompTable.saveAndFlush(computer);
        }
        else {
            int old_place = computer.getId_place();
            room.findPCbyPlace(place_id_to).setId_place(old_place);
            computer.setId_place(place_id_to);
            CompTable.saveAndFlush(computer);
            CompTable.saveAndFlush(room.findPCbyPlace(old_place));
        }
    }
}

