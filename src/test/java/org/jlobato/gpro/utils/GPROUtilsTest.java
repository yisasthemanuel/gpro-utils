package org.jlobato.gpro.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GPROUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetIDManagerFromLink() {
		assertEquals("113612", GPROUtils.getIDManagerFromLink("ManagerProfile.asp?IDM=113612"));
	}
	
	@Test
	public void testCategoryAndGroup() {
		String group = "M-1";
		assertEquals("M", GPROUtils.getCategoryCode(group));
		assertEquals("1", GPROUtils.getGroupId(group));
		
		group = "Elite";
		assertEquals("E", GPROUtils.getCategoryCode(group));
		assertNull(GPROUtils.getGroupId(group));
	}
	
	@Test
	public void testDriverEnergy() {
		//Test driver energy
		String driverEnergyExample = "98% > 90%";
		assertEquals(98, GPROUtils.getDriverEnergyAtStart(driverEnergyExample));
		assertEquals(90, GPROUtils.getDriverEnergyAtEnd(driverEnergyExample));
	}
	
	@Test
	public void testRomanNumeral() {
	    assertEquals("MMMMCMXCIX", RomanNumeral.toRoman(4999));
	    assertEquals("CMXCIX", RomanNumeral.toRoman(999));
	    assertEquals("CMLXXXIX", RomanNumeral.toRoman(989));
	    assertEquals("DCXXVI", RomanNumeral.toRoman(626));
	    assertEquals("DCXXIV", RomanNumeral.toRoman(624));
	    assertEquals("CDXCVIII", RomanNumeral.toRoman(498));
	    assertEquals("CXXIII", RomanNumeral.toRoman(123));
	    assertEquals("XCIX", RomanNumeral.toRoman(99));
	    assertEquals("LI", RomanNumeral.toRoman(51));
	    assertEquals("XLIX", RomanNumeral.toRoman(49));
	    //Mis tests especiales
	    assertEquals("MCMLXXVI", RomanNumeral.toRoman(1976));
	    assertEquals("MCMLXXII", RomanNumeral.toRoman(1972));
	}
	
	@Test
	public void testMoney() {
		String balance = "$219.871.105";
		String negativeBalance = "$-470.691";
		assertEquals(new Integer(219871105), GPROUtils.getMoneyAsInt(balance));
		assertEquals(new Integer(-470691), GPROUtils.getMoneyAsInt(negativeBalance));
	}

}
