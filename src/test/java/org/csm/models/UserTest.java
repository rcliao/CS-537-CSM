package org.csm.models;

import junit.framework.Assert;
import org.junit.Test;

public class UserTest {
	@Test
	public void testUsername() {
		// create a dummy user to test the assert function
		User testUser = new User("eliao");

		Assert.assertEquals(testUser.getUsername(), "eliao");
	}
}