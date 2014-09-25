package com.hangman.players;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YourPlayerTest {
    YourPlayer player;
    ArrayList<Character> theList;

    @Before
    public void setup() {
        player = new YourPlayer();
        theList = new ArrayList<Character>();
    }

    @Test
    public void GetsNewGuessEachTime() throws Exception {
        for (int xctr = 0; xctr < 26; xctr++) {
            theList.add(new Character('_'));
        }

        char guess = ' ';

        for (int xctr = 0; xctr < 26; xctr++) {
            guess = player.GetGuess(theList);

            for (Character inList : theList) {

                assertTrue(inList.charValue() != guess);
            }

            for (int yctr = 0; yctr < 26; yctr++) {
                Character theChar = theList.get(yctr);
                if (theChar.charValue() == '_') {
                    theList.set(yctr, guess);
                    break;
                }
            }
        }

    }

    @Test
    public void testFindFirstUnusedLetter() throws Exception {
        String testStr = "abcdef";
        for (int xctr = 0; xctr < testStr.length(); xctr++) {
            theList.add(testStr.charAt(xctr));
        }

        assertEquals('g', player.findFirstUnusedLetter(theList, "abcdefg"));
    }

    @Test
    public void foundCharCountTest() throws Exception {
        String testStr = "abcdef";
        for (int xctr = 0; xctr < 20; xctr++) {
            if (xctr < testStr.length()) {
                theList.add(testStr.charAt(xctr));
            }
            else {
                theList.add('_');
            }
        }

        assertEquals(6, player.foundCharCount(theList));
    }
}
