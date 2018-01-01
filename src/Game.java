import java.util.ArrayList;
/**
 *  This class is the central class of the "World of Home" application. 
 *  "World of Home" is a very simple, text based travel game.  Users 
 *  can walk around some house. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 * @author  Michael Kölling, David J. Barnes, Olaf Chitil and Gursimran S Khalsa(gk264).
 * @version 9/2/17
 */

public class Game 
{
    private Room currentRoom;
    private boolean finished;
    private Room goal;
    private Room challengeGoal;
    private int moves = 0;
    private ArrayList<Item> playerItems; //The players inventory. 
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        finished = false;
        createRooms();
        playerItems = new ArrayList<Item>();
    }
    

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room front, hall, kitchen, livingroom, garden, cloakroom, stairs, bathroom, 
            master, ensuite, guest;
            
        // create the rooms
        front = new Room("in front of the house");
        hall = new Room("in the hallway");
        kitchen = new Room("in the kitchen");
        livingroom = new Room("in the livingroom");
        garden = new Room("in the garden");
        cloakroom = new Room("in the cloakroom");
        stairs = new Room("at the top of the stairs");
        bathroom = new Room("in the bathroom");
        master = new Room("in the master bedroom");
        ensuite = new Room("in the ensuite");
        guest = new Room("in the guest bedroom");
        
        // initialise room exits
        
        front.setExit(Direction.NORTH, hall);
        hall.setExit(Direction.SOUTH, front);
        hall.setExit(Direction.UP, stairs);
        hall.setExit(Direction.WEST, cloakroom);
        hall.setExit(Direction.EAST, kitchen);
        hall.setExit(Direction.NORTH, livingroom);
        kitchen.setExit(Direction.WEST, hall);
        kitchen.setExit(Direction.NORTH, livingroom);
        cloakroom.setExit(Direction.EAST, hall);
        livingroom.setExit(Direction.SOUTH, hall);
        livingroom.setExit(Direction.EAST, kitchen);
        livingroom.setExit(Direction.NORTH, garden);
        garden.setExit(Direction.SOUTH, livingroom);
        stairs.setExit(Direction.DOWN, hall);
        stairs.setExit(Direction.EAST, bathroom);
        stairs.setExit(Direction.SOUTH, guest);
        stairs.setExit(Direction.NORTH, master);
        bathroom.setExit(Direction.WEST, stairs);
        guest.setExit(Direction.NORTH, stairs);
        master.setExit(Direction.SOUTH, stairs);
        master.setExit(Direction.EAST, ensuite);
        ensuite.setExit(Direction.WEST, master);
            
        currentRoom = front;  // start game at the front of the house
        goal = ensuite;       // end game if in the ensuite
        challengeGoal = kitchen; //end game based on cook requirements in the kitchen.
        
        //add characters to rooms.
        livingroom.addCharacter(Character.MOTHER);
        bathroom.addCharacter(Character.FATHER);
        stairs.addCharacter(Character.SON);
        stairs.addCharacter(Character.DAUGHTER);
    }
    
    /**
     * Return whether the game has finished or not.
     */
    public boolean finished()
    {
        return finished;
    }

    /**
     * Opening message for the player.
     */
    public String welcome()
    {
        return "\nWelcome to the World of Home!\n" +
            "World of Home is a new game.\n" +
            currentRoom.getLongDescription() + "\n";
    }


    // implementations of user commands:

    /**
     * Give some help information.
     */
    public String help() 
    {
        return "You are lost. You are alone. You wander around the home.\nCollect the ingredients and take them to the kitchen.\n";
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room and return its long description; otherwise return an error message.
     * @param direction The direction in which to go.
     * Pre-condition: direction is not null.
     */
    public String goRoom(Direction direction) 
    {
        assert direction != null : "Game.goRoom gets null direction";
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        moves ++;
        
        // Checks if player has reached the goal in time.
        if (moves > 12){
            quit();
            return "GAME OVER : You ran out of time!";
        }else if(nextRoom == goal){
            quit();
            return "Congratulations, you have reached the ensuite in time!";
        }
        
        if (nextRoom == null) {
            return "There is no exit in that direction!";
        }else{
            currentRoom = nextRoom;
            return currentRoom.getLongDescription();
        }
    } 
    
    /**
     * Execute quit command.
     * @return A message to exit out of the game.
     */
    public String quit()
    {
        finished = true;
        return "Thank you for playing.  Good bye.";
    }
    
    /**
     * Execute look command.
     * Re prints the description and exits of the room.
     * @return The description and exits.
     */
    public String look()
    {
        return currentRoom.getLongDescription();
    }
    
    /**
     * Takes an item from the player if there is an item to take and based on
     * if the item has already been previously taken.
     * @return message - A message to display to the player based on the success of taking an item.
     * Pre-condition: item is not null.
     */
    public String take(Item item)
    {
        assert item != null : "Game.take(item) gets null item";
        String message = new String();
        if(currentRoom.getCharacters().size() == 0){
            message = "There are no items in this room";
        }else{
            for(Character character : currentRoom.getCharacters()){
                Item charItem = character.getItem();
                if(currentRoom.take(item)){
                    if(charItem != null){
                        message = "You have taken the " + charItem.toString();
                        playerItems.add(charItem);
                        character.setItem(null);
                    }  
                }else{
                    message = character.getDescription() + " does not have an item anymore.";
                }
            }
        }
        return message;
    }
    
    /**
     * Execute cook command.
     * @return mainGoalMessage - The message to print based on if the player has successfully met the requirements.
     * Requirements: All 3 items (Flour, Egg, Sugar) in the players inventory whilst in the kitchen.
     */
    public String cook()
    {
        String mainGoalMessage = new String();
        if(currentRoom == challengeGoal){
            if(playerItems.contains(Item.FLOUR) && playerItems.contains(Item.EGG) && playerItems.contains(Item.SUGAR)){
                mainGoalMessage = "Congratulations, you have completed the game!";
                quit();
            }else{
                mainGoalMessage = "You don't have all of the ingredients.";
            }
        }else{
            mainGoalMessage = "Type 'help' for more information";
        }
        return mainGoalMessage;
    }
}
