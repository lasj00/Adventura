/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Třída PrikazVyhod implementuje pro hru příkaz vyhod.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class PrikazVyhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, so kterého budeme vyhazovat věci
     *  @param batho batoh, ze kterého věc mažeme (vyhazujeme)
     */
    public PrikazVyhod(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "vyhod". Zkouší vyhodit věc z batohu. Pokud věc v batohu je, bude vyhozena
     *  do aktuálního prostoru. Pokud věc v batohu není, nebo věc neexistuje, vypíše chybové
     *  hlášení.
     *  Věc "Pokéball" nelze vyhodit z batohu. Pokud bude pokus o jeho vyhození, vypíše chybové
     *  hlášení.
     *
     *@param parametry - jako parametr obsahuje jméno mazané věci - věc, kterou chceme vyhodit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Co přesně mám vyhodit?";
        }

        String jmenoMazaneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec mazana = batoh.vyberVecVBatohu(jmenoMazaneVeci);

        if (mazana == null) {
            // pokud mazana věc není v batohu.
            return "Taková věc v batohu není!";            
        }
        else {
            if(mazana.getNazev().equals("Pokéball")){
                batoh.vlozVecDoBatohu(mazana);
                return "Pokéball nejde vyhodit z batohu!";
            }
            else{
                // pokud je věc smazána z batohu, přesune se do aktualního prostoru
                aktualniProstor.vlozVec(mazana);
                plan.notifyObservers();
                return "Vyhodil jsi " + jmenoMazaneVeci;
            }
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
