package com.hangman.players;
import com.hangman.Player;

import java.util.List;
import java.util.Random;

public class YourPlayer implements Player {
    private Random rand = new Random(System.currentTimeMillis());
    private StringBuffer previousGuesses = new StringBuffer();

    private String[] orders = {
            "aibcdefghijklmnopqrstuvwxyz",
            "aoeimhnustyblpxdfrwgjk",
            "aeoitsuprndbgmylhwfckxvjzq",
            "aesoirltnudpmhcbkgywfvjzxq",
            "searoiltnudcypmhgbkfwvzxjq",
            "esariolntducmpghbykfwvzxjq",
            "esiarntolducgpmhbyfkwvzxjq",
            "esiarntoldcugmphbyfkwvzxqj",
            "esirantolcdugmphbyfvkwzxqj",
            "eisrantolcdugmphbyfvkwzxqj",
            "eisnartolcudpmghbyfvkwzxqj",
            "eisntarolcpumdghybvfzkwxqj",
            "ientsaorlcpumgdhybvfzxkwqj",
            "ietsnaorlcpumdhgybvfzxkwqj",
            "ietnsoarlcpumdhgybvfzxwkqj",
            "ietsnaorlcpumhdygbvfzxwqkj",
            "ietnsoarlcpumdhgybvfzxwkqj",
            "isetonralcpmuhdgybvzfxqwk",
            "ietonasrlcpmuhdgybvfzxkjqw",
            "ioetrsanclphumydgbzvfkxjq"
    };

    String[] probBias = {
            "ai",
            "aoeiumbh",
            "aeoiuyhbck",
            "aeoiuysbf",
            "seaoiuyh",
            "eaiousy",
            "eiaous",
            "eiaou",
            "eiaou",
            "eioau",
            "eioad",
            "eioaf",
            "ieoa",
            "ieo",
            "iea",
            "ieh",
            "ier",
            "iea",
            "iea",
            "ie"
    };



    @Override
    public char GetGuess(List<Character> clue) {
        String possibleChars = "etaoinshrdlcumwfgypbvkjxqz";
        int listSize = clue.size();
        int foundChars = foundCharCount(clue);

        StringBuffer charSet = new StringBuffer((listSize > 0 && listSize <= 20 ? orders[listSize - 1] : possibleChars));

        for (int xctr = 0; xctr < previousGuesses.length(); xctr++) {
            String charStr = Character.toString(previousGuesses.charAt(xctr));
            int index = charSet.indexOf(charStr);
            if (index != -1) {
                charSet.deleteCharAt(index);
            }
        }

        boolean keepGoing = true;
        char retVal = '_';

        while(keepGoing) {
            char nextChar = findFirstUnusedLetter(clue, charSet.toString());

            if (nextChar != '_') {
                retVal = nextChar;
                previousGuesses.append(nextChar);
                break;
            }
        }
        //System.out.println("GetGuess: " + new Character(retVal).toString());
        return(retVal);
    }

    public char findFirstUnusedLetter(List<Character> clue, String chars) {
        char retVal = '_';
        boolean foundChar = false;

        for (int xctr = 0; xctr < chars.length(); xctr++) {
            char compChar = chars.charAt(xctr);
            foundChar = false;

            for (Character listChar : clue) {
                if (listChar.charValue() == compChar) {
                    foundChar = true;
                    break;
                }
            }

            if (!foundChar) {
                retVal = compChar;
                break;
            }
        }

        return(retVal);
    }

    public int foundCharCount(List<Character> theList) {
        int charCtr = 0;

        for (Character theChar : theList) {
            if (theChar.charValue() != '_') {
                charCtr++;
            }
        }
        return charCtr;
    }
}
