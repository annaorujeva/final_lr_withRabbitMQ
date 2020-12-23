package app.repository;

import app.domain.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerT extends JpaRepository<Computer, Long> {
}
