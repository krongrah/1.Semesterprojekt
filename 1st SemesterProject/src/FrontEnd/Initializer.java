/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Acquaintance.IBackEnd;
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
    static IBackEnd backEnd;
    public Initializer(){}
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = (Parent) loader.load();
        FXMLDocumentController c = loader.getController();
        c.importBackEnd(backEnd);

        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public void begin(String[] args) {
        backEnd.play();
        launch(args);
        
        
    }
    void injectBackEnd(IBackEnd backEnd){
    this.backEnd=backEnd;
    
    }

    
}
