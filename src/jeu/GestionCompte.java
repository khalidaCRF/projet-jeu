package jeu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Cette classe gère les opérations de création de compte, de connexion et de gestion des comptes utilisateur.
 */
public class GestionCompte {
    private static final String FICHIER_COMPTES = "comptes.json";
    /**
     * Crée un nouveau compte utilisateur.
     *
     * @param nomUtilisateur Le nom d'utilisateur du nouveau compte.
     * @param motDePasse     Le mot de passe du nouveau compte.
     * @return true si le compte a été créé avec succès, sinon false.
     * @throws JSONException En cas d'erreur de manipulation JSON.
     */
    public boolean creerCompte(String nomUtilisateur, String motDePasse) throws JSONException {
        JSONArray comptes = chargerComptes();
        if (comptes == null) {
            System.err.println("Erreur lors du chargement du fichier des comptes.");
            return false;
        }

        // Vérifier si le nom d'utilisateur existe déjà
        for (int i = 0; i < comptes.length(); i++) {
            JSONObject compte = comptes.getJSONObject(i);
            String nom = compte.getString("nom_utilisateur");
            if (nom.equals(nomUtilisateur)) {
                System.out.println("Le nom d'utilisateur est déjà utilisé. Veuillez choisir un autre nom.");
                return false;
            }
        }

        // Ajouter le nouveau compte
        JSONObject nouveauCompte = new JSONObject();
        nouveauCompte.put("nom_utilisateur", nomUtilisateur);
        nouveauCompte.put("mot_de_passe", motDePasse);
        comptes.put(nouveauCompte);

        // Enregistrer les comptes dans le fichier JSON
        enregistrerComptes(comptes);

        return true;
    }

    /**
     * Charge les comptes à partir du fichier JSON.
     *
     * @return Un tableau JSON contenant les comptes.
     */
    private JSONArray chargerComptes() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(FICHIER_COMPTES));
            String jsonString = new String(jsonData);
            return new JSONArray(jsonString);
        } catch (IOException | JSONException e) {
            return new JSONArray(); // Retourner un nouveau tableau JSON en cas d'erreur
        }
    }

    /**
     * Enregistre les comptes dans le fichier JSON.
     *
     * @param comptes Le tableau JSON contenant les comptes à enregistrer.
     */
    private void enregistrerComptes(JSONArray comptes) {
        try (FileWriter file = new FileWriter(FICHIER_COMPTES)) {
            file.write(comptes.toString());
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement des comptes dans le fichier.");
            e.printStackTrace();
        }
    }

    /**
     * Vérifie les informations de connexion.
     *
     * @param nomUtilisateur Le nom d'utilisateur à vérifier.
     * @param motDePasse     Le mot de passe à vérifier.
     * @return true si les informations de connexion sont valides, sinon false.
     * @throws JSONException En cas d'erreur de manipulation JSON.
     */

    public boolean verifierConnexion(String nomUtilisateur, String motDePasse) throws JSONException {
        try {
            // Lire le contenu du fichier JSON
            String jsonContent = new String(Files.readAllBytes(Paths.get("comptes.json")));
            // Convertir le contenu en objet JSON
            JSONArray jsonArray = new JSONArray(jsonContent);
            // Parcourir tous les objets JSON dans le tableau
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // Vérifier si le nom d'utilisateur et le mot de passe correspondent
                if (nomUtilisateur.equals(jsonObject.getString("nom_utilisateur")) &&
                        motDePasse.equals(jsonObject.getString("mot_de_passe"))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
