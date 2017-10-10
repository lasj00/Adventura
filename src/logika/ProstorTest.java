package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku");
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }


    @Test
    public void testVeci()
    {
        logika.Prostor prostor1 = new logika.Prostor(null, null);
        logika.Vec vec1 = new logika.Vec("a", true);
        logika.Vec vec2 = new logika.Vec("b", false);
        assertEquals(true, prostor1.vlozVec(vec1));
        assertEquals(true, prostor1.vlozVec(vec2));
        assertEquals(false, prostor1.vlozVec(vec1));
        assertSame(vec1, prostor1.odeberVec("a"));
        assertNull(prostor1.odeberVec("a"));
        assertNull(prostor1.odeberVec("c"));
    }
    
    @Test
    /**
     * Testuje metodu vlozVec
     */
    public void testVlozVec()
    {
        Vec vec1 = new Vec("Koláč", true);
        Vec vec2 = new Vec("Stůl", false);
        Prostor prostor1 = new Prostor("Domov", "Jseš doma");
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertEquals(true, prostor1.obsahujeVec("Koláč"));
        assertEquals(true, prostor1.obsahujeVec("Stůl"));
        assertEquals(false, prostor1.obsahujeVec("Mapa"));
    }
    
    @Test
    /**
     * Testuje metodu odeberVec
     */
    public void testOdeberVec()
    {
        Vec vec1 = new Vec("Koláč", true);
        Prostor prostor1 = new Prostor("Domov", "Jseš doma");
        prostor1.vlozVec(vec1);
        assertEquals(true, prostor1.obsahujeVec("Koláč"));
        prostor1.odeberVec("Koláč");
        assertEquals(false, prostor1.obsahujeVec("Koláč"));
    }
    
    @Test
    /**
     * Testuje vložení postavy do Prostoru
     */
    public void testPostavy()
    {
        Postava postava1 = new Postava("Máma", "ahoj");
        Postava postava2 = new Postava("Táta","čau");
        Prostor prostor1 = new Prostor("Domov", "Jseš doma");
        prostor1.vlozPostavu(postava1);
        prostor1.vlozPostavu(postava2);
        assertEquals(true, prostor1.obsahujePostavu("Máma"));
        assertEquals(true, prostor1.obsahujePostavu("Táta"));
        assertEquals(false, prostor1.obsahujePostavu("Děda"));
    }
    
    @Test
    /**
     * Testuje vše kolem Pokémonů v prostoru
     */
    public void testPokemoni()
    {
        Pokemon pokemon1 = new Pokemon("Pikachu");
        Pokemon pokemon2 = new Pokemon("Weedles");
        Prostor prostor1 = new Prostor("Louka", "je tu hezky");
        prostor1.vlozPokemona(pokemon1);
        prostor1.vlozPokemona(pokemon2);
        assertEquals(true, prostor1.obsahujePokemona("Pikachu"));
        assertEquals(true, prostor1.obsahujePokemona("Weedles"));
        assertEquals(false, prostor1.obsahujePokemona("Zubat"));
        prostor1.odeberPokemona("Pikachu");
        assertEquals(false, prostor1.obsahujePokemona("Pikachu"));
        assertEquals(true, prostor1.obsahujePokemona("Weedles"));
    }
}

