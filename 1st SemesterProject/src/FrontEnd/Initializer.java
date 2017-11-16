/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Acquaintance.IBackEnd;
import Acquaintance.IFrontEnd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

/**
 *
 * @author Krongrah
 */
public class Initializer extends Application{
    IBackEnd backEnd;
    public Initializer(){}
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        //FXMLDocumentController c = loader.getController();
        //c.test();
        //todo
        
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public void begin(String[] args) {
        
        launch(args);
        backEnd.play();
    }
    void injectBackEnd(IBackEnd backEnd){
    this.backEnd=backEnd;
    
    }

    
}
