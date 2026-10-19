import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    // TEST DATA FROM PAGE 15 & 16
    String validRecipient = "+27718693002";
    String validMessage = "Hi Mike, can you join us for dinner tonight?";
    String invalidRecipient = "08575975889";
    String message2 = "Hi Keegan, did you receive the payment?";

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(validRecipient, validMessage, 0);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "a".repeat(260);
        Message msg = new Message(validRecipient, longMsg, 0);
        String expected = "Message exceeds 250 characters by 10, please reduce the size.";
        assertEquals(expected, msg.checkMessageLength());
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message(validRecipient, validMessage, 0);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message(invalidRecipient, validMessage, 0);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message(validRecipient, validMessage, 0);
        String hash = msg.createMessageHash();
        // Should contain :0: and HI and TONIGHT - Case 1
        assertTrue(hash.contains(":0:"));
        assertTrue(hash.toUpperCase().contains("HI"));
        assertTrue(hash.toUpperCase().contains("TONIGHT"));
        System.out.println("Hash: " + hash);
    }

    @Test
    public void testMessageID() {
        Message msg = new Message(validRecipient, validMessage, 0);
        assertEquals(10, msg.getMessageID().length());
        assertTrue(msg.checkMessageID());
        System.out.println("Message ID generated: " + msg.getMessageID());
    }

    @Test
    public void testMessageHashesInLoop() {
        // Page 16 #4 - test remainder of hashes in loop
        String[] msgs = {validMessage, message2};
        for (int i = 0; i < msgs.length; i++) {
            Message m = new Message(validRecipient, msgs[i], i);
            String hash = m.createMessageHash();
            assertNotNull(hash);
            System.out.println("Message ID generated: " + m.getMessageID() + " Hash: " + hash);
        }
    }

    @Test
    public void testMessageSent() {
        Message msg = new Message(validRecipient, validMessage, 0);
        assertEquals("Message successfully sent.", msg.sendMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sendMessage(2));
        assertEquals("Message successfully stored.", msg.sendMessage(3));
    }
}