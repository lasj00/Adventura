/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.Vec;
import utils.Observer;


public class PanelBatohu implements Observer{
    
    private HerniPlan plan;
    ListView<Object> list;
    ObservableList<Object> data;
    private TextArea centralText;

    /*
    * Konstruktor pro panel batohu.
    */
    public PanelBatohu(HerniPlan plan, TextArea text) {
       this.plan = plan;
       plan.registerObserver(this);
       
       centralText = text;
        init();
    }

    /*
    * Metoda vytvoří list pro věci v batohu.
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(100);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Vec> seznam;
                    seznam = plan.getHra().getBatoh().getSeznamVeci();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = x;
                       }
                       pomocna++;
                    }
                    
                    String vstupniPrikaz = "vyhod "+nazev;
                    String odpovedHry = plan.getHra().zpracujPrikaz("vyhod "+nazev);

                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
               
                    plan.notifyObservers();
                }
            }
        });
        
        update();
    }
    
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
        return list;
    }
    
    /*
    * Metoda aktualizuje list věcí v batohu. Zobrazuje obrázky věcí, které má hráč u sebe.
    */
    @Override 
    public void update() 
    {        
        Map<String, Vec> seznam;
        seznam = plan.getHra().getBatoh().getSeznamVeci();
        data.clear();
        for (String x : seznam.keySet()) 
        {
        Vec pomocna = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+pomocna.getObrazek()), 80, 80, false, false));
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
