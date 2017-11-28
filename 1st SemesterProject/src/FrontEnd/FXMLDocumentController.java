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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Krongrah
 */
public class FXMLDocumentController implements Initializable {
    private IBackEnd backEnd;
    
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
    @FXML
    private GridPane gridPane;
    private Pane testPane;
    private ListView<?> listView;
    @FXML
    private ListView<String> talkListView;
    @FXML
    private Pane talkTestPane;
    
    
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
        talkTestPane.setVisible(true);
        talkListView.setItems(FXCollections.observableList(new ArrayList(backEnd.talkMenu())));
    }

    @FXML
    private void searchGui(ActionEvent event) {
      backEnd.search();
    }

    @FXML
    private void ConvictGUI(ActionEvent event) {
      backEnd.convict();
    }

    @FXML
    private void drinkGUI(ActionEvent event) {
       backEnd.drink();
       //listv.addEventHandler(EventType.ROOT, eventHandler);//todo
       talkTestPane.setVisible(false);
    }

    @FXML
    private void ArrestGUI(ActionEvent event) {
        backEnd.arrest();
    }

    @FXML
    private void saveGUI(ActionEvent event) {
        //backEnd.save();
        testPane.setVisible(false);
    }

    @FXML
    private void inspectGUI(ActionEvent event) {
        backEnd.inspect();
    }

    @FXML
    private void talkMenuSelect(MouseEvent event) {
        backEnd.talk(talkListView.getSelectionModel().getSelectedItem());
        talkTestPane.setVisible(false);
    }
}
