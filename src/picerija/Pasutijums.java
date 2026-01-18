package picerija;

public class Pasutijums {
	
	int ID;
	String picasVeids;
	int izmers;
	boolean parastaGaroza;
	String[] piedevas;
	String dzeriens;
	String uzkoda;
	String vards;
	boolean piegade;
	String talrNr;
	String adrese;
	double cena;
	
	public Pasutijums(int ID, String picasVeids, int izmers, boolean parastaGaroza, String[] piedevas, String dzeriens, String uzkoda, String vards, boolean piegade, String talrNr, String adrese, double cena) {
		this.picasVeids = picasVeids;
		this.izmers = izmers;
		this.parastaGaroza = parastaGaroza;
		this.piedevas = piedevas;
		this.dzeriens = dzeriens;
		this.uzkoda = uzkoda;
		this.vards = vards;
		this.piegade = piegade;
		this.talrNr = talrNr;
		this.adrese = adrese;
		this.cena = cena;
	}
	
}
