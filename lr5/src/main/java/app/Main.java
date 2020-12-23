package app;

import app.domain.*;
import app.repository.*;
import app.service.*;
import app.store.*;
import app.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Main implements CommandLineRunner{
    @Autowired
    StoreService storeService;

    @Autowired
    RoomService roomService;

    @Autowired
    ComputerService computerService;

    @Autowired
    Repository fileFepository;

    @Autowired
    ComputerT computerRep;

    @Autowired
    RoomT roomRep;

    public static void main(String[] args) throws UnknownRoomException, UnknownComputerException, UnknownPlace {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
       ComputerStore.allcomputers = (ArrayList<Computer>) computerRep.findAll();
       RoomStore.allrooms = (ArrayList<Room>) roomRep.findAll();
       storeService.prepare_places();
       storeService.prepare_countEquip();

    }
//    @Override
//        public void run(String... args) throws Exception {
//        storeService.startofwork();
//        for (int i=0;i<RoomStore.allrooms.size();i++){
//        System.out.println(RoomStore.allrooms.get(i));}
//        for (int i=0;i< ComputerStore.allcomputers.size();i++){
//            System.out.println(ComputerStore.allcomputers.get(i));}
//        Computer c = new Computer(44, 2,22,"work");
//        ComputerStore.allcomputers.add(c);
//        fileFepository.write(c);
//        System.out.println(roomService.findAllPC(1));
//        System.out.println(roomService.findWriteOffPC(2));
//        System.out.println(roomService.countOfEquipment(2));
//        //computerService.WriteOffPC(6666);
//        computerService.ChangePCPlace(44,100);
//      }
    }
