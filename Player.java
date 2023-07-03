import java.util.concurrent.ThreadLocalRandom;

/**
 * Model the player
 */
public class Player {
    /**
     * The length of the list
     */
    private static final int LIST_LENGTH = 6;
    /**
     * A secret list of a player. This list would not be changed during the game
     */
    private Character[] myList;
    /**
     * The number of veto (reject) voting card. Decrement it when the player vote
     * reject.
     */
    private int vetoCard;
    /**
     * The name of the player.
     */
    private String name;

    /**
     * Compute the score of a player. Each player should have a list of character
     *
     * @return the score of a player.
     */
    public int getScore() {
        int score = 0;
        for (int i = 0; i < LIST_LENGTH; i++) {    //count the score with using the score list
            if(myList[i].getPosition()!=-1) {
                score += Gameboard.SCORES[myList[i].getPosition()];
            }
            }
        return score;
    }

    /**
     * Return the name of a player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }


    public int getVeto() {
        return vetoCard;
    }

    /**
     * Initialize the number of veto card
     *
     * @param card the number of veto card
     */
    public void initVetoCard(int card) {
        this.vetoCard = card;
    }

    /**
     * Initialize all data attributes.
     *
     * @param name the name of the player
     * @param list a whole list of characters and the player should pick
     *             LIST_LENGTH of <b>unique</b> characters
     */
    public Player(String name, Character[] list) {
        this.name = name;
        Character[] sixCharacters = new Character[LIST_LENGTH];
        for (int i = 0; i < sixCharacters.length; i++) {
            boolean check = true;
            outer: //the point of outer loop
            while (check == true) {
                check =false;
                int num = ThreadLocalRandom.current().nextInt(0, list.length);      //the raandom range
                Character newarray = list[num];
                for (int p=0; p< sixCharacters.length;p++) {
                    if (sixCharacters[p]==newarray) {
                        check = true;

                    }
                }
                if(check == false) {
                    sixCharacters[i] = newarray;
                    continue outer; //stop loop in move to outer loop
                }

            }
        }
        this.myList = sixCharacters;  //replace the old mylist
    }

    /**
     * A method to vote according to the preference of the parameter support.
     * A player is forced to vote yes (support) if he/she has no more veto card.
     *
     * @param support The preference of the player
     * @return true if the player support it. False if the player reject (veto).
     */
    public boolean vote(boolean support) {
        if (this.vetoCard == 0) {
            return true;
        }

        if(support == false){
            this.vetoCard-=1;
        }
        return support;
    }

    /**
     * Vote randomly. The player will be forced to vote support(true) if he is
     * running out of veto card.
     *
     * @return true if the player support it. False if the player reject (veto).
     */
    public boolean voteRandomly() {
        if (this.vetoCard != 0) {
            boolean check = ThreadLocalRandom.current().nextBoolean();   //random use veto card or not
            if(check == false){
                this.vetoCard-=1;  //if use veto card, then veto is count to -1 in total
            }
            return check;

        }
        return true;
    }

    /**
     * Randomly place a character during the placing stage. This method should pick a
     * random unplaced character and place it to a random valid position.
     *
     * @param list The list of all characters. Some of these characters may have been
     *             placed already
     * @return the character that just be placed.
     */
    public Character placeRandomly(Character[] list) {
        int[] totalnumoffloor = new int[7];     //set the array of length to all floor => (floor 0-6)
        for (int i=0; i<list.length; i++) {
            if (list[i].getPosition() != -1) {   //if the character is no in the -1 save the position
                totalnumoffloor[list[i].getPosition()]++;
            }
        }

        Character Char= null;   //set the Char to null by default
        boolean check = false;
        while (check==false) {
            int CharaNumber = ThreadLocalRandom.current().nextInt(0, list.length); // save the random number in a new integer
            Char = list[CharaNumber];
            if (Char.getPosition() == Character.OUT_OF_GAME){   //if the random Character is out of game( position in -1) ,do the next loop
                boolean IsOK = false;
                while (!IsOK) {
                    int pos = ThreadLocalRandom.current().nextInt(1, 5);
                    if (totalnumoffloor[pos]!=Gameboard.FULL) {
                        Char.setPosition(pos);
                        IsOK = true;
                        check = true;
                    }
                }
            }
        }
        return Char;

    }

    /**
     * The player shall vote smartly (i.e., its decision will be based on if the player has that
     * character in his/her list.) If the player is running out of veto card, he/she will be forced
     * to vote support (true).
     *
     * @param character The character that is being vote.
     * @return true if the player support, false if the player reject(veto).
     */
    public boolean voteSmartly(Character character) {

        for (int i = 0; i< myList.length;i++){ //if the character is him/her , then vote yes by default
            if(myList[i].getName().equals(character.getName())){
                return true;
            }
            if(this.vetoCard==0){ //if no veto card, then auto vote yes
                return true;
            }
        }
        return voteRandomly();  //else random veto
    }

    /**
     * The player shall pick a randomly character that is <i>movable</i> during the playing stage.
     * Movable means the character has not yet be killed and the position right above it is not full.
     * <p>
     * Note: this method should not change the position of the character.
     *
     * @param list The entire list of characters
     * @return the character being picked to move. It never returns null.
     */
    public Character pickCharToMoveRandomly(Character[] list) {

        int randomcharacter;

        do {
            randomcharacter = ThreadLocalRandom.current().nextInt(0, list.length );

            for (int i = 0; i < list.length; i++) {  //return the random character which is not killed no unplaced and not the king
                if (list[randomcharacter].getPosition() != Character.OUT_OF_GAME && list[randomcharacter].getPosition() != Gameboard.THRONE){
                    return list[randomcharacter];
                }

        }
        }while (true);
    }

    /**
     * This method return the character who's name is the same as the
     * variable name if the character is <i>movable</i>. Movable means
     * the character has not yet be killed and the position right above
     * it is not full.
     *
     * If the name of the character can't be found from the list or the
     * character is not movable, this method returns null.
     *
     * Note: this method should not change the position of the character.
     *
     * @param list The entire list of characters
     * @param name The name of the character being picked.
     * @return the character being picked to move or null if the character
     *          can't be found or the it is not movable.
     */
    public Character pickCharToMove(Character[] list, String name) {
        int pos = 0;
        int count = 0;
        int character = 0;

        for (int i = 0; i < list.length; i++) {  // to get the upper floor position
            if (list[i].getName().equals(name)) {
                pos = list[i].getPosition() + 1;
                character = i;
            }
        }

        if (pos == Gameboard.THRONE) {  //to be a king
            return list[character];
        }


        for (int i = 0; i < list.length; i++) {
            if (list[i].getPosition() == pos && pos != 0) {
                count++;
            }
        }

        if (pos != 0&& count < 4) {
            return list[character];
        } else {
            return null;
        }
    }



        public boolean checkfill() {   //to check any character in -1
        boolean check = false;
            for (int i = 0; i < myList.length; i++) {
                if (myList[i].getPosition() == -1) {
                    return true;
                }

            }
            return check;
        }


    /**
     * Similar to pickCharToMoveRandomly only as the character being picked to move
     * is related to the secret list of the player. The implementation of this part is
     * open and you are advised to optimize it to increase the chance of winning.
     *
     * Note: this method should not change the position of the character.
     *
     * @param list The list of character
     * @return the character to be move. It never returns null.
     */
    public Character pickCharToMoveSmartly(Character[] list) {
        boolean method = false;


        for(int i = 0; i< myList.length;i++){  //use loop to find out the number of character in the upper floor
            int count = 0 ;
            for(int j = 0; j< list.length;j++){
                if(myList[i].getPosition()+1 == list[j].getPosition()){
                    count++;
                }
            }
            if(count<Gameboard.FULL){   // if the upper floor is not full, turn to true
                method = true;
            }

        }
        if(method){      //if true, use playerlist
           return pickCharToMoveRandomly(myList);
        }else{           //if false, use the public character list
           return pickCharToMoveRandomly(list);
        }

    }

    /**
     * This returns the name of the player and the secret list of the characters.
     * as a string
     * @return The name of the player followed by the secret list of the characters.
     */
    public String toString() {
        String msg = "";
        for (int i = 0; i < myList.length; i++) {
            msg += myList[i].getName()+"["+myList[i].getPosition()+"]"+"\t";
        }

        return msg;
    }
}
