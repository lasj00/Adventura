/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída Pokemon - popisuje jednotlivé Pokemony vložené do prostoru
 *
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private String mluva;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  Postava má jméno a hráči sděluje informace (mluva)
     */
    public Postava(String jmeno, String mluva)
    {
        this.jmeno = jmeno;
        this.mluva = mluva;
    }
    

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     * Metoda vrací jméno postavy.
     * 
     * @return   String jméno postavy.
     */
    public String getJmeno() {
        return jmeno; 
    }
    
    /**
     * Metoda vrací to co řekne hráčovi postava.
     * 
     * @return   String mluva postavy.
     */
    public String getMluvu() {
        return mluva; 
    }
    

    //== Soukromé metody (instancí i třídy) ========================================

}
