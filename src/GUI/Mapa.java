/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author Jan Laštůvka
 */
public class Mapa extends AnchorPane implements Observer{
    
    
    private IHra hra;
    private Circle tecka;
      
    /*
    * Konstruktor pro mapu.
    */
    public Mapa(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    /*
    * Metoda vytvoří kolečko pro lokaci hráče.
    */
    private void init(){
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),250,600,false,true));
        
        tecka = new Circle(5, Paint.valueOf("black"));
   
        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }
    
    /*
    * Metoda vytvoří defaultní kolečko při nové hře.
    */
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    /*
    * Metoda pro zadávání pouřadnic kolečku.
    */
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
    
}
