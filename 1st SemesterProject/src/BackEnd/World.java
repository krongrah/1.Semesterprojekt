package BackEnd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import BackEnd.Room;
import BackEnd.WorldFill.Beverage;
import BackEnd.WorldFill.Clue;
import BackEnd.WorldFill.Dialogue;
import BackEnd.WorldFill.HostileNPC;
import BackEnd.WorldFill.Item;
import BackEnd.WorldFill.NPC;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Krongrah
 */
public class World implements Serializable {
    
    private Map<String,NPC> npcs=new HashMap<>();
    private Map<String,Item> items=new HashMap<>();
    private Map<String,Clue> clues=new HashMap<>();
    private Map<String,Room> rooms=new HashMap<>();

    public World(){
    createWorld();
    }
    
    private void createWorld() {

        Clue bartenderStatement = new Clue("Bartender's statement", "According to Bartender Bert everyone hated the victim.", false);
        Clue hobo1Statement = new Clue("No-Teeth Terry's statement", "According to No-Teeth Terry the murderer was a drunk man.", false);
        Clue hobo2Statement = new Clue("Dirty Darryl's statement", "Dirty Darryl obviously hates cops.", true);
        Clue hobo3Statement = new Clue("Heroin Harry's statement", "According to Heroin Harry Dirty Darryl is the killer.", true);
        Clue hobo4Statement = new Clue("Insane Dwayne's statement", "Insane Dwayne is insane.", true);
        Clue coronerStatement = new Clue("Coroner's statement", "According to the coroner the murder was a crime of passion, \nand the victim knew his killer.", true);
        Clue murderWeaponClue = new Clue("Murder Weapon Evidence", "The murder weapon is a broken bottle of your favorite beer.", true);
        Clue bloodsplatterClue = new Clue("Blood Splatter", "There was a lot of blood on the crimescene, suggesting a violent conflict.", false);
        Clue corpseClue = new Clue("Corpse", "The body of the victim was stabbed repeatedly, and had spit covering it's face.", false);
        Clue bloodSplatteredBadgeClue = new Clue("Blood Splattered Badge evidence", "This is the badge of the victim was found in your home, \nwhich points to you being the killer.", true);

        
        
        // creation of the multiple items via the constructor in Item.java
        Item murderWeapon = new Item("Murder Weapon", "This is a broken bottle"
                + " with sharp edges and blood covering the edges", true, true, murderWeaponClue);
        Item bloodSplatter = new Item("Blood splatter", "the ground is covered"
                + " in blood", true, false, bloodsplatterClue);
        Item gun = new Item("Gun", "Its a smith and wesson, your best friend",
                false, true, corpseClue);
        Item corpse = new Item("Corpse", "its a dead guy, he looks to be stabbed"
                + " brutally multiple times.\n When you look closer you notice"
                + " his face is covered in spit", true, false, corpseClue);
        Item bloodSplatteredBadge = new Item("Blood Splattered Badge", "Its your"
            + " former partners badge covered in blood, odd that you would find"
            + " this here. \n A wave of guilt washes over you as you realise"
            + " what you have done", true, true, bloodSplatteredBadgeClue);
        Item partnerKey = new Item("Key to Partner's home", "This key belongs to your deceased partner, "
            + "and it allows you to enter his home.", false, true, null);
    
        
        Beverage whiskey=new Beverage("Whiskey","This bottle is your favorite drink, and the reason you love coming home.",false, true, null,2,5);
        Beverage gin = new Beverage ("Half-empty Gin", "its a half-empty bottle of gin, some idiot wasted his drink", false, true, null,1,7);
        Beverage beerKeg = new Beverage ("Keg of beer", "Its a keg of beer, nobody will notice if you take this", false, true, null, 4,4);
        Beverage coffee = new Beverage ("Coffee", "A normal looking drink of coffee, expect you can smell a faint vodka odor", false, true, null, 2,6);
        Beverage wine = new Beverage ("Wine", "the fancy people left out a glass of wine", false, true, null, 3,7);
        Beverage beer = new Beverage("Beer", "It's a well known brand called pisswasser", false, true, null, 2, 2);
    
        
        Room leftStreet = new Room(" on left street", "Left Street");
        Room rightStreet = new Room(" on Right street", "Right Street");
        Room bar = new Room(" in the bar", "Bar");
        Room hoboAlley = new Room(" in the Hobo Alley, try not to get stabbed", "Hobo Alley");
        Room crimeScene = new Room(" at the crime scene", "Crime scene");
        Room partnerHome = new Room(" in your deceased partners house", "Partner's home");
        Room home = new Room(" in your home", "Home");
        Room pd = new Room(" in the Police Department", "Police Department");
        Room jail = new Room(" visiting the jail, in the pd", "Jail");
        Room court = new Room(" in court", "Court");

      

        //create dialogue
        String[] coronerLine =  {
            "Welcome to the murder scene, make yourself at home.",
            "The victim is your partner, Detective Prickard. He was a dick, and the world is a better place without him.",
            "The victim was stabbed several times, and died from blood loss. It appears to be a crime of passion, due to the many stab wounds the spit on the victim’s face.",
            "The victim was surprised by the attack, so I believe he knew his killer. (update cluelist)",
            "I’ll get the cleaning team here know, so we can get this shit of the street. (loop; if played, NPC will disappear on next loading.)"
        };
        String[] wifeLine = {
            "What are you doing here? You love your job more than me, so go do your job and leave me alone.",
            "You are always out drinking, you sad piece of shit.",
            "Where were you even last night? You came home covered in shit all worked up?",
            "Go away, I want a good day. (end of dialogue: loop)"
        };
        String[] bartenderLine = {
            "Isn't it a bit early for you to be here?",
            "Detective Prickard is dead? I can’t say I’m surprised, he didn’t seem to get along with anyone, especially not you. (add to cluelist)",
            "I think you should get back at work now. (loop)"
        };
        String[] hobo1Line = {
            "Coppers don’t often come up to ‘dese parts",
            "I ‘eard a drunk fella’ shoutin’ at ’em.	(clue)",
            "Bugger off. (loop)"
        };
        String[] hobo2Line = {
            "We don’ take kindly to your kind ‘roun’ ‘ere!",
            "What de’ ye’ stinkin’ cops wan’ over ‘ere? (clue)",
            "Ay ain’t tellin’ ye’ nutin’, stupid cop. (loop)"
        };
        String[] hobo3Line = {
            "It gats’ ta’ be Darryl! He be lookin’ funny at me! (clue)",
            "Gat any smack? (loop)"
        };
        String[] hobo4Line = {
            "I bet your ass killed him, cops can’t help killing.",
            "I think you guilty, the voice in my head told me so. (loop)",};
        String[] commissionerLine1 = {
            "Detective, there has been a murder in the Hobo Alley, get over there ASAP. no time to wait for your partner, detective Prickard.",
            "Get your fat ass out of here! (loop)"
        };
        String[] commissionerLine2 = {
            "Did you find his badge? We need it for the memorial. (loop)"

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

        //add dialogue to dialogue object
        Dialogue coronerDialogue = new Dialogue(coronerLine, coronerAlibi, true);
        Dialogue wifeDialogue = new Dialogue(wifeLine, wifeAlibi, true);
        Dialogue bartenderDialogue = new Dialogue(bartenderLine, bartenderAlibi, true);
        Dialogue hobo1Dialogue = new Dialogue(hobo1Line, hobo1Alibi, false);
        Dialogue hobo2Dialogue = new Dialogue(hobo2Line, hobo2Alibi, false);
        Dialogue hobo3Dialogue = new Dialogue(hobo3Line, hobo3Alibi, false);
        Dialogue hobo4Dialogue = new Dialogue(hobo4Line, hobo4Alibi, false);
        Dialogue commissionerDialogue = new Dialogue(commissionerLine1, commissionerLine2, commissionerAlibi, true);

        //create clues
        
        
        
        
        
        
        NPC hobo1 = new NPC("No-Teeth Terry", hobo1Dialogue, hobo1Statement, 2);
        NPC hobo2 = new NPC("Dirty Darryl", hobo2Dialogue, hobo2Statement, 2);
        NPC hobo3 = new NPC("Heroin Harry", hobo3Dialogue, hobo3Statement, 1);
        NPC hobo4 = new NPC("Insane Dwayne", hobo4Dialogue, hobo4Statement, 1);
        NPC commissioner = new NPC("Commissioner Curt", commissionerDialogue, null, 0);
        NPC bartender = new NPC("Bartender Bert", bartenderDialogue, bartenderStatement, 2);
        HostileNPC wife = new HostileNPC("Wife", wifeDialogue, null, 0, 50, 5, 0.5);
        NPC coroner = new NPC("Coroner", coronerDialogue, coronerStatement, 4);
        HostileNPC wife1 = new HostileNPC("Wife", null, null, 0, 50, 5, 0.5); //todo what is this
        
        
        bar.addNpcToRoom(bartender);
        home.addNpcToRoom(wife);
        pd.addNpcToRoom(commissioner);
        crimeScene.addNpcToRoom(coroner);
        crimeScene.addNpcToRoom(hobo1);
        crimeScene.addNpcToRoom(hobo2);
        crimeScene.addNpcToRoom(hobo3);
        crimeScene.addNpcToRoom(hobo4);

        hoboAlley.addItemsToRoom(murderWeapon);
        crimeScene.addItemsToRoom(bloodSplatter);
        crimeScene.addItemsToRoom(corpse);
        pd.addItemsToRoom(gun);
        bar.addItemsToRoom(beer);
        home.addItemsToRoom(whiskey);
        pd.addItemsToRoom(partnerKey);
        hoboAlley.addItemsToRoom(gin);
        pd.addItemsToRoom(coffee);
        rightStreet.addItemsToRoom(beerKeg);
        leftStreet.addItemsToRoom(wine);
        
        

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

        
        
        npcs.put(hobo1.getName(), hobo1);
        npcs.put(hobo2.getName(), hobo2);
        npcs.put(hobo3.getName(), hobo3);
        npcs.put(hobo4.getName(), hobo4);
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
        items.put(bloodSplatteredBadge.getName(), bloodSplatteredBadge);
        items.put(gun.getName(), gun);
        
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
        
        
    }
    
    public NPC getNPC(String name){
    return npcs.get(name);
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
}