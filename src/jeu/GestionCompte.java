package jeu;

import java.sql.*;

public class GestionCompte {
    // Informations de connexion à la base de données
    private static final String url = "jdbc:postgresql://localhost:5432/JEU";
    private static final String utilisateurDB = "postgres";
    private static final String motDePasseDB = "admin";

    // Méthode pour créer un compte utilisateur dans la base de données
    public boolean creerCompte(String nomUtilisateur, String motDePasse) {
        // Requête SQL pour vérifier si le nom d'utilisateur existe déjà
        String sqlVerification = "SELECT COUNT(*) FROM comptes_utilisateurs WHERE nom_utilisateur = ?";

        // Requête SQL pour insérer un nouveau compte utilisateur
        String sqlInsertion = "INSERT INTO comptes_utilisateurs (nom_utilisateur, mot_de_passe) VALUES (?, ?)";

        // Définir une variable pour indiquer si le compte a été créé avec succès
        boolean compteCree = false;

        try (
                // Établir la connexion à la base de données
                Connection connexion = DriverManager.getConnection(url, utilisateurDB, motDePasseDB);
                // Préparer la déclaration SQL pour la vérification
                PreparedStatement statementVerification = connexion.prepareStatement(sqlVerification);
                // Préparer la déclaration SQL pour l'insertion
                PreparedStatement statementInsertion = connexion.prepareStatement(sqlInsertion)
        ) {
            // Remplacer les paramètres de la requête de vérification par le nom d'utilisateur
            statementVerification.setString(1, nomUtilisateur);

            // Exécuter la requête de vérification
            ResultSet resultSet = statementVerification.executeQuery();
            resultSet.next();
            int nombreUtilisateurs = resultSet.getInt(1);

            // Si le nom d'utilisateur existe déjà, afficher un message d'erreur et retourner false
            if (nombreUtilisateurs > 0) {
                System.out.println("Le nom d'utilisateur est déjà utilisé. Veuillez choisir un autre nom.");
                return false;
            }

            // Si le nom d'utilisateur est unique, insérer le nouveau compte utilisateur
            statementInsertion.setString(1, nomUtilisateur);
            statementInsertion.setString(2, motDePasse);
            int lignesModifiees = statementInsertion.executeUpdate();

            // Si au moins une ligne a été modifiée, le compte a été créé avec succès
            compteCree = (lignesModifiees > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compteCree;
    }
}
