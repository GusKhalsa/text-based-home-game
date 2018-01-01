/**
 * A character in the game.
 * 
 * @author Gursimran S Khalsa
 * @version 6/2/17
 */
public enum Character
{
    MOTHER("Mother", Item.FLOUR), FATHER("Father", Item.EGG), DAUGHTER("Daughter", Item.SUGAR), SON("Son", null);
    
    //single word description of the character.
    private String description;
    //The item the character is carrying.
    private Item item;
    
    /**
     * @param description - The description of the character.
     * @param item - The item the character has.
     */
    private Character(String description, Item item)
    {
        assert description != null : "Character.Character has null description";
        this.description = description;
        this.item = item;
    }
    
    /**
     * @return Return the description of the character and the item they hold.
     */
    public String toString()
    {
        if(item == null){
            return description + " has nothing";
        }else{
            return description + " has " + item.toString();
        }
    }
    
    /**
     * @return The item object the character holds.
     */
    public Item getItem()
    {
        return item;
    }
    
    /**
     * @return The description of the character.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Sets a character with a new item or no item.
     */
    public void setItem(Item item)
    {
        this.item = item;
    }
    
    /**
     * @param item - A characters item.
     * @return True or false based on if the item is taken from 
     * the character successfully.
     */
    public boolean take(Item item)
    {
        if(this.item == item){
            return true;
        }else{
            return false;
        }
    }
}
