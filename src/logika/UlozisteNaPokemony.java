/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Map;
import java.util.HashMap;


/*******************************************************************************
 * Třída UlozisteNaPokemony - sem se ukládají nachytaní Pokémoni
 *
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2015
 */
public class UlozisteNaPokemony
{
    //== Datové atributy (statické i instancí)======================================
    private static final int KAPACITA = 6 ;
    private Map<String, Pokemon> seznamPokemonu ;  
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor seznamu Pokémonů
     */
    public UlozisteNaPokemony()
    {
        seznamPokemonu = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     
    /**
     * Vloží Pokémona do uložiště, pokud se tam vejde
     * @return true když se Pokémon uloží, false, pokud se neuloží
     */
     public boolean ulozPokemonaDoUloziste (Pokemon pokemon) {
        if (seznamPokemonu.size() < KAPACITA ) {
            seznamPokemonu.put(pokemon.getPojmenovani(), pokemon);
            return true;
        }
        return false;
    }
    
    /**
     * Napíše info o uložených Pokemonech
     */
     public boolean obsahujePokemonaVUlozisti (String pojmenovaniPokemonu) {
        return seznamPokemonu.containsKey(pojmenovaniPokemonu);     
    }
    
    /**
     * Vypíše Pokémony, které jsou v uložišti
     */
    public String nazvyPokemoniVUlozisti() {
        String nazvy = "uložení Pokémoni: ";
        for (String pojmenovaniPokemonu : seznamPokemonu.keySet()){
            nazvy += pojmenovaniPokemonu + " ";
        }
        return nazvy;
    }
    
    /**
     * Vypíše počet uložených Pokémonů - číslo
     */
    public int getPocetPokemonu(){
        return seznamPokemonu.size();
    }
    
    
    //== Soukromé metody (instancí i třídy) ========================================

    public Map<String, Pokemon> getSeznamPokemonu() {
        return seznamPokemonu;
    }

}
