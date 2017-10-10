/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class BatohTest
{
    private Vec vec1;
    private Vec vec2;
    private Vec vec3;
    private Vec vec4;
    private Vec vec5;
    private Vec vec6;
    private Batoh batoh1;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        vec1 = new Vec("Pokéball", true);
        vec2 = new Vec("xSpeed", true);
        vec3 = new Vec("Maliny", true);
        vec4 = new Vec("Borůvky", true);
        vec5 = new Vec("Potion", true);
        vec6 = new Vec("Mapa", true);
        batoh1 = new Batoh();
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
     * Testuje metody vlozVecDoBatohu a obsahujeVec
     */
    @Test
    public void testVlozVecDoBatohu()
    {
        assertEquals(true, batoh1.vlozVecDoBatohu(vec1));
        assertEquals(true, batoh1.obsahujeVecVBatohu("Pokéball"));
        assertEquals(true, batoh1.vlozVecDoBatohu(vec2));
        assertEquals(true, batoh1.obsahujeVecVBatohu("xSpeed"));
        assertEquals(true, batoh1.vlozVecDoBatohu(vec3));
        assertEquals(true, batoh1.obsahujeVecVBatohu("Maliny"));
    }
    
    /**
     * Testuje metodu vlozVecDoBatohu a vyberVec
     */
    @Test
    public void testVyberVec()
    {
        assertEquals(true, batoh1.vlozVecDoBatohu(vec1));
        assertEquals(vec1, batoh1.vyberVecVBatohu("Pokéball"));
        assertEquals(null, batoh1.vyberVecVBatohu("Borůvky"));
    }
    
    /**
     * Testuje kapacitu batohu
     */
    @Test
    public void testMaxKapacita()
    {
        assertEquals(true, batoh1.vlozVecDoBatohu(vec1));
        assertEquals(true, batoh1.vlozVecDoBatohu(vec2));
        assertEquals(true, batoh1.vlozVecDoBatohu(vec3));
        assertEquals(true, batoh1.vlozVecDoBatohu(vec4));
        assertEquals(true, batoh1.vlozVecDoBatohu(vec5));
        assertEquals(false, batoh1.vlozVecDoBatohu(vec6));
    }
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
}
