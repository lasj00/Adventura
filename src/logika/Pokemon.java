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
public class Pokemon
{
    //== Datové atributy (statické i instancí)======================================
    private String pojmenovani;
    private String obrazek;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  Pokémon má pojmenování
     */
    public Pokemon(String pojmenovani,String obrazek)
    {
        this.pojmenovani = pojmenovani;
        this.obrazek = obrazek;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vrací jak se Pokémon jmenuje.
     * 
     * @return   String return pojmenovaní.
     */
    public String getPojmenovani() {
        return pojmenovani; 
    }
    
    

    //== Soukromé metody (instancí i třídy) ========================================

    public String getObrazek() {
        return obrazek;
    }

}
