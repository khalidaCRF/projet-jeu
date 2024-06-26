/*
package jeu;
import enigmes.Enigme;
import org.json.JSONException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
*/
/**
 * Cette classe gère l'interface utilisateur graphique (GUI) pour le jeu d'aventure.
 *//*

public class GUI implements ActionListener {
    private Jeu jeu;
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;
    private static GestionCompte gestionCompte = new GestionCompte();
    private JButton nordButton;
    private JButton sudButton;
    private JButton estButton;
    private JButton ouestButton;
    private JTextField nomUtilisateurField;
    private JPasswordField motDePasseField;
    */
/**
     * Constructeur de la classe GUI.
     * Initialise l'interface utilisateur graphique.
     *
     * @param j Instance du jeu.
     *//*

    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
        afficherInterfaceConnexion();
    }
    */
/**
     * Affiche un texte dans la zone de texte.
     *
     * @param s Le texte à afficher.
     *//*

    public void afficher(String s) {
        texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
    }

    public void afficher() {
        afficher("\n");
    }
    */
/**
     * Affiche une image spécifiée.
     *
     * @param nomImage Le nom de l'image à afficher.
     *//*

    public void afficheImage(String nomImage) {
        URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + nomImage);
        if (imageURL != null) {
            image.setIcon(new ImageIcon(imageURL));
            fenetre.pack();
        }
    }
    */
/**
     * Active ou désactive la zone de texte pour l'entrée du joueur.
     *
     * @param ok true pour activer, false pour désactiver.
     *//*

    public void enable(boolean ok) {
        entree.setEditable(ok);
        if (!ok)
            entree.getCaret().setBlinkRate(0);
    }

    private void creerGUI() {
        fenetre = new JFrame("Jeu");

        entree = new JTextField(34);

        texte = new JTextArea();
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100, 100));

        JPanel panel = new JPanel();
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);

        fenetre.getContentPane().add(panel, BorderLayout.CENTER);

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.pack();
        fenetre.setVisible(true);
        entree.requestFocus();
    }
    */
/**
     * Gère les actions de l'utilisateur.
     *
     * @param e L'événement déclenché par l'utilisateur.
     *//*

    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        jeu.traiterCommande(commandeLue);
    }

    public void afficherInterfaceConnexion() {
        // Affichage de l'interface graphique pour la connexion
        fenetre = new JFrame("Connexion au jeu");
        fenetre.setSize(400, 200);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        fenetre.add(panel);
        placeComponents(panel);
        fenetre.setVisible(true);

        // Jouer le fichier audio de bienvenue
        AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
    }
    */
/**
     * Place les composants de l'interface de connexion sur le panneau spécifié.
     *
     * @param panel Le panneau sur lequel placer les composants de l'interface de connexion.
     *//*


    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        userLabel.setBounds(10, 20, 120, 25);
        panel.add(userLabel);

        nomUtilisateurField = new JTextField(20);
        nomUtilisateurField.setBounds(140, 20, 165, 25);
        panel.add(nomUtilisateurField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(10, 50, 120, 25);
        panel.add(passwordLabel);

        motDePasseField = new JPasswordField(20);
        motDePasseField.setBounds(140, 50, 165, 25);
        panel.add(motDePasseField);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setBounds(10, 80, 120, 25);
        panel.add(loginButton);

        JButton createAccountButton = new JButton("Créer un compte");
        createAccountButton.setBounds(140, 80, 165, 25);
        panel.add(createAccountButton);

        // Action à effectuer lorsque l'utilisateur appuie sur le bouton de connexion
        loginButton.addActionListener(e -> {
            String username = nomUtilisateurField.getText();
            String password = new String(motDePasseField.getPassword());
            // Vérifier les identifiants de connexion
            try {
                if (gestionCompte.verifierConnexion(username, password)) {
                    fenetre.dispose(); // Fermer la fenêtre de connexion
                    // Afficher l'interface principale du jeu
                    afficherInterface();
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Action à effectuer lorsque l'utilisateur appuie sur le bouton de création de compte
        createAccountButton.addActionListener(e -> {
            String username = nomUtilisateurField.getText();
            String password = new String(motDePasseField.getPassword());
            // Appeler la méthode de création de compte de GestionCompte
            try {
                gestionCompte.creerCompte(username, password);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    */
/**
     * Affiche l'interface principale du jeu d'aventure.
     *
     * @implNote Cette méthode crée et affiche l'interface graphique du jeu d'aventure,
     *           composée de boutons de direction (nord, sud, est, ouest) et d'une grille 300x200.
     *           Elle initialise également les écouteurs d'événements pour chaque bouton afin de
     *           permettre au joueur de se déplacer dans le jeu. De plus, elle associe chaque
     *           bouton à une action d'affichage de fenêtre contextuelle pour une énigme.
     *//*

    private void afficherInterface() {
        JFrame frame = new JFrame("Jeu d'aventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200); // Taille réduite pour les boutons plus petits

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3)); // Grille 3x3

        // Initialisation des boutons avec des flèches vides
        nordButton = new JButton("");
        sudButton = new JButton("");
        estButton = new JButton("");
        ouestButton = new JButton("");

        // Ajout des écouteurs d'événements pour les boutons (désactivés par défaut)
        nordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("NORD"); // Déplacer le joueur vers le nord
            }
        });
        sudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("SUD"); // Déplacer le joueur vers le sud
            }
        });
        estButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("EST"); // Déplacer le joueur vers l'est
            }
        });
        ouestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("OUEST"); // Déplacer le joueur vers l'ouest
            }
        });

        // Ajout des boutons au panneau
        panel.add(new JLabel());
        panel.add(nordButton);
        panel.add(new JLabel());
        panel.add(ouestButton);
        panel.add(new JLabel());
        panel.add(estButton);
        panel.add(new JLabel());
        panel.add(sudButton);
        panel.add(new JLabel());

        frame.add(panel);
        frame.setVisible(true);
        nordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
        sudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
        estButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
        ouestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
    }
    */
/**
     * Affiche une fenêtre contextuelle pour une énigme.
     *
     * @implNote La méthode obtient une énigme aléatoire du jeu et affiche une fenêtre contextuelle
     *           avec la question de l'énigme. Si l'utilisateur entre une réponse, cette méthode vérifie
     *           si la réponse est correcte. Si la réponse est correcte, un message de félicitations est affiché
     *           et l'image est mise à jour. Si la réponse est incorrecte, un message d'erreur est affiché et
     *           une deuxième tentative est offerte. Si la deuxième tentative échoue, un message "Game Over" est affiché.
     *//*


    private void afficherPopupEnigme() {
        Enigme enigme = jeu.obtenirEnigmeAleatoire(); // Méthode à implémenter dans la classe Jeu
        String question = enigme.getQuestion();
        JTextField reponseField = new JTextField();
        Object[] message = { question, reponseField };
        int option = JOptionPane.showConfirmDialog(null, message, "Énigme", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String reponse = reponseField.getText();
            if (enigme.verifierReponse(reponse)) {
                // Réponse correcte
                JOptionPane.showMessageDialog(null, "Bravo, vous avez résolu l'énigme !", "Correct", JOptionPane.INFORMATION_MESSAGE);
                // Jouer un message vocal de félicitations
                AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
                // Mettre à jour l'image en cas de réponse correcte
                afficheImage("image_correcte.jpg");
            } else {
                // Réponse incorrecte
                JOptionPane.showMessageDialog(null, "Réponse incorrecte. Il vous reste 2 tentatives.", "Incorrect", JOptionPane.ERROR_MESSAGE);
                // Afficher une deuxième fois la popup pour une deuxième tentative
                option = JOptionPane.showConfirmDialog(null, message, "Énigme", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    reponse = reponseField.getText();
                    if (enigme.verifierReponse(reponse)) {
                        // Réponse correcte lors de la deuxième tentative
                        JOptionPane.showMessageDialog(null, "Bravo, vous avez résolu l'énigme !", "Correct", JOptionPane.INFORMATION_MESSAGE);
                        // Jouer un message vocal de félicitations
                        AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
                        // Mettre à jour l'image en cas de réponse correcte
                        afficheImage("image_correcte.jpg");
                    } else {
                        // Deuxième tentative incorrecte, afficher "Game Over"
                        JOptionPane.showMessageDialog(null, "Game Over", "Incorrect", JOptionPane.ERROR_MESSAGE);
                        // Jouer un message vocal d'échec
                        AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
                    }
                }
            }
        }
    }

    */
/**
     * Met à jour l'affichage des flèches en fonction des directions possibles.
     *
     * @param nord  true si la direction nord est possible, sinon false.
     * @param sud   true si la direction sud est possible, sinon false.
     * @param est   true si la direction est possible, sinon false.
     * @param ouest true si la direction ouest est possible, sinon false.
     *//*
    public void mettreAJourFleches(boolean nord, boolean sud, boolean est, boolean ouest) {
        nordButton.setText(nord ? "\u2191" : ""); // Flèche vers le nord si possible, sinon vide
        sudButton.setText(sud ? "\u2193" : ""); // Flèche vers le sud si possible, sinon vide
        estButton.setText(est ? "\u2192" : ""); // Flèche vers l'est si possible, sinon vide
        ouestButton.setText(ouest ? "\u2190" : ""); // Flèche vers l'ouest si possible, sinon vide
    }
}
*/







package jeu;
import enigmes.Enigme;
import org.json.JSONException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
/**
 * Cette classe gère l'interface utilisateur graphique (GUI) pour le jeu d'aventure.
 */
public class GUI implements ActionListener {
    private Jeu jeu;
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;
    private static GestionCompte gestionCompte = new GestionCompte();
    private JButton nordButton;
    private JButton sudButton;
    private JButton estButton;
    private JButton ouestButton;
    private JTextField nomUtilisateurField;
    private JPasswordField motDePasseField;
/**
 * Constructeur de la classe GUI.
 * Initialise l'interface utilisateur graphique.
 *
 * @param j Instance du jeu.
 */
    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
        //afficherInterfaceConnexion();
        creerInterfaceConnexion();
    }
    public void creerInterfaceConnexion() {
        fenetre = new JFrame("Connexion au jeu");
        fenetre.setSize(400, 200);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        definirArrierePlan("src/jeu/images/musee.jpg"); // Appel de la méthode pour définir l'arrière-plan
        JPanel panel = new JPanel();
        fenetre.add(panel);
        placeComposantsConnexion(panel);
        fenetre.setVisible(true);

    }

    private void definirArrierePlan(String cheminImage) {
        ImageIcon imageIcon = new ImageIcon(cheminImage);
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, fenetre.getWidth(), fenetre.getHeight());
        fenetre.add(backgroundLabel);
        fenetre.setContentPane(backgroundLabel);
        fenetre.getContentPane().setLayout(new BorderLayout());
        System.out.println("Chemin de l'image : " + cheminImage);

    }
    /**
     * Place les composants de l'interface de connexion sur le panneau spécifié.
     *
     * @param panel Le panneau sur lequel placer les composants de l'interface de connexion.
     */
    private void placeComposantsConnexion(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        userLabel.setBounds(10, 20, 120, 25);
        panel.add(userLabel);

        nomUtilisateurField = new JTextField(20);
        nomUtilisateurField.setBounds(140, 20, 165, 25);
        panel.add(nomUtilisateurField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(10, 50, 120, 25);
        panel.add(passwordLabel);

        motDePasseField = new JPasswordField(20);
        motDePasseField.setBounds(140, 50, 165, 25);
        panel.add(motDePasseField);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setBounds(10, 80, 120, 25);
        panel.add(loginButton);

        JButton createAccountButton = new JButton("Créer un compte");
        createAccountButton.setBounds(140, 80, 165, 25);
        panel.add(createAccountButton);

        loginButton.addActionListener(e -> {
            String username = nomUtilisateurField.getText();
            String password = new String(motDePasseField.getPassword());
            try {
                if (gestionCompte.verifierConnexion(username, password)) {
                    fenetre.dispose(); // Fermer la fenêtre de connexion
                    afficherInterfacePrincipale(); // Afficher l'interface principale du jeu
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        });

        createAccountButton.addActionListener(e -> {
            String username = nomUtilisateurField.getText();
            String password = new String(motDePasseField.getPassword());
            try {
                gestionCompte.creerCompte(username, password);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    /**
     * Affiche l'interface principale du jeu d'aventure.
     *
     * @implNote Cette méthode crée et affiche l'interface graphique du jeu d'aventure,
     *           composée de boutons de direction (nord, sud, est, ouest) et d'une grille 300x200.
     *           Elle initialise également les écouteurs d'événements pour chaque bouton afin de
     *           permettre au joueur de se déplacer dans le jeu. De plus, elle associe chaque
     *           bouton à une action d'affichage de fenêtre contextuelle pour une énigme.
     */
    private void afficherInterfacePrincipale() {
        fenetre = new JFrame("Jeu d'aventure");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(400, 400);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Texte
        texte = new JTextArea();
        texte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(texte);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Image
        image = new JLabel();
        afficheImage("couloir1b.jpg");
        panel.add(image, BorderLayout.NORTH);

        // Entrée utilisateur
        entree = new JTextField(20);
        panel.add(entree, BorderLayout.SOUTH);
        entree.addActionListener(this);

        // Boutons directionnels
        JPanel boutonsPanel = new JPanel(new GridLayout(3, 3));
        nordButton = new JButton("");
        sudButton = new JButton("");
        estButton = new JButton("");
        ouestButton = new JButton("");
        boutonsPanel.add(new JLabel());
        boutonsPanel.add(nordButton);
        boutonsPanel.add(new JLabel());
        boutonsPanel.add(ouestButton);
        boutonsPanel.add(new JLabel());
        boutonsPanel.add(estButton);
        boutonsPanel.add(new JLabel());
        boutonsPanel.add(sudButton);
        boutonsPanel.add(new JLabel());

        panel.add(boutonsPanel, BorderLayout.EAST);

        fenetre.getContentPane().add(panel);
        fenetre.pack();
        fenetre.setVisible(true);

        // Écouteurs pour les boutons directionnels
        nordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme(); // Afficher la popup d'énigme lorsque l'utilisateur clique sur le bouton nord
                jeu.allerEn("NORD"); // Déplacer le joueur vers le nord
            }
        });
        sudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme(); // Afficher la popup d'énigme lorsque l'utilisateur clique sur le bouton sud
                jeu.allerEn("SUD"); // Déplacer le joueur vers le sud
            }
        });
        estButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme(); // Afficher la popup d'énigme lorsque l'utilisateur clique sur le bouton est
                jeu.allerEn("EST"); // Déplacer le joueur vers l'est
            }
        });
        ouestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme(); // Afficher la popup d'énigme lorsque l'utilisateur clique sur le bouton ouest
                jeu.allerEn("OUEST"); // Déplacer le joueur vers l'ouest
            }
        });


        // Récupération et affichage de la première énigme
        // afficherPopupEnigme();
    }
    public void afficher(String s) {
        texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
    }
    /**
     * Affiche un texte dans la zone de texte.
     *

     */
    public void afficher() {
        afficher("\n");
    }
/**
        * Affiche une image spécifiée.
            *
            * @param nomImage Le nom de l'image à afficher.
            */
    public void afficheImage(String nomImage) {
        URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + nomImage);
        if (imageURL != null) {
            image.setIcon(new ImageIcon(imageURL));
            fenetre.pack();
        }
    }
    /**
     * Active ou désactive la zone de texte pour l'entrée du joueur.
     *
     * @param ok true pour activer, false pour désactiver.
     */
    public void enable(boolean ok) {
        entree.setEditable(ok);
        if (!ok)
            entree.getCaret().setBlinkRate(0);
    }

    private void creerGUI() {


        image = new JLabel();

        texte = new JTextArea();







    }

    /**
     * Gère les actions de l'utilisateur.
     *
     * @param e L'événement déclenché par l'utilisateur.
     */
    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        jeu.traiterCommande(commandeLue);
    }

    public void afficherInterfaceConnexion() {
        // Affichage de l'interface graphique pour la connexion
        fenetre = new JFrame("Connexion au jeu");
        fenetre.setSize(400, 200);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        fenetre.add(panel);
        placeComponents(panel);
        fenetre.setVisible(true);

        // Jouer le fichier audio de bienvenue
        AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Nom d'utilisateur:");
        userLabel.setBounds(10, 20, 120, 25);
        panel.add(userLabel);

        nomUtilisateurField = new JTextField(20);
        nomUtilisateurField.setBounds(140, 20, 165, 25);
        panel.add(nomUtilisateurField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(10, 50, 120, 25);
        panel.add(passwordLabel);

        motDePasseField = new JPasswordField(20);
        motDePasseField.setBounds(140, 50, 165, 25);
        panel.add(motDePasseField);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setBounds(10, 80, 120, 25);
        panel.add(loginButton);

        JButton createAccountButton = new JButton("Créer un compte");
        createAccountButton.setBounds(140, 80, 165, 25);
        panel.add(createAccountButton);

        // Action à effectuer lorsque l'utilisateur appuie sur le bouton de connexion
        loginButton.addActionListener(e -> {
            String username = nomUtilisateurField.getText();
            String password = new String(motDePasseField.getPassword());
            // Vérifier les identifiants de connexion
            try {
                if (gestionCompte.verifierConnexion(username, password)) {
                    fenetre.dispose(); // Fermer la fenêtre de connexion
                    // Afficher l'interface principale du jeu
                    afficherInterface();
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Action à effectuer lorsque l'utilisateur appuie sur le bouton de création de compte
        createAccountButton.addActionListener(e -> {
            String username = nomUtilisateurField.getText();
            String password = new String(motDePasseField.getPassword());
            // Appeler la méthode de création de compte de GestionCompte
            try {
                gestionCompte.creerCompte(username, password);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void afficherInterface() {
        JFrame frame = new JFrame("Jeu d'aventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200); // Taille réduite pour les boutons plus petits

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3)); // Grille 3x3

        // Initialisation des boutons avec des flèches vides
        nordButton = new JButton("");
        sudButton = new JButton("");
        estButton = new JButton("");
        ouestButton = new JButton("");

        // Ajout des écouteurs d'événements pour les boutons (désactivés par défaut)
        nordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("NORD"); // Déplacer le joueur vers le nord
            }
        });
        sudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("SUD"); // Déplacer le joueur vers le sud
            }
        });
        estButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("EST"); // Déplacer le joueur vers l'est
            }
        });
        ouestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu.allerEn("OUEST"); // Déplacer le joueur vers l'ouest
            }
        });

        // Ajout des boutons au panneau
        panel.add(new JLabel());
        panel.add(nordButton);
        panel.add(new JLabel());
        panel.add(ouestButton);
        panel.add(new JLabel());
        panel.add(estButton);
        panel.add(new JLabel());
        panel.add(sudButton);
        panel.add(new JLabel());

        frame.add(panel);
        frame.setVisible(true);
        nordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
        sudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
        estButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
        ouestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherPopupEnigme();
            }
        });
    }
    /**
     * Affiche une fenêtre contextuelle pour une énigme.
     *
     * @implNote La méthode obtient une énigme aléatoire du jeu et affiche une fenêtre contextuelle
     *           avec la question de l'énigme. Si l'utilisateur entre une réponse, cette méthode vérifie
     *           si la réponse est correcte. Si la réponse est correcte, un message de félicitations est affiché
     *           et l'image est mise à jour. Si la réponse est incorrecte, un message d'erreur est affiché et
     *           une deuxième tentative est offerte. Si la deuxième tentative échoue, un message "Game Over" est affiché.
     */
    private void afficherPopupEnigme() {
        Enigme enigme = jeu.obtenirEnigmeAleatoire(); // Méthode à implémenter dans la classe Jeu
        String question = enigme.getQuestion();
        JTextField reponseField = new JTextField();
        Object[] message = { question, reponseField };
        int option = JOptionPane.showConfirmDialog(null, message, "Énigme", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String reponse = reponseField.getText();
            if (enigme.verifierReponse(reponse)) {
                // Réponse correcte
                JOptionPane.showMessageDialog(null, "Bravo, vous avez résolu l'énigme !", "Correct", JOptionPane.INFORMATION_MESSAGE);
                // Jouer un message vocal de félicitations
                AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
                // Mettre à jour l'image en cas de réponse correcte
                afficheImage("image_correcte.jpg");
            } else {
                // Réponse incorrecte
                JOptionPane.showMessageDialog(null, "Réponse incorrecte. Il vous reste 2 tentatives.", "Incorrect", JOptionPane.ERROR_MESSAGE);
                // Afficher une deuxième fois la popup pour une deuxième tentative
                option = JOptionPane.showConfirmDialog(null, message, "Énigme", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    reponse = reponseField.getText();
                    if (enigme.verifierReponse(reponse)) {
                        // Réponse correcte lors de la deuxième tentative
                        JOptionPane.showMessageDialog(null, "Bravo, vous avez résolu l'énigme !", "Correct", JOptionPane.INFORMATION_MESSAGE);
                        // Jouer un message vocal de félicitations
                        AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
                        // Mettre à jour l'image en cas de réponse correcte
                        afficheImage("image_correcte.jpg");
                    } else {
                        // Deuxième tentative incorrecte, afficher "Game Over"
                        JOptionPane.showMessageDialog(null, "Game Over", "Incorrect", JOptionPane.ERROR_MESSAGE);
                        // Jouer un message vocal d'échec
                        AudioPlayer.jouerSon("ElevenLabs_2024-04-07T21_51_56_Serena.mp3");
                    }
                }
            }
        }
        /*if (option == JOptionPane.OK_OPTION) {
            String reponse = reponseField.getText();
            if (enigme.verifierReponse(reponse)) {
                // Réponse correcte
                JOptionPane.showMessageDialog(null, "Bravo, vous avez résolu l'énigme !", "Correct", JOptionPane.INFORMATION_MESSAGE);
                // Jouer un son de succès
                AudioPlayer.jouerSon("nom_du_fichier.wav");
                // Autres actions à effectuer en cas de réponse correcte...
            } else {
                // Réponse incorrecte
                JOptionPane.showMessageDialog(null, "Réponse incorrecte. Il vous reste 2 tentatives.", "Incorrect", JOptionPane.ERROR_MESSAGE);
                // Autres actions à effectuer en cas de réponse incorrecte...
            }
        }*/
    }

    // Méthode pour mettre à jour l'affichage des flèches en fonction des directions possibles

    /**
     * Met à jour l'affichage des flèches en fonction des directions possibles.
     *
     * @param nord  true si la direction nord est possible, sinon false.
     * @param sud   true si la direction sud est possible, sinon false.
     * @param est   true si la direction est possible, sinon false.
     * @param ouest true si la direction ouest est possible, sinon false.
     */
    public void mettreAJourFleches(boolean nord, boolean sud, boolean est, boolean ouest) {
        nordButton.setText(nord ? "\u2191" : ""); // Flèche vers le nord si possible, sinon vide
        sudButton.setText(sud ? "\u2193" : ""); // Flèche vers le sud si possible, sinon vide
        estButton.setText(est ? "\u2192" : ""); // Flèche vers l'est si possible, sinon vide
        ouestButton.setText(ouest ? "\u2190" : ""); // Flèche vers l'ouest si possible, sinon vide
    }
}
