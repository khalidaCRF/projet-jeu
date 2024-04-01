package jeu;
import enigmes.Enigme;

import java.util.HashMap;

public class Zone 
{
    private String description;
    private String nomImage;
    private HashMap<String,Zone> sorties;
    private Enigme enigme;

    public Zone(String description, String image) {
        this.description = description;
        nomImage = image;
        sorties = new HashMap<>();
    }
    public void setEnigme(Enigme enigme) {
        this.enigme = enigme;
    }

    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }

    public String nomImage() {
        return nomImage;
    }
     
    public String toString() {
        return description;
    }

    public String descriptionLongue()  {
        return "Vous êtes dans " + description + "\nSorties : " + sorties();
    }

    private String sorties() {
        return sorties.keySet().toString();
    }

    public Zone obtientSortie(String direction) {
    	return sorties.get(direction);
    }
}

