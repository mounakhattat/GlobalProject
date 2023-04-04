package loantree.example.pidev.services;



import loantree.example.pidev.Entities.Events;
import loantree.example.pidev.repository.IEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEvents  implements IServiceEvents {
    @Autowired
    private IEventsRepository iEventsRepository;
    @Override
    public List<Events> list() {
        return (List<Events>) iEventsRepository.findAll();
    }
    @Override
    public  Events add(Events e) {
        return iEventsRepository.save(e);    }
    @Override
    public Events update(Events e) {
        return iEventsRepository.save(e);
    }
    @Override
    public Events getOne(int id) {
        Events e = iEventsRepository.findById(id).get();
        return iEventsRepository.save(e);
    }

    @Override
    public List<Object> findById(int id) {
        return null;
    }

    @Override
    public boolean deleteEventById(int id) {
        if(iEventsRepository.existsById(id)) {
            iEventsRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    @Override
    public boolean updateEvent(Events events) {
        if (iEventsRepository.existsById(events.getId_Event())){
            iEventsRepository.save(events);
            return true;
        }
        return false;    }
    
    @Override
    public void delete(int id) {
        Events e = iEventsRepository.findById(id).get();

        iEventsRepository.delete(e);
    }



}
