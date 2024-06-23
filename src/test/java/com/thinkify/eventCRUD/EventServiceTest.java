package com.thinkify.eventCRUD;

import com.thinkify.eventCRUD.Model.Event;
import com.thinkify.eventCRUD.Repository.EventRepository;
import com.thinkify.eventCRUD.Service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    private Event event1;
    private Event event2;

    private Event updateEvent;

    @BeforeEach
    public void setup() {
        event1 = new Event(1, "Event 1", LocalDate.of(2024, 6, 23), "Location 1","registrationDetail1");
        event2 = new Event(2, "Event 2", LocalDate.of(2024, 6, 24), "Location 2","registrationDetail2");
        updateEvent = new Event(1,"updated Event",LocalDate.of(2024, 6, 23), "Location 1","registrationDetail1");
    }

    @Test
    public void testCreateEvent(){
        Event event = new Event();
        event.setEventName("TestEvent");
        event.setDate(LocalDate.now());
        event.setLocation("testLocation");
        event.setRegistrationDetails("test registration detail");

        Mockito.when(eventRepository.save(Mockito.any(Event.class))).thenReturn(event);

        Event createdEvent = eventService.createEvent(event);

        assertNotNull(createdEvent);
        assertEquals("TestEvent",createdEvent.getEventName());
    }

    @Test
    public void testGetEventById() {
        System.out.println(event1);
        Mockito.when(eventRepository.findById(1)).thenReturn(Optional.of(event1));

        Event foundEvent = eventService.getEventById(1);
        assertNotNull(foundEvent);
        assertEquals("Event 1",foundEvent.getEventName());

        verify(eventRepository,times(1)).findById(1);
    }

    @Test
    public void testGetAllEvent(){
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        List<Event> eventList = eventService.getAllEvents();

        assertNotNull(eventList);
        assertEquals(2,eventList.size());
        assertEquals("Event 1",eventList.get(0).getEventName());
        assertEquals("Event 2",eventList.get(1).getEventName());
    }

//    @Test
//    public void getEventByID_NotFound() throws Exception{
//        Mockito.when(eventRepository.findById(3)).thenReturn(Optional.empty());
//        Event foundEvent = eventService.getEventById(3);
//        assertNull(foundEvent);
//        verify(eventRepository,times(1)).findById(3);
//    }

    @Test
    public void updateEventTest(){
        when(eventRepository.findById(1)).thenReturn(Optional.of(event1));
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(updateEvent);

        Event updatedEvent=eventService.updateEvent(1,updateEvent);

        assertNotNull(updatedEvent);
        assertEquals("updated Event",updatedEvent.getEventName());
        assertEquals("Location 1",updatedEvent.getLocation());

    }

    @Test
    public void testUpdateEventNotFound() {

        when(eventRepository.findById(2)).thenReturn(Optional.empty());
        try{
            Event updatedEvent = eventService.updateEvent(2, updateEvent);
            assertNull(updatedEvent);
        }catch (Exception e){
        }
        verify(eventRepository, times(1)).findById(2);
        verify(eventRepository, never()).save(Mockito.any(Event.class));
    }

    @Test
    public void deleteEventTest(){
        when(eventRepository.findById(1)).thenReturn(Optional.of(event1));

        eventService.deleteEvent(1);

        verify(eventRepository,times(1)).findById(1);
    }

    @Test
    public void deleteEventTestNotFound() throws Exception{
        when(eventRepository.findById(2)).thenReturn(Optional.empty());
        try {
            eventService.deleteEvent(2);
        }catch (Exception e){
        }
        verify(eventRepository, times(1)).findById(2);
        verify(eventRepository, never()).deleteById(2);
    }


}
