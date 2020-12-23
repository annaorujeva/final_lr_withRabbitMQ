package app.controller;


import app.exception.NoPlaceException;
import app.exception.UnknownComputerException;
import app.exception.UnknownRoomException;
import app.service.ComputerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RestController {
    public RestController() {
    }

    @Autowired
    RabbitTemplate connection;

    @Autowired
    ComputerService computerService;


    @GetMapping("/writeoff/{id_pc}")//Готовая ссылка для проверки: http://localhost:8080/writeoff/1
    public ResponseEntity<String> writeoff(@PathVariable("id_pc") int id_pc){
        String message = String.valueOf(id_pc);
        connection.setExchange("exchangeA");
        String response = (String) connection.convertSendAndReceive(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/changepcplace/{id_pc}/{id_place_to}")  //Готовая ссылка для проверки: http://localhost:8080/changepcplace/1/10
    public ResponseEntity<String> changepcplace(@PathVariable("id_pc") int id_pc, @PathVariable("id_place_to") int id_place){
        String message = String.valueOf(id_pc) + " " + String.valueOf(id_place);
        connection.setExchange("exchangeA");
        String response = (String) connection.convertSendAndReceive(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
