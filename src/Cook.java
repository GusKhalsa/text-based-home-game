
/**
 * The cook command for the game.
 * It checks if the player has met all the requirements to finish the game.
 * 
 * @author Gursimran S Khalsa
 * @version 8/2/17
 */
public class Cook extends Command
{
    
    /**
     * Cook with all 3 ingredients in the kitchen to end the game.
     * Pre-condition: game is not null
     */
    public String process(GameMain ui, Game game)
    {
        assert game != null;
        return game.cook();
    }

}
