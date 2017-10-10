package logika;
import java.util.Map;
import java.util.HashMap;


/*******************************************************************************
 * Třída Batoh - ukládají se sem všechny posbírané věci
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Laštůvka (lasj00)
 * @version   02.01.2016
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================
    private static final int KAPACITA = 5 ;
    private Map<String, Vec> seznamVeci ;   

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor seznam věcí v batohu
     */
    public Batoh()
    {
         seznamVeci = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Vloží věc do batohu, pokud se tam vejde
     * 
     * @return true když se věc vloží, false, pokud se nevložila
     * @param objekt třídy Vec
     */
     public boolean vlozVecDoBatohu (Vec vec) {
        if (seznamVeci.size() < KAPACITA ) {
            seznamVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }
    
    /**
     * Napíše info o věcech v batohu
     */
     public boolean obsahujeVecVBatohu (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }
    
    /**
     * Vrátí věc z batohu
     */ 
    public Vec vyberVecVBatohu (String jmenoVeci) {
        Vec nalezenaVec;
        if (seznamVeci.containsKey(jmenoVeci)) {
            nalezenaVec = seznamVeci.get(jmenoVeci);
            seznamVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }
    
    /**
     * Vypíše věci, které jsou v batohu
     */
    public String nazvyVeciVBatohu() {
        String nazvy = "věci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }
  
    /**
     * Vypíše počet uložených věcí v batohu - číslo
     */
    public int getPocetVeci() {
        return seznamVeci.size();
    }
    
    /**
     * Vypíše kolik se ještě vejde věcí do batohu - číslo
     */
    public int getPocetVeciJeste() {
        return KAPACITA - seznamVeci.size();
    }
    
    //== Soukromé metody (instancí i třídy) ========================================

}
