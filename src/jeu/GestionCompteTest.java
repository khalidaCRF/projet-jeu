package jeu;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionCompteTest {

    @Test
    public void testCreerCompte() throws JSONException {
        GestionCompte gestionCompte = new GestionCompte();
        assertTrue(gestionCompte.creerCompte("utilisateurTest", "motDePasseTest"));
    }

    @Test
    public void testVerifierConnexion() throws JSONException {
        GestionCompte gestionCompte = new GestionCompte();
        gestionCompte.creerCompte("utilisateurTest", "motDePasseTest");
        assertTrue(gestionCompte.verifierConnexion("utilisateurTest", "motDePasseTest"));
        assertFalse(gestionCompte.verifierConnexion("utilisateurInexistant", "motDePasseInexistant"));
    }
}
