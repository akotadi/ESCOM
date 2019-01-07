public class Tiro{
	public int x1;
	public int y1;
	public int x2;
	public int y2;

	public Tiro(){}

	public Tiro(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean checkPoints(){
		return (x1 == x2) || (y1 == y2) || (Math.abs(x2-x1) == Math.abs(y2-y1));
	}

	public String toString(){
		return Integer.toString(x1)+":"+Integer.toString(y1)+" - "+Integer.toString(x2)+":"+Integer.toString(y2);
	}
}