package app.repository;

import app.domain.Computer;
import app.domain.Room;

public interface Repository {
    void write(Computer out);
    void write(Room out);
    void read();

    void setDirectory(String s);

    boolean isDirectoryExists();

    boolean isEmptyDirectory();

    void GenerateComputersAndRooms();

    void createDirectory();
}
