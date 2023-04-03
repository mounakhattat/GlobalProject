package loantree.example.pidev.Controllers;


import loantree.example.pidev.Entities.Events;
import loantree.example.pidev.Services.ServiceEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
public class EventsController {
    @Autowired
    private ServiceEvents serviceEvents;
    @GetMapping("listEvents")
    public List<Events> listEvents(){
        return serviceEvents.list();
    }
    @PostMapping("addEvents")
    public Events addEvent(@RequestBody Events event){
        return serviceEvents.add(event);
    }

    @DeleteMapping("/Events/{id}")
    public void deleteEvent(@PathVariable("id") int id) {
        serviceEvents.deleteEventById(id);
    }
    @PutMapping("updateEvent/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable("id") int id, @RequestBody Events events) {
        events.setId_Event(id);
        boolean isUpdated = serviceEvents.updateEvent(events);
        if (isUpdated) {
            return ResponseEntity.ok("Event with id " + id + " updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
