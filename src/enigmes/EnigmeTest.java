package enigmes;

import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class EnigmeTest {
    private static List<Enigme> enigmes;

    @BeforeAll
    static void setUp() {
        try {
            enigmes = Enigme.chargerEnigmesDepuisJSON("src/enigmes/MesEnigmesJson.json");
        } catch (IOException e) {
            fail("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (JSONException e) {
            fail("Erreur lors du traitement du JSON : " + e.getMessage());
        }
    }

    @Test
    public void testChargerEnigmesDepuisJSON() {
        assertNotNull(enigmes);
        assertFalse(enigmes.isEmpty());
    }

    @Test
    public void testChoisirEnigmeAleatoire() {
        assertNotNull(enigmes);
        assertFalse(enigmes.isEmpty());
        Enigme enigmeAleatoire = Enigme.choisirEnigmeAleatoire(enigmes);
        assertNotNull(enigmeAleatoire);
        assertTrue(enigmes.contains(enigmeAleatoire));
    }

    @Test
    public void testVerifierReponse() {
        Enigme enigme = new Enigme("Question ?", "Réponse");
        assertTrue(enigme.verifierReponse("Réponse"));
        assertFalse(enigme.verifierReponse("Autre réponse"));
    }

    @Test
    public void testVerifierReponse1() {
        for (Enigme enigme : enigmes) {
            assertTrue(enigme.verifierReponse(enigme.getReponse()), "Réponse attendue : " + enigme.getReponse());
        }


    }

    @Test
    public void testVerifierReponsesUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        for (Enigme enigme : enigmes) {
            System.out.println(enigme.getQuestion());
            System.out.print("Entrez votre réponse : ");
            String reponseUtilisateur = scanner.nextLine();
            assertTrue(enigme.verifierReponse(reponseUtilisateur), "Réponse attendue : " + enigme.getReponse());
        }
    }

    @AfterAll
    static void tearDown() {
        // Nettoyage après l'exécution de tous les tests
        enigmes = null;
    }
}
