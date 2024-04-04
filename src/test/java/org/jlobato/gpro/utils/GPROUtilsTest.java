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
		assertEquals(Integer.valueOf(219871105), GPROUtils.getMoneyAsInt(balance));
		assertEquals(Integer.valueOf(-470691), GPROUtils.getMoneyAsInt(negativeBalance));
	}
	
	@Test
	public void testGetIDSeasonAndRace() {
		assertEquals("67", GPROUtils.getIDSeason("Season 67, Race 16"));
		assertEquals("16", GPROUtils.getIDRace("Season 67, Race 16"));
		assertEquals("72", GPROUtils.getIDSeason("Season 72, Race 13"));
		assertEquals("13", GPROUtils.getIDRace("Season 72, Race 13"));
	}
	
	@Test
	public void testConvertDate() {
		//Oct 11th, 2019
		assertEquals("11/10/2019", GPROUtils.convertDate("Oct 11th, 2019"));
		//Mar 22nd, 2022
		assertEquals("22/03/2022", GPROUtils.convertDate("Mar 22nd, 2022"));
		//Sep 22nd, 2023
		assertEquals("22/09/2023", GPROUtils.convertDate("Sep 22nd, 2023"));
		//Jul 1st, 1972
		assertEquals("01/07/1972", GPROUtils.convertDate("Jul 1st, 1972"));
		//May 3rd, 2019
		assertEquals("03/05/2019", GPROUtils.convertDate("May 3rd, 2019"));
		//Mar 7th, 2023
		assertEquals("07/03/2023", GPROUtils.convertDate("Mar 7th, 2023"));
		//Mar 1st, 2016
		assertEquals("01/03/2016", GPROUtils.convertDate("Mar 1st, 2016"));
	}

}
