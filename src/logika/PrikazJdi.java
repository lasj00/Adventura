package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Jan Laštůvka (lasj00)
 *@version    02.01.2016
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private UlozisteNaPokemony ulozisteNaPokemony;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit"
    *  @param ulozisteNaPokemony uložiště na Pokémony, které omezuje vstup do určitých prostor
    */    
    public PrikazJdi(HerniPlan plan, UlozisteNaPokemony ulozisteNaPokemony) {
        this.plan = plan;
        this.ulozisteNaPokemony = ulozisteNaPokemony;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *  Pokud hráč nemá dost Pokémonů, neprojde do prostoru "Travnatá_Route1"
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu), do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
      if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno místa kam chceš jít!";
      }

      String smer = parametry[0];
      Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            if(sousedniProstor.getNazev().equals("Travnatá_Route1") && ulozisteNaPokemony.getPocetPokemonu()== 0){
                return "Je příliš nebezpečné se vydat do trávy bez Pokémona!";
            }
            else{
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
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
}
