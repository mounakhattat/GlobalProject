package loantree.example.pidev.Controllers;


import loantree.example.pidev.Entities.Reclamation;
import loantree.example.pidev.Services.ServiceReclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReclamationController {

    @Autowired
    private ServiceReclamation serviceReclamation;

    @GetMapping("listReclamation")
    public List<Reclamation> listReclamation() {
        return serviceReclamation.list();
    }

    @PostMapping("addReclamation")
    public Reclamation addReclamation(@RequestBody Reclamation reclamation) {
        return serviceReclamation.add(reclamation);
    }

    @DeleteMapping("/Reclamation/{id}")
    public void deleteReclamation(@PathVariable("id") int id) {
        serviceReclamation.deleteReclamationById(id);
    }
    @PutMapping("updateRec/{id}")
    public ResponseEntity<String> updateReclamation(@PathVariable("id") int id, @RequestBody Reclamation reclamation) {
        reclamation.setIdReclamation(id);
        boolean isUpdated = serviceReclamation.updateReclamation(reclamation);
        if (isUpdated) {
            return ResponseEntity.ok("Reclamation with id " + id + " updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}