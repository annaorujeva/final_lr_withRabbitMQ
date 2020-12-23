package app.repository;

import app.domain.*;
import app.store.*;
import java.io.*;

public class FileRepository implements Repository{
    public String line;
    public String[] data;
    private static String directory = "C:/Users/Anna/lr5_rv/";

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }


    @Override
    public void write(Computer allcomputers) {
        try (FileWriter fw = new FileWriter(directory +"comp/comp" + allcomputers.getId() + ".txt")){
            fw.write(allcomputers.getId()+" " + allcomputers.getRoom() + " "+ allcomputers.getId_place()+ " "
                    +allcomputers.getStatus()+" "+allcomputers.getWriteoff_date());
            fw.flush();
        }
        catch (IOException ex){
            System.out.println("Не удалось сохранить файл" + ex.getMessage());
        }
    }

    @Override
    public void write(Room allrooms) {
        try (FileWriter fw = new FileWriter(directory +"room/room" + allrooms.getId() + ".txt")){
            fw.write(allrooms.getId()+" " + allrooms.getLast_inventory_date() + " "
                    + allrooms.getNext_inventory_date());
            fw.flush();
        }
        catch (IOException ex){
            System.out.println("Не удалось сохранить файл" + ex.getMessage());
        }

    }

    @Override
    public void read() {
        try {
            File file = new File(directory+"comp/");
            File[] listofFiles = file.listFiles();
            for (int i = 0; i < listofFiles.length; i++) {
                File infile = new File(directory + "comp/"+ listofFiles[i].getName());
                FileReader fileReader = new FileReader(infile);
                BufferedReader reader = new BufferedReader(fileReader);
                line = reader.readLine();
                data = line.split(" ");
                Computer computer = new Computer(Integer.parseInt(data[0]),Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]);
                ComputerStore.allcomputers.add(computer);
            }
            File file1 = new File(directory+"room/");
            File[] listofFiles1 = file1.listFiles();
            for (int i = 0; i < listofFiles1.length; i++) {
                File infile1 = new File(directory+"room/" + listofFiles1[i].getName());
                FileReader fileReader1 = new FileReader(infile1);
                BufferedReader reader = new BufferedReader(fileReader1);
                line = reader.readLine();
                data = line.split(" ");
                Room room = new Room(Integer.parseInt(data[0]),data[1], data[2]);
                RoomStore.allrooms.add(room);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public boolean isEmptyDirectory(){
        File dir = new File(directory);
        String files[] = dir.list();
        return files.length == 0;
    }

    public boolean isDirectoryExists(){
        File dir1= new File(directory+"comp/");
        File dir2= new File(directory+"room/");
        if (dir1.exists() & dir2.exists()){
            System.out.println("Директория существует");
            return true;
        }
        else return false;
    }

    public void createDirectory(){
        File dir1 = new File(directory+"comp/");
        File dir2 = new File(directory+"room/");
        if(isDirectoryExists()==false){
            dir1.mkdir();
            dir2.mkdir();
            System.out.println("Директория создана");
        }
    }

    public void GenerateComputersAndRooms(){
        Computer computer = new Computer(111, 1, 1, "work");
        ComputerStore.allcomputers.add(computer);
        write(computer);
        Computer computer1 = new Computer(222, 1, 2, "work");
        ComputerStore.allcomputers.add(computer1);
        write(computer1);
        Computer computer2 = new Computer(333, 2, 1, "work");
        ComputerStore.allcomputers.add(computer2);
        write(computer2);
        Computer computer3 = new Computer(444, 2, 2, "work");
        ComputerStore.allcomputers.add(computer3);
        write(computer3);
        Room room1 = new Room(1, "01.06.20", "01.12.20");
        RoomStore.allrooms.add(room1);
        write(room1);
        Room room2 = new Room(2, "01.06.20", "01.12.20");
        RoomStore.allrooms.add(room2);
        write(room2);
        System.out.println("Директория заполнена");
    }
}
