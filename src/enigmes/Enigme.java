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

public class Enigme {
    private String question;
    private String reponse;

    public Enigme(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponse() {
        return reponse;
    }

    public boolean verifierReponse(String reponseJoueur) {
        return reponseJoueur.equalsIgnoreCase(reponse);
    }

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

    public static Enigme choisirEnigmeAleatoire(List<Enigme> enigmes) {
        Random rand = new Random();
        return enigmes.get(rand.nextInt(enigmes.size()));
    }

}
