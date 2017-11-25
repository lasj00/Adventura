/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Třída PrikazChyt implementuje pro hru příkaz chyt
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class PrikazChyt implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "chyt";
    private HerniPlan plan;
    private UlozisteNaPokemony ulozisteNaPokemony;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém budeme chytat Pokémony
     *  @param ulozisteNaPokemony uložiště na Pokémony, do kterého se ukládají chycení Pokémoni
     *  @param batoh batoh, z kterého jsou tahány Pokébally, potřebné k chycení Pokémona
     */
    public PrikazChyt(HerniPlan plan, UlozisteNaPokemony ulozisteNaPokemony, Batoh batoh)
    {
        this.plan = plan;
        this.ulozisteNaPokemony = ulozisteNaPokemony;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "chyt". Zkouší chytit Pokémona. Pokud se daný Pokémon v prostoru nachází a
     *  pokud má hráč v batohu Pokéball, Pokémon bude chycen. Pokud zadaný Pokémon neexistuje nebo
     *  se nenachází v daném prostoru, bude vypsáno chybové hlášení.
     *  Pokud hráč nemá v batohu Pokéball, bude vypsáno chybové hlášení
     *
     *@param parametry - jako  parametr obsahuje pojmenování chytaného Pokémona
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Koho mám chytit? Musíš zadat pojmenování Pokémona";
        }

        String jmenoChytPokemona = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Pokemon chytany = aktualniProstor.najdiPokemona(jmenoChytPokemona);
        
        if (chytany == null) {
            return "Ten tu není!";
        }
        else {
            //je v batohu nějaký Pokéball?
           if(batoh.obsahujeVecVBatohu("Pokéball") == true){
               //není plné uložiště?
                if(ulozisteNaPokemony.ulozPokemonaDoUloziste(chytany) == true){
                //v uložišti je prostor na Pokémona a mám Pokéball
                ulozisteNaPokemony.ulozPokemonaDoUloziste(chytany);
                aktualniProstor.odeberPokemona(jmenoChytPokemona);
                batoh.vyberVecVBatohu("Pokéball");
                plan.notifyObservers();
                return "Chytil si " + chytany.getPojmenovani();
               }
                }
           else {
                //v batohu není Pokéball
                return "Na to aby si mohl chytit Pokémona musíš mít Pokéball";
             }
        }
        //uložiště je plné
        return "Už si pochytal 6 Pokémonů, víc jich mít nemůžeš!";
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
