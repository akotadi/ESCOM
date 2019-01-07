
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.*;

public class Reproductor {
    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    String mensajes[]={ "NOTSTARTED", "PLAYING", "PAUSED", "FINISHED" };
	private Player player;
     // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private int playerStatus = NOTSTARTED;
    Post2 ste;
	Reproductor(Post2 ste){
                this.ste=ste;
		//player = new Player();
	}
	
    public void Play() throws JavaLayerException {
    	//player.play();
        synchronized (playerLock) {
            System.out.println("Play() "+mensajes[playerStatus]);
            switch (playerStatus) {
                
                case NOTSTARTED:
                    System.out.println("case NOTST");
                    final Runnable r = new Runnable() {
                        public void run() {
			    System.out.println("run");
                            playInternal();
                            ste.reproduciendo=false;  
                	    ste.pausado=false;  
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
		    System.out.println("case NOST Play() "+mensajes[playerStatus]);
                    t.start();
                    break;
                case PAUSED:
                    Continuar();
                    break;
                default:
                    break;
            }
        }
    }
     
    public void AbrirFichero(String ruta) throws JavaLayerException {       
        FileInputStream fis;
        try {
            fis = new FileInputStream(ruta);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis); 
            playerStatus = NOTSTARTED;
	} catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    public  boolean Pausa() throws JavaLayerException {
    	//player.pause();
	synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }
     
    public  boolean Continuar()  {    
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    	//player.resume();
    }
     
    public void Stop() throws Exception {

    	//player.stop();
         synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
        }
    }
     private void playInternal() {
        while (playerStatus != FINISHED) {
            //System.out.println("pInternal"+ mensajes[playerStatus]);
            try {
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
                e.printStackTrace();
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }  
        System.out.println("termino ciclo "+mensajes[playerStatus]);
        close();
    }
     public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
        }
        try {
            player.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }
}
