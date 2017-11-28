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
public class Initializer extends Application implements IFrontEnd{
    /**
     * The backEnd attribute is static to allow it to be injected into the controller,
     * despite the controller being instantiated by the start method, in a foreign class.
     */
    private static IBackEnd backEnd;
    public Initializer(){}
    
    /**
     * This method starts the GUI and injects the backEnd into th controller.
     * @param stage
     * @throws Exception 
     */
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
     * This method is responsible for calling the launch method.
     * @param args the command line arguments
     */
    public void begin(String[] args) {
        backEnd.play();
        launch(args);
    }
    /**
     * This method injects the backEnd into this object.
     * @param backEnd The reference to the backEnd, created in the driver.
     */
    public void injectBackEnd(IBackEnd backEnd){
    this.backEnd=backEnd;
    
    }


    
}
