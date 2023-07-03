import java.util.Scanner;

/**
 * The class Gameboard is to print control the flow of the game and
 * contains a set of players and characters.
 */
public class Gameboard {

    /**
     * The maximum number of characters can be placed in the same position.
     */
    public static final int FULL = 4;
    /**
     * The total number of characters
     */
    public static final int NO_OF_CHARACTER = 13;
    /**
     * The total number of player
     */
    public static final int NO_OF_PLAYERS = 4;
    /**
     * The position of Throne
     */
    public static final int THRONE = 6;
    /**
     * The scores calculation formula
     */
    public static final int[] SCORES = {0, 1, 2, 3, 4, 5, 10};
    /**
     * The name of the characters
     */
    public static final String[] CHARACTER_NAMES = {
            "Aligliero", "Beatrice", "Clemence", "Dario",
            "Ernesto", "Forello", "Gavino", "Irima",
            "Leonardo", "Merlino", "Natale", "Odessa", "Piero"
    };
    /**
     * The name of the players
     */
    public static final String[] PLAYER_NAMES = {
        "You", "Computer 1", "Computer 2", "Computer 3"
    };
    /**
     * Determine if the players are human player or not.
     */
    public static final boolean[] HUMAN_PLAYERS = {
        true, false, false, false
    };
    /**
     * A list of character
     */
    private Character[] characters;
    /**
     * A list of player
     */
    private Player[] players;


    public static void main(String[] argv) {
        new Gameboard().runOnce();
    }

    /**
     * Initialize all data attributes. You will need to initialize and create
     * the array players and characters. You should initialize them using the
     * String array PLAYER_NAMES and CHARACTER_NAMES respectively.
     */
    public Gameboard() {

        characters = new Character[NO_OF_CHARACTER];      // the all chatacter inside this list
        for (int i = 0; i < NO_OF_CHARACTER; i++) {
            characters[i] = new Character(CHARACTER_NAMES[i]);
            characters[i].setPosition(-1);

        }
            players = new Player[NO_OF_PLAYERS];                // the all players inside this list
            for (int j = 0; j < NO_OF_PLAYERS; j++) {
                players[j] = new Player(PLAYER_NAMES[j],characters);
                players[j].initVetoCard(3);         //set the veto card is 3 by default
            }


    }
    /**
     * The main logic of the game. This part has been done for you.
     */
    public void runOnce() {

        print();
        System.out.println("======= Placing stage ======= \n"
                + "Each player will take turns to place three characters on the board.\n"
                + "No character can be placed in the position 0 or 5 or 6 (Throne) at this stage.\n"
                + "A position is FULL when there are four characters placed there already.\n"
                + "The remaining character will be placed at the position 0.\n");

        placingStage();

        print();
        System.out.println("======= Playing stage ======= \n"
                + "Each player will take turn to move a character UP the board.\n"
                + "You cannot move a character that is been killed or its immediate upper position is full.\n"
                + "A voting will be trigger immediately when a character is moved to the Throne (position 6).");

        playingStage();

        print();
        System.out.println("======= Scoring stage ======= \n"
                + "This will trigger if and only if the voting result is ALL positive, i.e., no player play the veto (reject) card. \n"
                + "The score of each player is computed by the secret list of characters owned by each player.");

        scoringStage();
    }


    /**
     * Print the scores of all players correctly. This part has been done
     * for you.
     */
    private void scoringStage() {
        for (Player p : players) {
            System.out.println(p.getName()+"      Veto Card :"+p.getVeto() ); //get the number of veto card
            System.out.println(p);
            System.out.println("Score: " + p.getScore()); //get the score for the player, which is counted in getScore method
        }
        int player = 0;
        int max = 0;
        for(int i =0; i< players.length;i++){ //this is a additional thing, to find out the winner
            if(players[i].getScore()>max){
                max = players[i].getScore();
                player = i;
            }
        }

        System.out.println("\n\nThe Winner is "+players[player].getName());
    }

    /**
     * Perform the placing stage. You have to be careful that human player will need to chosen on what to place
     * Non-human player will need to place it using the method placeRandomly (see Player.java)
     */
    private void placingStage() {
        //loop until 12 of the characters have been placed 
        //and place the last character at position 0


        int num=0;
        int floor = 0;
        int characterInt = 0;
        int count = 0;
        int []save= new int[characters.length];
        for (int i = 0; i < characters.length; i++) {
            save[i] = characters[i].getPosition();
        }
        Scanner sc = new Scanner(System.in);



        print();
        for(int i=0; i<10;i++) {
            for (int j = 0; j < HUMAN_PLAYERS.length; j++) {

                num=0;
                for(int y=0;y<characters.length;y++){  //count how many character in position of -1
                    if(characters[y].getPosition()==-1){
                        num++;
                    }
                }
                if(num==1) {
                    for (int y = 0; y < characters.length; y++) {   // if it is last character is in position of -1, set it to 0 position
                        if (characters[y].getPosition() == -1) {
                            characters[y].setPosition(0);
                        }
                    }
                }
                skip:
                if (HUMAN_PLAYERS[j]) {
                    outer:
                    while (true) { //if is player


                       if(players[j].checkfill() == false && HUMAN_PLAYERS[j] == true){ //if fill full then skip this selection step
                            break skip;
                       }

                        System.out.println(players[j].getName()+"      Veto Card :"+players[j].getVeto() );
                        System.out.println(players[j]+"      , this is your turn to place a character");
                        System.out.println("Please pick a character ");
                        String character = sc.next();
                        for (int k = 0; k < characters.length; k++) {
                            if (character.equals(characters[k].getName()) &&  characters[k].getPosition() == -1) {

                                characterInt = k;
                                break outer;
                            }

                        }
                    }
                        outer1:

                        while(true) {
                            count=0;
                            System.out.println("Please enter the floor you want to place");
                            floor = sc.nextInt();
                            if (floor > 0 && floor < 5) {    //make sure the input is 1 to 4
                                for (int k = 0; k < characters.length; k++) {
                                    if (floor == characters[k].getPosition()) {
                                        count++;
                                    }
                                }
                                if(count<4){
                                    characters[characterInt].setPosition(floor);
                                    print();
                                    break outer1 ;
                                }
                            }



                }
                }
                 else { //if is computer
                    if(players[j].checkfill() == false && HUMAN_PLAYERS[j] == false){ //if fill full then skip this selection step
                        break skip;
                    }
                    System.out.println(players[j].getName()+"      Veto Card :"+players[j].getVeto() );
                    System.out.println(players[j]+"      , this is your turn to place a character");
                   players[j].placeRandomly(characters);
                    print();
                }


            }
        }
        for(int e=0;e<characters.length;e++){   //set position 0 for the last characters
            if(characters[e].getPosition() == -1){
                characters[e].setPosition(0);
            }
        }
        }



    /**
     * Perform playing stage. Be careful that human player will need to pick the character to move.
     * You should detect any invalid input and stop human player from doing something nonsense or illegal.
     * Computer players will need to run the code pickCharToMoveRandomly or pickCharToMoveSmartly to pick which character to move.
     */
    private void playingStage() {

        //loop until a character has been voted for the new King.
        print();
        Scanner scanner = new Scanner(System.in);
        String Char;
        int pos=0;
        outerloop:
        while (true) {
            for (int j = 0; j < HUMAN_PLAYERS.length; j++) {
                if (HUMAN_PLAYERS[j]) { //check the player is computer or user
                    int count = 0;

                    do {

                    System.out.println(players[j].getName() + "      Veto Card :" + players[j].getVeto());
                    System.out.println(players[j]);
                    System.out.println("This is your turn to move a character up");
                    System.out.println();
                    System.out.println("Please type the character that you want to move.");

                        Char = scanner.next();
                        players[j].pickCharToMove(characters, Char);  //call pickCharToMove

                    } while (players[j].pickCharToMove(characters, Char) == null);

                    for(int i = 0;i< characters.length;i++){  //set new position if valid
                        if(characters[i].getName().equals(Char)){
                            characters[i].setPosition(characters[i].getPosition() + 1);
                        }
                    }
                    for (int i = 0; i < characters.length; i++) { //check be a king or not
                        if (characters[i].getPosition() == 6) {
                            boolean checkveto = true;
                            for (int y = 0; y < HUMAN_PLAYERS.length; y++) {
                                if(HUMAN_PLAYERS[y]){
                                    System.out.println("Please vote. Type V for veto. Other for accept");
                                    String vote = scanner.next();
                                    if(vote.equals("V")){
                                        players[y].vote(false);
                                        checkveto = false;
                                    }else{
                                        players[y].vote(true);
                                    }
                                }else{
                                    if(players[y].voteSmartly(characters[i])==false){
                                        checkveto = false;
                                    }
                                }

                            }
                            if(checkveto ==true){   //if someone vote veto, set position to -1
                                break outerloop;
                            }else{
                                characters[i].setPosition(-1);
                            }
                        }
                    }


                    print();
                } else {
                    System.out.println(players[j].getName() + "      Veto Card :" + players[j].getVeto());
                    System.out.println(players[j]);
                    System.out.println("This is your turn to move a character up");

                    int countpos=4;
                    while(countpos>=4){
                        boolean Check = false;
                         countpos=0;
                        int place ;
                    Char = players[j].pickCharToMoveRandomly(characters).getName();
                    for (int i = 0; i < characters.length; i++) {  //set new position if valid
                        if (characters[i].getName().equals(Char)) {
                             place = i;

                            int[] save = new int[characters.length];
                            for (int a = 0; a < characters.length; a++) {
                                save[a] = characters[a].getPosition();
                            }
                            for (int f = 0; f < save.length; f++) {
                                if (characters[place].getPosition() + 1 == save[f]) {
                                    countpos++;
                                }
                            }
                            if(countpos <4){
                                Check = true;
                            }
                        }

                        }
                    if(Check == true){
                        for (int i = 0; i < characters.length; i++) {  //set new position if valid
                            if (characters[i].getName().equals(Char)) {
                                characters[i].setPosition(characters[i].getPosition() + 1);
                            }}

                    }
                    }



                    print();
                    for (int i = 0; i < characters.length; i++) { //check be a king or not
                        if (characters[i].getPosition() == 6) {
                            boolean checkveto = true;
                            for (int y = 0; y < HUMAN_PLAYERS.length; y++) {
                                if(HUMAN_PLAYERS[y]){
                                    System.out.println("Please vote. Type V for veto. Other for accept");
                                    String vote = scanner.next();
                                    if(vote.equals("V")){
                                        players[y].vote(false);
                                        checkveto = false;
                                    }else{
                                        players[y].vote(true);
                                    }
                                }else{
                                    if(players[y].voteRandomly()==false){
                                        checkveto = false;
                                    }
                                }

                            }
                            if(checkveto ==true){   //if all someone vote veto, set position to -1
                                break outerloop;
                            }else{
                                characters[i].setPosition(-1);
                            }
                        }
                    }
                }
                }
        }

    }


    /**
     * Print the gameboard. Please see the assignment webpage or the demo program for
     * the format. You should call this method after a character has been moved or placed or killed.
     */
    private void print() {
        //used for loop to print out all of the floor
    String msg="";
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==6){
                msg+=characters[i]+"\t";
            }
        }
        System.out.println("Level 6 "+msg);

         msg="";
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==5){
                msg+=characters[i]+"\t";
            }
        }
        System.out.println("Level 5 "+msg);


         msg="";
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==4){
                msg+=characters[i]+"\t";
            }
        }
        System.out.println("Level 4 "+msg);



        msg="";
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==3){
                msg+=characters[i]+"\t";
            }
        }
        System.out.println("Level 3 "+msg);



        msg="";
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==2){
                msg+=characters[i]+"\t";
            }
        }
        System.out.println("Level 2 "+msg);


        msg="";
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==1){
                msg+=characters[i]+"\t";
            }
        }
        System.out.println("Level 1 "+msg);


        msg="";
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==0){
                msg+=characters[i]+"\t";
            }
        }
        System.out.println("Level 0 "+msg);


        System.out.println("Unplaced/Killed Characters"); //the character in position of -1(killed or unplaced)
        for(int i = 0; i<characters.length;i++){
            if(characters[i].getPosition()==-1){
                System.out.printf("%15s",characters[i]+" ");

            }
        }System.out.println("\n");
    }

}
