package jeu;
import java.util.ArrayList;
import java.util.List;
/**
 * Cette énumération représente les commandes disponibles dans le jeu.
 */
public enum Commande {
	NORD("N", "N (aller à la sortie nord)"),
	SUD("S", "S (aller à la sortie sud)"),
	EST("E", "E (aller à la sortie est)"),
	OUEST("O", "O (aller à la sortie ouest)"),
	AIDE("?", "? (aide)"),
	QUITTER("Q", "Q (quitter)");

	private String abreviation;
	private String description;/**
	 * Constructeur de l'énumération Commande.
	 *
	 * @param abreviation L'abréviation de la commande.
	 * @param description La description de la commande.
	 */

	private Commande(String abreviation, String description ) {
		this.abreviation = abreviation;
		this.description = description;
	}
	/**
	 * Renvoie une représentation textuelle de l'énumération.
	 *
	 * @return Le nom de l'énumération.
	 */
	@Override
	public String toString() {
		return name();
	}
	/**
	 * Renvoie toutes les descriptions des commandes disponibles.
	 *
	 * @return Une liste contenant toutes les descriptions des commandes.
	 */
	public static List<String> toutesLesDescriptions() {
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.description);
		}
		return resultat;
	}
	/**
	 * Renvoie toutes les abréviations des commandes disponibles.
	 *
	 * @return Une liste contenant toutes les abréviations des commandes.
	 */
	public static List<String> toutesLesAbreviations() {
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.abreviation);
		}
		return resultat;
	}
	/**
	 * Renvoie tous les noms des commandes disponibles.
	 *
	 * @return Une liste contenant tous les noms des commandes.
	 */
	public static List<String> tousLesNoms() {
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.name());
		}
		return resultat;
	}

}
