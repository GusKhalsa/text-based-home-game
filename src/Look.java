
/**
 * Displays the description of the room and the exits.
 * 
 * @author Gursimran Khalsa
 * @version 1/2/17
 */
public class Look extends Command
{
    
    /**
     * Constructor for objects of class Look
     * Pre-condition: game is not null.
     */
    public String process(GameMain ui, Game game)
    {
        assert game != null;
        return game.look();
    }


}
