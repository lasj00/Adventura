/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třida PrikazTvojiPokemoni implementuje pro hru příkaz moji_pokemoni
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Laštůvka
 * @version   02.01.2016
 */
public class PrikazMojiPokemoni implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "moji_pokemoni";
    private UlozisteNaPokemony ulozisteNaPokemony;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy
     *  
     *  @param ulozisteNaPokemony uložiště na Pokémony, z kterého budou vypisovány chycení Pokémoni
     */
    public PrikazMojiPokemoni(UlozisteNaPokemony ulozisteNaPokemony)
    {
        this.ulozisteNaPokemony = ulozisteNaPokemony;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "moji_pokemoni". Vypíše obsah uložiště - jednotlivá jména pochytaných Pokémonů
     *
     *@return jména pochytaných Pokémonů
     */ 
    @Override
    public String proved(String... parametry) {
        return ulozisteNaPokemony.nazvyPokemoniVUlozisti();
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
