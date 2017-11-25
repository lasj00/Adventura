package logika;

import java.util.*;


/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Laštůvka (lasj00)
 * @version 02.01.2016
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;
    private Map<String, Postava> postavy;
    private Map<String, Pokemon> pokemoni;
    private double posLeft;
    private double posTop;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        this.posLeft = posLeft;
        this.posTop = posTop;
        
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap<>();
        pokemoni = new HashMap<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "\nJsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"  
                + popisVeci() + "\n"
                + popisPostavy() + "\n"
                + popisPokemonu() + "\n";
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;  
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Metoda vkládá věci do prostoru
     */
    public boolean vlozVec(Vec neco){
        if(veci.containsKey(neco.getNazev())){
            return false;
        }
        else{
            veci.put(neco.getNazev(),neco);
            return true;
        }
    }
   
    /**
     * Metoda maže věci
     * 
     * @return název mazané věci
     */
    public Vec odeberVec(String nazev){
        return veci.remove(nazev);
    }
    
    /**
     *  Zjisti, jestli je věc v prostoru
     *  Využito pouze v testech
     *  
     *  @param jmenoVeci
     *  @return true, když obsahuje věc a false, když neobsahuje věc
     */
    public boolean obsahujeVec (String jmenoVeci) {
        return veci.containsKey(jmenoVeci);
    }
    
    /**
     * Metoda vypíše věci, které se nacházejí v prostoru
     * Pokud v prostoru nic není, je to hráčovi sděleno
     * 
     * @return vzkaz pro hráče
     */
    private String popisVeci(){
        String popisVeci = "";
        if(veci.isEmpty()){
            popisVeci = "Nic tu není.";
        }
        else{
            popisVeci = "A je tu: ";
            for(String nazev : veci.keySet()){
                popisVeci += nazev + " ";
            }
        }
        return popisVeci;
    }
    
     /**
     * Metoda vloží postavu do prostoru.
     * 
     * @return jméno vkládáné postavy
     */
    public void vlozPostavu(Postava postava)
    {
        getPostavy().put(postava.getJmeno(), postava);
    }
    
    /**
     * Metoda najde postavu
     * 
     * @return jméno hledané postavy
     */
    public Postava najdiPostavu(String jmeno)
     {
        return getPostavy().get(jmeno);
     }
    
    /**
     * Vypíše názvy postav v Prostoru
     * Pokud v prostoru nikdo nestojí, je to hráči sděleno
     * 
     * @return vzkaz pro hráče
     */
     private String popisPostavy(){
        String popisPostavy = "";
        if(getPostavy().isEmpty()){
            popisPostavy = "Nikdo tu není.";
        }
        else{
            popisPostavy = "A stojí tu: ";
            for(String nazev : getPostavy().keySet()){
                popisPostavy += nazev + " ";
            }
        }
        return popisPostavy;
    }
    
     /**
     *  Zjisti, jestli je Postava v prostoru
     *  Využito pouze v testech
     *  
     *  @param jmenoPostavy
     *  @return true, když tam postava je a false, když tam postava není
     */
    public boolean obsahujePostavu (String jmenoPostavy) {
        return getPostavy().containsKey(jmenoPostavy);
    }
    
    /**
     * Metoda vkládá Pokémona do prostoru
     * 
     * @return pojmenováné vkládaného Pokémona
     */
    public void vlozPokemona(Pokemon pokemon)
    {
        pokemoni.put(pokemon.getPojmenovani(), pokemon);
    }
    
    /**
     * Metoda vyhledá Pokémona
     * 
     * @return pojmenování hledaného Pokémona
     */
    public Pokemon najdiPokemona(String pojmenovani)
     {
        return pokemoni.get(pojmenovani);
     }
     
    /**
     * Metoda vymaže Pokémona z prostoru
     * 
     * @return pojmenování mazaného Pokémona
     */
     public Pokemon odeberPokemona(String pojmenovani){
        return pokemoni.remove(pojmenovani);
    }
    
    /**
     * Metoda vypíše pojmenování Pokémonů v prostoru
     * Pokud se v prostoru žádný Pokémon nenachází, je to sděleno hráčovi
     * 
     * @return vzkaz pro hráče
     */
    private String popisPokemonu(){
        String popisPokemonu = "";
        if(pokemoni.isEmpty()){
            popisPokemonu = "Není tu žádný Pokémon.";
        }
        else{
            popisPokemonu = "A běhají zde: ";
            for(String nazev : pokemoni.keySet()){
                popisPokemonu += nazev + " ";
            }
        }
        return popisPokemonu;
    }
    
     /**
     *  Zjisti, jestli je Pokémon v prostoru
     *  Využito pouze v testech
     *  
     *  @param jmenoPokemona
     *  @return true, když tam Pokémon je a false, když tam Pokémon není
     */
    public boolean obsahujePokemona (String jmenoPokemona) {
        return pokemoni.containsKey(jmenoPokemona);
    }
    
    public String seznamVychodu() 
    {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
             vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    

    /**
     * @return the posLeft
     */
    public double getPosLeft() {
        return posLeft;
    }

    /**
     * @return the posTop
     */
    public double getPosTop() {
        return posTop;
    }

    public Map<String, Vec> getVeci() {
        return veci;
    }

    public Map<String, Pokemon> getPokemony() {
         return pokemoni;
    }

    /**
     * @return the postavy
     */
    public Map<String, Postava> getPostavy() {
        return postavy;
    }
    
  
    
}


