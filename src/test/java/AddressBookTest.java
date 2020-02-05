
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.myApp.model.AddressBook;
import com.myApp.model.BuddyInfo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

public class AddressBookTest {
    private AddressBook book;

    @BeforeAll
    public void setUp() {
        book = new AddressBook();
    }

    @Test
    public void testEmptyAddressBook() {
        assertEquals("", 0, book.size());
    }

    @Test
    public void testAddressBookSizeWithTwo() {
        book.addBuddy(new BuddyInfo("name", "location", "phone", 19));
        book.addBuddy(new BuddyInfo("name2", "location2", "phone2", 17));
        assertEquals("", 2, book.size());
    }

    @Test
    public void testClearedAddressBook() {
        book.addBuddy(new BuddyInfo("name3", "location3", "phone3", 19));
        assertTrue("", book.clear());

    }

}
