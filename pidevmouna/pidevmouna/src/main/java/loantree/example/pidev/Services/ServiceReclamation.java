package loantree.example.pidev.Services;
import loantree.example.pidev.Entities.Reclamation;
import loantree.example.pidev.Repositories.IReclamationRepository;
import loantree.example.pidev.Services.interfaces.IServiceReclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ServiceReclamation  implements IServiceReclamation {
    @Autowired
    private IReclamationRepository iReclamationRepository;

    @Override
    public List<Reclamation> list() {
        return (List<Reclamation>) iReclamationRepository.findAll();
    }

    @Override
    public Reclamation add(Reclamation r) {
        return iReclamationRepository.save(r);
    }

    @Override
    public boolean update(Reclamation reclamation) {
        return false;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean deleteReclamationById(int id) {
        if(iReclamationRepository.existsById(id)) {
            iReclamationRepository.deleteById(id);
            return true;
        }
        else return false;
    }
    @Override
    public void UpdateReclamation(Reclamation reclamation)
    {
        iReclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation getOne(int id) {
        Reclamation r = iReclamationRepository.findById(id).get();
        return iReclamationRepository.save(r);
    }
    @Override
    public boolean updateReclamation(Reclamation reclamation) {
        if (iReclamationRepository.existsById(reclamation.getIdReclamation())){
            iReclamationRepository.save(reclamation);
            return true;
        }
        return false;
    }




}