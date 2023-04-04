package loantree.example.pidev.controllers;


import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import loantree.example.pidev.Entities.Accounting;
import loantree.example.pidev.repository.AccountingRepository;
import loantree.example.pidev.services.AccountingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/accounting")
public class AccountingController {

@Autowired
AccountingService accountingService;
@Autowired
AccountingRepository accountingRepository;



    //http://localhost:8089/stationSki/accounting/retrieve-all-accounting
    @GetMapping("/retrieve-all-accounting")
    public List<Accounting> getAccounting() {
        return accountingService.retrieveAllAccountings();
    }
    // http://localhost:8089/stationSki/moniteur/retrieve-moniteur/8
    @GetMapping("/retrieve-accounting/{accounting-id}")
    public Accounting retrieveAllAccounting(@PathVariable("accounting-id") Integer accountingId) {
        return accountingService.retrieveAccounting(accountingId);
    }
    // http://localhost:8089/stationSki/moniteur/add-moniteur
    @PostMapping("/add-accounting")
    public Accounting addAccounting(@RequestBody Accounting c) {
        return accountingService.addAccounting(c);
    }
    // http://localhost:8089/stationSki/moniteur/remove-moniteur/1
    @DeleteMapping("/remove-accounting/{accounting-id}")
    public void removeAccounting(@PathVariable("accounting-id") Integer accountingId) {
        accountingService.deleteAccounting(accountingId);
    }
    // http://localhost:8089/stationSki/moniteur/update-moniteur
    @PutMapping("/update-accounting")
    public Accounting updateAccounting(@RequestBody Accounting c) {
        return accountingService.updateAccounting(c);
    }

    // http://localhost:8089/stationSki/accounting/chiffre_affaires?totalcharges=100&totalrevenues=500
 /*   @PostMapping("/chiffre_affaires")
    public double calculerChiffreAffaires(@RequestParam double totalcharges, @RequestParam double totalrevenues) {
        Accounting accounting = accountingService.saveChiffreAffaires(totalcharges, totalrevenues);
        return accounting.getChiffreAffaires();
    }
*/
    @PostMapping("/chiffre_affaires")
    public double calculerChiffreAffaires(@RequestParam double totalcharges, @RequestParam double totalrevenues) {
        // Calcul du chiffre d'affaires
        double chiffreAffaires = totalrevenues - totalcharges;

        // Récupération de la dernière entité Accounting dans la base de données
        Accounting accounting = accountingRepository.findFirstByOrderByDateAccDesc();

        // Mise à jour de l'entité Accounting avec le chiffre d'affaires calculé
        if (accounting != null) {
            accounting.setTotalCharges(totalcharges);
            accounting.setTotalRevenues(totalrevenues);
            accounting.setChiffreAffaires(chiffreAffaires);
        } else {
            // Création d'une nouvelle entité Accounting si aucune n'existe dans la base de données
            accounting = new Accounting(LocalDate.now(), "Chiffre d'affaires mensuel", totalcharges, totalrevenues);
            accounting.setChiffreAffaires(chiffreAffaires);
        }

        // Enregistrement de l'entité Accounting mise à jour ou créée dans la base de données
        accountingRepository.save(accounting);

        // Affichage du chiffre d'affaires récupéré de la base de données
        Accounting accountingWithChiffreAffaires = accountingRepository.findFirstByOrderByDateAccDesc();
        System.out.println("Chiffre d'affaires sauvegardé : " + accountingWithChiffreAffaires.getChiffreAffaires());

        return chiffreAffaires;
    }
  /*  @GetMapping("/chiffre_affaires/annee")
    public List<Double> calculerChiffreAffairesAnnee() {
        // Récupération de l'année en cours
        int anneeEnCours = LocalDate.now().getYear();

        // Initialisation de la liste des chiffres d'affaires mensuels
        List<Double> chiffresAffairesMensuels = new ArrayList<>();

        // Boucle sur les 12 mois de l'année en cours
        for (int mois = 1; mois <= 12; mois++) {
            // Récupération de la première et de la dernière date du mois en cours
            LocalDate debutMois = LocalDate.of(anneeEnCours, mois, 1);
            LocalDate finMois = debutMois.withDayOfMonth(debutMois.lengthOfMonth());

            // Récupération des totaux de charges et de revenus pour le mois en cours
            double totalCharges = chargeService.calculateTotalCharge(debutMois, finMois);
            double totalRevenues = revenueService.calculateTotalRevenuee(debutMois, finMois);

            // Calcul du chiffre d'affaires pour le mois en cours
            double chiffreAffaires = totalRevenues - totalCharges;

            // Ajout du chiffre d'affaires à la liste des chiffres d'affaires mensuels
            chiffresAffairesMensuels.add(chiffreAffaires);
        }

        return chiffresAffairesMensuels;
    }
}*/

}
