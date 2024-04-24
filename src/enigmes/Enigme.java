package enigmes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Cette classe représente une énigme avec une question et une réponse.
 */
public class Enigme {
    private String question;
    private String reponse;
    /**
     * Constructeur de la classe Enigme.
     *
     * @param question La question de l'énigme.
     * @param reponse  La réponse de l'énigme.
     */
    public Enigme(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }
    /**
     * Obtient la question de l'énigme.
     *
     * @return La question de l'énigme.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Obtient la réponse de l'énigme.
     *
     * @return La réponse de l'énigme.
     */
    public String getReponse() {
        return reponse;
    }
    /**
     * Vérifie si la réponse donnée par le joueur est correcte.
     *
     * @param reponseJoueur La réponse donnée par le joueur.
     * @return true si la réponse est correcte, sinon false.
     */
    public boolean verifierReponse(String reponseJoueur) {
        return reponseJoueur.equalsIgnoreCase(reponse);
    }
    /**
     * Charge les énigmes à partir d'un fichier JSON.
     *
     * @param nomFichier Le nom du fichier JSON contenant les énigmes.
     * @return Une liste d'énigmes.
     * @throws IOException   En cas d'erreur d'entrée/sortie lors de la lecture du fichier.
     * @throws JSONException En cas d'erreur de manipulation JSON.
     */
    public static List<Enigme> chargerEnigmesDepuisJSON(String nomFichier) throws IOException, JSONException, JSONException {
        List<Enigme> enigmes = new ArrayList<>();
        String contenuFichier = new String(Files.readAllBytes(Paths.get(nomFichier)));
        JSONArray jsonArray = new JSONArray(contenuFichier);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String question = jsonObject.getString("question");
            String reponse = jsonObject.getString("solution");
            enigmes.add(new Enigme(question, reponse));
        }
        return enigmes;
    }
    /**
     * Choisi une énigme aléatoire parmi une liste d'énigmes.
     *
     * @param enigmes La liste d'énigmes parmi lesquelles choisir.
     * @return Une énigme choisie aléatoirement.
     */
    public static Enigme choisirEnigmeAleatoire(List<Enigme> enigmes) {
        Random rand = new Random();
        return enigmes.get(rand.nextInt(enigmes.size()));
    }

}
