import sun.audio.*;
import java.io.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class Audio{
Clip ol;

public Audio(){
        File sf=new File("overworld.wav");
        AudioFileFormat aff;
        AudioInputStream ais;

        try{
                aff=AudioSystem.getAudioFileFormat(sf);
                ais=AudioSystem.getAudioInputStream(sf);
                AudioFormat af=aff.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class,ais.getFormat(),((int)ais.getFrameLength() *af.getFrameSize()));
                ol = (Clip) AudioSystem.getLine(info);
                ol.open(ais);
                ol.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(UnsupportedAudioFileException ee){}
        catch(IOException ea){}
        catch(LineUnavailableException LUE){};
        }
}