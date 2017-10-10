/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída UlozisteNaPokemonyTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class UlozisteNaPokemonyTest
{
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon pokemon3;
    private Pokemon pokemon4;
    private Pokemon pokemon5;
    private Pokemon pokemon6;
    private Pokemon pokemon7;
    private UlozisteNaPokemony uloziste;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        pokemon1 = new Pokemon("Charmander");
        pokemon2 = new Pokemon("Weedles");
        pokemon3 = new Pokemon("Catherpie");
        pokemon4 = new Pokemon("Pikachu");
        pokemon5 = new Pokemon("Zubat");
        pokemon6 = new Pokemon("Machop");
        pokemon7 = new Pokemon("Abra");
        uloziste = new UlozisteNaPokemony();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================
    /**
     * Testuje metody ulozPokemonaDoUloziste a obsahujePokemonaVUlozisti
     */
    @Test
    public void testUlozPokemona()
    {
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon1));
        assertEquals(true, uloziste.obsahujePokemonaVUlozisti("Charmander"));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon2));
        assertEquals(true, uloziste.obsahujePokemonaVUlozisti("Weedles"));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon3));
        assertEquals(true, uloziste.obsahujePokemonaVUlozisti("Catherpie"));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon4));
        assertEquals(true, uloziste.obsahujePokemonaVUlozisti("Pikachu"));
    }
    
    /**
     * Testuje metodu getPocetPokemonu
     */
    @Test
    public void testPocetPokemonu()
    {
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon1));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon2));
        assertEquals(2, uloziste.getPocetPokemonu());
    }
    
    /**
     * Testuje maximální kapacitu uložiště
     */
    @Test
    public void testMaxKapacita()
    {
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon1));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon2));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon3));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon4));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon5));
        assertEquals(true, uloziste.ulozPokemonaDoUloziste(pokemon6));
        assertEquals(false, uloziste.ulozPokemonaDoUloziste(pokemon7));
    }
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
}
