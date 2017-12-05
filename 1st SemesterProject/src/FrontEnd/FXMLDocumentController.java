/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Acquaintance.IBackEnd;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private List<String> ask = Arrays.asList("Yes", "No");
    @FXML
    private Button talk;
    @FXML
    private ProgressIndicator Drunkmeter;
    @FXML
    private TextArea textOutput;
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
    private ListView<String> arrestList;
    @FXML
    private ListView<String> talkList;
    @FXML
    private ListView<String> searchList;
    @FXML
    private ListView<String> convictList;
    @FXML
    private ListView<String> inspectList;
    @FXML
    private ImageView arrowNorth;
    @FXML
    private ImageView arrowWest;
    @FXML
    private ImageView arrowSouth;
    @FXML
    private ImageView roomPicture;
    @FXML
    private ImageView arrowEast;
    @FXML
    private GridPane gridPaneList;
    @FXML
    private ListView<String> pickUpList;
    @FXML
    private ListView<String> inventoryList;
    @FXML
    private ListView<String> journalList;
    
    
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
        arrowEast.setVisible(false);
        arrowNorth.setVisible(false);
        arrowSouth.setVisible(false);
        arrowWest.setVisible(false);
        
    }
        
    void resetArrows(){
        arrowEast.setVisible(false);
        arrowNorth.setVisible(false);
        arrowSouth.setVisible(false);
        arrowWest.setVisible(false);
    }  
        
    void importBackEnd(IBackEnd backEnd){
    this.backEnd=backEnd;
    }

    @FXML
    private void talkGui(ActionEvent event) {
        Set<String> set=backEnd.talkMenu();
        if(set!=null){
        gridPaneList.setVisible(true);
        talkList.setVisible(true);
        talkList.setItems(FXCollections.observableList(new ArrayList(set)));
        }   
    }

    @FXML
    private void searchGui(ActionEvent event) {
      Set<String> set=backEnd.searchMenu();
        if(set!=null){
        searchList.setVisible(true);
        searchList.setItems(FXCollections.observableList(new ArrayList(set)));
        }  
    }

    @FXML
    private void ConvictGUI(ActionEvent event) {
      convictList.setVisible(true);
        convictList.setItems(FXCollections.observableList(new ArrayList(backEnd.convictMenu())));

    }

    @FXML
    private void drinkGUI(ActionEvent event) {
       backEnd.drink();
    }

    @FXML
    private void ArrestGUI(ActionEvent event) {
        List<String> list=new ArrayList(backEnd.arrestMenu());
        if(list!=null){

        arrestList.setVisible(true);
        arrestList.setItems(FXCollections.observableList(list));
        }
    }

    @FXML
    private void saveGUI(ActionEvent event) {
        backEnd.save();
        
    }

    @FXML
    private void inspectGUI(ActionEvent event) {
        inspectList.setVisible(true);
        inspectList.setItems(FXCollections.observableList(new ArrayList(backEnd.inspectMenu())));

    }

    @FXML
    private void talkMenuSelect(MouseEvent event) {
        backEnd.talk(talkList.getSelectionModel().getSelectedItem());
        talkList.setVisible(false);
    }

    @FXML
    private void arrestMenuSelect(MouseEvent event) {
        String selection=arrestList.getSelectionModel().getSelectedItem();
        if(selection!="No one"){    //todo is this okay?
        backEnd.arrest(selection);
        }
        arrestList.setVisible(false);

    }

    @FXML
    private void searchMenuSelect(MouseEvent event) {
        if(backEnd.search(searchList.getSelectionModel().getSelectedItem())){
        pickUpList.setVisible(true);
        pickUpList.setItems(FXCollections.observableList(ask));//todo
        }
        searchList.setVisible(false);
    }

    @FXML
    private void convictMenuSelect(MouseEvent event) {
        backEnd.convict(convictList.getSelectionModel().getSelectedItem());
        convictList.setVisible(false);
    }

    @FXML
    private void inspectMenuSelect(MouseEvent event) {
        String string=inspectList.getSelectionModel().getSelectedItem();
        ObservableList list=FXCollections.observableList(new ArrayList(backEnd.inspect(string)));
        if(!list.isEmpty()){
        if(string.equals("Inventory")){
        inventoryList.setVisible(true);
        inventoryList.setItems(list);
        }else{
        journalList.setVisible(true);
        journalList.setItems(list);
        
        }
        backEnd.inspect(string);
        inspectList.setVisible(false);
    }else{
        inspectList.setVisible(false);
        }}

    public void updateRoomImage(){
        Image Bar = new Image("file:src/Texures/Bar.png");
        Image PD = new Image("file:src/Texures/PD.png");
        Image Home = new Image("file:src/Texures/Home.png");
        Image Jail = new Image("file:src/Texures/Jail.png");
        Image PartnerHouse= new Image("file:src/Texures/PartnerHouse.png");
        Image rightstreet= new Image("file:src/Texures/rightstreet.png");
        Image Leftstreet= new Image("file:src/Texures/Leftstreet.png");
    if(backEnd.getCurrentRoom().equals("Bar")){
        roomPicture.setImage(Bar);
    }
    else if (backEnd.getCurrentRoom().equals("Left Street")){
        roomPicture.setImage(Leftstreet);
    }
    else if (backEnd.getCurrentRoom().equals("Home")){
        roomPicture.setImage(Home);
    }
    else if (backEnd.getCurrentRoom().equals("Police Department")){
        roomPicture.setImage(PD);
    }
    else if (backEnd.getCurrentRoom().equals("Partner's Home")){
        roomPicture.setImage(PartnerHouse);
    }
    else if (backEnd.getCurrentRoom().equals("Jail")){
        roomPicture.setImage(Jail);
    }
    else if (backEnd.getCurrentRoom().equals("Court")){
        roomPicture.setImage(null);
    }
    else if (backEnd.getCurrentRoom().equals("Hobo Alley")){
        roomPicture.setImage(null);
    }
    else if (backEnd.getCurrentRoom().equals("Right Street")){
        roomPicture.setImage(rightstreet);
    }
    else if (backEnd.getCurrentRoom().equals("Crime Scene")){
        roomPicture.setImage(null);
    }
    }
    
    public void startRooms(){
        resetArrows();
        for (String exit : backEnd.getExits()) {
            if(exit.contains("north")){
                arrowNorth.setVisible(true);
            }else if(exit.contains("south")){
                arrowSouth.setVisible(true);
            }else if(exit.contains("east")){
            arrowEast.setVisible(true);
            }else if(exit.contains("west")){
            arrowWest.setVisible(true);
            }
        }
    }
    
    @FXML
    private void GoArrowNorth(MouseEvent event) {
        backEnd.UIGo("north");
        updateRoomImage();
        startRooms();
       
        }
        

    @FXML
    private void GoArrowWest(MouseEvent event) {
        backEnd.UIGo("west");
        updateRoomImage();
        startRooms();
    }

    @FXML
    private void GoArrowSouth(MouseEvent event) {
        backEnd.UIGo("south");
        updateRoomImage();
        startRooms();
    }

    @FXML
    private void GoArrowEast(MouseEvent event) {
        backEnd.UIGo("east");
        updateRoomImage();
        startRooms();
    }

    @FXML
    private void pickUpSelect(MouseEvent event) {
        backEnd.pickUpAsk(pickUpList.getSelectionModel().getSelectedItem());
        pickUpList.setVisible(false);
    }

    @FXML
    private void inventorySelect(MouseEvent event) {
        backEnd.inventory(inventoryList.getSelectionModel().getSelectedItem());
        inventoryList.setVisible(false);
    }

    @FXML
    private void journalSelect(MouseEvent event) {
        backEnd.journal(journalList.getSelectionModel().getSelectedItem());
        journalList.setVisible(false);
    }
}
