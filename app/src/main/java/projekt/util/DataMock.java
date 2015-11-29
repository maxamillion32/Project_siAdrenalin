package projekt.util;

import java.util.ArrayList;
import java.util.List;

import projekt.data.SportList;
import projekt.data.TipSporta;

public class DataMock 
{
		
	public static List<TipSporta> getMockTipSportaList() 
	{
		List<TipSporta> lista = new ArrayList<TipSporta>();
		
		//Adrenalinski parki
		/*TipSporta AdrenalinskiPark = new TipSporta();
		AdrenalinskiPark.setNaziv("Adrenalinski parki");
		AdrenalinskiPark.setOpis("Guganje na orja�kih gugalnicah, plezanje po drevesih, spu��anje oziroma �e kar adrenalinsko letenje po jeklenih �icah, veliki trampolini ... Pri vseh teh stvareh u�ivajo tako otroci kot odrasli.");
		
		Sport Bukovnica = new Sport();
		Bukovnica.setNaziv("Pustolovski park Bukovni�ko jezero");
		Bukovnica.setOpis("Primeren za udele�ence starej�e od 4 leta. V pre�udovitem gozdu ob Bukovni�kem jezeru. 7 unikatnih elementov, skupaj 58 elementov. Prilagojen starosti in zmogljivosti. Trajanje: okrog 2 uri. Sprejme do 100 udele�encev");
		Bukovnica.setStevilka("+386 41 788 631");
		Bukovnica.setEmail("info@pustolovskipark.si");
		Bukovnica.setNaslov("Bukovni�ko jezero 9223 Dobrovnik Slovenija");
		Bukovnica.setAdrenalin(40);
		Bukovnica.setKoordinate(new LatLng(46.674274, 16.335546));				
		Bukovnica.setDruzina(5);
		Bukovnica.setZabava(4);
		Bukovnica.setUrejenost(5);
		Bukovnica.setCene(3);
		Bukovnica.setFavorite(true);
		AdrenalinskiPark.vstaviSport(Bukovnica);

		Sport Bovec = new Sport();
		Bovec.setNaziv("Adrenalinski park Zip-Line");
		Bovec.setOpis("Zip-line park nad Bovcem vam omogo�a pribli�no 2,4 kilometra dolgo adrenalinsko letenje na 200 metrih vi�ine. Vseskozi se vam odpirajo �udoviti razgledi na dolino zgornjega Poso�ja. Med letenjem boste dosegli hitrost tudi do 60 kilometrov na uro.");
		Bovec.setStevilka("040 639 433");
		Bovec.setEmail("info@ziplineslovenia.si");
		Bovec.setNaslov("Trg golobarskih �rtev 19");
		Bovec.setAdrenalin(80);
		Bovec.setKoordinate(new LatLng(46.338290, 13.552488));
		Bovec.setDruzina(5);
		Bovec.setZabava(4);
		Bovec.setUrejenost(5);
		Bovec.setCene(3);
		Bovec.setFavorite(true);
		AdrenalinskiPark.vstaviSport(Bovec);

		Sport SkofjaLoka = new Sport();
		SkofjaLoka.setNaziv("Adrenalinski park Stor�");
		SkofjaLoka.setOpis("Najvi�ji adrenalinski park v Slovenji (do 24 m) z najvi�jo orja�ko gugalnico v Sloveniji (21 m) je primeren za vse generacije. Ima bogato dodatno ponudbo: paintball, igri��e za odbojko in nogomet, kolesarjenje, pohodni�tvo, smu�i��e Stari vrh, jahanje, skok s padalom (3 km) ... pri njih imate lahko tudi piknik.");
		SkofjaLoka.setStevilka("041 749 014");
		SkofjaLoka.setEmail("ernest.kastelic@gmail.com");
		SkofjaLoka.setNaslov("Spodnja Lu�a 23, 4227 Rantovse, Slovenija");
		SkofjaLoka.setAdrenalin(60);
		SkofjaLoka.setKoordinate(new LatLng(46.179826, 14.230034));
		SkofjaLoka.setDruzina(5);
		SkofjaLoka.setZabava(4);
		SkofjaLoka.setUrejenost(5);
		SkofjaLoka.setCene(3);
		AdrenalinskiPark.vstaviSport(SkofjaLoka);

		Sport Bohinj = new Sport();
		Bohinj.setNaziv("Adrenalinski park Bohinj, pri Hostlu Pod Voglom");
		Bohinj.setOpis("Adrenalinski park v Bohinju ima poligon na vi�ini od 8 do 14 metrov, sestavljen je iz razli�nih nalog, ki jih je mo�no premagovati individualno ali skupinsko. Namenjen je odraslim, otroci naj bi se vadbe v adrenalinskem parku udele�ili po 11. letu, visoki pa naj bi bili vsaj 1,5 metra. V sklop parka spada tudi Orja�ka gugalnica.");
		Bohinj.setStevilka("040 864 202");
		Bohinj.setEmail("info@pac.si");
		Bohinj.setNaslov("Rib�ev laz 60 4265 Bohinjsko jezero Slovenija");
		Bohinj.setAdrenalin(60);
		Bohinj.setKoordinate(new LatLng(46.278717, 13.866429));
		Bohinj.setDruzina(5);
		Bohinj.setZabava(4);
		Bohinj.setUrejenost(5);
		Bohinj.setCene(3);
		AdrenalinskiPark.vstaviSport(Bohinj);

		Sport Ljubelj = new Sport();
		Ljubelj.setNaziv("Adrenalinski park Ljubelj");
		Ljubelj.setOpis("Adrenalinski park na Ljubelju je poligon na vi�ini od 8 do 14 metrov, ki je sestavljen iz razli�nih nalog, ki jih je mo�no premagovati individualno ali skupinsko. Je prvi adrenalinski park v Sloveniji (�e od leta 2001), vodijo pa ga vrhunsko usposobljeni in�truktorji. Park pa je najbolj privla�en zaradi �udovite okolice. Programa se lahko udele�ijo le osebe, ki so visoke vsaj 1,5 metra. �e so otroci mlaj�i od 12 let, jih morajo pri programu ves �as spremljati star�i.");
		Ljubelj.setStevilka("+386 31 513 645");
		Ljubelj.setEmail("info@koren-sports.si");
		Ljubelj.setNaslov("Pot na Bistri�ko planino 10 4290 Tr�i� Slovenija");
		Ljubelj.setAdrenalin(30);
		Ljubelj.setKoordinate(new LatLng(46.360688, 14.293648));
		Ljubelj.setDruzina(5);
		Ljubelj.setZabava(4);
		Ljubelj.setUrejenost(5);
		Ljubelj.setCene(3);
		AdrenalinskiPark.vstaviSport(Ljubelj);

		Sport Tolmin = new Sport();
		Tolmin.setNaziv("Adrenalinski park Tolmin");
		Tolmin.setOpis("Adrenalinski park Maya je postavljen v naravnem okolju � na drevesih. Stoji blizu soto�ja So�e in Tolminke in ima devet razli�nih preprek. Imajo jungle bridge, steber poguma, Jakobovo lestev, adrenalinsko �i�nico, zdaj ali nikoli (most iz vrvi), gugalnico, gume, hojo po jekleni vrvi ... Lahko so kombinirane z vodnimi �porti (rafting, kajak, mini rafting), lahko pa se tudi kopate.");
		Tolmin.setStevilka("+386 51 312 972");
		Tolmin.setEmail("info@maya.si");
		Tolmin.setNaslov("Borut Nikola� S. P. Padlih borcev 1 5220 Tolmin");
		Tolmin.setAdrenalin(20);
		Tolmin.setKoordinate(new LatLng(46.182350, 13.731246));
		Tolmin.setDruzina(5);
		Tolmin.setZabava(4);
		Tolmin.setUrejenost(5);
		Tolmin.setCene(3);
		Tolmin.setFavorite(true);
		AdrenalinskiPark.vstaviSport(Tolmin);

		Sport Klepec = new Sport();
		Klepec.setNaziv("Adrenalinski park Peter Klepec, Sela pri Osilnici");
		Klepec.setOpis("Park sestavljajo stolp, na katerega se povzpnete po posebnih jeklenih stopnicah, hexagon, sestavljen iz visoko stoje�ih elementov (high ropes course), plezalna stena (climbing wall), stena za spu��anje (absailing wall), spust po jeklenih vrveh (lete�i peter-zip wire), skok v prazno (all abord platforma), orja�ka gugalnica, poligon za razli�ne team buildinge in zabavne programe. Vstop v adrenalinski park je dovoljen vsakemu, ki v vi�ino meri vsaj 1,5 metra in je psihofizi�no zdrav.");
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
		Bled.setNaziv("Pustolovski park Bled");
		Bled.setOpis("Park je �e prav posebej namenjen dru�inskim obiskom, ker ponuja zabavo vsej dru�ini in je primeren za otroke od �etrtega leta dalje. Pustolovski park omogo�a gibalne aktivnosti v naravi, tako v vertikalni (plezanje, spu��anje, drsenje ...) kot tudi horizontalni (hoja, skoki ...) smeri, skratka predstavlja prostor oziroma igri��e, ki je namenjen igri, gibanju in do�ivetjem v naravi.");
		Bled.setStevilka("031 513 645");
		Bled.setEmail("info@pustolovski-park-bled.si");
		Bled.setNaslov("Pustolovski park Bled d.o.o. Bled - Stra�a 4260 Bled Slovenija");
		Bled.setAdrenalin(25);
		Bled.setKoordinate(new LatLng(46.360076, 14.104614));
		Bled.setDruzina(5);
		Bled.setZabava(4);
		Bled.setUrejenost(5);
		Bled.setCene(3);
		AdrenalinskiPark.vstaviSport(Bled);

		Sport Pohorje = new Sport();
		Pohorje.setNaziv("Adrenalinski park Pohorje, Maribor");
		Pohorje.setOpis("Adrenalinski park Pohorje ponuja zabavo in do�ivetje za adrenalinske frike in tiste, ki bi poskusili prvi�. Za mlade, za otroke in za 'navadne' odrasle. Za skupine in tiste, ki greste najraje sami. Je tudi odli�no okolje za preizkus timskega dela in/ali tekmovalnosti � adrenalinsko poletno sankali��e, minijet, vi�inski poligon, bike park, paintball poligon ... Za vsakogar se nekaj najde.");
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
		Betnava.setNaziv("Pustolovski park Betnava, Ho�e pri Mariboru");
		Betnava.setOpis("Pustolovski park Betnava ponuja najbolj�i na�in, kako odvrniti otroke in ostale od zaslonov, stolov in drugih na�inov nezdravega pre�ivljanja prostega �asa. Z razli�nimi elementi oziroma igrali mladi in manj mladi po srcu razvijajo gibljivost, se gibajo, �ivijo bolj zdravo in �utijo naravo, ki ji zaradi Betnavskega gozda, kjer se vse skupaj dogaja, �e bolj privla�na.");
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
		Jezersko.setNaziv("Do�ivljajski park Raj, Jezersko");
		Jezersko.setOpis("Park je zgrajen v naravnem okolju in z naravnimi materiali, na pribli�no 30 metrov visoki skalni pe�ini sredi gozdne jase. Vsa igrala v parku so med seboj povezana z unikatno leseno potjo. Pot se zaklju�i na razgledni plo��adi, s katere se odpre �udovit pogled na Kamni�ke Alpe. Orja�ka gugalnica, adrenalinska �i�nica, naravna stena (zavarovana plezalna pot), ferata, abzajling (spu��anje po vrvi), vise�i most, pajkova mre�a, most za padec zaupanja, tehtnica.");
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
		PaintBall.setNaziv("Paintball poligoni");
		PaintBall.setOpis("Paintball se igra v naravi (paintball markerji imajo domet od 40 do 150 metrov), ki s pomo�jo plina ali zraka izstreljujejo barvne kroglice, s katerimi se igralci medsebojno markirajo in tako izlo�ajo iz igre. ");
		
		Sport Bolfenk = new Sport();
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
		Spyder.setNaziv("Paintball park Spyder");
		Spyder.setOpis("V Paintball parku Spyder imamo najbolj sodobno opremo za igranje paintballa, s katero vam nudimo varno, udobno in najbolj adrenalinsko igranje paintballa.");
		Spyder.setStevilka("+386 41 504 955");
		Spyder.setEmail("info@spyder.si");
		Spyder.setNaslov("PAINTBALL PARK SPYDER Cesta v Gorice 13 1000 Ljubljana- Vi�");
		Spyder.setAdrenalin(75);
		Spyder.setKoordinate(new LatLng(46.142364, 14.591332));
		Spyder.setDruzina(5);
		Spyder.setZabava(4);
		Spyder.setUrejenost(5);
		Spyder.setCene(3);
		Spyder.setFavorite(true);
		PaintBall.vstaviSport(Spyder);				

		Sport PBPetanjci = new Sport();
		PBPetanjci.setNaziv("No Limits Paintball Petanjci");
		PBPetanjci.setOpis("Za varnost je poskrbljeno z uporabo vse potrebne varnostne opreme in sodni�ko nadzorovanimi igrami. Ker se igre odvijajo na neravnem terenu priporo�amo, da si priskrbite primerno obutev. Igralci morajo biti polnoletni v treznem stanju, mladoletniki pa le v spremstvu star�ev oz. skrbnikov.");
		PBPetanjci.setStevilka("031 612 672");
		PBPetanjci.setEmail("info@paintball.si");
		PBPetanjci.setNaslov("No Limits Paintball Petanjci 27 9251 Ti�ina");
		PBPetanjci.setAdrenalin(67);
		PBPetanjci.setKoordinate(new LatLng(46.650363, 16.074924));
		PBPetanjci.setDruzina(5);
		PBPetanjci.setZabava(4);
		PBPetanjci.setUrejenost(5);
		PBPetanjci.setCene(3);
		PaintBall.vstaviSport(PBPetanjci);

		Sport PBKraljevHrib = new Sport();
		PBKraljevHrib.setNaziv("Paintball Kraljev Hrib");
		PBKraljevHrib.setOpis("Preizkusite se na prvem in najatraktivnej�em slovenskem Paintball poligonu v Kamni�ki Bistrici na Kraljevem hribu, ki je znan po svoji izredni legi in urejenosti poligona (tudi za gledalce). Na Kraljevem hribu si lahko igralci izposodijo ali najamejo najsodobnej�o paintball opremo.");
		PBKraljevHrib.setStevilka("041 816 477");
		PBKraljevHrib.setEmail("info@kraljevhrib.si");
		PBKraljevHrib.setNaslov("KAMNI�KA BISTRICA 2 1242 STAHOVICA Slovenija");
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
		Padalstvo.setNaziv("Skok z padalom");
		Padalstvo.setOpis("Tandemski skok s padalom je skok iz letala na vi�ini 4.000 metrov, seveda v spremstvu izku�enega tandem pilota. Spu��ali se boste s hitrostjo do 220 km/h, zraven pa u�ivali ob izjemnem razgledu.");
		
		Sport ProstiPad = new Sport();
		ProstiPad.setNaziv("Padalsko dru�tvo prosti pad");
		ProstiPad.setOpis("Ekipo Prosti pad sestavljajo �lani dr�avne reprezentance v likovnih skokih, ki velja za najbolj elitno padalsko disciplino. Na�i in�truktorji, edini v Sloveniji, organizirajo tunnelcampe, v simulatorjih prostega pada po vsem svetu.");
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
		PohorjePadalo.setNaziv("�portni center Pohorje");
		PohorjePadalo.setOpis("�e imate �eljo po prostem padu, ob�utku neomejene svobode in letenja, vam organiziramo skok s padalom v tandemu iz letala z vi�ine 3000 � 4000 metrov. Skoke izvajamo na �portnih letali��ih po Sloveniji.");
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
		SkyDive.setNaziv("Skydive Maribor");
		SkyDive.setOpis("Smo padalska sekcija Letalskega centra Maribor (LCM), najve�ji padalski center v Sloveniji. V padalski sekciji smo odprti za vse, ki vas zanima padalski �pot in imate �eljo, da se nam pridru�ite.");
		SkyDive.setStevilka("02 629 62 06");
		SkyDive.setEmail("lcmaribor@siol.net");
		SkyDive.setNaslov("Padalska sekcija LCM Letali�ka cesta 30 2204 SKOKE");
		SkyDive.setAdrenalin(85);
		SkyDive.setKoordinate(new LatLng(46.482618, 15.690153));
		SkyDive.setDruzina(5);
		SkyDive.setZabava(4);
		SkyDive.setUrejenost(5);
		SkyDive.setCene(3);
		Padalstvo.vstaviSport(SkyDive);

		Sport Vogel = new Sport();
		Vogel.setNaziv("Jadralno padalstvo na voglu v zimski sezoni");
		Vogel.setOpis("Vogel spada med najlep�e vzletne to�ke za polete z jadralnim padalom v Sloveniji. Iz Ukanca se z nihalko zapeljemo do zgornje postaje �i�nice Vogel, od koder se nato s �tirisede�nico odpeljemo do Orlovih glav.");
		Vogel.setStevilka("04 572 97 12");
		Vogel.setEmail("info@vogel.si");
		Vogel.setNaslov("�i�nice Vogel Bohinj, d.d. Ukanc 6 4265 Bohinjsko Jezero");
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
		AdrenalinskoSankanje.setNaziv("Adrenalinsko sankanje");
		AdrenalinskoSankanje.setOpis("�e sodite med tiste, ki prisegajo na adrenalinske u�itke, potem je kot nala�� za vas adrenalinsko sankanje.");
		
		Sport SankanjePohorje = new Sport();
		SankanjePohorje.setNaziv("PohorJET Maribor");
		SankanjePohorje.setOpis("Proga PohorJET-a te�e po Sne�nem stadionu od Trikotne jase do ciljne arene Sne�nega stadiona. Na start vas pripelje sede�nica Radvanje. �e ob pogledu na progo je o�itno, da ostri zavoji med drevesi, drzni spusti v nepredvidljiva brezna in ravninski hitri deli ne bodo dopu��ali le�erne in spro��ene vo�nje. Vedno pa lahko hitrost prilagodimo lastnim sposobnostim in �eljam s pomo�jo zavore. Za varnost je ustrezno poskrbljeno.");
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
		Zlodejevo.setNaziv("Zimsko letno sankali��e Zlodejevo");
		Zlodejevo.setOpis("�e sodite med tiste, ki prisegajo na adrenalinske u�itke, potem je kot nala�� za vas zimsko letno sankali��e Zlodejevo. Drveti po njem je resni�no pravo do�ivetje. Udobne sani so namenjene dvema osebama, lahko pa jih vozite tudi sami. Vo�nja po 1.360 m dolgi vijugasti progi je zabavna, tudi kadar de�uje ali sne�i. Sr�ni utrip se dvigne, a je strah odve�, saj zavore vedno zelo dobro delujejo. Hitrost vo�nje je v va�ih rokah, vendar nikoli ne prese�e 40 km/h. Odlo�itev je torej va�a. Lahko se peljete po�asi ali pa adrenalinsko hitro. Avantura se kon�a na nadmorski vi�ini 1.340, od tam pa vas vle�nica, skupaj s sanmi, pripelje nazaj na vrh.");
		Zlodejevo.setStevilka("+386 3 75 74 31");
		Zlodejevo.setEmail("unitur@unitur.eu");
		Zlodejevo.setNaslov("Unior d.d. Program Turizem Cesta na Roglo 15 3214 Zre�e");
		Zlodejevo.setAdrenalin(45);
		Zlodejevo.setKoordinate(new LatLng(46.369996, 15.388657));
		Zlodejevo.setDruzina(5);
		Zlodejevo.setZabava(4);
		Zlodejevo.setUrejenost(5);
		Zlodejevo.setCene(3);
		AdrenalinskoSankanje.vstaviSport(Zlodejevo);

		Sport Ljubelj1 = new Sport();
		Ljubelj1.setNaziv("Zimsko sankanje na Ljubelju");
		Ljubelj1.setOpis("Urejena proga za sankanje s starega mednarodnega mejnega prehoda Ljubelj. Na voljo vam je tako dnevno kot no�no sankanje. Poleg sankanja vam lahko organiziramo tudi zabave.Mo�en je tudi najem sani!");
		Ljubelj1.setStevilka("031 513 645 ");
		Ljubelj1.setEmail("info@koren-sports.si");
		Ljubelj1.setNaslov("Pot na Bistri�ko planino 10 4290 Tr�i� Slovenija");
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
		Airsoft.setNaziv("Airsoft poligoni");
		Airsoft.setOpis("Airsoft je dinami�en �port, ki izhaja iz voja�kih in policijskih takti�nih vaj. Poudarek je na realizmu, orientaciji ter teamskem delu. Cilj igre je dose�i dolo�en cilj, na primer osvajanje zastave, eliminacija, respond, scenarij�");
		
		Sport AirSpyder = new Sport();
		AirSpyder.setNaziv("Airsoft park Spyder");
		AirSpyder.setOpis("Poligoni so opremljeni z naravnimi barikadami, mosti�ki, ovirami ter za��itno mre�o. Poleg samega igri��a imamo mize in klopi, kjer si lahko oddahnete od igre, malo posedite in tudi kaj pojeste in popijete. Lahko si priredite svoj piknik ali praznujete svoj rojstni dan.");
		AirSpyder.setStevilka("+386 41 504 955");
		AirSpyder.setEmail("info@spyder.si");
		AirSpyder.setNaslov("PAINTBALL PARK SPYDER Cesta v Gorice 13 1000 Ljubljana- Vi�");
		AirSpyder.setAdrenalin(85);
		AirSpyder.setKoordinate(new LatLng(46.142408, 14.591300));
		AirSpyder.setDruzina(5);
		AirSpyder.setZabava(4);
		AirSpyder.setUrejenost(5);
		AirSpyder.setCene(3);
		Airsoft.vstaviSport(AirSpyder);		
		
		lista.add(Airsoft);*/

		return lista;
	}

	public static SportList getMockSportList() {
		
		return null;
	}
	

}
