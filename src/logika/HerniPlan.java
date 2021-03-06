package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Laštůvka (lasj00)
 *@version    02.01.2016
 */
public class HerniPlan implements Subject{
    
    private Prostor aktualniProstor;
    private boolean vyhra = false;
    private boolean oak = false;
    private Hra hra;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) 
    {
        zalozProstoryHry();
        this.hra = hra;
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor mujdum = new Prostor("Domov", "Dům, do kterého jste s mámou se nastěhovali,\nje zde uklizeno a ve vzduchu je cítít teplo domova", 95, 515);
        Prostor mesto1 = new Prostor("Pallet_Town","Městečko, do kterého jste se mámou nastěhovali. Malé a velmi klidné městečko obklopené lesem", 120, 530);
        Prostor laborator = new Prostor("Laboratoř","Moderní laboratoř profesora Oaka, ve které hlouběji zkoumá Pokémony", 150, 552);
        Prostor jehodum = new Prostor("Dům_rivala","Dům, ve kterém bydlí tvůj rival Garry. Garry se stejně jako ty chce stát Pokémoním mástrem", 150, 515);
        Prostor route11 = new Prostor("Travnatá_Route1","Zatravněná část Route 1. Je zde vysoká tráva, ve které se ukrývají divocí Pokémoni",95, 435);
        Prostor route12 = new Prostor("Prázdná_Route1","Klidná část Route1, je zde klid. Je jen slyšet šumění okolního lesa", 95, 340);
        Prostor route13 = new Prostor("Zalesněná_Route1","Route 1 se zde mění v malý lesík. Není problém ho projít. Žijí zde divocí Pokémoni, rostou zde maliny a borůvky",150, 280);
        Prostor mesto2 = new Prostor("Viridian_City","Velké měste, je zde několik domů, lékárna a samotná Gym. Vládne zde městský ruch a lze zde potkat zajímavé lidi",170, 125);
        Prostor dum1 = new Prostor("Dům_starce","V tomto baráku bydlí moudrý stařec",140, 100);
        Prostor dum2 = new Prostor("Dům_mladých","Bydlí zde mladá rodina",140, 60);
        Prostor dum3 = new Prostor("Dům_Christiana","Bydlí zde Christian, bývalí vítěz Pokémonní ligy", 115, 15);
        Prostor sklad = new Prostor("Sklad","V tomto skladě jsou zdarma k dispozici Pokébally pro nové trenéry",200, 105);
        Prostor gym = new Prostor("Gym","Gym, ve které je samotná Gym Leader Brock, pokud si myslíš, že ho zvládneš porazit, vyzvi ho k souboji",200, 50);
        Prostor lekarna = new Prostor("Lékárna","Je zde k dispozici Potion - lék pro Pokémony", 140, 152);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        mujdum.setVychod(mesto1);
        mesto1.setVychod(mujdum);
        mesto1.setVychod(laborator);
        mesto1.setVychod(jehodum);
        mesto1.setVychod(route11);
        laborator.setVychod(mesto1);
        jehodum.setVychod(mesto1);
        route11.setVychod(mesto1);
        route11.setVychod(route12);
        route12.setVychod(route11);
        route12.setVychod(route13);
        route13.setVychod(route12);
        route13.setVychod(mesto2);
        mesto2.setVychod(route13);
        mesto2.setVychod(dum1);
        mesto2.setVychod(dum2);
        mesto2.setVychod(dum3);
        mesto2.setVychod(sklad);
        mesto2.setVychod(gym);
        mesto2.setVychod(lekarna);
        lekarna.setVychod(mesto2);
        dum1.setVychod(mesto2);
        dum2.setVychod(mesto2);
        dum3.setVychod(mesto2);
        sklad.setVychod(mesto2);
        gym.setVychod(mesto2);
        
        //vloží věco do prostoru
        Vec kolac = new Vec ("Koláč", true,"Kolac.png");
        mujdum.vlozVec(kolac);
        Vec stul = new Vec ("Stůl", false,"Stul.png");
        mujdum.vlozVec(stul);
        jehodum.vlozVec(stul);
        laborator.vlozVec(stul);
        dum1.vlozVec(stul);
        dum2.vlozVec(stul);
        Vec lampa = new Vec ("Lampa", false,"Lampa.png");
        mujdum.vlozVec(lampa);
        Vec pokeball = new Vec ("Pokéball", true,"Pokeball.png");
        route12.vlozVec(pokeball);
        sklad.vlozVec(pokeball);
        dum3.vlozVec(pokeball);
        gym.vlozVec(pokeball);
        laborator.vlozVec(pokeball);
        route13.vlozVec(pokeball);
        dum1.vlozVec(pokeball);
        Vec potion = new Vec ("Potion", true,"Potion.png");
        lekarna.vlozVec(potion);
        Vec bonbon = new Vec ("Bonbón", true,"Bonbon.png");
        route13.vlozVec(bonbon);
        Vec voda = new Vec("Čistá_voda", true,"Voda.png");
        dum2.vlozVec(voda);
        Vec dalekohled = new Vec("Dalekohled", false,"Dalekohled.png");
        laborator.vlozVec(dalekohled);
        Vec vyzkum = new Vec("Výzkumné_zařízení", false,"Zarizeni.png");
        laborator.vlozVec(vyzkum);
        Vec rentgen = new Vec("Rentgén", false,"Rentgen.png");
        laborator.vlozVec(rentgen);
        Vec televize = new Vec("Televize", false,"Televize.png");
        dum1.vlozVec(televize);
        dum2.vlozVec(televize);
        dum3.vlozVec(televize);
        Vec gauc = new Vec("Gauč", false,"Gauc.png");
        dum3.vlozVec(gauc);
        Vec boruvky = new Vec("Borůvky", true,"Boruvky.png");
        route13.vlozVec(boruvky);
        Vec maliny = new Vec("Maliny", true,"Maliny.png");
        route13.vlozVec(maliny);
        Vec xspeed = new Vec("xSpeed", true,"Speed.png");
        dum3.vlozVec(xspeed);
        Vec pokedex = new Vec("PokéDex", true,"Pokedex.png");
        laborator.vlozVec(pokedex);
        Vec mapa = new Vec("Mapa", true,"Mapa_vec.png");
        jehodum.vlozVec(mapa);
        
        //hra začíná doma
        aktualniProstor = mujdum;      
        
        //vloží postavy do prostoru
        mujdum.vlozPostavu(new Postava("Máma", "Synu! už jsi vyrostl, jseš dospělý! Dávej si na tvém dobrodružství pozor! Kdyby cokoliv, vždy víš kam se vrátit - domů\n","Mama.png"));
        jehodum.vlozPostavu(new Postava("Garryho_máma", "Ahoj, Garry již vyšel na dobrodružství! Říkal, že jako první půjde do profesorovi laboratoře a pak vyrazí. Přeji ti krásnou cestu\n","Mama2.png"));
        laborator.vlozPostavu(new Postava("Profesor_Oak", "Ahoj! Ashi! Již tě očekávám.\nNež vyjdeš na tvé dobrodružství musím tě nejdřív seznámit se světem Pokémonů. Pokémoni od teď budou tvý nejlepší kamarádi.\n" + 
        "Jsou to stvoření, která můžeš chytat, bojovat s nimi a hrát si s nimi.\n" +
        "Existuje mnoho druhů Pokémonů, každý má nějaké slabiny a nějaké silné stránky.\nNa světě je více Pokémonů než si myslíš a dodnes jsme je ještě všechny neprozoumali....\nNo už dost povídání...." +
        "Nyní potřebuješ tvého prvního Pokémona, jelikož vydat se na dobrodružství bez něho by bylo nebezpečné!\nNaučím tě tedy jak Pokémony chytat. Zde v laboratoři leží Pokéball a běhá zde Charmander - ohnivý Pokémon!\n" + 
        "Pokéball je zařízení, do kterého chytáš Pokémony. Abys chytil tohoto Charmandera,seber ten Pokéball a hoď ho po něm!\nNení to žádná věda....Inuž neváhej pusť se do toho!\n" +
        "Přeji ti krásnou cestu!\n","Oak.png"));
        route12.vlozPostavu(new Postava("Trenér", "Ahoj, ty si nový trenér? pokud chceš chytit nového Pokémona potřebuješ Pokéball....A pozor! můžeš nachytat maximálně 6 Pokémonů. Šťastnou cestu!\n","Trener.png"));
        dum1.vlozPostavu(new Postava("Stařec", "Vítej...mladý muži! V tomto městě máme Gymu, je to místo kde prokazuješ svoje schopnosti a dovednosti se svými Pokémony. \n Pokud se rozhodneš bojovat nezapomeň mít u sebe Potion.\n" + 
        "Potion je lék, který podáváš svému Pokémonovi, když mu v boji docházejí síly. Proto je důležité ji mít vždy u sebe.\n Přeji ti mnoho zdaru!\n","Starik.png"));
        dum2.vlozPostavu(new Postava("Otec_rodiny","Ahoj trenére. Nedavno jsme se nastěhovali do Viridian City, moc se nám tu líbí\n","Otec.png"));
        dum2.vlozPostavu(new Postava("Matka_rodiny","Máme dceru, je zatím moc malá, ale až vyroste, také se chce stát trenérem\n","Mama3.png"));
        dum2.vlozPostavu(new Postava("Dcera","Můj oblíbený Pokémon je Pikachu!\n","Holka.png"));
        dum3.vlozPostavu(new Postava("Christian","Čau! Před pěti lety jsem vyhrál celou Pokémonní ligu! Není to rozhodně lehká cesta, ale ty vypadáš jako odhodlaný trenér!\n" +
        "V tomto městě je první Gym na tvé cestě. Gym Leader Brock je silný a rozhodně ho neporazíš jen s jedním Pokémonem!\n Kdyby se ti ho nedařilo porazit, neváhej vyrazit na Route 1 a pochytat nové Pokémony! \n Přeji ti krásnou cestu!\n","Master.png"));
        sklad.vlozPostavu(new Postava("Skladník","Zdravíčku trenére! Toto je sklad Pokéballů. Pokud tu ještě nějaký zbyl, tak neváhej a vem si ho!\n","Skladnik.png"));
        lekarna.vlozPostavu(new Postava("Lékárnice","Ahojky! Vítej v lékarně, zde si můžeš vzít Potion pro tvé Pokémony\n","Sestra.png"));
        mesto1.vlozPostavu(new Postava("Chlapec","Ahoj, víš o tom, že kdysi byl veliký nedostatek Pokéballů, a tak vstoupil v platnost zákon o Pokéballech? \n" +
        "Zákon říká, že trenér může mít u sebe (v batohu) maximálně jeden prázdný Pokéball!...Pokéball ani nesmíš vyhodit! \n" + 
        "Když už si ho jednou sebral musíš si ho buď nechat nebo ho použít! Zajímavé...co?\n","Kluk.png"));
        mesto2.vlozPostavu(new Postava("Horolezec","Zdravím! Víš o tom, že nejvyšší místo v Johto regionu je hora Mt. Indingo?\n","Hiker.png"));
        mesto2.vlozPostavu(new Postava("Cestovatel","Největší město v regionu je Saffron City, je tam obrovský obchod, zářící světla a všechno se třpití! Jinak svět Pokémonů je větší než si myslíš!..\n","Cestovatel.png"));
        gym.vlozPostavu(new Postava("Brock","Já jsem Brock, Gym Leader této Gym. Cítíš se býti dosti silný na to, aby si mě porazil a obdržel Badge?\n","Brock.png"));
  
        
        //vloží pokemony do prostoru
        route11.vlozPokemona(new Pokemon("Pikachu","Pikachu.jpg"));
        route11.vlozPokemona(new Pokemon("Ratata","Ratata.jpg"));
        route11.vlozPokemona(new Pokemon("Pidgey","Pidgey.jpg"));
        route13.vlozPokemona(new Pokemon("Caterpie","Caterpie.jpg"));
        route13.vlozPokemona(new Pokemon("Machop","Machop.jpg"));
        route13.vlozPokemona(new Pokemon("Weedle","Weedle.jpg"));
        route13.vlozPokemona(new Pokemon("Zubat","Zubat.jpg"));
        laborator.vlozPokemona(new Pokemon("Charmander","Charmander.jpg"));
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }
    
    /**
     * Metoda vrací true pokud nastala výhra
     */
    public boolean isVyhra() {
        return vyhra;
    }
    
    /**
     * Metoda slouží k nastavení "true" u výhry
     */
    public void setVyhra(boolean stav) {
        this.vyhra = stav;
    }
    
    /**
     * Metoda slouží k nastavení "true" v případě promluvení s Profesorem Oakem
     */
    public void setOak(boolean mluv) {
        this.oak = mluv;
    }
    
    /**
     * Metoda vrací true pokud hráč promluvil s Profesorem Oakem
     */
    public boolean isOak(){
        return oak;
    }

    public Hra getHra() {
        return hra;
    }
    
    
    
    //abstraktní metody
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);    
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);   
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
