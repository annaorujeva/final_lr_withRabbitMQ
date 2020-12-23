package app.domain;

import app.store.ComputerStore;
import app.store.RoomStore;
import org.springframework.stereotype.Component;


public class Place {

    private int id_place;
    private int id_room;
    private int id_pc;
    private String status;

    @Override
    public String toString() {
        return "Place{" +
                "id_place=" + id_place +
                ", id_room=" + id_room +
                ", id_pc=" + id_pc +
                ", status='" + status + '\'' +
                '}';
    }

    public Place(int id_place, int id_room, int id_pc, String status) {
        this.id_place = id_place;
        this.id_room = id_room;
        this.id_pc = id_pc;
        this.status = status;
    }


    public Place(){
    }

    public int getId_pc() {
        return id_pc;
    }

    public void setId_pc(int id_pc) {
        this.id_pc = id_pc;
    }

    public Place(int id_place){
        this.id_place=id_place;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
