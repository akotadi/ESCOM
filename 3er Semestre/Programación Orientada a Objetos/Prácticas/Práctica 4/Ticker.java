
public class Ticker implements Runnable {

	Thread TickerThread;
	String s,sub;
	int sleepTime;
	char c;

	public Ticker(String s, int sleep){

		this.s=s;
		sleepTime=sleep;
		TickerThread=new Thread(this);
		TickerThread.start();
	}

	public void run(){

		System.out.println(s);
		while(true){
			c = s.charAt(0);
			sub = s.substring(1,s.length());
			s = sub + c;
			System.out.println(sub + c);
			try{
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				return;
			}
		}
	}

	public static void main(String args[]){
		new Ticker("El gato volador", 50);
	}
}