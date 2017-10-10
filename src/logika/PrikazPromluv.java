/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Třída PrikazPromluv implementuje pro hru příkaz promluv.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jan Laštůvka (lasj00)
 *@version    02.01.2016
 */
public class PrikazPromluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "promluv";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se nachází postavy
     */
    public PrikazPromluv(HerniPlan plan)
    {
        this.plan = plan; 
    }
   
    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "promluv". Zkouší promluvit s postavami. Pokud se postava nachází v aktuálním prostoru, tak
     *  hráči sdělí informaci, která mu může pomoci dále ve hře. Pokud se postava v prostoru nenachází, nebo neexistuje,
     *  vrací chybové hlášení.
     *  
     *  @param parametry - jako parametr je zde jméno postavy, se kterou chceme promluvit
     *  @return - proslov postavy
     */ 
    public String proved(String... parametry) {
     if (parametry.length == 0) {
            return "Nezadal si název postavy, se kterou chceš promluvit.";
     }
     
     String jmeno = parametry[0];
     Prostor aktualniProstor = plan.getAktualniProstor();
     Postava postava = aktualniProstor.najdiPostavu(jmeno);
     
        if (postava == null) {
            return "Tato postava tu není.";
        }
        else {
            if (postava.getJmeno().contains("Profesor_Oak")) {
            plan.setOak(true);
            return postava.getMluvu();
        }
        else{
            return postava.getMluvu();
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
    //== Soukromé metody (instancí i třídy) ========================================


