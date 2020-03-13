package ua.aleksandr.shakespear;

import java.util.Map;

public class View {
     private static final String CHOOSE_SONNET = "Lots of sonnets here...";
     private static final String FROM = "From what sonnet would you like to start?";
     private static final String UNTIL = "What's the ending sonnet?";
     private static final String WRONG = "Wrong input! ";
     private static final String WORD = "Input a word please";
     private static final String NO_MATCH = "The word has not been found";

     private void print(String message){
        System.out.println(message);
    }
     void printChooseSonnet(){
        print(CHOOSE_SONNET);
    }
     void printFromRequest(){print(FROM);}
     void printUntilRequest(){print(UNTIL);}
     void printWrongFormat(){print(WRONG);}
     void printSearchedWordRequest(){print(WORD);}
     void printNoMatches(){print(NO_MATCH);}
     void printResult (Map <String, Integer> map) {
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            print("Link: " + entry.getKey() +
                    ", Frequency of word = " + entry.getValue());
        }
    }
}