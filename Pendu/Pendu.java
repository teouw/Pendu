import java.util.List;
import java.util.ArrayList;
import java.util.Scanner; 

public class Pendu{

    //Fields
    private String hiddenSecretWord;
    private String secretWord;
    private int chances = 6;
    private List<Character> alphabet = new ArrayList<Character>();

    //Constructors
    public Pendu(String sw){
        this.secretWord = sw;
        this.hiddenSecretWord = repeat(sw.length(), "_");
    }

    //Properties
    public String check(String hw, String sw, char l){
        String newHiddenSecretWord = hw;
        if(this.alphabet.contains(l)){
            this.chances -= 1;
            System.out.println(this.hiddenSecretWord);
            return hw;
        }
        else{
            this.alphabet.add(l);
            newHiddenSecretWord = checkCharInString(l);

        }
        return newHiddenSecretWord;
    }

    public Boolean completed(){
        int i = this.hiddenSecretWord.indexOf('_'); //if not return -1
        if (i == -1){
            return true;
        }
        return false;
    }

    //check if the char appears in the secretWord and set the char to the right index in the hiddenSecretWord
    public String checkCharInString(char l){
        StringBuilder newHW = new StringBuilder(this.hiddenSecretWord);
        int i = this.secretWord.indexOf(l);
        if(i == -1){
            this.chances -= 1;
        }
        else{
            while(i >= 0) {
                newHW.setCharAt(i, l);
                i = this.secretWord.indexOf(l, i+1);
            } 
        } 
        this.hiddenSecretWord = newHW.toString();
        System.out.println(newHW.toString());
        return newHW.toString();    
    }


    //Methodes
    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    public static void main(String[] args) {
        boolean completed = false;
        Pendu p = new Pendu("chatc");
        while(p.chances > 0){
            System.out.println("Chances left: " + p.chances);
            System.out.println("Insert letter: ");
            Scanner sc = new Scanner(System.in); 
            char c = sc.next().charAt(0); 

            p.check(p.hiddenSecretWord, p.secretWord, c);
            if(p.completed()){
                completed = true;
                break;
            }
            System.out.println("***********************");
        }
        if(completed){
            System.out.println("you found the secret word: " + p.secretWord);
        }
        else{
            System.out.println("you lost the secret word was: " + p.secretWord);
        }
    }
    


}