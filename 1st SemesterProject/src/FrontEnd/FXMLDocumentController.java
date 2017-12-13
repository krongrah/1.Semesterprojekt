/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Acquaintance.IBackEnd;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private ListView<String> winGoodHiScore;
    @FXML
    private ListView<String> winBadHighScore;
    @FXML
    private StackPane stackPane;
    @FXML
    private BorderPane introScreen;
    @FXML
    private GridPane ControlPanel;
    @FXML
    private GridPane fightScreen;
    @FXML
    private ProgressBar playerHealth;
    @FXML
    private ProgressBar enemyHealth;
    @FXML
    private TextArea fightOutput;
    @FXML
    private ListView<String> dropList;
    @FXML
    private BorderPane mapPane;
    @FXML
    private BorderPane HiScoreScreen;
    @FXML
    private ListView<String> GoodScore;
    @FXML
    private ListView<String> badScore;
    @FXML
    private Button newGame;
    @FXML
    private TextField nameField;
    @FXML
    private Button loadGame;
    @FXML
    private Button hiScore;
    @FXML
    private Button quit;
    @FXML
    private Button doneButton;
    @FXML
    private Label labelStartHealt;
    @FXML
    private Label startDamage;
    @FXML
    private Label labelName;
    @FXML
    private ImageView fightImage;
    
    private Image combatImageDirty = new Image("file:src/Texures/combatImageDirty.png");    
    private Image combatImageHeroin = new Image("file:src/Texures/combatImageHeroin.png");    
    private Image combatImageInsane = new Image("file:src/Texures/combatImageInsane.png");    
    private Image combatImageNoTeeth = new Image("file:src/Texures/combatImageNoTeeth.png");    
    @FXML
    private Label watch;
    @FXML
    private Label drunkMeter;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OutputStream o = new OutputStream() {
    @Override
    public void write(int b) throws IOException {
                textOutput.appendText(String.valueOf((char) b));
                textOutput.setWrapText(true);
                fightOutput.appendText(String.valueOf((char) b));
                fightOutput.setWrapText(true);
        }
        };
        System.setOut(new PrintStream(o, true));
        stackPane.setVisible(false);
        introScreen.setVisible(true);
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
        if(!list.isEmpty()){

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
        if (input(talkList)!=null){
        backEnd.talk(input(talkList));
        talkList.setVisible(false);
        updateHUD();
    }}

    @FXML
    private void arrestMenuSelect(MouseEvent event) {
        if (input(arrestList)!=null){
        if(input(arrestList)!="No one"){    //todo is this okay?
        if(backEnd.arrest(input(arrestList))){
        convict.setVisible(true);
        startRooms();
        }else{
        showLoseScreen();
        }
        }
        arrestList.setVisible(false);
        updateHUD();
    }}

    @FXML
    private void searchMenuSelect(MouseEvent event) {
        if (input(searchList)!=null){
            if(backEnd.search(input(searchList))){
        pickUpList.setVisible(true);
        pickUpList.setItems(FXCollections.observableList(ask));
        
            }else{
                updateHUD();
            }
        searchList.setVisible(false);
        
    }}

    @FXML
    private void convictMenuSelect(MouseEvent event) {
        if (input(convictList)!=null){
        int convictResult=backEnd.convict(input(convictList));
        
        if(convictResult==1){
          showWinScreen();  
        }else if(convictResult==2){
        badgeList.setVisible(true);
        badgeList.setItems(FXCollections.observableList(ask));
        }else{
        convictList.setVisible(false);
        updateHUD();
    }}}

    @FXML
    private void inspectMenuSelect(MouseEvent event) {
        if (input(inspectList)!=null){
        ObservableList list=FXCollections.observableList(new ArrayList(backEnd.inspect(input(inspectList))));
        if(!list.isEmpty()){
        if(input(inspectList).equals("Inventory")){
        inventoryList.setVisible(true);
        inventoryList.setItems(list);
        }else{
        journalList.setVisible(true);
        journalList.setItems(list);
        
        }
        backEnd.inspect(input(inspectList));
        inspectList.setVisible(false);
    }else{
        inspectList.setVisible(false);
        }}}

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
        if(backEnd.UIGo("north")){
        combat();
        }
        startRooms();
        updateHUD();
        }
        
    @FXML
    private void GoArrowWest(MouseEvent event) {
        if(backEnd.UIGo("west")){
        combat();
        }
        startRooms();
        updateHUD();
    }

    @FXML
    private void GoArrowSouth(MouseEvent event) {
        if(backEnd.UIGo("south")){
        combat();
        }
        startRooms();
        updateHUD();
    }

    @FXML
    private void GoArrowEast(MouseEvent event) {
        
        if(backEnd.UIGo("east")){
        combat();
        }
        startRooms();
        updateHUD();
    }

    @FXML
    private void pickUpSelect(MouseEvent event) {
        if (input(pickUpList)!=null){
        backEnd.pickUpAsk(input(pickUpList));
        pickUpList.setVisible(false);
        updateHUD();
    }}

    @FXML
    private void inventorySelect(MouseEvent event) {
        if (input(inventoryList)!=null){
        backEnd.inventory(input(inventoryList));
        inventoryList.setVisible(false);
        updateHUD();
    }}

    @FXML
    private void journalSelect(MouseEvent event) {
        if (input(journalList)!=null){
        backEnd.journal(input(journalList));
        journalList.setVisible(false);
        updateHUD();
    }}

    @FXML
private void badgeSelect(MouseEvent event) {
        if (input(badgeList)!=null){
        backEnd.badgeResponse(input(badgeList));
        if(input(badgeList).equals("No")){
        showWinScreen();
        }else{
        badgeList.setVisible(false);
        
    }
updateHUD();
}}

    private void showWinScreen(){
    
    winScreen.setVisible(true);
    winScore.setText(backEnd.endScore());
    mapPane.setVisible(false);
    winGoodHiScore.setItems(FXCollections.observableList(backEnd.getScores().get(0)));
    winBadHighScore.setItems(FXCollections.observableList(backEnd.getScores().get(1)));
    winText.setText(backEnd.endMessage());
        winText.setWrapText(true);

    }
    private void showLoseScreen(){
    LoseScreen.setVisible(true);
    loseScore.setText(backEnd.endScore());
    loseText.setText(backEnd.endMessage());
        loseText.setWrapText(true);

    }
    protected void updateHUD(){
        stackPane.setVisible(false);
        Drunkmeter.setProgress(backEnd.getHUD().get(0)/100.0);
        watch.setText(backEnd.getTime());
        drunkMeter.setText(backEnd.getDrunkenness());
        if(backEnd.getHUD().get(1)==null){
        badCopBar.setProgress(backEnd.getHUD().get(2)/100.0);
        GoodCopBar.setProgress(0);
        }else{
        GoodCopBar.setProgress(backEnd.getHUD().get(1)/100.0);
        badCopBar.setProgress(0);
        
        }
        if(Drunkmeter.getProgress()<=0||Drunkmeter.getProgress()>=1.0){
        showLoseScreen();
        }
    }

    @FXML
    private void Quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void newGame(ActionEvent event) {
        nameField.setVisible(true);
        newGame.setVisible(false);
        loadGame.setVisible(false);
        hiScore.setVisible(false);
        quit.setVisible(false);
        doneButton.setVisible(true);
    }

    @FXML
    private void loadGame(ActionEvent event) {
        backEnd.load();
        introScreen.setVisible(false);
    }

    @FXML
    private void hiScore(ActionEvent event) {
        GoodScore.setItems(FXCollections.observableList(backEnd.getScores().get(0)));
        badScore.setItems(FXCollections.observableList(backEnd.getScores().get(1)));
        HiScoreScreen.setVisible(true);
        
    }

    @FXML
    private void fight(ActionEvent event) {
        int f=backEnd.fight();
        if(f==2){
            fightScreen.setVisible(false);
    }else if(f==0){
        showLoseScreen();
        fightScreen.setVisible(false);
    }else{
        updateHealth();
    }}

    @FXML
    private void run(ActionEvent event) {
    int f=backEnd.run();
        if(f==2){
            fightScreen.setVisible(false);
            startRooms();

    }else if(f==0){
        showLoseScreen();
        fightScreen.setVisible(false);
    }else{
        updateHealth();
    }}

    @FXML
    private void calm(ActionEvent event) {
     int f=backEnd.calm();
        if(f==2){
            fightScreen.setVisible(false);
    }else if(f==0){
        showLoseScreen();
        fightScreen.setVisible(false);
    }
        updateHealth();
    }
    private void updateHealth(){
        playerHealth.setProgress(backEnd.getPlayerHealth());
        enemyHealth.setProgress(backEnd.getEnemyHealth());
        
    }
    
    private void combat(){
        labelName.setText(backEnd.getEnemyData()[0]);
        startDamage.setText(backEnd.getEnemyData()[1]);
        labelStartHealt.setText(backEnd.getEnemyData()[2]);
            switch(backEnd.getEnemyData()[0]){
                case "Dirty Darryl":
                    fightImage.setImage(combatImageDirty);
                    break;
                case "Heroin Harry":
                    fightImage.setImage(combatImageHeroin);
                    break;
                case "No-Teeth Terry":
                    fightImage.setImage(combatImageNoTeeth);
                    break;
                case "Insane Dwayne":
                    fightImage.setImage(combatImageInsane);
                    break;
    }
        
    fightScreen.setVisible(true);
    updateHealth();
    }
    
    private String input(ListView<String> input){
        String string=input.getSelectionModel().getSelectedItem();
    return string;
    
    }

    @FXML
    private void dropGUI(ActionEvent event) {
       closeMenus();
        Set<String> set=backEnd.dropMenu();
        if(set!=null){
        dropList.setVisible(true);
        dropList.setItems(FXCollections.observableList(new ArrayList(set)));
        }  
    }

    @FXML
    private void dropMenuSelect(MouseEvent event) {
        if (input(dropList)!=null){
        backEnd.drop(input(dropList));
        dropList.setVisible(false);
        updateHUD();
    }} 

    @FXML
    private void Back(ActionEvent event) {
        HiScoreScreen.setVisible(false);
    }


    @FXML
    private void doneButton(ActionEvent event) {
            
        backEnd.setName(nameField.getText());
        introScreen.setVisible(false);
        backEnd.printWelcome();
    }

    @FXML
    private void helpGUI(ActionEvent event) {
        backEnd.help();
    }

    @FXML
    private void enter(KeyEvent event) {
    }
}
