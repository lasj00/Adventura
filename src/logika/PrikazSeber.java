/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class PrikazSeber implements IPrikaz
{
   private static final String NAZEV = "seber";
   private HerniPlan plan;
   private Batoh batoh;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém budeme sbírat věci
    *  @param batoh batoh, do kterého se ukladají sebrané věci
    */    
    public PrikazSeber(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "seber". Zkouší sebrat věc z prostoru. Pokud se věc v prostoru nachází a je přenositelná
     *  uloží se do batohu a z prostoru zmizí. Pokud věc v prostoru není nebo není přenostitelná, vypíše se chybové hlášení.
     *  Pokud již hráč má v batohu věc "Pokéball", vypíše chybové hlášení.
     *  Pokud se hráč pokusí sebrat něco před tím, než splní podmínku pro sebrání, vypíše se chybové hlášení
     *
     *@param parametry - jako parametr je název sbírané věci, kterou hráč chce sebrat
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (název věci), tak ....
            return "Co mám sebrat? Musíš zadat název věci";
     }

     String nazevSbiraneVeci = parametry[0];
     Prostor aktualniProstor = plan.getAktualniProstor();
     Vec sbirana = aktualniProstor.odeberVec(nazevSbiraneVeci);
        
        if (sbirana == null) {
            return "To tu není!";
        }
        else {
            //hráč může mít pouze jeden Pokéball, nejdřív tedy zjištujeme zdali hráč nemá v batohu Pokéball
            if(sbirana.getNazev().equals("Pokéball") && batoh.obsahujeVecVBatohu("Pokéball") == true){
                aktualniProstor.vlozVec(sbirana);
                return "Již v batohu máš Pokéball!";
            }
            else{
        if(sbirana.getNazev().equals("Pokéball") && aktualniProstor.getNazev().equals("Laboratoř") && plan.isOak() == false){
            aktualniProstor.vlozVec(sbirana);
            return "První promluv s Profesorem Oakem!";
        }
        else{
        //Dá se věc sebrat?
        if(sbirana.isPrenositelna()){         
            //je v batohu místo?
            if(batoh.vlozVecDoBatohu(sbirana) == true){
                    //věc se dá sebrat a v batohu je místo
                    batoh.vlozVecDoBatohu(sbirana);
                    return "Sebral si " + sbirana.getNazev() + "a do bahotu se ti vejde ještě " +  batoh.getPocetVeciJeste() + " věc/i";
            }
         }
        else{
                //věc se nedá sebrat
                aktualniProstor.vlozVec(sbirana);
                return "To je moc velký!";
            }
        //není v něm místo
        aktualniProstor.vlozVec(sbirana);
        return "Máš plný batoh";
       }
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
