package com.thinkify.eventCRUD.Service;

import com.thinkify.eventCRUD.Model.Event;
import com.thinkify.eventCRUD.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event){
        return eventRepository.save(event);
//        return "created";
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event not found with id: " + id));
    }

    public Event updateEvent(Integer id, Event updatedEvent) {
        Event event = getEventById(id);
        event.setEventName(updatedEvent.getEventName());
        event.setDate(updatedEvent.getDate());
        event.setLocation(updatedEvent.getLocation());
        return eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }
}
