package app.domain;

import app.store.ComputerStore;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name= "COMPUTERS")
public class Computer {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="id_room", nullable = false)
    private int id_room;

    @Column(name= "id_place", nullable = true)
    @GeneratedValue
    private int id_place;

    @Column(name= "status", nullable = false)
    private String status;

    @Column(name = "writeoff_date", nullable = true)
    private String writeoff_date;

    public Computer() {
    }

    public Computer(int id, int id_room, int id_place, String status) {
        this.id = id;
        this.id_room = id_room;
        this.id_place = id_place;
        this.status = status;
        this.writeoff_date = null;
    }

    public Computer getbyid(int id_pc){
        for (Computer computer: ComputerStore.allcomputers){
            if (computer.getId()==id_pc) return computer;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", id_room=" + id_room +
                ", id_place=" + id_place +
                ", status='" + status + '\'' +
                ", writeoff_date='" + writeoff_date + '\'' +
                '}';
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoom(int id_room) {
        this.id_room = id_room;
    }


    public void setWriteoff_date(String writeoff_date) {
        this.writeoff_date = writeoff_date;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getRoom() {
        return id_room;
    }


    public String getWriteoff_date() {
        return writeoff_date;
    }


    public String getStatus() {
        return status;
    }
}
