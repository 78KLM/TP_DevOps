import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PanierReductionTest {

    @ParameterizedTest
    @CsvSource({
            // quantite, prixUnitaire, code, totalAttendu
            // --- Cas de base (1 article à 100€) ---
            "1, 100.0, '', 100.0",           // Pas de réduction
            "1, 100.0, 'REDUC10', 90.0",     // -10%
            "1, 100.0, 'REDUC20', 80.0",     // -20%

            // --- Cas avec plusieurs articles (ex: 10 classeurs à 10€ = 100€) ---
            "10, 10.0, '', 100.0",           // Pas de réduction
            "10, 10.0, 'REDUC10', 90.0",     // -10%
            "10, 10.0, 'REDUC20', 80.0",     // -20%

            // --- Autre montant (ex: 2 articles à 25€ = 50€) ---
            "2, 25.0, 'REDUC10', 45.0"       // 50€ - 10% = 45€
    })
    void calculerTotalDoitAppliquerLaBonneReduction(
            int quantite, double prixUnitaire, String code, double totalAttendu) {

        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-TEST", "Article Test", prixUnitaire);
        panier.ajouterArticle(article, quantite);

        // Agir
        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }

        // Affirmer
        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }
}