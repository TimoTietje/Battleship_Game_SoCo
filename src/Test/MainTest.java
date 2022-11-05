import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private InputStream sysInBackup;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        sysInBackup = System.in; // backup System.in to restore it later
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }
}