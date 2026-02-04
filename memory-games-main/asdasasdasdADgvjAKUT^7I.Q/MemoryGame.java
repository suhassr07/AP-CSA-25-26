/**
 * Project 3.1.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
//extra credit implimented: difficulty levels and speed increase over time




import javax.swing.JOptionPane;

public class MemoryGame
{
   // Difficulty arrays
   static String mem[] = {"a","b","c"}; //difficulty 1 (easy mode)
   static String mem2[] = {"a","b","c","d"}; //difficulty 2 (medium mode)
   static String mem3[] = {"a","b","c","d","e"}; //difficulty 3 (hard mode)
   
   public static String[] getDifficultyArray(String difficulty){ //difficulty selection logic. returns reference to array from input
     if(difficulty.equals("Easy")){
       return mem;
     }
     else if(difficulty.equals("Hard")){
       return mem3;
     }
     else{ 
       return mem2; 
     }
   }
   
   public static void main(String[] args) {

 
boolean match=false;


    MemoryGameGUI game = new MemoryGameGUI();
    String[] difficultyOptions = {"Easy", "Medium", "Hard"}; //difficulty selector
    int choice = JOptionPane.showOptionDialog(null, "Select Difficulty:", "Difficulty", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, difficultyOptions, "Medium"); //show option dialog for difficulty selection
    String difficulty = difficultyOptions[choice]; //set difficulty string based on user choice
    game.createBoard(3, true);
    int score = 0;
    int rounds = 1;
    double time = .5; // Initial speed time
    
    // TO DO: Play the game until user wants to quit.
  while(true){
   String temp[] = RandomPermutation.next(getDifficultyArray(difficulty)); //get random permutation of memory strings based on difficulty
   String seq = "";
   for(int i=0; i < temp.length; i++){
    seq+=temp[i];
   }
        // TO DO: Call the next method in RandomPermutation to create a random version 
        // of the "memory strings"
  
        // TO DO: Play one sequence with a .5 second delay. Save the player's guess. 
        // (Later, you can speed up or slow down the game.)
        String guess = game.playSequence(temp, time); //time thingy

        // TO DO: If the guess is not null, determine a match
        if(guess!=null){ //only do if guess is not null
      
          guess = guess.replaceAll(", ", ""); //replace comma and space with nothing
          for(int i=0; i < temp.length; i++){ //iterate over each array element
            if(guess.equals(seq)){ //set match to true if guess is the same as an array element
              match = true;
            }
              
          }
        }

            if (match) //execute if match is correct
            {
              match = false; //set match back to false
              game.matched(); 
              score++; // increase score
             } 
            else
            {
              game.tryAgain();
              
              }

        // TO DO: Ask if user wants to play another round of the game 
        // and track the number of games played.
              game.showScore(score, rounds);
        
              if (game.playAgain())
              {
                rounds++; // new rounds
                time *= 0.9; //decrease time over time
               }
              else
              {
                game.quit(); // end game
               }
            }
    // When done playing, show score and end the game.
  }
}
