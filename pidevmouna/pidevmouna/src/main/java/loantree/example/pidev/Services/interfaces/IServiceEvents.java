package loantree.example.pidev.Services.interfaces;

import loantree.example.pidev.Entities.Events;
import loantree.example.pidev.Entities.Reclamation;

import java.util.List;

public interface IServiceEvents {

    List<Events> list ();
    Events add (Events r);
    Events update (Events r);

    boolean deleteEventById(int id);

    boolean updateEvent(Events events);

    void delete (int id);

    Events getOne(int id);

    List<Object> findById(int id);
}

