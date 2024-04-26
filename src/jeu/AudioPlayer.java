/*
package jeu;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class AudioPlayer {
    public static void jouerSon(String cheminFichier) {
        try {
            File fichierAudio = new File(cheminFichier);
            if (fichierAudio.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fichierAudio);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("Fichier audio introuvable : " + cheminFichier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
package jeu;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {

    public static void jouerSon(String nomFichier) {
        try {
            // Charger le fichier audio
            File fichierAudio = new File("jeu/BlueBoyAdventure.wav/" + nomFichier);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(fichierAudio);

            // Créer un clip audio
            Clip clip = AudioSystem.getClip();

            // Ouvrir le fichier audio dans le clip
            clip.open(audioInput);

            // Jouer le clip
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Utilisation : AudioPlayer.jouerSon("nom_de_votre_fichier.wav");
}
