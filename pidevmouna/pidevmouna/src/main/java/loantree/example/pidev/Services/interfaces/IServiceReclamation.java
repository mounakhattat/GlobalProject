package loantree.example.pidev.Services.interfaces;
import loantree.example.pidev.Entities.Reclamation;

import java.util.List;

public interface IServiceReclamation {

    List<Reclamation> list ();
    Reclamation add (Reclamation r);

    abstract boolean update(Reclamation reclamation);
    boolean delete (int id);

    void UpdateReclamation(Reclamation reclamation);

    Reclamation getOne(int id);

    boolean deleteReclamationById(int idReclamation);

    boolean updateReclamation(Reclamation reclamation);
}
