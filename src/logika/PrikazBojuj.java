/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Třída PrikazBojuj implementuje pro hru příkaz bojuj.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class PrikazBojuj implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "bojuj";
    private HerniPlan plan;
    private UlozisteNaPokemony ulozisteNaPokemony;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém budeme bojovat s postavami
     *  @param ulozisteNaPokemony uložiště na Pokémony, které určí úspěšnost boje
     *  @param batoh batoh, ve kterém musí být určitý předmět 
     */
    public PrikazBojuj(HerniPlan plan, UlozisteNaPokemony ulozisteNaPokemony, Batoh batoh)
    {
        this.plan = plan;
        this.ulozisteNaPokemony = ulozisteNaPokemony;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     *  Provádí příkaz "bojuj". Pokud hráč pochytal požadované množství Pokémonů a našel (má v batohu) vyžadovaný předmět
     *  porazí protivníka. Pokud nemá dost Pokémonů nebo nemá předmět prohraje. Pokud nezadá jméno postavy, nebo postava neexistuje,
     *  nebo postava se v prostoru nenachází vypíše chybové hlášení.
     *
     *@param parametry - jako parametr je zde jméno postavy se kterou hodláme bojovat
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Nezadal si název postavy, se kterou chceš bojovat";
        }
        
        String jmeno = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.najdiPostavu(jmeno);
        
        if (postava == null) {
            return "Tato postava tu není.";
        }
        if (postava.getJmeno().contains("Brock")) {
            if(ulozisteNaPokemony.getPocetPokemonu()>=3 && batoh.obsahujeVecVBatohu("Potion") == true){
                plan.setVyhra(true);
            }
            else{
                return "Brock byl silnější než ty! Porazil tě! Až budeš silnější, vrať se a znovu vyzvi Brocka k souboji...";
            }
        }
        return "S touto postavou nejde bojovat";
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
