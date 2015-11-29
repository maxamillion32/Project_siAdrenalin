package projekt.data;

import java.util.ArrayList;

public class TipSporta 
{
    private String id;
	private String naziv;
	private String opis;
	
	private ArrayList<Sport> data;
	
	public TipSporta() {
		super();
		
		this.data = new ArrayList<Sport>();
	}
    public String getId(){return id;}
    public void setId(String id) { this.id = id; }
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public ArrayList<Sport> getData() {
		return data;
	}
	public void setData(ArrayList<Sport> data) {
		this.data = data;
	}
	public void vstaviSport(Sport sport)
	{
		this.data.add(sport);
	}
}
