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
import javafx.application.Platform;
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
import javafx.scene.layout.StackPane;

/**
 *
 * @author Krongrah
 */
public class FXMLDocumentController implements Initializable {
    private IBackEnd backEnd;
    private List<String> ask = Arrays.asList("Yes", "No");//todo is it better to add them on separate lines?
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
    
    private Image Bar = new Image("file:src/Texures/Bar.png");
    private Image PD = new Image("file:src/Texures/PD.png");
    private Image Court = new Image("file:src/Texures/Court.png");
    private Image CrimeScene = new Image("file:src/Texures/Crimescene.png");
    private Image hoboAlley = new Image("file:src/Texures/Hoboalley.png");
    private Image Home = new Image("file:src/Texures/Home.png");
    private Image Jail = new Image("file:src/Texures/Jail.png");
    private Image PartnerHouse= new Image("file:src/Texures/PartnerHouse.png");
    private Image rightstreet= new Image("file:src/Texures/rightstreet.png");
    private Image Leftstreet= new Image("file:src/Texures/Leftstreet.png");
    @FXML
    private ListView<String> badgeList;
    @FXML
    private BorderPane LoseScreen;
    @FXML
    private TextArea loseText;
    @FXML
    private TextField loseScore;
    @FXML
    private BorderPane winScreen;
    @FXML
    private TextField winScore;
    @FXML
    private TextArea winText;
    @FXML
    private ListView<?> winGoodHiScore;
    @FXML
    private ListView<?> winBadHighScore;
    @FXML
    private StackPane stackPane;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OutputStream o = new OutputStream() {
    @Override
    public void write(int b) throws IOException {
                textOutput.appendText(String.valueOf((char) b));
                textOutput.setWrapText(true);
        }
        };
        System.setOut(new PrintStream(o, true));
        stackPane.setVisible(false);
    }
        
    void closeMenus(){
     stackPane.setVisible(true);
     arrestList.setVisible(false);
     talkList.setVisible(false);
     searchList.setVisible(false);
     convictList.setVisible(false);
     inspectList.setVisible(false);
     pickUpList.setVisible(false);
     inventoryList.setVisible(false);
     journalList.setVisible(false);
    
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
        closeMenus();
        Set<String> set=backEnd.talkMenu();
        if(set!=null){
        talkList.setVisible(true);
        talkList.setItems(FXCollections.observableList(new ArrayList(set)));
        }   
    }

    @FXML
    private void searchGui(ActionEvent event) {
      closeMenus();
        Set<String> set=backEnd.searchMenu();
        if(set!=null){
        searchList.setVisible(true);
        searchList.setItems(FXCollections.observableList(new ArrayList(set)));
        }  
    }

    @FXML
    private void ConvictGUI(ActionEvent event) {
      closeMenus();
        Set<String> set=backEnd.convictMenu();
        if(set!=null){
        convictList.setVisible(true);
        convictList.setItems(FXCollections.observableList(new ArrayList(set)));
        } 
    }

    @FXML
    private void drinkGUI(ActionEvent event) {
       closeMenus();
        backEnd.drink();
        updateHUD();
    }

    @FXML
    private void ArrestGUI(ActionEvent event) {
        closeMenus();
        List<String> list=new ArrayList(backEnd.arrestMenu());
        if(list!=null){

        arrestList.setVisible(true);
        arrestList.setItems(FXCollections.observableList(list));
        }
    }

    @FXML
    private void saveGUI(ActionEvent event) {
        closeMenus();
        backEnd.save();
        updateHUD();
        
    }

    @FXML
    private void inspectGUI(ActionEvent event) {
        closeMenus();
        inspectList.setVisible(true);
        inspectList.setItems(FXCollections.observableList(new ArrayList(backEnd.inspectMenu())));

    }

    @FXML
    private void talkMenuSelect(MouseEvent event) {
        backEnd.talk(talkList.getSelectionModel().getSelectedItem());
        talkList.setVisible(false);
        updateHUD();
    }

    @FXML
    private void arrestMenuSelect(MouseEvent event) {
        String selection=arrestList.getSelectionModel().getSelectedItem();
        if(selection!="No one"){    //todo is this okay?
        if(backEnd.arrest(selection)){
        convict.setVisible(true);
        startRooms();
        }else{
        showLoseScreen();
        }
        }
        arrestList.setVisible(false);
        updateHUD();
    }

    @FXML
    private void searchMenuSelect(MouseEvent event) {
        if(backEnd.search(searchList.getSelectionModel().getSelectedItem())){
        pickUpList.setVisible(true);
        pickUpList.setItems(FXCollections.observableList(ask));//todo
        }
        searchList.setVisible(false);
        updateHUD();
    }

    @FXML
    private void convictMenuSelect(MouseEvent event) {
        int convictResult=backEnd.convict(convictList.getSelectionModel().getSelectedItem());
        if(convictResult==1){
          showWinScreen();  
        }else if(convictResult==2){
        badgeList.setVisible(true);
        badgeList.setItems(FXCollections.observableList(ask));
        }
        convictList.setVisible(false);
        updateHUD();
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
        switch (backEnd.getCurrentRoom()) {
            case "Bar":
                roomPicture.setImage(Bar);
                break;
            case "Left Street":
                roomPicture.setImage(Leftstreet);
                break;
            case "Home":
                roomPicture.setImage(Home);
                break;
            case "Police Department":
                roomPicture.setImage(PD);
                break;
            case "Partner's Home":
                roomPicture.setImage(PartnerHouse);
                break;
            case "Jail":
                roomPicture.setImage(Jail);
                break;
            case "Court":
                roomPicture.setImage(Court);
                break;
            case "Hobo Alley":
                roomPicture.setImage(hoboAlley);
                break;
            case "Right Street":
                roomPicture.setImage(rightstreet);
                break;
            case "Crime Scene":
                roomPicture.setImage(CrimeScene);
                break;
            default:
                break;
        }
       
    }
    
    public void startRooms(){
        updateRoomImage();
        resetArrows();
        for (Iterator it = backEnd.getMapExits().iterator(); it.hasNext();) {
            String exit = (String) it.next();
            switch (exit){
                case "north":
                    arrowNorth.setVisible(true);
                    break;
                case "east":
                    arrowEast.setVisible(true);
                    break;
                case "south":
                    arrowSouth.setVisible(true);
                    break;
                case "west":
                    arrowWest.setVisible(true);
                    break;
            }
        }
    }
    
    @FXML
    private void GoArrowNorth(MouseEvent event) {
        backEnd.UIGo("north");
        startRooms();
        updateHUD();
        }
        
    @FXML
    private void GoArrowWest(MouseEvent event) {
        backEnd.UIGo("west");
        startRooms();
        updateHUD();
    }

    @FXML
    private void GoArrowSouth(MouseEvent event) {
        backEnd.UIGo("south");
        startRooms();
        updateHUD();
    }

    @FXML
    private void GoArrowEast(MouseEvent event) {
        backEnd.UIGo("east");
        startRooms();
        updateHUD();
    }

    @FXML
    private void pickUpSelect(MouseEvent event) {
        backEnd.pickUpAsk(pickUpList.getSelectionModel().getSelectedItem());
        pickUpList.setVisible(false);
        updateHUD();
    }

    @FXML
    private void inventorySelect(MouseEvent event) {
        backEnd.inventory(inventoryList.getSelectionModel().getSelectedItem());
        inventoryList.setVisible(false);
        updateHUD();
    }

    @FXML
    private void journalSelect(MouseEvent event) {
        backEnd.journal(journalList.getSelectionModel().getSelectedItem());
        journalList.setVisible(false);
        updateHUD();
    }

    @FXML
    private void badgeSelect(MouseEvent event) {
        backEnd.badgeResponse(badgeList.getSelectionModel().getSelectedItem());
        badgeList.setVisible(false);
        updateHUD();
    }
    private void showWinScreen(){
    
    winScreen.setVisible(true);
    winScore.setText(backEnd.endScore());
    //load hiscores
    //todo
    }
    private void showLoseScreen(){
    LoseScreen.setVisible(true);
    loseScore.setText(backEnd.endScore());
    }
    protected void updateHUD(){
        stackPane.setVisible(false);
        Drunkmeter.setProgress(backEnd.getHUD().get(0)/100.0);
        if(backEnd.getHUD().get(1)==null){
        badCopBar.setProgress(backEnd.getHUD().get(2)/100.0);
        GoodCopBar.setProgress(0);
        }else{
        GoodCopBar.setProgress(backEnd.getHUD().get(1)/100.0);
        badCopBar.setProgress(0);
        
        }
    }

    @FXML
    private void Quit(ActionEvent event) {
        Platform.exit();
    }
}
