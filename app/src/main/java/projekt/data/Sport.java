package projekt.data;

import com.google.android.gms.maps.model.LatLng;


public class Sport 
{
    private String id;
	private String naziv;
	private String opis;
	private String email;
	private String stevilka;
	private String naslov;
	private String odpiralni;
	private String cenik;
	private String logotip;


	private String logotipUrl;
	private String slika;
	private String slikaUrl;
	private double adrenalin;
	private LatLng koordinate;
	private int druzina;
	private int zabava;
	private int urejenost;
	private int cene;
	private boolean favorite;

	public Sport() {
		super();
		this.favorite = false;
		this.logotip = " ";
		this.slika = " ";
		this.logotipUrl = " ";
		this.slikaUrl = " ";
	}
	public Sport(String id, String naziv, String opis, String email, String stevilka,
				 String naslov, String odpiralni, String cenik, double adrenalin,
				 LatLng koo, int druzina, int zabava, int urejenost, int cene,
				 boolean favorite, String logotip, String slika, String logotipUrl, String slikaUrl) {
		super();
        this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.email = email;
		this.stevilka = stevilka;
		this.naslov = naslov;
		this.odpiralni = odpiralni;
		this.cenik = cenik;
		this.adrenalin = adrenalin;
		this.koordinate = koo;
		this.druzina = druzina;
		this.zabava = zabava;
		this.urejenost = urejenost;
		this.cene = cene;
		this.favorite = favorite;
		this.logotip = logotip;
		this.slika = slika;
		this.logotipUrl = logotipUrl;
		this.slikaUrl = slikaUrl;
	}
	@Override
	public String toString() {
		return "Sport [naziv=" + naziv + ", opis=" + opis + ", email=" + email
				+ ", stevilka=" + stevilka + ", naslov=" + naslov + "]";
	}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStevilka() {
		return stevilka;
	}
	public void setStevilka(String stevilka) {
		this.stevilka = stevilka;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getOdpiralni(){return odpiralni;}
	public void setOdpiralni(String odpiralni) {this.odpiralni = odpiralni;}
	public String getCenik() {return cenik;}
	public void setCenik(String cenik) {this.cenik = cenik;}
	public void setAdrenalin(double adrenalin)
	{
		this.adrenalin = adrenalin;
	}
	public double getAdrenalin()
	{
		return adrenalin;
	}
	public LatLng getKoordinate() {
		return koordinate;
	}
	public void setKoordinate(LatLng koordinate) {
		this.koordinate = koordinate;
	}
	public int getDruzina() {
		return druzina;
	}
	public void setDruzina(int druzina) {
		this.druzina = druzina;
	}
	public int getZabava() {
		return zabava;
	}
	public void setZabava(int zabava) {
		this.zabava = zabava;
	}
	public int getUrejenost() {
		return urejenost;
	}
	public void setUrejenost(int urejenost)
	{
		this.urejenost = urejenost;
	}
	public void setCene(int cene) {
		this.cene = cene;
	}
	public int getCene()
	{
		return cene;
	}
	public void setFavorite(boolean favorite)
	{
		this.favorite = favorite;
	}
	public boolean getFavorite()
	{
		return favorite;
	}
	public String getLogotip() {
		return logotip;
	}
	public void setLogotip(String logotip) {
		this.logotip = logotip;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public String getLogotipUrl() {
		return logotipUrl;
	}
	public void setLogotipUrl(String logotipUrl) {
		this.logotipUrl = logotipUrl;
	}
	public String getSlikaUrl() {
		return slikaUrl;
	}
	public void setSlikaUrl(String slikaUrl) {
		this.slikaUrl = slikaUrl;
	}

}
