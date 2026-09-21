import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationTest {

    @Test
    public void testValidUsername() {
        Registration reg = new Registration();
        String username = "mp_ho";
        assertTrue("Username should be valid", reg.validateUsername(username));
    }

    @Test
    public void testInvalidUsername() {
        Registration reg = new Registration();
        String username = "mpho123";
        assertFalse("Username should be invalid", reg.validateUsername(username));
    }

    @Test
    public void testValidPassword() {
        Registration reg = new Registration();
        String password = "Mpho@123";
        assertTrue(reg.validatePassword(password), "Password should meet complexity");
    }

    @Test
    public void testInvalidPassword() {
        Registration reg = new Registration();
        String password = "mpho";
        assertFalse("Password should fail complexity", reg.validatePassword(password));
    }

    @Test
    public void testValidPhoneNumber() {
        Registration reg = new Registration();
        String phone = "+27831234568";
        assertTrue("Phone number should be valid", reg.validatePhone(phone));
    }

    @Test
    public void testInvalidPhoneNumber() {
        Registration reg = new Registration();
        String phone = "0831234568";
        assertFalse("Phone number should be invalid", reg.validatePhone(phone));
    }
}
