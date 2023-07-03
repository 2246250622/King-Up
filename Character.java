/**
 * Model a Character in the game. It should keeps its name and its position.
 */
public class Character {
    public static final int OUT_OF_GAME = -1;
    private String name;
    private int position;

    /**
     * Initialize the charcter with its name
     * @param name The name of the character
     */
    public Character(String name) {
        this.name= name;
    }

    /**
     * Return the position of where the character is.
     *
     * @return the position of where the character is. Only possible values are OUT_OF_GAME (-1), 0, 1, 2, 3, 4 ,5, 6
     */
    public int getPosition() {
        return position;
    }

    /**
     * Set the position of the character. It should not be allowed to set to a level lower than its position, or two steps (or more)
     * higher than its current position, except:
     * 1) The character is OUT_OF_GAME, which mean it is in placing stage
     * 2) The character is to be taken out of the game, which mean it is killed during the voting stage.
     *
     * @param pos The position where the character is to be placed.
     * @return true if the setPosition is successful, false if the calling of the method is disallowed (as stated above).
     */
    public boolean setPosition(int pos) {
        if (pos != OUT_OF_GAME && position != OUT_OF_GAME && pos != position + 1){
            return false;
        }else {
            position = pos;
            return true;
        }
     }


    /**
     * Return the name of the character with its position in the following format
     * NAME[POSITION], e.g. Aligliero[2] which means Aligliero in position 2. or
     * e.g. Dario[-1] which means Dario is out of the game.
     *
     * @return the name of the character with its position
     */
    public String toString() {
        return name+"["+position+"]";
    }

    /**
     * Return the name of the character
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

}
