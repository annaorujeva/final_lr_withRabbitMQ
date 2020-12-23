package app.service;


import app.domain.*;
import app.store.*;
import app.exception.*;
import app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class FileRoomService implements RoomService{

    public FileRoomService() {
    }

    @Autowired
    Repository fileRepository;

    @Override
    public ArrayList<Computer> findAllPC(int id_room) throws UnknownRoomException {
        Room room = null;
        for (Room rm: RoomStore.allrooms){
            if (rm.getId()==id_room){
                room = rm;
                break;
            }
        }
        if (room == null){
            throw new UnknownRoomException();
        }
        for (Computer comp: ComputerStore.allcomputers){
            if(comp.getRoom() == id_room){
                room.getComputersInRoom().add(comp);
            }
        }
        return room.getComputersInRoom();
    }

    @Override
    public ArrayList<Computer> findWriteOffPC(int id_room) throws UnknownRoomException{
        Room room = null;
        for (Room rm: RoomStore.allrooms){
            if (rm.getId()==id_room){
                room = rm;
                break;
            }
        }
        if (room == null){
            throw new UnknownRoomException();
        }
        for (Computer comp: ComputerStore.allcomputers){
            if((comp.getRoom() == id_room)&(!comp.getStatus().equals("work"))){
                room.getWriteOffPCInRoom().add(comp);
            }
        }
        return room.getWriteOffPCInRoom();
    }

    /*поиск общего количества всего оборудования, рабочего и списанного
    @Override
    public int countOfEquipment(int id_room) throws UnknownRoomException{
        Room room = null;
        int count = 0;
        for (Room rm: RoomStore.allrooms){
            if (rm.getId()==id_room){
                room = rm;
                break;
            }
        }
        if (room == null){
            throw new UnknownRoomException();
        }
        for (Computer comp: ComputerStore.allcomputers){
            if(comp.getRoom() == id_room){
                count++;
            }
        }
        return count;
    }*/


    /*@Override
    public void moveEquipment(int fromId_room, int toId_room, int id_pc) throws UnknownRoomException,UnknownComputerException, UnknownPlace{
        Room roomFrom = null;
        Room roomTo = null;
        Computer computer = null;
        for (Room room: RoomStore.allrooms){
            if (room.getId()==fromId_room){
                roomFrom = room;
            }
            if (room.getId()==toId_room){
                roomTo = room;
            }
        }
        for (Computer comp: ComputerStore.allcomputers){
            if (comp.getId()==id_pc){
                computer = comp;
                break;
            }
        }
        if ((roomFrom == null)|(roomTo==null)){
            throw new UnknownRoomException();
        }
        if(computer==null){
            throw new UnknownComputerException();
        }
        if(roomTo.getPlaces_in_room().size()>=20){
            throw new UnknownPlace();
        }

        roomFrom.getPlaces_in_room().get(computer.getId_place()).setStatus("free");
        computer.setRoom(toId_room);
        computer.setId_place(roomTo.getLastIdPlace()+1);
        roomTo.getPlaces_in_room().get(computer.getId_place()).setStatus("used");
        fileRepository.write(computer);
    }*/
}
