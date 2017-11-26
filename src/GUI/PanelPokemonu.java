/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.HerniPlan;
import logika.Pokemon;
import utils.Observer;

/**
 *
 * @author Jan Laštůvka
 */
public class PanelPokemonu implements Observer{
    
    private HerniPlan plan;
    ListView<Object> list;
    ObservableList<Object> data;

    /*
    * Konstruktor pro panel Pokémonů.
    */
    public PanelPokemonu(HerniPlan plan) {
       this.plan = plan;
       plan.registerObserver(this);
        init();
    }

    /*
    * Metoda vytvoří list pro Pokémony v uložišti.
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(120);
        update();
    }
    
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
        return list;
    }
    
    /*
    * Metoda aktualizuje list Pokémonů v uložišti. Zobrazuje obrázky Pokémonů, které hráč chytil.
    */
    @Override 
    public void update() 
    {        
        Map<String, Pokemon> seznam;
        seznam = plan.getHra().getUloziste().getSeznamPokemonu();
        data.clear();
        for (String x : seznam.keySet()) 
        {
        Pokemon pomocny = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+pomocny.getObrazek()), 100, 100, false, false));
        data.add(obrazek);
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }



}
