package app.rabbit;

import app.domain.Computer;
import app.exception.NoPlaceException;
import app.exception.UnknownComputerException;
import app.exception.UnknownRoomException;
import app.service.ComputerService;
import app.service.RoomService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class Listeners {

    @Autowired
    ComputerService computerService;

    @Autowired
    RoomService roomService;

    @RabbitListener(queues = "forservice1")
    public String service1(String message) throws UnknownComputerException, UnknownRoomException {
        int id = Integer.parseInt(message);
        computerService.WriteOffPC(id);
        return "Компьютер №" + message + " успешно списан";
        //return "Received on worker : " + id;
    }

    @RabbitListener(queues = "forservice2")
    public String service2(String message) throws UnknownComputerException, NoPlaceException {
        String buffer[] = message.split(" ");
        System.out.println(buffer[0]+buffer[1]);
        computerService.ChangePCPlace(Integer.parseInt(buffer[0]), Integer.parseInt(buffer[1]));
        return "Компьютер №" + buffer[0] +" переставлен на место №" + computerService.findPC(Integer.parseInt(buffer[0])).getId_place();
    }


}
