
public class ContactBook {

    // total contacts in the Contact book
    private static final int MAX_CONTACTS = 5;

    // array to hold the contacts
    private Contact[] contacts;

    // counter to track number of contacts currently stored in array
    private int numberOfContacts;


    public ContactBook() {
        // no contacts are stored as yet
        // -1 because there are no elements
        numberOfContacts = 0;

        // allocate memory for the array
        contacts = new Contact[MAX_CONTACTS];
    }

    // returns the number of contacts
    public int getNumberOfContacts() {
        // this is used to track the number or entries made in the array.
        // if we use contacts.length, it will always return the size of
        // the array and not number of items
        return  numberOfContacts;
    }


    // returns true if contact with same number already exists
    public boolean contactWithSameNumberExists(Contact contact) {
        boolean contactExists = false;

        for(int i=1; i<=numberOfContacts; i++) {
            if(contacts[i-1].getPhoneNumber().equalsIgnoreCase(contact.getPhoneNumber())) {
                contactExists = true;
                break;
            }
        }

        return contactExists;
    }

    /** add a method "addContact()" which accepts a
     *          parameter of type "Contact" and returns
     *          true or false. Return the default
     *          value true
     **/
    public boolean addContact(Contact contact) {
        if (numberOfContacts + 1 <= MAX_CONTACTS) {
            if (contactWithSameNumberExists(contact)) {
                throw new IllegalArgumentException("Phone number already exists.");
            }
            contacts[numberOfContacts++] = contact;
            return true;
        }

        return false;
    }

    public Contact searchContactByPhone(String phoneNumber) {
        /** TODO 8: Add the code to loop through the existing contact object in the
         *         contacts array from the beginning. Each time check if the phone number is same.
         *         If it is return the contact and break the loop.
         *         Otherwise return the default contact object which is returnContact.
         *              Hint:  Look at method contactWithSameNumberExists()  which contains similar logic.
         **/
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }

    public boolean deleteContactByPhone(String phoneNumber) {
        boolean recordFound = false;

        for(int i=1; i<=numberOfContacts; i++) {
            if(contacts[i-1].getPhoneNumber().equalsIgnoreCase(phoneNumber)) {

                System.arraycopy(contacts, i, contacts, i, numberOfContacts - i);
                contacts[--numberOfContacts] = null;

                recordFound = true;
                return recordFound;
            }
        }

        /** TODO 11: replace the return statement with a "throw" statement
         *           to throw an IllegalArgumentException with any message
         *           you like to indicate the record was not found.
          **/
      throw new IllegalArgumentException("Record not found to delete.");

    }
}
