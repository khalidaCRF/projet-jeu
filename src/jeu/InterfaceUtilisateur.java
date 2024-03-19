package jeu;
import java.util.Scanner;

public class InterfaceUtilisateur {
    private GestionCompte gestionCompte;

    public InterfaceUtilisateur() {
        this.gestionCompte = new GestionCompte();
    }

    public void demarrerJeu() {
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

