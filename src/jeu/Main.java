package jeu;

import org.json.JSONException;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws JSONException, IOException {
		Jeu jeu = new Jeu();
		GUI gui = new GUI( jeu);
		jeu.setGUI( gui);
		gui.afficherInterfaceConnexion();
	}
}
