package picerija;

public class Pasutijums {

	int ID;
	String picasVeids;
	int izmers;
	String garoza;
	String piedevas;
	String dzeriens;
	String uzkoda;
	String vards;
	boolean piegade;
	String talrNr;
	String adrese;
	double cena;

	public Pasutijums(int ID, String picasVeids, int izmers, String garoza, String piedevas, String dzeriens, String uzkoda, String vards, boolean piegade, String talrNr, String adrese, double cena) {
		this.ID = ID;  // DON'T FORGET THIS!
		this.picasVeids = picasVeids;
		this.izmers = izmers;
		this.garoza = garoza;
		this.piedevas = piedevas;
		this.dzeriens = dzeriens;
		this.uzkoda = uzkoda;
		this.vards = vards;
		this.piegade = piegade;
		this.talrNr = talrNr;
		this.adrese = adrese;
		this.cena = cena;
	}
	
	// Getter methods
	public int getID() {
		return ID;
	}
	
	public String getPicasVeids() {
		return picasVeids;
	}
	
	public int getIzmers() {
		return izmers;
	}
	
	public String getGaroza() {
		return garoza;
	}
	
	public String getPicasPiedevas() {
		return piedevas;
	}
	
	public String getDzeriens() {
		return dzeriens;
	}
	
	public String getUzkoda() {
		return uzkoda;
	}
	
	public String getVards() {
		return vards;
	}
	
	public boolean isPiegade() {
		return piegade;
	}
	
	public String getTalrNr() {
		return talrNr;
	}
	
	public String getAdrese() {
		return adrese;
	}
	
	public double getCena() {
		return cena;
	}

}