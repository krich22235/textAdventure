package textadventure.game;


import java.util.HashMap;
import java.util.Set;


public class Game {
	private Parser parser;
	private Room currentRoom;
	private Player player;
	private CLS cls_var;
	private HashMap <String, Item> inventory;

	public Game() {
	    parser = new Parser();
	    player = new Player();
	}

	public static void main(String[] args) {
	    Game game = new Game();
	    game.setupGame();
	    game.play();
	}

	public void printInformation() {
	    System.out.println("\n");
	    System.out.println("-------------------------------------------------------------------------------------------------------");
	    System.out.println(currentRoom.getShortDescription());
	    System.out.println("-------------------------------------------------------------------------------------------------------");
	    System.out.println("\n");
	    System.out.println("-------------------------------------------------------------------------------------------------------");
	    System.out.println(currentRoom.getExitString());
	    System.out.println(currentRoom.getInventoryString());
	    System.out.println(player.getInventoryString());
	    System.out.println("-------------------------------------------------------------------------------------------------------");
	}
	    
	public void printTitle() {
	    System.out.println("Welcome to...");
		System.out.println("\n");
		System.out.println(" ___  ___ ___  ___  ___  ___     | |_  ___  ___  _ _ _  ___  _ _ _| |_ ___");
	    System.out.println("/ ._><_-</ | '<_> || . \\/ ._>    | . |/ . \\/ . || | | |<_> || '_> | | <_-<");
	    System.out.println("\\___./__/\\_|_.<___||  _/\\___.    |_|_|\\___/\\_. ||__/_/ <___||_|   |_| /__/");
	    System.out.println("                   |_|                     <___'                          ");
		System.out.println("\n");
	}
	
	public void printIntro() {
		//Program introduces the text adventure to the player, providing BOTH instruction on how to play and flavor text to introduce the lore/environment of the game.//
		System.out.println("The game based off of your favorite movie and book series, Harry Potter, escape hogwarts is the perfect game for those who love the movies and books." + "\n" + "Type 'play' to start and enter the game by using commands such as 'go', 'speak', 'grab', 'drop', 'look', 'help', 'toss', 'open', and 'search'."); 
	}

	public void setupGame() {
		Room threeBroomsticks = new Room ("three broomsticks", "You appear in a busy restaurant and want to find a way out.","You are in a busy restaurant. You do not know how you got here, but are greeterd by all the people there. You are confused and want to find a way outside." );
	    Room hogsmeade =  new Room ("hogsmeade", "You are free from the restaurant and see a busy town in front of you.", "You find the loose brick and are able to escape from the restaurant. You now see a busy town with different small shops and businesses. Go window shopping!");
	    Room kingsCross = new Room ("kings cross", "You appear in a busy train station, King's Cross.", "You appear to be in a busy train station. You are accompanied by your belongings and a cart. You also carry a ticket. There are many people around and a few trains on each side of the walkway.");
	    Room hogwarts = new Room ("hogwarts", "You appear at the front of a castle.", "You appear at the front of a castle. There are huge doors in front and a few knights made of stone. You have all your belongings with you again. There are different paths to your side and steps going down.");
	    Room hagridsHut = new Room ("hagrids hut", "You appear to be on a trail. There is a hut a few feet away.", "You appear to be on a trail. There is a hut a few feet away. You smell something and there is smoke coming out of the huts chimney.");
	    Room forbiddenForest = new Room ("forbidden forest", "You are on a trail with lots of trees. It continues into darkness.", "You are on a trail with lots of trees. It continues into darkeness. You hear faint noises coming from deep within the forest. You are warned.");        

	    currentRoom = threeBroomsticks;

	    threeBroomsticks.setExit("hogsmeade", hogsmeade);
	    hogsmeade.setExit("threeBroomsticks", threeBroomsticks);
	    hogsmeade.setExit("kingsCross", kingsCross);
	    kingsCross.setExit("hogwarts", hogwarts);
	    hogwarts.setExit("hagridsHut", hagridsHut);
	    hogwarts.setExit("forbiddenForest", forbiddenForest);
	    hagridsHut.setExit("forbiddenForest", forbiddenForest);
	    hagridsHut.setExit("hogwarts", hogwarts);
	    forbiddenForest.setExit("hogwarts", hogwarts);
	    forbiddenForest.setExit("hagridsHut", hagridsHut);

	    Item brick = new Item ("brick", "the brick is red and heavy. you can use it to escape.");
	    Item letter = new Item ("letter", "long description");
	        
	    threeBroomsticks.setItem("brick", brick);
	    threeBroomsticks.setItem("letter", letter);
	        
	    Item wand = new Item ("wand", "you gain a wand from the shopkeeper. it is made of some kind of wood and is kind of heavy. you can use it along your adventure and to help you.");
	    Item animal = new Item ("animal", "you have a choice of animal: cat, turtle, frog, owl, and mouse. choose one:");
	    Item luggage = new Item ("luggage", "you have your belongings situated in a few pieces of luggage. they are made of leather.");
	    Item ticket = new Item ("ticket", "your ticket is for kings cross station. there is a platform number 9 ¾. you are really confused.");
	        
	    hogsmeade.setItem("brick", brick);
	    hogsmeade.setItem("letter", letter);
	    hogsmeade.setItem("wand", wand);
	    hogsmeade.setItem("animal", animal);
	    hogsmeade.setItem("luggage", luggage);
	    hogsmeade.setItem("ticket", ticket);

	    Item cart = new Item ("cart", "you happen to find an empty cart on the platform. it can help carry your belongings.");

	    kingsCross.setItem("cart", cart);

	    Item food = new Item ("food", "you smell food. some kind of meat and some garlic. you are hungry.");
	    hagridsHut.setItem("food", food);

	    
	    currentRoom = threeBroomsticks;

	    try {
	           cls_var.main(); 
	    	} catch(Exception e) {
	           System.out.println(e); 
	    	}
	        
	    printTitle();
	    printIntro();
	    System.out.println("To enter the game, type 'play'.");
	    play();   
	    printInformation();
	    getInventoryString();
	}

	public String getInventoryString() {
        String returnString = "Player Inventory: ";
        Set <String> keys =  inventory.keySet() ;
        if (player.getInventoryString().equals(null)) {
    		return returnString;
    	} 
        for (String item: keys) {
            returnString += " " + item;
        }
        return returnString;
    }
	
	public void play() {
	    while(true) {            
	        Command command = parser.getCommand(); 
	        try {
	            cls_var.main(); 
	        }catch(Exception e) {
	            System.out.println(e); 
	        }
	        processCommand(command);
	        printInformation();   
	    }
	}

	public void processCommand (Command command) {
	    String commandWord = command.getCommandWord().toLowerCase();
	    switch(commandWord) {
	       	case "speak":
	            System.out.println("you wanted me to speak this word, " + command.getSecondWord());
	            break;
	        case "go":
	            goRoom(command);
	            break;
	        case "grab":
	            grab(command);
	            break;
	        case "drop":
	            drop(command);
	            break;
	        case "look":
	            look(command);
	            break;
	        case "help":
	            System.out.print("your commands: grab, drop, go, speak, look" + "\n");
	            break;
	        case "toss":
	            toss(command);
	        case "open":
	            open(command);
	        case "search":
	            search(command);
	    }
	}
	    
	public void grab(Command command) {
	    String item;
	    if(!command.hasSecondWord()) {
	       System.out.println("grab what?");
	       return;
	    }
	    item = command.getSecondWord();
	    Item itemToGrab = currentRoom.removeItem(item);
	    if (itemToGrab == null) {
	    	System.out.println("you cannot grab that!");
	    }
	    else {
	        player.setItem(item, itemToGrab);
	    }
	}
	    
	public void toss(Command command) {
	    String item;
	    if(!command.hasSecondWord()) {
	        System.out.println("toss what?");
	        return;
	    }
	    item = command.getSecondWord();
	    Item itemToGrab = currentRoom.removeItem(item);
	    if (itemToGrab == null) {
	        System.out.println("you cannot toss that!");
	    }
	    else {
	        player.setItem(item, itemToGrab);
	    }
	}
	    
	public void open(Command command) {
	    String item;
	    if(!command.hasSecondWord()) {
	        System.out.println("open what?");
	        return;
	    }
	    item = command.getSecondWord();
	    Item itemToGrab = currentRoom.removeItem(item);
	    if (itemToGrab == null) {
	        System.out.println("you cannot open that!");
	    }
	    else {
	        player.setItem(item, itemToGrab);
	    }
	}

	public void drop(Command command) {
	    String item;
	    if(!command.hasSecondWord()) {
	        System.out.println("drop what?");
	        return;
	    }
	    item = command.getSecondWord();
	    Item itemToDrop = player.removeItem(item);
	    if (itemToDrop == null) {
	        System.out.println("you cannot drop that!");
	        return;
	    }
	    else {
	        currentRoom.setItem(item, itemToDrop);
	    }
 }
	    
	    public void look(Command command) {
	        String printString = "Looking at ";
	        String thingToLook;
	        if(!command.hasSecondWord()) {
	            System.out.println("look at what?");
	            return;
	        }
	        
	        if(!command.hasLine()){
	             thingToLook = command.getSecondWord();
	        }
	        else if (command.hasLine()) {
	             thingToLook = command.getSecondWord() + command.getLine();
	        }
	        
	        thingToLook = command.getSecondWord() + command.getLine();
	        
	        if(thingToLook.equals(currentRoom.getName())) {
	            printString += "the room: " + currentRoom.getName() + "\n" + currentRoom.getLongDescription();
	        }
	        else if (currentRoom.getItem(thingToLook) != null) {
	            printString += "the item: " + currentRoom.getItem(thingToLook).getName() + "\n" + currentRoom.getItem(thingToLook).getItemDescription();
	        }
	        else if (player.getItem(thingToLook) != null) {
	            printString += "the item: " + player.getItem(thingToLook).getName() + "\n" + player.getItem(thingToLook).getItemDescription();
	        }
	        else {
	            printString += "\nYou cannot look at that";
	        }
	        	
	        System.out.println (printString);
	    }
	    
	    public void goRoom(Command command) {
	        String direction = "" ;
	        if(!command.hasSecondWord()) {
	            System.out.println("go where?");
	        }
	        if(!command.hasLine()) {
	            direction = command.getSecondWord();
	        }
	        else if (command.hasLine()) {
	            direction = command.getSecondWord() + command.getLine();
	        }
	        Room nextRoom = currentRoom.getExit(direction);
	        if (nextRoom == null) {
	            System.out.println("you cannot go there!");
	        }
	        else {
	            currentRoom= nextRoom;
	        }
	    }
	    
	    public void search(Command command) {
	    }
	    
	}