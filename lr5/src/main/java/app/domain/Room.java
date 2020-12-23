package app.domain;

import app.exception.UnknownRoomException;
import app.store.ComputerStore;
import app.store.RoomStore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;

@Component
@Entity
@Table(name = "ROOMS")
public class Room {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "last_inventory_date", nullable = false)
    private String last_inventory_date;

    @Column(name = "next_inventory_date", nullable = false)
    private String next_inventory_date;

    //Кол-во несписанного, рабочего оборудования
    @Column(name="work_equipment", nullable = true)
    private int count_equipment = 0;

    @Transient
    ArrayList<Computer> computers_in_room = new ArrayList<Computer>();

    @Transient
    ArrayList<Computer> writeoffpc_in_room = new ArrayList<Computer>();

    @Transient
    ArrayList<Place> places_in_room = new ArrayList<>();

    public Room(int id, String last_inventory_date, String next_inventory_date) {
        this.id = id;
        this.last_inventory_date = last_inventory_date;
        this.next_inventory_date = next_inventory_date;
    }

    public Room() {
    }

    public ArrayList<Computer> getComputersInRoom(){
        return computers_in_room;
    }
    public ArrayList<Computer> getWriteOffPCInRoom(){
        return writeoffpc_in_room;
    }
    public ArrayList<Place> getPlaces_in_room() {
        return places_in_room;
    }

    public void countOfWorkEquipment(){
        for (Computer comp: ComputerStore.allcomputers){
            if((comp.getRoom() == this.id)&(comp.getStatus().equals("work"))){
                count_equipment++;
            }
        }
    }

    public Room getbyid(int id_room){
        for (Room rm: RoomStore.allrooms){
            if (rm.getId()==id_room) return rm;
        }
        return null;
    }

    public Place getPlace(int id_place){
        for(Place place:places_in_room){
            if (place.getId_place()==id_place)
                return place;
        }
        return null;
    }

    public Computer findPCbyPlace(int id_place){
        for (Computer comp: ComputerStore.allcomputers){
            if ((comp.getId_place()==id_place)&(comp.getRoom()==this.id)) return comp;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", last_inventory_date='" + last_inventory_date + '\'' +
                ", next_inventory_date='" + next_inventory_date + '\'' +
                ", count_equipment=" + count_equipment +
                '}';
    }

    public int getLastIdPlace(){
        return getPlaces_in_room().get(getPlaces_in_room().size()-1).getId_place();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_inventory_date() {
        return last_inventory_date;
    }

    public void setLast_inventory_date(String last_inventory_date) {
        this.last_inventory_date = last_inventory_date;
    }

    public String getNext_inventory_date() {
        return next_inventory_date;
    }

    public void setNext_inventory_date(String next_inventory_date) {
        this.next_inventory_date = next_inventory_date;
    }

    public int getCount_equipment() {
        return count_equipment;
    }

    public void setCount_equipment(int count_equipment) {
        this.count_equipment = count_equipment;
    }
}
