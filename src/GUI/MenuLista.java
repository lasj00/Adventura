/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.IHra;
import main.Main;

/**
 *
 * @author Jan Laštůvka
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    private Stage stage;

    
    /*
    * Konstruktor pro lištu menu.
    */
    public MenuLista(IHra hra, Main main,Stage stage){
        this.hra = hra;
        this.main = main;
        this.stage = stage;
        init();
    }
     /*
    * Metoda vytvoří objeky v menu.
    */
    private void init(){
        
        Menu novySoubor = new Menu("Adventura");
        
        Menu napoveda = new Menu("Help");
        
        MenuItem novaHra = new MenuItem("Nová hra");
        //obrazek menuItemu , new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png")));
        
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new MenuItem("Konec hry");
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napovedaItem = new MenuItem("Nápověda");
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event)
            {
                main.start(stage);
            }
        });
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("O programu");
                oProgramuAlert.setHeaderText("Pokémon: Red/Blue Short Text Game");
                oProgramuAlert.setContentText("Textová hra pro splnění kurzu 4IT115 - vytvořil Jan Laštůvka (lasj00) 2017/2018");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
                
            }
        });
        
        napovedaItem.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500,500));
                stage.show();
                
            }
        });
                
       
    }
}
