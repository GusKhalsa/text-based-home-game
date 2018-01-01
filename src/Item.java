
/**
 * An item in the game.
 * 
 * @author Gursimran S Khalsa
 * @version 1/2/17
 */
public enum Item
{
    FLOUR("flour"), SUGAR("sugar"), EGG("egg");
    
    private String description; //single word description of item.
    
    /**
     * Constructor with a parameter.
     * @param description - The description of the item.
     */
    private Item(String description)
    {
        assert description != null : "Item.Item has null name";
        this.description = description;
        assert toString().equals(description) : "Item.Item gives wrong toString";
    }
    
    /**
     * @return The description of the item.
     */
    public String toString()
    {
        return description;
    }
    
}
