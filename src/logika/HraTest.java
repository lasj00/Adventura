package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje správný průběh hry. Testuje zda hra končí po zavolání určitých příkazů. Testuje zdali
     * fungují nastavené podmínky pro chytání Pokémonů a sbírání věcí, zdali je možné sebrat přenositelné věci a není možné
     * sebrat nepřenositelné věci. Testuje zdali hra správně 
     * přesouvá věci z prostoru do batohu. Nakonec je otestován příkaz konec.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("Domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber Stůl");
        assertEquals(false, hra1.getBatoh().obsahujeVecVBatohu("Stůl"));
        hra1.zpracujPrikaz("jdi Pallet_Town");
        assertEquals(false, hra1.konecHry());
        assertEquals("Pallet_Town", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi Laboratoř");
        assertEquals(false, hra1.konecHry());
        assertEquals("Laboratoř", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber Pokéball");
        assertEquals(false, hra1.getBatoh().obsahujeVecVBatohu("Pokéball"));
        hra1.zpracujPrikaz("seber PokéDex");
        assertEquals(true, hra1.getBatoh().obsahujeVecVBatohu("PokéDex"));
        hra1.zpracujPrikaz("chyt Charmander");
        assertEquals(false, hra1.getUloziste().obsahujePokemonaVUlozisti("Charmander"));
        hra1.zpracujPrikaz("promluv Profesor_Oak");
        hra1.zpracujPrikaz("seber Pokéball");
        assertEquals(true, hra1.getBatoh().obsahujeVecVBatohu("Pokéball"));
        hra1.zpracujPrikaz("chyt Charmander");
        assertEquals(true, hra1.getUloziste().obsahujePokemonaVUlozisti("Charmander"));
        assertEquals(false, hra1.getBatoh().obsahujeVecVBatohu("Pokéball"));
        assertEquals(true, hra1.getBatoh().obsahujeVecVBatohu("PokéDex"));
        hra1.zpracujPrikaz("vyhod PokéDex");
        assertEquals(false, hra1.getBatoh().obsahujeVecVBatohu("PokéDex"));
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
    
    /***************************************************************************
     * Testuje nejrychlejší vítězný postup, včetně ověření vítězných podmínek
     * 
     */
    @Test
    public void testVitezstvi() {
        assertEquals("Domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi Pallet_Town");  
        hra1.zpracujPrikaz("jdi Laboratoř");
        hra1.zpracujPrikaz("promluv Profesor_Oak");
        hra1.zpracujPrikaz("seber Pokéball");
        hra1.zpracujPrikaz("chyt Charmander");
        hra1.zpracujPrikaz("jdi Pallet_Town");
        hra1.zpracujPrikaz("jdi Travnatá_Route1");
        hra1.zpracujPrikaz("jdi Prázdná_Route1");
        hra1.zpracujPrikaz("seber Pokéball");
        hra1.zpracujPrikaz("jdi Zalesněná_Route1");
        hra1.zpracujPrikaz("chyt Weedle");
        hra1.zpracujPrikaz("seber Pokéball");
        hra1.zpracujPrikaz("chyt Caterpie");
        hra1.zpracujPrikaz("jdi Viridian_City");
        hra1.zpracujPrikaz("jdi Lékárna");
        hra1.zpracujPrikaz("seber Potion");
        hra1.zpracujPrikaz("jdi Viridian_City");
        hra1.zpracujPrikaz("jdi Gym");
        assertEquals(3, hra1.getUloziste().getPocetPokemonu());
        assertEquals(true, hra1.getBatoh().obsahujeVecVBatohu("Potion"));
        hra1.zpracujPrikaz("bojuj Brock");
        assertEquals(true, hra1.konecHry());
    }
}
