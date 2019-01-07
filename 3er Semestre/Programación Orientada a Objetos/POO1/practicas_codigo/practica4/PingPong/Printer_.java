//Printer_.java
public class Printer_ implements Runnable {
	Thread PrinterThread;
	String string;
	int count;
	int sleepTime;

public Printer_(String s, int howMany, int sleep){
		count=howMany;
		string=s;
		sleepTime=sleep;
		PrinterThread=new Thread(this);
		PrinterThread.start();
	}
	public void run(){
		while(count-- >0){
			System.out.println(string);
try{
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				return;
			}
		}
	}
	public static void main(String args[]){
		new Printer_("Ping", 5, 300);
		new Printer_("Pong", 5, 500);
	}
}
