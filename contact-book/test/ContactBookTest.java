import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ContactBookTest {

    // common object for ContactBook
    static ContactBook contactBook;

    @BeforeAll
    static void setUp() {
        contactBook = new ContactBook();
    }

    @Test
    public void testAddContact() {
        Contact testContact = new Contact("AmazonStudent","1111122334");

        int currentNumOfContacts = contactBook.getNumberOfContacts();

        contactBook.addContact(testContact);

        assertEquals(contactBook.getNumberOfContacts(), currentNumOfContacts+1);

    }

    @ParameterizedTest
    @CsvSource({
            "Alex, 1239292",
            "Taylor, 23939258",
            "Alice, 33939252 ",
            "Clark, 43939251",
            "Toni, 53939257",
            "Casey, 63939258"
    })
    public void testAddContact_onSizeLimitExceed_returnsFalseAndNotException(String name, String phone) {
            Contact testContact = new Contact(name, phone);
            if (contactBook.getNumberOfContacts() >= 5) {
                assertFalse(contactBook.addContact(testContact));
            } else {
                assertTrue(contactBook.addContact(testContact));
            }
    }

    @Test
    public void testAddContact_onSameNumber_ThrowsIllegalArgumentException() {

        Contact testContact1 = new Contact("Alex","11111");
        Contact testContact2 = new Contact("Toni","11111");

        // first contact
        contactBook.addContact(testContact1);

        // second contact with same phone number
        //check if exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            contactBook.addContact(testContact2);
        });

    }


    @Test
    public void testSearchContactByPhone() {

        /** Do the following:
         *          i. create a contact with the name "Toni" and contact number "184048"
         *             and assign it to an object named "testContactForPhone".
         *          ii. add the contact to the contact book using the method "addContact()"
         *         iii. Now call the method searchContactByPhone() with the value "184048" being
         *              passed as a parameter of type String, for the phoneNumber.
         *          iv.	Assert that the object returned is not null.
         *          v.	Assert that the name and phone number of the Contact returned is the same
         *              as the object created having the same name and phone number.
         **/
        Contact testContactForPhone = new Contact("Toni", "184048");
        contactBook.addContact(testContactForPhone);
        Contact found = contactBook.searchContactByPhone("184048");

        assertNotNull(found);
        assertEquals("Toni", found.getName());
        assertEquals("184048", found.getPhoneNumber());

    }

    @Test
    public void testRemoveContactByPhone_found_returnsTrue() {

        Contact testContactForPhone = new Contact("Wills","939503");
        contactBook.addContact(testContactForPhone);

        /** TODO 9: assert that when the method "deleteContactByPhone()"
         *          is called with the phone number 939503, it returns true
         **/
        assertTrue(contactBook.deleteContactByPhone(testContactForPhone.getPhoneNumber()));

    }

    @Test
    public void testRemoveContactByPhone_notFound_throwsIllegalArgumentException() {
        /** TODO 10: put an assertion to check if the method "deleteContactByPhone()" in the
         *           class ContactBook throws an IllegalArgumentException, when
         *            passed a phone number which does not exist in the list of contacts like
         *            923746439503.
         **/
        assertThrows(IllegalArgumentException.class, () -> {
            contactBook.deleteContactByPhone("923746439503");
        });
    }
}

