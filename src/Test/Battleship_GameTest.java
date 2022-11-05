import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import static org.junit.jupiter.api.Assertions.*;

class Battleship_GameTest {
    private InputStream sysInBackup;
    private Battleship_Game aGame;

    @BeforeEach
    void setUp() {
        sysInBackup = System.in; // backup System.in to restore it later
        aGame = new Battleship_Game();
    }

    /* Falls ihr de code mit me andere Input teste wend,
    * schribed en neue Test, wo ihr anderi Koordinate in StringBufferInputStream
    * gebed und kommentiered the test da unne us*/
    @Test
    void setUpGame() {
        InputStream inStream = new StringBufferInputStream("A0,A5 B0,B3 B4,B7 C0,C2 C3,C5 C6,C8 D0,D1 D2,D3 D4,D5 D6,D7");
        System.setIn(inStream);
        aGame.setUpGame();
        assertEquals(1, 1); // This test always passes. I just want to see the printed board in the console.
    }

    @Test
    void playGame() {
    }

    @AfterEach
    void tearDown() {
        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }
}