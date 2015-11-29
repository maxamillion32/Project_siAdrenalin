package projekt.util;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import projekt.data.Sport;
import projekt.data.TipSporta;

public class Podatki { 
	private ArrayList<TipSporta> lista;

	public Podatki() {
		super();
		lista = new ArrayList<TipSporta>();
		this.lista = UstvariListo();
	}

	public ArrayList<TipSporta> getLista()
	{
		return this.lista;
	}
	
	public ArrayList<TipSporta> UstvariListo() {
		
		
		//Adrenalinski parki
				TipSporta AdrenalinskiPark = new TipSporta();
                AdrenalinskiPark.setId("00");
				AdrenalinskiPark.setNaziv("Adrenalinski parki");
				AdrenalinskiPark.setOpis("Guganje na orjaških gugalnicah, plezanje po drevesih, spuščanje oziroma že kar adrenalinsko letenje po jeklenih žicah, veliki trampolini ... Pri vseh teh stvareh uživajo tako otroci kot odrasli.");
				
				Sport Bukovnica = new Sport();
        Bukovnica.setId(AdrenalinskiPark.getId() + "00");
				Bukovnica.setNaziv("Pustolovski park Bukovniško jezero");
				Bukovnica.setOpis("Primeren za udeležence starejše od 4 leta. V prečudovitem gozdu ob Bukovniškem jezeru. 7 unikatnih elementov, skupaj 58 elementov. Prilagojen starosti in zmogljivosti. Trajanje: okrog 2 uri. Sprejme do 100 udeležencev.");
				Bukovnica.setStevilka("+386 41 788 631");
				Bukovnica.setEmail("info@pustolovskipark.si");
				Bukovnica.setNaslov("Bukovniško jezero 9223 Dobrovnik Slovenija");
				Bukovnica.setAdrenalin(40);
				Bukovnica.setKoordinate(new LatLng(46.674274, 16.335546));				
				Bukovnica.setDruzina(5);
				Bukovnica.setZabava(4);
				Bukovnica.setUrejenost(5);
				Bukovnica.setCene(3);
				Bukovnica.setFavorite(true);
				AdrenalinskiPark.vstaviSport(Bukovnica);

				Sport Bovec = new Sport();
        Bovec.setId(AdrenalinskiPark.getId() + "01");
				Bovec.setNaziv("Adrenalinski park Zip-Line");
				Bovec.setOpis("Zip-line park nad Bovcem vam omogoča približno 2,4 kilometra dolgo adrenalinsko letenje na 200 metrih višine. Vseskozi se vam odpirajo čudoviti razgledi na dolino zgornjega Posočja. Med letenjem boste dosegli hitrost tudi do 60 kilometrov na uro.");
				Bovec.setStevilka("040 639 433");
				Bovec.setEmail("info@ziplineslovenia.si");
				Bovec.setNaslov("Trg golobarskih žrtev 19");
				Bovec.setAdrenalin(80);
				Bovec.setKoordinate(new LatLng(46.338290, 13.552488));
				Bovec.setDruzina(5);
				Bovec.setZabava(4);
				Bovec.setUrejenost(5);
				Bovec.setCene(3);
				Bovec.setFavorite(true);
				AdrenalinskiPark.vstaviSport(Bovec);

				Sport SkofjaLoka = new Sport();
        SkofjaLoka.setId(AdrenalinskiPark.getId() + "02");
				SkofjaLoka.setNaziv("Adrenalinski park Storž");
				SkofjaLoka.setOpis("Najvišji adrenalinski park v Slovenji (do 24 m) z najvišjo orjaško gugalnico v Sloveniji (21 m) je primeren za vse generacije. Ima bogato dodatno ponudbo: paintball, igrišče za odbojko in nogomet, kolesarjenje, pohodništvo, smučišče Stari vrh, jahanje, skok s padalom (3 km) ... pri njih imate lahko tudi piknik.");
				SkofjaLoka.setStevilka("041 749 014");
				SkofjaLoka.setEmail("ernest.kastelic@gmail.com");
				SkofjaLoka.setNaslov("Spodnja Luča 23, 4227 Rantovse, Slovenija");
				SkofjaLoka.setAdrenalin(60);
				SkofjaLoka.setKoordinate(new LatLng(46.179826, 14.230034));
				SkofjaLoka.setDruzina(5);
				SkofjaLoka.setZabava(4);
				SkofjaLoka.setUrejenost(5);
				SkofjaLoka.setCene(3);
				AdrenalinskiPark.vstaviSport(SkofjaLoka);

				Sport Bohinj = new Sport();
        Bohinj.setId(AdrenalinskiPark.getId() + "03");
				Bohinj.setNaziv("Adrenalinski park Bohinj, pri Hostlu Pod Voglom");
				Bohinj.setOpis("Adrenalinski park v Bohinju ima poligon na višini od 8 do 14 metrov, sestavljen je iz različnih nalog, ki jih je možno premagovati individualno ali skupinsko. Namenjen je odraslim, otroci naj bi se vadbe v adrenalinskem parku udeležili po 11. letu, visoki pa naj bi bili vsaj 1,5 metra. V sklop parka spada tudi Orjaška gugalnica.");
				Bohinj.setStevilka("040 864 202");
				Bohinj.setEmail("info@pac.si");
				Bohinj.setNaslov("Ribčev laz 60 4265 Bohinjsko jezero Slovenija");
				Bohinj.setAdrenalin(60);
				Bohinj.setKoordinate(new LatLng(46.278717, 13.866429));
				Bohinj.setDruzina(5);
				Bohinj.setZabava(4);
				Bohinj.setUrejenost(5);
				Bohinj.setCene(3);
				Bohinj.setFavorite(true);
				AdrenalinskiPark.vstaviSport(Bohinj);

				Sport Ljubelj = new Sport();
        Ljubelj.setId(AdrenalinskiPark.getId() + "04");
				Ljubelj.setNaziv("Adrenalinski park Ljubelj");
				Ljubelj.setOpis("Adrenalinski park na Ljubelju je poligon na višini od 8 do 14 metrov, ki je sestavljen iz različnih nalog, ki jih je možno premagovati individualno ali skupinsko. Je prvi adrenalinski park v Sloveniji (že od leta 2001), vodijo pa ga vrhunsko usposobljeni inštruktorji. Park pa je najbolj privlačen zaradi čudovite okolice. Programa se lahko udeležijo le osebe, ki so visoke vsaj 1,5 metra. če so otroci mlajši od 12 let, jih morajo pri programu ves čas spremljati starši.");
				Ljubelj.setStevilka("+386 31 513 645");
				Ljubelj.setEmail("info@koren-sports.si");
				Ljubelj.setNaslov("Pot na Bistriško planino 10 4290 Tržič Slovenija");
				Ljubelj.setAdrenalin(30);
				Ljubelj.setKoordinate(new LatLng(46.360688, 14.293648));
				Ljubelj.setDruzina(5);
				Ljubelj.setZabava(4);
				Ljubelj.setUrejenost(5);
				Ljubelj.setCene(3);
				AdrenalinskiPark.vstaviSport(Ljubelj);

				Sport Tolmin = new Sport();
        Tolmin.setId(AdrenalinskiPark.getId() + "05");
				Tolmin.setNaziv("Adrenalinski park Tolmin");
				Tolmin.setOpis("Adrenalinski park Maya je postavljen v naravnem okolju na drevesih. Stoji blizu sotočja Soče in Tolminke in ima devet različnih preprek. Imajo jungle bridge, steber poguma, Jakobovo lestev, adrenalinsko žičnico, zdaj ali nikoli (most iz vrvi), gugalnico, gume, hojo po jekleni vrvi ... Lahko so kombinirane z vodnimi športi (rafting, kajak, mini rafting), lahko pa se tudi kopate.");
				Tolmin.setStevilka("+386 51 312 972");
				Tolmin.setEmail("info@maya.si");
				Tolmin.setNaslov("Borut Nikolas S. P. Padlih borcev 1 5220 Tolmin");
				Tolmin.setAdrenalin(20);
				Tolmin.setKoordinate(new LatLng(46.182350, 13.731246));
				Tolmin.setDruzina(5);
				Tolmin.setZabava(4);
				Tolmin.setUrejenost(5);
				Tolmin.setCene(3);
				Tolmin.setFavorite(true);
				AdrenalinskiPark.vstaviSport(Tolmin);

				Sport Klepec = new Sport();
        Klepec.setId(AdrenalinskiPark.getId() + "06");
				Klepec.setNaziv("Adrenalinski park Peter Klepec, Sela pri Osilnici");
				Klepec.setOpis("Park sestavljajo stolp, na katerega se povzpnete po posebnih jeklenih stopnicah, hexagon, sestavljen iz visoko stoječih elementov (high ropes course), plezalna stena (climbing wall), stena za spuščanje (absailing wall), spust po jeklenih vrveh (leteči peter-zip wire), skok v prazno (all abord platforma), orjaška gugalnica, poligon za različne team buildinge in zabavne programe. Vstop v adrenalinski park je dovoljen vsakemu, ki v višino meri vsaj 1,5 metra in je psihofizično zdrav.");
				Klepec.setStevilka("041 652 048");
				Klepec.setEmail("info@kovac-kolpa.com");
				Klepec.setNaslov("Sela 5 1337 Osilnica Slovenija");
				Klepec.setAdrenalin(55);
				Klepec.setKoordinate(new LatLng(45.528758, 14.693336));
				Klepec.setDruzina(5);
				Klepec.setZabava(4);
				Klepec.setUrejenost(5);
				Klepec.setCene(3);
				AdrenalinskiPark.vstaviSport(Klepec);

				Sport Bled = new Sport();
        Bled.setId(AdrenalinskiPark.getId() + "07");
				Bled.setNaziv("Pustolovski park Bled");
				Bled.setOpis("Park je še prav posebej namenjen družinskim obiskom, ker ponuja zabavo vsej družini in je primeren za otroke od četrtega leta dalje. Pustolovski park omogoča gibalne aktivnosti v naravi, tako v vertikalni (plezanje, spuščanje, drsenje ...) kot tudi horizontalni (hoja, skoki ...) smeri, skratka predstavlja prostor oziroma igrišče, ki je namenjen igri, gibanju in doživetjem v naravi.");
				Bled.setStevilka("031 513 645");
				Bled.setEmail("info@pustolovski-park-bled.si");
				Bled.setNaslov("Pustolovski park Bled d.o.o. Bled - Straža 4260 Bled Slovenija");
				Bled.setAdrenalin(25);
				Bled.setKoordinate(new LatLng(46.360076, 14.104614));
				Bled.setDruzina(5);
				Bled.setZabava(4);
				Bled.setUrejenost(5);
				Bled.setCene(3);
				AdrenalinskiPark.vstaviSport(Bled);

				Sport Pohorje = new Sport();
        Pohorje.setId(AdrenalinskiPark.getId() + "08");
				Pohorje.setNaziv("Adrenalinski park Pohorje, Maribor");
				Pohorje.setOpis("Adrenalinski park Pohorje ponuja zabavo in doživetje za adrenalinske frike in tiste, ki bi poskusili prvič. Za mlade, za otroke in za 'navadne' odrasle. Za skupine in tiste, ki greste najraje sami. Je tudi odlično okolje za preizkus timskega dela in/ali tekmovalnosti v adrenalinsko poletno sankališče, minijet, višinski poligon, bike park, paintball poligon ... Za vsakogar se nekaj najde.");
				Pohorje.setStevilka("+386 5 925 90 23");
				Pohorje.setEmail("info.park@sc-pohorje.si");
				Pohorje.setNaslov("Pohorska ulica 60, 2000 Maribor");
				Pohorje.setAdrenalin(25);
				Pohorje.setKoordinate(new LatLng(46.533315, 15.599377));
				Pohorje.setDruzina(5);
				Pohorje.setZabava(4);
				Pohorje.setUrejenost(5);
				Pohorje.setCene(3);
				AdrenalinskiPark.vstaviSport(Pohorje);

				Sport Betnava = new Sport();
        Betnava.setId(AdrenalinskiPark.getId() + "09");
				Betnava.setNaziv("Pustolovski park Betnava, Hoče pri Mariboru");
				Betnava.setOpis("Pustolovski park Betnava ponuja najboljši način, kako odvrniti otroke in ostale od zaslonov, stolov in drugih načinov nezdravega preživljanja prostega časa. Z različnimi elementi oziroma igrali mladi in manj mladi po srcu razvijajo gibljivost, se gibajo, živijo bolj zdravo in čutijo naravo, ki ji zaradi Betnavskega gozda, kjer se vse skupaj dogaja, še bolj privlačna.");
				Betnava.setStevilka("031 787 425");
				Betnava.setEmail("evod@telemach.net");
				Betnava.setNaslov("Maribor Ljubljanska ulica 128");
				Betnava.setAdrenalin(15);
				Betnava.setKoordinate(new LatLng(46.535194, 15.635753));
				Betnava.setDruzina(5);
				Betnava.setZabava(4);
				Betnava.setUrejenost(5);
				Betnava.setCene(3);
				AdrenalinskiPark.vstaviSport(Betnava);

				Sport Jezersko = new Sport();
        Jezersko.setId(AdrenalinskiPark.getId() + "10");
				Jezersko.setNaziv("Doživljajski park Raj, Jezersko");
				Jezersko.setOpis("Park je zgrajen v naravnem okolju in z naravnimi materiali, na približno 30 metrov visoki skalni pečini sredi gozdne jase. Vsa igrala v parku so med seboj povezana z unikatno leseno potjo. Pot se zaključi na razgledni ploščadi, s katere se odpre čudovit pogled na Kamniške Alpe. Orjaška gugalnica, adrenalinska žičnica, naravna stena (zavarovana plezalna pot), ferata, abzajling (spuščanje po vrvi), viseči most, pajkova mreža, most za padec zaupanja, tehtnica.");
				Jezersko.setStevilka("+386 31 203 930");
				Jezersko.setEmail("info@feelgreen.si");
				Jezersko.setNaslov("Raj Jezersko d.o.o. Zg. Jezersko 28a 4206 Zg. Jezersko");
				Jezersko.setAdrenalin(65);
				Jezersko.setKoordinate(new LatLng(46.390959, 14.493747));
				Jezersko.setDruzina(5);
				Jezersko.setZabava(4);
				Jezersko.setUrejenost(5);
				Jezersko.setCene(3);
				AdrenalinskiPark.vstaviSport(Jezersko);

				lista.add(AdrenalinskiPark);

				//Paintball poligoni
				TipSporta PaintBall = new TipSporta();
        PaintBall.setId("02");
				PaintBall.setNaziv("Paintball poligoni");
				PaintBall.setOpis("Paintball se igra v naravi (paintball markerji imajo domet od 40 do 150 metrov), ki s pomočjo plina ali zraka izstreljujejo barvne kroglice, s katerimi se igralci medsebojno markirajo in tako izločajo iz igre.");
				
				Sport Bolfenk = new Sport();
        Bolfenk.setId(PaintBall.getId() + "00");
				Bolfenk.setNaziv("Paintball poligon Bolfenk");
				Bolfenk.setOpis("");
				Bolfenk.setStevilka("+386 5 925 90 23");
				Bolfenk.setEmail("info.park@sc-pohorje.si");
				Bolfenk.setNaslov("Mladinska ul. 29, 2000 Maribor");
				Bolfenk.setAdrenalin(55);
				Bolfenk.setKoordinate(new LatLng(46.563785, 15.639484));
				Bolfenk.setDruzina(5);
				Bolfenk.setZabava(4);
				Bolfenk.setUrejenost(5);
				Bolfenk.setCene(3);
				PaintBall.vstaviSport(Bolfenk);

				Sport Spyder = new Sport();
        Spyder.setId(PaintBall.getId() + "01");
				Spyder.setNaziv("Paintball park Spyder");
				Spyder.setOpis("V Paintball parku Spyder imamo najbolj sodobno opremo za igranje paintballa, s katero vam nudimo varno, udobno in najbolj adrenalinsko igranje paintballa.");
				Spyder.setStevilka("+386 41 504 955");
				Spyder.setEmail("info@spyder.si");
				Spyder.setNaslov("PAINTBALL PARK SPYDER Cesta v Gorice 13 1000 Ljubljana- Vič");
				Spyder.setAdrenalin(75);
				Spyder.setKoordinate(new LatLng(46.142364, 14.591332));
				Spyder.setDruzina(5);
				Spyder.setZabava(4);
				Spyder.setUrejenost(5);
				Spyder.setCene(3);
				Spyder.setFavorite(true);
				PaintBall.vstaviSport(Spyder);				

				Sport PBPetanjci = new Sport();
        PBPetanjci.setId(PaintBall.getId() + "02");
				PBPetanjci.setNaziv("No Limits Paintball Petanjci");
				PBPetanjci.setOpis("Za varnost je poskrbljeno z uporabo vse potrebne varnostne opreme in sodniško nadzorovanimi igrami. Ker se igre odvijajo na neravnem terenu priporočamo, da si priskrbite primerno obutev. Igralci morajo biti polnoletni v treznem stanju, mladoletniki pa le v spremstvu staršev oz. skrbnikov.");
				PBPetanjci.setStevilka("031 612 672");
				PBPetanjci.setEmail("info@paintball.si");
				PBPetanjci.setNaslov("No Limits Paintball Petanjci 27 9251 Tišina");
				PBPetanjci.setAdrenalin(67);
				PBPetanjci.setKoordinate(new LatLng(46.650363, 16.074924));
				PBPetanjci.setDruzina(5);
				PBPetanjci.setZabava(4);
				PBPetanjci.setUrejenost(5);
				PBPetanjci.setCene(3);
				PaintBall.vstaviSport(PBPetanjci);

				Sport PBKraljevHrib = new Sport();
        PBKraljevHrib.setId(PaintBall.getId() + "03");
				PBKraljevHrib.setNaziv("Paintball Kraljev Hrib");
				PBKraljevHrib.setOpis("Preizkusite se na prvem in najatraktivnejšem slovenskem Paintball poligonu v Kamniški Bistrici na Kraljevem hribu, ki je znan po svoji izredni legi in urejenosti poligona (tudi za gledalce). Na Kraljevem hribu si lahko igralci izposodijo ali najamejo najsodobnejšo paintball opremo.");
				PBKraljevHrib.setStevilka("041 816 477");
				PBKraljevHrib.setEmail("info@kraljevhrib.si");
				PBKraljevHrib.setNaslov("Kamniška Bistrica 2 1242 Strahovica");
				PBKraljevHrib.setAdrenalin(70);
				PBKraljevHrib.setKoordinate(new LatLng(46.305352, 14.611093));
				PBKraljevHrib.setDruzina(5);
				PBKraljevHrib.setZabava(4);
				PBKraljevHrib.setUrejenost(5);
				PBKraljevHrib.setCene(3);
				PaintBall.vstaviSport(PBKraljevHrib);
				
				lista.add(PaintBall);
				
				//Padalstvo
				TipSporta Padalstvo = new TipSporta();
        Padalstvo.setId("03");
				Padalstvo.setNaziv("Skok z padalom");
				Padalstvo.setOpis("Tandemski skok s padalom je skok iz letala na višini 4.000 metrov, seveda v spremstvu izkušenega tandem pilota. Spuščali se boste s hitrostjo do 220 km/h, zraven pa uživali ob izjemnem razgledu.");
				
				Sport ProstiPad = new Sport();
        ProstiPad.setId(Padalstvo.getId() + "00");
				ProstiPad.setNaziv("Padalsko društvo prosti pad");
				ProstiPad.setOpis("Ekipo Prosti pad sestavljajo člani državne reprezentance v likovnih skokih, ki velja za najbolj elitno padalsko disciplino. Naši inštruktorji, edini v Sloveniji, organizirajo tunnelcampe, v simulatorjih prostega pada po vsem svetu.");
				ProstiPad.setStevilka("031 594 111");
				ProstiPad.setEmail("info@prostipad.s");
				ProstiPad.setNaslov("Gospodinjska ulica 8 1000 Ljubljana");
				ProstiPad.setAdrenalin(85);
				ProstiPad.setKoordinate(new LatLng(46.070164, 14.487445));
				ProstiPad.setDruzina(5);
				ProstiPad.setZabava(4);
				ProstiPad.setUrejenost(5);
				ProstiPad.setCene(3);
				Padalstvo.vstaviSport(ProstiPad);

				Sport PohorjePadalo = new Sport();
        PohorjePadalo.setId(Padalstvo.getId() + "01");
				PohorjePadalo.setNaziv("Športni center Pohorje");
				PohorjePadalo.setOpis("Če imate željo po prostem padu, občutku neomejene svobode in letenja, vam organiziramo skok s padalom v tandemu iz letala z višine 3000 oz. 4000 metrov. Skoke izvajamo na športnih letališčih po Sloveniji.");
				PohorjePadalo.setStevilka("+386 59 259 023");
				PohorjePadalo.setEmail("info.park@sc-pohorje.si");
				PohorjePadalo.setNaslov("Mladinska ul. 29, 2000 Maribor");
				PohorjePadalo.setAdrenalin(85);
				PohorjePadalo.setKoordinate(new LatLng(46.563785, 15.639484));
				PohorjePadalo.setDruzina(5);
				PohorjePadalo.setZabava(4);
				PohorjePadalo.setUrejenost(5);
				PohorjePadalo.setCene(3);
				PohorjePadalo.setFavorite(true);
				Padalstvo.vstaviSport(PohorjePadalo);

				Sport SkyDive = new Sport();
        SkyDive.setId(Padalstvo.getId() + "02");
				SkyDive.setNaziv("Skydive Maribor");
				SkyDive.setOpis("Smo padalska sekcija Letalskega centra Maribor (LCM), največji padalski center v Sloveniji. V padalski sekciji smo odprti za vse, ki vas zanima padalski šport in imate željo, da se nam pridružite.");
				SkyDive.setStevilka("02 629 62 06");
				SkyDive.setEmail("lcmaribor@siol.net");
				SkyDive.setNaslov("Padalska sekcija LCM Letališka cesta 30 2204 SKOKE");
				SkyDive.setAdrenalin(85);
				SkyDive.setKoordinate(new LatLng(46.482618, 15.690153));
				SkyDive.setDruzina(5);
				SkyDive.setZabava(4);
				SkyDive.setUrejenost(5);
				SkyDive.setCene(3);
				Padalstvo.vstaviSport(SkyDive);

				Sport Vogel = new Sport();
        Vogel.setId(Padalstvo.getId() + "03");
				Vogel.setNaziv("Jadralno padalstvo na voglu v zimski sezoni");
				Vogel.setOpis("Vogel spada med najlepše vzletne točke za polete z jadralnim padalom v Sloveniji. Iz Ukanca se z nihalko zapeljemo do zgornje postaje žičnice Vogel, od koder se nato s štirisedežnico odpeljemo do Orlovih glav.");
				Vogel.setStevilka("04 572 97 12");
				Vogel.setEmail("info@vogel.si");
				Vogel.setNaslov("Žičnice Vogel Bohinj, d.d. Ukanc 6 4265 Bohinjsko Jezero");
				Vogel.setAdrenalin(85);
				Vogel.setKoordinate(new LatLng(46.275884, 13.834930));
				Vogel.setDruzina(5);
				Vogel.setZabava(4);
				Vogel.setUrejenost(5);
				Vogel.setCene(3);
				Padalstvo.vstaviSport(Vogel);

				lista.add(Padalstvo);
				
				//Adrenalinsko sankanje	
				TipSporta AdrenalinskoSankanje = new TipSporta();
        AdrenalinskoSankanje.setId("04");
				AdrenalinskoSankanje.setNaziv("Adrenalinsko sankanje");
				AdrenalinskoSankanje.setOpis("Če sodite med tiste, ki prisegajo na adrenalinske užitke, potem je kot nalašč za vas adrenalinsko sankanje.");
				
				Sport SankanjePohorje = new Sport();
        SankanjePohorje.setId(AdrenalinskoSankanje.getId() + "00");
				SankanjePohorje.setNaziv("PohorJET Maribor");
				SankanjePohorje.setOpis("Proga PohorJET-a teče po Snežnem stadionu od Trikotne jase do ciljne arene Snežnega stadiona. Na start vas pripelje sedežnica Radvanje. če ob pogledu na progo je očitno, da ostri zavoji med drevesi, drzni spusti v nepredvidljiva brezna in ravninski hitri deli ne bodo dopuščali ležerne in sproščene vožnje. Vedno pa lahko hitrost prilagodimo lastnim sposobnostim in željam s pomočjo zavore. Za varnost je ustrezno poskrbljeno.");
				SankanjePohorje.setStevilka("+386 5 925 90 23");
				SankanjePohorje.setEmail("info.park@sc-pohorje.si");
				SankanjePohorje.setNaslov("Mladinska ul. 29, 2000 Maribor");
				SankanjePohorje.setAdrenalin(45);
				SankanjePohorje.setKoordinate(new LatLng(46.563785, 15.639484));
				SankanjePohorje.setDruzina(5);
				SankanjePohorje.setZabava(4);
				SankanjePohorje.setUrejenost(5);
				SankanjePohorje.setCene(3);
				AdrenalinskoSankanje.vstaviSport(SankanjePohorje);

				Sport Zlodejevo = new Sport();
        Zlodejevo.setId(AdrenalinskoSankanje.getId() + "01");
				Zlodejevo.setNaziv("Zimsko letno sankališče Zlodejevo");
				Zlodejevo.setOpis("Če sodite med tiste, ki prisegajo na adrenalinske užitke, potem je kot nalašč za vas zimsko letno sankališče Zlodejevo. Drveti po njem je resnično pravo doživetje. Udobne sani so namenjene dvema osebama, lahko pa jih vozite tudi sami. Vožnja po 1.360 m dolgi vijugasti progi je zabavna, tudi kadar dežuje ali sneži. Srčni utrip se dvigne, a je strah odveč, saj zavore vedno zelo dobro delujejo. Hitrost vožnje je v vaših rokah, vendar nikoli ne preseže 40 km/h. Odločitev je torej vaša. Lahko se peljete počasi ali pa adrenalinsko hitro. Avantura se konča na nadmorski višini 1.340, od tam pa vas vlečnica, skupaj s sanmi, pripelje nazaj na vrh.");
				Zlodejevo.setStevilka("+386 3 75 74 31");
				Zlodejevo.setEmail("unitur@unitur.eu");
				Zlodejevo.setNaslov("Unior d.d. Program Turizem Cesta na Roglo 15 3214 Zreče");
				Zlodejevo.setAdrenalin(45);
				Zlodejevo.setKoordinate(new LatLng(46.369996, 15.388657));
				Zlodejevo.setDruzina(5);
				Zlodejevo.setZabava(4);
				Zlodejevo.setUrejenost(5);
				Zlodejevo.setCene(3);
				AdrenalinskoSankanje.vstaviSport(Zlodejevo);

				Sport Ljubelj1 = new Sport();
        Ljubelj1.setId(AdrenalinskoSankanje.getId() + "02");
				Ljubelj1.setNaziv("Zimsko sankanje na Ljubelju");
				Ljubelj1.setOpis("Urejena proga za sankanje s starega mednarodnega mejnega prehoda Ljubelj. Na voljo vam je tako dnevno kot nočno sankanje. Poleg sankanja vam lahko organiziramo tudi zabave.Možen je tudi najem sani!");
				Ljubelj1.setStevilka("031 513 645 ");
				Ljubelj1.setEmail("info@koren-sports.si");
				Ljubelj1.setNaslov("Pot na Bistriško planino 10 4290 Tržič Slovenija");
				Ljubelj1.setAdrenalin(15);
				Ljubelj1.setKoordinate(new LatLng(46.360762, 14.293594));
				Ljubelj1.setDruzina(5);
				Ljubelj1.setZabava(4);
				Ljubelj1.setUrejenost(5);
				Ljubelj1.setCene(3);
				AdrenalinskoSankanje.vstaviSport(Ljubelj1);

				lista.add(AdrenalinskoSankanje);
				
				//Airsoft poligoni
				TipSporta Airsoft = new TipSporta();
        Airsoft.setId("05");
				Airsoft.setNaziv("Airsoft poligoni");
				Airsoft.setOpis("Airsoft je dinamičen šport, ki izhaja iz vojaških in policijskih taktičnih vaj. Poudarek je na realizmu, orientaciji ter teamskem delu. Cilj igre je doseči določen cilj, na primer osvajanje zastave, eliminacija, respond.");
				
				Sport AirSpyder = new Sport();
        AirSpyder.setId(Airsoft.getId() + "00");
				AirSpyder.setNaziv("Airsoft park Spyder");
				AirSpyder.setOpis("Poligoni so opremljeni z naravnimi barikadami, mostički, ovirami ter zaščitno mrežo. Poleg samega igrišča imamo mize in klopi, kjer si lahko oddahnete od igre, malo posedite in tudi kaj pojeste in popijete. Lahko si priredite svoj piknik ali praznujete svoj rojstni dan.");
				AirSpyder.setStevilka("+386 41 504 955");
				AirSpyder.setEmail("info@spyder.si");
				AirSpyder.setNaslov("PAINTBALL PARK SPYDER Cesta v Gorice 13 1000 Ljubljana- Vič");
				AirSpyder.setAdrenalin(85);
				AirSpyder.setKoordinate(new LatLng(46.142408, 14.591300));
				AirSpyder.setDruzina(5);
				AirSpyder.setZabava(4);
				AirSpyder.setUrejenost(5);
				AirSpyder.setCene(3);
				Airsoft.vstaviSport(AirSpyder);
				
				lista.add(Airsoft);
		
		return lista;
	}

}
