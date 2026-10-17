import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

    @Test
    public void testCheckUserNameValid() {
        Registration reg = new Registration();
        assertTrue(reg.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameInvalid() {
        Registration reg = new Registration();
        assertFalse(reg.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        Registration reg = new Registration();
        assertTrue(reg.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        Registration reg = new Registration();
        assertFalse(reg.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneValid() {
        Registration reg = new Registration();
        assertTrue(reg.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneInvalid() {
        Registration reg = new Registration();
        assertFalse(reg.checkCellPhoneNumber("08966553"));
    }
}