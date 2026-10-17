import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message("0718693002", "Test", 0);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi", 0);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageHashFormat() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        String hash = msg.createMessageHash();
        assertTrue(hash.contains(":"));
        assertTrue(hash.toUpperCase().equals(hash)); // Must be uppercase
    }

    @Test
    public void testSendMessage() {
        Message msg = new Message("+27718693002", "Test", 0);
        assertEquals("Message successfully sent.", msg.sendMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sendMessage(2));
        assertEquals("Message successfully stored.", msg.sendMessage(3));
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message("+27718693002", "Test", 0);
        assertTrue(msg.getMessageID().length() <= 10);
    }
}