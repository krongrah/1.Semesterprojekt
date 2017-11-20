/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Acquaintance.IBackEnd;
import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Krongrah
 */
public class FXMLDocumentController implements Initializable {
    IBackEnd backEnd;
    
    @FXML
    private Button talk;
    @FXML
    private ProgressIndicator Drunkmeter;
    @FXML
    private TextArea textOutput;
    @FXML
    private TextField textInput;
    @FXML
    private ProgressBar badCopBar;
    @FXML
    private ProgressBar GoodCopBar;
    @FXML
    private Button search;
    @FXML
    private Button convict;
    @FXML
    private Button drink;
    @FXML
    private Button arrest;
    @FXML
    private Button save;
    @FXML
    private Button inspect;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OutputStream o = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textOutput.appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(o, true));
        
    }  
        
    void importBackEnd(IBackEnd backEnd){
    this.backEnd=backEnd;
    }

    @FXML
    private void talkGui(ActionEvent event) {
        textOutput.setText(backEnd.test());
        
    }

    @FXML
    private void searchGui(ActionEvent event) {
        
    }

    @FXML
    private void ConvictGUI(ActionEvent event) {
    }

    @FXML
    private void drinkGUI(ActionEvent event) {
    }

    @FXML
    private void ArrestGUI(ActionEvent event) {
    }

    @FXML
    private void saveGUI(ActionEvent event) {
    }

    @FXML
    private void inspectGUI(ActionEvent event) {
    }
}
