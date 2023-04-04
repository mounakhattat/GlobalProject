package loantree.example.pidev.controllers;


import loantree.example.pidev.Entities.Reclamation;
import loantree.example.pidev.services.ServiceReclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Reclamation")
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