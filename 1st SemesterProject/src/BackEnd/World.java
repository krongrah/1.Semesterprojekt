package BackEnd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import BackEnd.WorldFill.Room;
import BackEnd.WorldFill.Beverage;
import BackEnd.WorldFill.Clue;
import BackEnd.WorldFill.Dialogue;
import BackEnd.WorldFill.HostileNPC;
import BackEnd.WorldFill.Item;
import BackEnd.WorldFill.NPC;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import BackEnd.WorldFill.Hobo;
import java.util.ArrayList;


/**
 *
 * @author Krongrah
 */
public class World implements Serializable {
    
    private Map<String,NPC> npcs=new HashMap<>();
    private Map<String, HostileNPC> hostileNpcs = new HashMap<>();
    private Map<String,Item> items=new HashMap<>();
    private Map<String,Clue> clues=new HashMap<>();
    private Map<String,Room> rooms=new HashMap<>();
    private ArrayList<Hobo> hobos = new ArrayList<>();

    public World(){
    createWorld();
    }
    
    private void createWorld() {

        Clue bartenderStatement = new Clue("Bartender's Statement", "According to Bartender Bert everyone hated the victim.", false);
        Clue hobo1Statement = new Clue("No-Teeth Terry's Statement", "According to No-Teeth Terry the murderer was a drunk man.", false);
        Clue hobo2Statement = new Clue("Dirty Darryl's Statement", "Dirty Darryl obviously hates cops.", true);
        Clue hobo3Statement = new Clue("Heroin Harry's Statement", "According to Heroin Harry Dirty Darryl is the killer.", true);
        Clue hobo4Statement = new Clue("Insane Dwayne's Statement", "Insane Dwayne is insane.", true);
        Clue coronerStatement = new Clue("Coroner's Statement", "According to the coroner the murder was a crime of passion, \nand the victim knew his killer.", true);
        Clue murderWeaponClue = new Clue("Murder Weapon Evidence", "The murder weapon is a broken bottle of your favorite beer.", true);
        Clue bloodsplatterClue = new Clue("Blood Splatter", "There was a lot of blood on the crimescene, suggesting a violent conflict.", false);
        Clue corpseClue = new Clue("Corpse", "The body of the victim was stabbed repeatedly, and had spit covering it's face.", false);
        Clue bloodSplatteredBadgeClue = new Clue("Badge", "This is the badge of the victim was found in your home, \nwhich points to you being the killer.", true);

        
        
        // creation of the multiple items via the constructor in Item.java
        Item murderWeapon = new Item("Murder Weapon", "This is a broken bottle"
                + " with sharp edges and blood covering the edges", true, true, murderWeaponClue);
        Item bloodSplatter = new Item("Blood Splatter", "the ground is covered"
                + " in blood", true, false, bloodsplatterClue);
        Item gun = new Item("Gun", "Its a smith and wesson, your best friend",
                false, true, corpseClue);
        Item corpse = new Item("Corpse", "it's a dead guy, he looks to be stabbed"
                + " brutally multiple times.\n When you look closer you notice"
                + " his face is covered in spit", true, false, corpseClue);
        Item badge = new Item("Badge", "It's your"
            + " former partners badge covered in blood, odd that you would find"
            + " this here. \n A wave of guilt washes over you as you realise"
            + " what you have done", true, true, bloodSplatteredBadgeClue);
        Item partnerKey = new Item("Key To Partner's Home", "This key belongs to your deceased partner, "
            + "and it allows you to enter his home.", false, true, null);
        Item corpseOutline = new Item("Corpse Outline", "its was a dead guy, he looked like he was stabbed"
                + " brutally multiple times.\n When you looked closer you noticed"
                + " his face is covered in spit", true, false, corpseClue);

        
        Beverage whiskey=new Beverage("Whiskey","This bottle is your favorite drink, and the reason you love coming home.",false, true, null,2,5);
        Beverage gin = new Beverage ("Half-empty Gin", "its a half-empty bottle of gin, some idiot wasted his drink", false, true, null,1,7);
        Beverage beerKeg = new Beverage ("Keg Of beer", "Its a keg of beer, nobody will notice if you take this", false, true, null, 4,4);
        Beverage coffee = new Beverage ("Coffee", "A normal looking drink of coffee, expect you can smell a faint vodka odor", false, true, null, 2,6);
        Beverage wine = new Beverage ("Wine", "the fancy people left out a glass of wine", false, true, null, 3,7);
        Beverage beer = new Beverage("Beer", "It's a well known brand called pisswasser", false, true, null, 2, 2);
    
        
        Room leftStreet = new Room(" on left street", "Left Street",true);
        Room rightStreet = new Room(" on Right street", "Right Street", true);
        Room bar = new Room(" in the bar", "Bar", false);
        Room hoboAlley = new Room(" in the Hobo Alley, try not to get stabbed", "Hobo Alley", true);
        Room crimeScene = new Room(" at the crime scene", "Crime Scene", true);
        Room partnerHome = new Room(" in your deceased partners house", "Partner's Home", false);
        Room home = new Room(" in your home", "Home", false);
        Room pd = new Room(" in the Police Department", "Police Department", false);
        Room jail = new Room(" visiting the jail, in the pd", "Jail", false);
        Room court = new Room(" in court", "Court", false);

      

        //create dialogue
        String[] coronerLine =  {
            "Welcome to the murder scene, make yourself at home.",
            "The victim is your partner, Detective Prickard. He was a dick, and the world is a better place without him.",
            "The victim was stabbed several times, and died from blood loss. It appears to be a crime of passion, due to the many stab wounds the spit on the victim's face.",
            "The victim was surprised by the attack, so I believe he knew his killer.",
            "I’ll get the cleaning team here know, so we can get this shit of the street."
        };
        String[] wifeLine = {
            "What are you doing here? You love your job more than me, so go do your job and leave me alone.",
            "You are always out drinking, you sad piece of shit.",
            "Where were you even last night? You came home covered in shit all worked up?",
            "Go away, I want a good day."
        };
        String[] bartenderLine = {
            "Isn't it a bit early for you to be here?",
            "Detective Prickard is dead? I can't say I'm surprised, he didn't seem to get along with anyone, especially not you. (add to cluelist)",
            "I think you should get back at work now."
        };
        String[] hobo1Line = {
            "Coppers don't often come up to 'dese parts",
            "I 'eard a drunk fella' shoutin' at 'em.",
            "Bugger off."
        };
        String[] hobo2Line = {
            "We don' take kindly to your kind 'roun' 'ere!",
            "What de' ye' stinkin' cops wan' over 'ere?",
            "Ay ain't tellin' ye' nutin', stupid cop."
        };
        String[] hobo3Line = {
            "It gats' ta' be Darryl! He be lookin' funny at me!",
            "Gat any smack?"
        };
        String[] hobo4Line = {
            "I bet your ass killed him, cops can't help killing.",
            "I think you guilty, the voice in my head told me so.",};
        String[] commissionerLine1 = {
            "Detective, there has been a murder in the Hobo Alley, get over there ASAP. no time to wait for your partner, detective Prickard.",
            "Get your fat ass out of here!"
        };
        String[] commissionerLine2 = {
            "Did you find his badge? We need it for the memorial."

        };
        String coronerAlibi = "The coroners fingerprints and footprints are everywhere  on the crimescene"
                + " thats probaly because he is a coroner, his alibi checks out.";
        String wifeAlibi = "Of all the crimes this human waste has done, murder is sadly not one of them.";
        String commissionerAlibi = "Are you just accusing random people at this point? it is heresy to accuse Curt";
        String bartenderAlibi = "This man has no motive, obviously you are mistaken...";
        String hobo1Alibi = "With his broken english and his distrust for cops, this... thing is easily a suspect";
        String hobo2Alibi = "He called you stupid, which should be a crime punishable by death, you are certain this is the man";
        String hobo3Alibi = "Randomly throwing around accusations is often a sign of guilt, except when you do it, of course...";
        String hobo4Alibi = "This guy is insane... theres i no other way around it.";

        //Fight Screams System.out.println((char)27 + "[31mThis text would show up red" + (char)27 + "[0m");
        String fightScreamHobo1= "You arrested my friend, I can't bite you but I can punch you!";
        String fightScreamHobo2= "You arrested my friend, imma mess ya suit, n' ya face up now!";
        String fightScreamHobo3= "You arrested my friend, I am gonna, kill you and sell your badge for heroin!";
        String fightScreamHobo4= "Monkey goes UH-UH-AH-AH! I like CAAAKEE!!";
        String fightScreamWife = "This is for coming home stinking like Booze, again!";
        
        //add dialogue to dialogue object
        Dialogue coronerDialogue = new Dialogue(coronerLine, coronerAlibi, true);
        Dialogue wifeDialogue = new Dialogue(wifeLine, wifeAlibi, true, fightScreamWife);
        Dialogue bartenderDialogue = new Dialogue(bartenderLine, bartenderAlibi, true);
        Dialogue hobo1Dialogue = new Dialogue(hobo1Line, hobo1Alibi, false, fightScreamHobo1);
        Dialogue hobo2Dialogue = new Dialogue(hobo2Line, hobo2Alibi, false, fightScreamHobo2);
        Dialogue hobo3Dialogue = new Dialogue(hobo3Line, hobo3Alibi, false, fightScreamHobo3);
        Dialogue hobo4Dialogue = new Dialogue(hobo4Line, hobo4Alibi, false, fightScreamHobo4);
        Dialogue commissionerDialogue = new Dialogue(commissionerLine1, commissionerLine2, commissionerAlibi, true);

        //create clues
     
        Hobo hobo1 =  new Hobo("No-Teeth Terry", hobo1Dialogue, hobo1Statement, 2);
        Hobo hobo2 =  new Hobo("Dirty Darryl", hobo2Dialogue, hobo2Statement, 2);
        Hobo hobo3 =  new Hobo("Heroin Harry", hobo3Dialogue, hobo3Statement, 1);
        Hobo hobo4 =  new Hobo("Insane Dwayne", hobo4Dialogue, hobo4Statement, 1);
        NPC commissioner = new NPC("Commissioner Curt", commissionerDialogue, null, 0);
        NPC bartender = new NPC("Bartender Bert", bartenderDialogue, bartenderStatement, 2);
        HostileNPC wife = new HostileNPC("Wife", wifeDialogue, null, 0, 50, 5, 0.5);
        NPC coroner = new NPC("Coroner", coronerDialogue, coronerStatement, 4);
        
        
        bar.addNpcToRoom(bartender);
        home.addNpcToRoom(wife);
        pd.addNpcToRoom(commissioner);
        crimeScene.addNpcToRoom(coroner);
        crimeScene.addNpcToRoom(hobo1);
        crimeScene.addNpcToRoom(hobo2);
        crimeScene.addNpcToRoom(hobo3);
        crimeScene.addNpcToRoom(hobo4);

        hoboAlley.addItemToRoom(murderWeapon);
        crimeScene.addItemToRoom(bloodSplatter);
        crimeScene.addItemToRoom(corpse);
        pd.addItemToRoom(gun);
        bar.addItemToRoom(beer);
        home.addItemToRoom(whiskey);
        pd.addItemToRoom(partnerKey);
        hoboAlley.addItemToRoom(gin);
        pd.addItemToRoom(coffee);
        rightStreet.addItemToRoom(beerKeg);
        leftStreet.addItemToRoom(wine);
        
        

        //leftStreet exits
        leftStreet.setExit("east", rightStreet);
        leftStreet.setExit("south", bar);
        leftStreet.setExit("north", hoboAlley);
        leftStreet.setExit("west", partnerHome);

        //rightsteet exits
        rightStreet.setExit("east", home);
        rightStreet.setExit("south", court);
        rightStreet.setExit("north", pd);
        rightStreet.setExit("west", leftStreet);

        //leftstreet room exits
        hoboAlley.setExit("north", crimeScene);
        hoboAlley.setExit("south", leftStreet);
        crimeScene.setExit("south", hoboAlley);
        bar.setExit("north", leftStreet);
        partnerHome.setExit("east", leftStreet);

        //rightstreet room exits
        pd.setExit("north", jail);
        pd.setExit("south", rightStreet);
        jail.setExit("south", pd);
        home.setExit("west", rightStreet);
        court.setExit("north", rightStreet);

        
        
        hostileNpcs.put(hobo1.getName(), hobo1);
        hostileNpcs.put(hobo2.getName(), hobo2);
        hostileNpcs.put(hobo3.getName(), hobo3);
        hostileNpcs.put(hobo4.getName(), hobo4);
        npcs.put(coroner.getName(), coroner);
        npcs.put(commissioner.getName(), commissioner);
        npcs.put(wife.getName(), wife);
        npcs.put(bartender.getName(), bartender);
        
        rooms.put(rightStreet.getRoomName(), rightStreet);
        rooms.put(leftStreet.getRoomName(), leftStreet);
        rooms.put(pd.getRoomName(), pd);
        rooms.put(court.getRoomName(), court);
        rooms.put(home.getRoomName(), home);
        rooms.put(jail.getRoomName(), jail);
        rooms.put(crimeScene.getRoomName(), crimeScene);
        rooms.put(partnerHome.getRoomName(), partnerHome);
        rooms.put(hoboAlley.getRoomName(), hoboAlley);
        rooms.put(bar.getRoomName(), bar);
        
        items.put(beer.getName(), beer);
        items.put(wine.getName(), wine);
        items.put(gin.getName(), gin);
        items.put(beerKeg.getName(), beerKeg);
        items.put(whiskey.getName(), whiskey);
        items.put(coffee.getName(), coffee);
        items.put(murderWeapon.getName(), murderWeapon);
        items.put(partnerKey.getName(), partnerKey);
        items.put(bloodSplatter.getName(), bloodSplatter);
        items.put(corpse.getName(), corpse);
        items.put(badge.getName(), badge);
        items.put(gun.getName(), gun);
        items.put(corpseOutline.getName(), corpseOutline);
        
        clues.put(hobo1Statement.getName(), hobo1Statement);
        clues.put(hobo2Statement.getName(), hobo2Statement);
        clues.put(hobo3Statement.getName(), hobo3Statement);
        clues.put(hobo4Statement.getName(), hobo4Statement);
        clues.put(coronerStatement.getName(), coronerStatement);
        clues.put(bartenderStatement.getName(), bartenderStatement);
        clues.put(bloodsplatterClue.getName(), bloodsplatterClue);
        clues.put(bloodSplatteredBadgeClue.getName(), bloodSplatteredBadgeClue);
        clues.put(murderWeaponClue.getName(), murderWeaponClue);
        clues.put(corpseClue.getName(), corpseClue);
        
        hobos.add(hobo1);
        hobos.add(hobo2);
        hobos.add(hobo3);
        hobos.add(hobo4);
        hoboAlley.addItemToRoom(badge);//for testing
    }
    
    public NPC getNPC(String name){
    return npcs.get(name);
    }
    public HostileNPC getHostileNPC(String name){
    return hostileNpcs.get(name);
    }
    public Item getItem(String name){
    return items.get(name);
    }
    public Room getRoom(String name){
    return rooms.get(name);
    }
    public Clue getClue(String name){
    return clues.get(name);
    }
    public boolean isRoom(String name){
        return rooms.containsKey(name);
    
    }
    public Map getTheCluse (){
    return clues;
    }
    public ArrayList<Hobo> getHobos(){
        return hobos;
    }

}

