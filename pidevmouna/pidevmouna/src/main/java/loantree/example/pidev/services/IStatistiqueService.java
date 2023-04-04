package loantree.example.pidev.services;



import java.util.List;
import java.util.Map;

    public interface IStatistiqueService {
        Map<String, Double> getChargesParMois();

        Map<String, Double> getRevenuesParMois();

        Map<String, Double> getBeneficesParMois();

        List<String> getMoisAvecCharges();

        List<String> getMoisAvecRevenues();

        List<String> getMoisAvecBenefices();
    }


