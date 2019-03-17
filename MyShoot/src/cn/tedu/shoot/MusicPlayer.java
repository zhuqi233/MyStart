package cn.tedu.shoot;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
 
 
/**
 * @author
 */
public class MusicPlayer {
	private String filename;
    private Player player;
    
    public MusicPlayer(String filename) {
        this.filename = filename;
    }
 
    public void play() {
        try {
            BufferedInputStream buffer = new BufferedInputStream(
                    new FileInputStream(filename));
            player = new Player(buffer);
            player.play();
        } catch (Exception e) {
            System.out.println(e);
        }
 
//        java.net.URL file1 = getClass().getResource("NewTournament.wav"); 
//        AudioClip sound1 = java.applet.Applet.newAudioClip(file1); 
//        sound1.play();
//      //sound1.loop();
//      //sound1.stop();
    }
 
//  public static void main(String[] args) {
//		new MusicPlayer("骆集益 - 回梦游仙.mp3").play();
//	}
}