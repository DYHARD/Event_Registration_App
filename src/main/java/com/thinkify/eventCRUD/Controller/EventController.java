package com.thinkify.eventCRUD.Controller;

import com.thinkify.eventCRUD.Model.Event;
import com.thinkify.eventCRUD.Model.UserInfo;
import com.thinkify.eventCRUD.Repository.UserRepository;
import com.thinkify.eventCRUD.Service.EventService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getAllEvent(){
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        Event event1 = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(event1);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public String healtCheck(){
        return "Service is Running";
    }

    @GetMapping("/event/health")
    public String eventHealth(){
        return "Event is running";
    }
}
