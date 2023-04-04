package loantree.example.pidev.services;



import loantree.example.pidev.Entities.Events;

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

