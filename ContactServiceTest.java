import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ContactService class.
 */
class ContactServiceTest {

    /**
     * Test adding a new contact successfully.
     */
    @Test
    void testAddContact() {
        ContactService contactService = new ContactService();

        // Create and add a valid contact
        Contact contact = new Contact("12345", "Tiba", "Alramdan", "0123456789", "886 st ");
        contactService.addContact(contact);

        // Verify contact is added
        assertNotNull(contactService.getContact("12345"));
        assertEquals("Tiba", contactService.getContact("12345").getFirstName());
    }

    /**
     * Test adding a contact with a duplicate ID.
     */
    @Test
    void testAddDuplicateContactId() {
        ContactService contactService = new ContactService();

        // Add the first contact
        Contact contact1 = new Contact("12345", "Tiba", "Alramdan", "0123456789", "886 st ");
        contactService.addContact(contact1);

        // Attempt to add another contact with the same ID
        Contact contact2 = new Contact("12345", "John", "Doe", "0987654321", "456 Oak St");
        assertThrows(IllegalArgumentException.class, () -> contactService.addContact(contact2));
    }

    /**
     * Test deleting a contact successfully.
     */
    @Test
    void testDeleteContact() {
        ContactService contactService = new ContactService();

        // Add and then delete a contact
        Contact contact = new Contact("12345", "Tiba", "Alramdan", "0123456789", "886 st ");
        contactService.addContact(contact);
        contactService.deleteContact("12345");

        // Verify contact is deleted
        assertNull(contactService.getContact("12345"));
    }

    /**
     * Test deleting a contact that does not exist.
     */
    @Test
    void testDeleteNonexistentContact() {
        ContactService contactService = new ContactService();

        // Attempt to delete a contact that isn't there
        assertThrows(IllegalArgumentException.class, () -> contactService.deleteContact("99999"));
    }

    /**
     * Test updating contact fields successfully.
     */
    @Test
    void testUpdateContact() {
        ContactService contactService = new ContactService();

        // Add a contact and update fields
        Contact contact = new Contact("12345", "Tiba", "Alramdan", "0123456789", "886 st ");
        contactService.addContact(contact);

        contactService.updateContact("12345", "Jane", "Smith", "0987654321", "123 New St");

        // Verify updates
        Contact updatedContact = contactService.getContact("12345");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Smith", updatedContact.getLastName());
        assertEquals("0987654321", updatedContact.getPhone());
        assertEquals("123 New St", updatedContact.getAddress());
    }

    /**
     * Test updating a contact that does not exist.
     */
    @Test
    void testUpdateNonexistentContact() {
        ContactService contactService = new ContactService();

        // Attempt to update a contact that doesn't exist
        assertThrows(IllegalArgumentException.class, 
            () -> contactService.updateContact("99999", "Jane", "Smith", "0987654321", "123 New St"));
    }

    /**
     * Test updating a contact with invalid field values.
     */
    @Test
    void testUpdateContactInvalidValues() {
        ContactService contactService = new ContactService();

        // Add a valid contact
        Contact contact = new Contact("12345", "Tiba", "Alramdan", "0123456789", "886 st ");
        contactService.addContact(contact);

        // Attempt to update with invalid values
        assertThrows(IllegalArgumentException.class, 
            () -> contactService.updateContact("12345", null, "Smith", "0987654321", "123 New St"));
        assertThrows(IllegalArgumentException.class, 
            () -> contactService.updateContact("12345", "Jane", null, "0987654321", "123 New St"));
        assertThrows(IllegalArgumentException.class, 
            () -> contactService.updateContact("12345", "Jane", "Smith", "invalidphone", "123 New St"));
        assertThrows(IllegalArgumentException.class, 
            () -> contactService.updateContact("12345", "Jane", "Smith", "0987654321", null));
    }
}
