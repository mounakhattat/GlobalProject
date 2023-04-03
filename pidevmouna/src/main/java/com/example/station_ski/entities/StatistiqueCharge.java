package com.example.station_ski.entities;


@Controller
public class StatistiqueCharge{

    @Autowired
    private IChargeService chargeService;

    @GetMapping("/statistiques")
    public String getStatistiques(Model model) {
        /* Récupérer les données à afficher */
        List<Object[]> chiffreAffaireMensuel = chargeService.findChargeSumByMonth();

        /* Préparer les données pour la vue */
        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        for (Object[] row : chiffreAffaireMensuel) {
            String label = String.format("%02d/%d", row[0], row[1]);
            labels.add(label);
            data.add((Long) row[2]);
        }

        /* Envoyer les données à la vue */
        model.addAttribute("labels", labels);
        model.addAttribute("data", data);

        /* Renvoyer le nom de la vue */
        return "index";
    }
}
