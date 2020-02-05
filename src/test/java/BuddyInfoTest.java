

import com.myApp.model.BuddyInfo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


public class BuddyInfoTest {
	private BuddyInfo aBudInfo;
	private int age;
	
	@BeforeEach
	public void setUp() {
		age = 20;
		aBudInfo = new BuddyInfo("Name", "Location", "Number", age);
	}

	@Test
	public void testBuddyInfoConstructor() {
		assertTrue("", aBudInfo.equals(new BuddyInfo(aBudInfo)));
	}
	
	@Test
	public void testBuddyInfoGreeting() {
		String actualGreeting = aBudInfo.getGreeting();
		assertEquals("Hello", actualGreeting);
	}
	
	@Test
	public void testBuddyInfoAge() {
		assertEquals(age, aBudInfo.getAge());
	}
	@Test
	public void testBuddyInfoIsOver18() {
		assertTrue("", aBudInfo.isOver18());
	}


	@Test
	public void getName() {
		assertEquals("Name", aBudInfo.getName());
	}

	@Test
	public void setName() {
		aBudInfo.setName("SettingName");
		assertEquals("SettingName", aBudInfo.getName());
	}

	@Test
	public void getAddress() {
		assertEquals("Location", aBudInfo.getAddress());
	}

	@Test
	public void setAddress() {
		aBudInfo.setAddress("SettingLocation");
		assertEquals("SettingLocation", aBudInfo.getAddress());
	}

	@Test
	public void getPhoneNumber() {
		assertEquals("Number", aBudInfo.getPhoneNumber());
	}

	@Test
	public void setPhoneNumber() {
		aBudInfo.setPhoneNumber("SettingPhone");
		assertEquals("SettingPhone", aBudInfo.getPhoneNumber());
	}

	@Test
	public void setAge() {
		aBudInfo.setAge(99);
		assertEquals(99, aBudInfo.getAge());
	}

	@Test
	public void getAge() {
		assertEquals(20, aBudInfo.getAge());
	}
}
