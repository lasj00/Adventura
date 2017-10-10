/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída Věc - popisuje jednotlivé věci vložené do prostoru
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelna;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  Věc má svůj název a poté může být přenositelná (lze sebrat) a nepřenositelná
     */
    public Vec(String nazev, boolean prenositelna)
    {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Vrací název věci
     * 
     * @return název věci
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Vrací true pokud je věc přenositelná a false pokud je nepřenositelná
     */
    public boolean isPrenositelna(){
        return prenositelna;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
