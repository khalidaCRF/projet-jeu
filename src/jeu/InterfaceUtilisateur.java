package jeu;
import org.json.JSONException;

import java.util.Scanner;
 /**
 * Cette classe gère l'interface utilisateur pour la création de compte et la connexion au jeu.
 */
public class InterfaceUtilisateur {
    private GestionCompte gestionCompte;

    public InterfaceUtilisateur() {
        this.gestionCompte = new GestionCompte();
    }
    /**
     * Lance le jeu.
     *
     * <p>Cette méthode permet à l'utilisateur de se connecter ou de créer un nouveau compte
     * afin de démarrer le jeu. Elle guide l'utilisateur tout au long du processus et
     * assure que les informations fournies sont valides.</p>
     *
     * <p>Si l'utilisateur choisit de se connecter (en saisissant "c"),
     * il sera invité à entrer son nom d'utilisateur et son mot de passe. S'il fournit
     * des informations valides, il pourra accéder au jeu.</p>
     *
     * <p>Si l'utilisateur choisit de créer un nouveau compte (en saisissant "n"),
     * il lui sera demandé de fournir un nom d'utilisateur et un mot de passe. Un nouveau compte
     * sera créé et il pourra ensuite s'y connecter.</p>
     *
     * @throws JSONException En cas d'erreur de manipulation JSON.
     */
    public void demarrerJeu() throws JSONException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue au jeu !");

        while (true) {
            System.out.println("Voulez-vous vous connecter (c) ou créer un compte (n) ?");
            String choix = scanner.nextLine();

            if (choix.equalsIgnoreCase("c")) {
                System.out.println("Entrez votre nom d'utilisateur :");
                String nomUtilisateur = scanner.nextLine();
                System.out.println("Entrez votre mot de passe :");
                String motDePasse = scanner.nextLine();

                // Vérifier l'authentification
                if (gestionCompte.creerCompte(nomUtilisateur, motDePasse)) {
                    System.out.println("Vous êtes connecté !");
                    // L'utilisateur est authentifié, vous pouvez maintenant lancer le jeu
                    // (appelez la méthode pour démarrer le jeu)
                    break;
                } else {
                    System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
                }
            } else if (choix.equalsIgnoreCase("n")) {
                System.out.println("Création d'un nouveau compte :");
                System.out.println("Entrez votre nom d'utilisateur :");
                String nomUtilisateur = scanner.nextLine();
                System.out.println("Entrez votre mot de passe :");
                String motDePasse = scanner.nextLine();

                // Créer un nouveau compte
                gestionCompte.creerCompte(nomUtilisateur, motDePasse);
                // L'utilisateur peut maintenant se connecter avec son nouveau compte
            } else {
                System.out.println("Choix invalide. Veuillez saisir 'c' pour vous connecter ou 'n' pour créer un compte.");
            }
        }

        scanner.close();
    }
}

