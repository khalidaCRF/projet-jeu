package jeu;
import enigmes.Enigme;

import java.util.HashMap;
/**
 * Représente une zone dans le jeu d'aventure.
 */
public class Zone 
{
    private String description;
    private String nomImage;
    private HashMap<String,Zone> sorties;
    private Enigme enigme;
    /**
     * Constructeur de la classe Zone.
     *
     * @param description La description de la zone.
     * @param image       Le nom de l'image associée à la zone.
     */
    public Zone(String description, String image) {
        this.description = description;
        nomImage = image;
        sorties = new HashMap<>();
    }

    /**
     * Méthode pour définir l'énigme associée à cette zone.
     *
     * @param enigme L'énigme associée à la zone.
     */
    public void setEnigme(Enigme enigme) {
        this.enigme = enigme;
    }
    /**
     * Méthode pour ajouter une sortie à cette zone.
     *
     * @param sortie      La direction de la sortie.
     * @param zoneVoisine La zone voisine à laquelle la sortie mène.
     */
    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }
    /**
     * Méthode pour obtenir le nom de l'image associée à cette zone.
     *
     * @return Le nom de l'image associée à la zone.
     */
    public String nomImage() {
        return nomImage;
    }
     
    public String toString() {
        return description;
    }
    /**
     * Méthode pour obtenir une description longue de la zone, y compris les sorties disponibles.
     *
     * @return Une description longue de la zone avec les sorties disponibles.
     */
    public String descriptionLongue()  {
        return "Vous êtes dans " + description + "\nSorties : " + sorties();
    }
    /**
     * Méthode privée pour obtenir la liste des sorties disponibles.
     *
     * @return Une chaîne représentant les directions des sorties disponibles.
     */
    private String sorties() {
        return sorties.keySet().toString();
    }
    /**
     * Méthode pour obtenir la zone voisine dans une direction donnée.
     *
     * @param direction La direction de la sortie.
     * @return La zone voisine dans la direction donnée, ou null s'il n'y a pas de sortie dans cette direction.
     */
    public Zone obtientSortie(String direction) {
    	return sorties.get(direction);
    }
}

