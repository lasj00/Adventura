/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Třída PrikazObsahBatohu implementuje pro hru příkaz obsah_batohu.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class PrikazObsahBatohu implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "obsah_batohu";
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param batoh batoh, ve kterém jsou uloženy všechny posbírané věci
     */
    public PrikazObsahBatohu(Batoh batoh)
    {
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "obsah_batohu". Vypíše obsah batohu - názvy všech věcí, které hráč posbíral
     *
     *@return názvy věcí v batohu
     */ 
    @Override
    public String proved(String... parametry) {
        return batoh.nazvyVeciVBatohu();
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
