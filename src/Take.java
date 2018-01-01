
/**
 * The Take command for the game which requires an item.
 * 
 * @author Gursimran S Khalsa
 * @version 7/2/2017
 */
public class Take extends Command
{
    private Item item;
    /**
     * Constructor for objects of class Take.
     * Initialise item.
     * Pre-condition: item is not null.
     */
    public Take(Item item)
    {
        assert item != null : "Take.Take gets null item";
        this.item = item;
    }
    
    /**
     * Take the specified item from the character.
     * Pre-condition: game is not null
     */
    public String process(GameMain ui, Game game)
    {
        assert game != null : "Take.process gets null game";
        return game.take(item);
    }
}
