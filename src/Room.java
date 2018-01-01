import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in a game.
 *
 * This class is part of the "World of Home" application. 
 * "World of Home" is a very simple, text based travel game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes, Olaf Chitil and Gursimran S Khalsa.
 * @version 6/2/17
 */

public class Room 
{
    private String description;
    private HashMap<Direction, Room> exits;        // stores exits of this room.
    private ArrayList<Character> characters;  // stores characters in this room.
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * Pre-condition: description is not null.
     */
    public Room(String description) 
    {
        assert description != null : "Room.Room has null description";
        this.description = description;
        exits = new HashMap<Direction, Room>();
        characters = new ArrayList<Character>();
        sane();
    }

    /**
     * Class invariant: getShortDescription() and getLongDescription() don't return null.
     */
    public void sane()
    {
        assert getShortDescription() != null : "Room has no short description" ;
        assert getLongDescription() != null : "Room has no long description" ;
    }
   
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     * Pre-condition: neither direction nor neighbor are null; 
     * there is no room in given direction yet.
     */
    public void setExit(Direction direction, Room neighbor) 
    {
        assert direction != null : "Room.setExit gets null direction";
        assert neighbor != null : "Room.setExit gets null neighbor";
        assert getExit(direction) == null : "Room.setExit set for direction that has neighbor";
        sane();
        exits.put(direction, neighbor);
        sane();
        assert getExit(direction) == neighbor : "Room.setExit has wrong neighbor";
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Father has flour.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getCharactersString() + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<Direction> keys = exits.keySet();
        for(Direction exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     * Pre-condition: direction is not null
     */
    public Room getExit(Direction direction) 
    {
        assert direction != null : "Room.getExit has null direction";
        sane();
        return exits.get(direction);
    }
    
    /**
     * @return A string of all characters and their item in the room.
     */
    public String getCharactersString()
    {
        String characterString = new String();
        for(Character character : characters){
            characterString += character.toString() + ". " ;
        }
        return characterString;
    }
    
    /**
     * @return characters - List of all the characters in the room.
     */
    public ArrayList<Character> getCharacters()
    {
        return characters;
    }
    
    /**
     * Adds a character to the characters list for the room.
     * @param character - Character object to add to the rooms collection.
     * Pre-condition: character is not null.
     */
    public void addCharacter(Character character)
    {
        assert character != null : "addCharacter(character) gives null character.";
        characters.add(character);
    }
    
    /**
     * Takes (item) from the character if they have it.
     * @return itemTaken - Returns true or false based on if the item is taken from the character
     * successfully.
     */
    public boolean take(Item item)
    {
        boolean itemTaken = true;
        for(Character character : characters){
            if(character.take(item)){
                itemTaken = true;
            }else{
                itemTaken = false;
            }
        }
        return itemTaken;
    }
}

