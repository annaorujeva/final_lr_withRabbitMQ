package app.store;

import app.domain.Computer;
import app.domain.Place;
import app.domain.Room;
import app.repository.FileRepository;
import app.repository.Repository;
import app.repository.RoomT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreService {
    public StoreService(){}

    @Autowired
    Repository filerepository;

    @Autowired
    RoomT RoomTable;



    public void prepare_places(){
        for(Computer computer: ComputerStore.allcomputers){
            for(Room room: RoomStore.allrooms){
                if ((computer.getRoom()==room.getId())&(computer.getStatus().equals("work"))){
                    room.getPlaces_in_room().add(new Place(computer.getId_place(), room.getId(), computer.getId(),"used"));
                }
            }
        }
    }

    public void prepare_countEquip(){
        for(Room room: RoomStore.allrooms){
            room.countOfWorkEquipment();
            RoomTable.saveAndFlush(room);
        }
    }

    public void startofwork() {
        filerepository.setDirectory("C:/Users/Anna/lr5_rv/");
        if (filerepository.isDirectoryExists()) {
            if (filerepository.isEmptyDirectory()) {
                filerepository.GenerateComputersAndRooms();
                prepare_places();
            }
            else {
                filerepository.read();
                prepare_places();
            }
        }
        else {
            filerepository.createDirectory();
            filerepository.GenerateComputersAndRooms();
            prepare_places();
        }
    }

}
