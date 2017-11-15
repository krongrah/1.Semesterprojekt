/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Krongrah
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button inventory;
    @FXML
    private Button drink;
    @FXML
    private Button search;
    @FXML
    private Button inspect;
    @FXML
    private Button talk;
    @FXML
    private Button save;
    @FXML
    private Button help;
    @FXML
    private ProgressIndicator drunkness;
    @FXML
    private TextArea outputTextBox;
    @FXML
    private TextField inputTextBox;
    
    public void onButtonClick(ActionEvent event){
        
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
