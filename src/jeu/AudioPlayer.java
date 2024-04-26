package jeu;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;

public class AudioPlayer {
    //TODO
    //FIXME JAVADOC :
    // - JavaDoc for JouerSon
    //

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
