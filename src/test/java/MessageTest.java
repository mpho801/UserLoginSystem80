import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testCheckRecipientValid() {
        Message msg = new Message(1, "+27838968976", "Hi");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientInvalid() {
        Message msg = new Message(1, "08966553", "Hi");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testCheckMessageLengthValid() {
        Message msg = new Message(1, "+27838968976", "Hello");
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLengthInvalid() {
        String longMsg = "a".repeat(300);
        Message msg = new Message(1, "+27838968976", longMsg);
        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message(1, "+27838968976", "Test");
        assertEquals(10, msg.getMessageID().length());
    }
}