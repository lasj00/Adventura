/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.PanelBatohu;
import GUI.PanelPokemonu;
import GUI.PanelVeci;
import GUI.PanelVychodu;
import GUI.PanelWild;
import GUI.PanelPostav;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author lasj00
 */
public class Main extends Application {
    
    private TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextArea;
    
    private Mapa mapa;
    private MenuLista menuLista;
    
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        
        setHra(new Hra());
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this,stage);
        
        BorderPane borderPane = new BorderPane();
        
        //Text s prubehem hry
        centralText = new TextArea();
        getCentralText().setText(hra.vratUvitani());
        getCentralText().setEditable(false);
        
        //Label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        //Dolni prikaz
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                getCentralText().appendText("\n" + vstupniPrikaz + "\n");
                getCentralText().appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextArea.setText("");
                
                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    getCentralText().appendText(hra.vratEpilog());
                
                    
                 }
               
                        
                
            }
        });
        
      
        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        
        
        //Border pany
        PanelBatohu panelBatohu = new PanelBatohu(hra.getHerniPlan(),centralText);
        PanelPokemonu panelPokemonu = new PanelPokemonu(hra.getHerniPlan());
        PanelVychodu panelVychodu = new PanelVychodu(hra.getHerniPlan(),centralText,zadejPrikazTextArea);
        PanelVeci panelVeci = new PanelVeci(hra.getHerniPlan(),centralText);
        PanelWild panelWild = new PanelWild(hra.getHerniPlan(),centralText);
        PanelPostav panelPostav = new PanelPostav(hra.getHerniPlan(),centralText);
        
        BorderPane cely = new BorderPane();
        
        BorderPane pravy = new BorderPane();
        pravy.setCenter(panelPostav.getList());
        pravy.setRight(panelWild.getList());
        pravy.setLeft(panelPokemonu.getList());
        
        BorderPane levy = new BorderPane();
        levy.setRight(panelVeci.getList());
        levy.setCenter(panelVychodu.getList());
        levy.setLeft(panelBatohu.getList());
        
        cely.setLeft(levy);
        cely.setRight(pravy);
        cely.setBottom(getCentralText());
        
        
        borderPane.setRight(getMapa());
        borderPane.setLeft(cely); 
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        
 
        
        
        //Celá scéna.
        Scene scene = new Scene(borderPane, 1000, 655);
        
        //Název scény
        primaryStage.setTitle("Pokémon: Red/Blue Short Text Game");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    /**
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @return the centralText
     */
    public TextArea getCentralText() {
        return centralText;
    }

    /**
     * @param hra the hra to set
     */
    public void setHra(IHra hra) {
        this.hra = hra;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

}
