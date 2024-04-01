package jeu;

import enigmes.Enigme;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jeu {

    private GUI gui;
    private Zone zoneCourante;
    private List<Enigme> enigmes;
    private Scanner scanner;
    private int tentativesRestantes;
    private Enigme enigmeCourante;
    public Jeu() throws JSONException, IOException {

        gui = null;
        this.enigmes = Enigme.chargerEnigmesDepuisJSON("src/enigmes/MesEnigmesJson.json");
        creerCarte(enigmes);
        gui = null;
        scanner = new Scanner(System.in); // Initialisation du scanner pour la saisie de l'utilisateur
        tentativesRestantes = 2; // Définir le nombre de tentatives autorisées
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }

    private void creerCarte(List<Enigme> enigmes) {
        Zone [] zones = new Zone [5];
        zones[0] = new Zone("le couloir", "Couloir.jpg" );
        zones[1] = new Zone("l'escalier", "Escalier.jpg" );
        zones[2] = new Zone("la grande salle", "GrandeSalle.jpg" );
        zones[3] = new Zone("la salle à manger", "SalleAManger.jpg" );
        zones[4] = new Zone("musee", "musee.jpg" );
        zones[0].ajouteSortie(Sortie.EST, zones[1]);
        zones[1].ajouteSortie(Sortie.OUEST, zones[0]);
        zones[1].ajouteSortie(Sortie.EST, zones[2]);
        zones[2].ajouteSortie(Sortie.OUEST, zones[1]);
        zones[3].ajouteSortie(Sortie.NORD, zones[1]);
        zones[1].ajouteSortie(Sortie.SUD, zones[3]);
        zones[4].ajouteSortie(Sortie.SUD, zones[2]);
        zones[1].ajouteSortie(Sortie.SUD, zones[4]);

        // Associer une énigme aléatoire à chaque zone
        Random random = new Random();
        for (Zone zone : zones) {
            int randomIndex = random.nextInt(enigmes.size());
            Enigme enigme = enigmes.get(randomIndex);
            zone.setEnigme(enigme);
        }


        zoneCourante = zones[4];
    }

    private void afficherLocalisation() {
        gui.afficher( zoneCourante.descriptionLongue());
        gui.afficher();
    }

    private void afficherMessageDeBienvenue() {
        gui.afficher("Bienvenue !");
        gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide.");
        gui.afficher();
        afficherLocalisation();
        gui.afficheImage(zoneCourante.nomImage());
    }

    public void traiterCommande(String commandeLue) {
        gui.afficher( "> "+ commandeLue + "\n");
        switch (commandeLue.toUpperCase()) {
            case "?" : case "AIDE" :
                afficherAide();
                break;
            case "N" : case "NORD" :
                allerEn( "NORD");
                break;
            case "S" : case "SUD" :
                allerEn( "SUD");
                break;
            case "E" : case "EST" :
                allerEn( "EST");
                break;
            case "O" : case "OUEST" :
                allerEn( "OUEST");
                break;
            case "Q" : case "QUITTER" :
                terminer();
                break;
            default :
                gui.afficher("Commande inconnue");
                break;
        }
    }

    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisées sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }

    /*private void allerEn(String direction) {
    	Zone nouvelle = zoneCourante.obtientSortie( direction);
    	if ( nouvelle == null ) {
        	gui.afficher( "Pas de sortie " + direction);
    		gui.afficher();
    	}
        else {
        	zoneCourante = nouvelle;
        	gui.afficher(zoneCourante.descriptionLongue());
        	gui.afficher();
        	gui.afficheImage(zoneCourante.nomImage());
        }
    }*/

    private void terminer() {
        gui.afficher( "Au revoir...");
        gui.enable( false);
    }

    public void allerEn(String direction) {
        // Récupérer la nouvelle zone en fonction de la direction
        Zone nouvelle = zoneCourante.obtientSortie(direction);
        if (nouvelle == null) {
            gui.afficher("Pas de sortie " + direction);
            gui.afficher();
        } else {
            // Mettre à jour la zone courante
            zoneCourante = nouvelle;
            gui.afficher(zoneCourante.descriptionLongue());
            gui.afficher();
            gui.afficheImage(zoneCourante.nomImage());

            // Vérifier s'il y a des énigmes ou des actions spécifiques à cette zone
            traiterActionZone(zoneCourante);
        }
    }
    private void traiterActionZone(Zone zone) {
        // Ajoutez ici la logique pour gérer les actions spécifiques à chaque zone
        // Par exemple, afficher des énigmes ou des actions particulières
    }
    public Enigme obtenirEnigmeAleatoire() {
        Random random = new Random();
        int index = random.nextInt(enigmes.size()); // Générer un index aléatoire dans la plage des énigmes disponibles
        return enigmes.get(index); // Retourner l'énigme correspondant à l'index généré
    }
}
