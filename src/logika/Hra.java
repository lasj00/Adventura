package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Laštůvka (lasj00)
 *@version    02.01.2016
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;
    private UlozisteNaPokemony ulozisteNaPokemony;     

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        batoh = new Batoh();
        ulozisteNaPokemony = new UlozisteNaPokemony();
        herniPlan = new HerniPlan(this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, ulozisteNaPokemony));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazObsahBatohu(batoh));
        platnePrikazy.vlozPrikaz(new PrikazPromluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMojiPokemoni(ulozisteNaPokemony));
        platnePrikazy.vlozPrikaz(new PrikazChyt(herniPlan, ulozisteNaPokemony, batoh));
        platnePrikazy.vlozPrikaz(new PrikazBojuj(herniPlan, ulozisteNaPokemony, batoh));
        platnePrikazy.vlozPrikaz(new PrikazVyhod(herniPlan, batoh));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Jmenuji se profesor Oak, zkoumám Pokémony. Pokémonů je na světě nepřeberné množství.\n" +
               "Je jen na vás jak se s nimi zkamarádíte a budete se k nim chovat.\n" +
               "Tímto začíná vaše dobrodružství ve světě Pokémonů!\n" +
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jste si zahráli.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        if (herniPlan.isVyhra()){
            konecHry = true;
            textKVypsani = "Porazil si Gym Leadera Brocka!!! Získáváš Badge!!! VYHRÁL SI!!! \n" +
                "Toto bylo Pokémon Red/Blue Short Text Game...Vytvořil Jan Laštůvka (lasj00) 2015/2016" +
                "Revize - doplnění grafického rozhraní Jan Laštůvka (lasj00) 2017/2018";
        }
        return textKVypsani;
    }
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
    /**
     *  Metoda vrátí odkaz na batoh, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává obsah batohu.
     *  
     *  return odkaz na batoh
     */
     public Batoh getBatoh(){
        return batoh;
     }
     
    /**
     * Metoda vrátí odkaz na uložiště Pokémonů
     * 
     * @return  odkaz na uložiště Pokémonů
     */
     public UlozisteNaPokemony getUloziste(){
        return ulozisteNaPokemony;
    }
}

