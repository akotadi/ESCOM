import java.awt.Component;
import java.net.*;
import javax.media.*;
import javax.swing.*;

public class VideoMini extends JPanel {	
	private Component visualComponent;
	private Player player;
	
	public VideoMini(String mediaURL){
		try {
			player = Manager.createRealizedPlayer(new URL(mediaURL));
                        Component video = player.getVisualComponent();
                        if ( video != null ) add( video); 	
			player.start();
		}
		catch(Exception e) { e.printStackTrace(); }
	}      
        public static void main( String s[] ){
	    String mediaURL="http://localhost:8080/servlets/servlet/AVI?file=";
	    String name;
	    if(s.length > 0)
		name=s[0];
	    else
		name="carrera.avi";
	    mediaURL=mediaURL+name;
	    JFrame mediaTest = new JFrame( "Reproductor de Video" );
         mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        
            VideoMini mediaPanel = new VideoMini( mediaURL );
            mediaTest.add( mediaPanel );         
            mediaTest.setSize( 300, 300 );
            mediaTest.setVisible( true );
        }
}
