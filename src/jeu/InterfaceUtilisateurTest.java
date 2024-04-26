package jeu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceUtilisateurTest {

    @Test
    void testDemarrerJeu() {
        String input = "c\nutilisateur1\nmotdepasse1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InterfaceUtilisateur interfaceUtilisateur = new InterfaceUtilisateur();

        try {
            interfaceUtilisateur.demarrerJeu();
        } catch (Exception e) {
            fail("Une exception inattendue a été levée : " + e.getMessage());
        }

        System.setIn(System.in);
    }
}
