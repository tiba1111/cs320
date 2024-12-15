import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Contact class.
 */
class ContactTest {

    /**
     * Test valid creation of a Contact object.
     */
    @Test
    void testValidContactCreation() {
        // Create a valid contact object
        Contact contact = new Contact("11223", "Tiba", "Alramdan", "0123456789", "886 st ");
        
        // Verify all fields are correctly assigned
        assertEquals("11223", contact.getContactId());
        assertEquals("Tiba", contact.getFirstName());
        assertEquals("Alramdan", contact.getLastName());
        assertEquals("0123456789", contact.getPhone());
        assertEquals("886 st ", contact.getAddress());
    }

    /**
     * Test invalid contact ID scenarios.
     */
    @Test
    void testInvalidContactId() {
        // Invalid contact ID: null
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact(null, "Tiba", "Alramdan", "0123456789", "886 st "));

        // Invalid contact ID: too long
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("12345678901", "Tiba", "Alramdan", "0123456789", "886 st "));
    }

    /**
     * Test invalid first name scenarios.
     */
    @Test
    void testInvalidFirstName() {
        // Invalid first name: null
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", null, "Alramdan", "0123456789", "886 st "));

        // Invalid first name: too long
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", "ThisNameIsTooLong", "Alramdan", "0123456789", "886 st "));
    }

    /**
     * Test invalid phone number scenarios.
     */
    @Test
    void testInvalidPhoneNumber() {
        // Invalid phone number: null
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", "Tiba", "Alramdan", null, "886 st "));

        // Invalid phone number: too short
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", "Tiba", "Alramdan", "123456", "886 st "));

        // Invalid phone number: too long
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", "Tiba", "Alramdan", "123456789012", "886 st "));

        // Invalid phone number: non-numeric characters
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", "Tiba", "Alramdan", "12345abcde", "886 st "));
    }

    /**
     * Test invalid address scenarios.
     */
    @Test
    void testInvalidAddress() {
        // Invalid address: null
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", "Tiba", "Alramdan", "0123456789", null));

        // Invalid address: too long
        assertThrows(IllegalArgumentException.class, 
            () -> new Contact("11223", "Tiba", "Alramdan", "0123456789", 
                "This address is way too long for validation to accept it."));
    }
}

