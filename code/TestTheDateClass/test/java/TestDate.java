
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestDate {

    /**
     * Test the creation of a Date with empty Constructor
     * Init : create new Date without parameters
     * Expected : must return the 1/1/2000
     */
    @Test
    @DisplayName("Test Date empty constructeur")
    public void testEmtpyConstructor() {
        Date theDateObject = new Date();
        assertEquals(1, theDateObject.getDay());
        assertEquals(1, theDateObject.getMonth());
        assertEquals(2000, theDateObject.getYear());
    }

    /**
     * Test the creation of a Date with correct inputs.
     * Init : create new Date with correct day, month and year.
     * Expected : day, month and year must be equal to the inputs
     */
    @Test
    @DisplayName("Test Date constructor with correct dates")
    public void testCorrectDateConstructor() throws WrongDateException {
        Date testDate = new Date(12, 9, 2020);
        assertEquals(12, testDate.getDay());
        assertEquals(9, testDate.getMonth());
        assertEquals(2020, testDate.getYear());
    }

    /**
     * Test the creation of a Date with incorrect inputs.
     * Expected : wrong date inputs must throw a WrongDateException
     */
    @Test
    @DisplayName("Test Date constructor with incorrect inputs")
    public void testIncorrectDateConstructor() {
        assertThrows(WrongDateException.class, () -> new Date(32, 12, 2029));
    }

    /**
     * Test the isValidDate method with correct inputs
     * Expected : isValidDate gives true for correct dates
     * @param day   int day of the date to be tested
     * @param month int month of the date to be tested
     * @param year  int year of the date to be tested
     */
    @ParameterizedTest
    @CsvSource({
            // day, month, year, isValidDate
            "1, 5, 2008",     // P1 -> B1
            "1, 5, 1996",     // P1 -> B1
            "29, 2, 2008",    // P1 -> P3 -> B2
            "29, 6, 2007",    // P1 -> B4
    })
    @DisplayName("Test method validDate with correct inputs")
    public void testValidDate(int day, int month, int year) {
        assertTrue(Date.isValidDate(day, month, year));
    }

    /**
     * Test the isValidDate method with incorrect inputs
     * Expected : isValidDate gives false for incorrect dates
     * @param day   int day of the date to be tested
     * @param month int month of the date to be tested
     * @param year  int year of the date to be tested
     */
    @ParameterizedTest
    @CsvSource({
            // day, month, year, isValidDate
            "0, 30, 56",     // P1 -> B6
            "-1, 5, 1326",   // P1 -> B1
            "40, 2, 2008",   // P1 -> P3 -> B2
            "30, 2, 2008",   // P1 -> P3 -> B2
            "29, 2, 2007",   // P1 -> P3 -> B3
            "-1, 6, 2007",   // P1 -> B4
            "31, 6, 2007",   // P1 -> B4
    })
    @DisplayName("Test method validDate with incorrect inputs")
    public void testInvalidDate(int day, int month, int year) {
        assertFalse(Date.isValidDate(day, month, year));
    }

    /**
     * Test the isLeapYear method with correct inputs.
     * Expected : isLeapYear returns true for correct leap years
     * @param year int year of the date to be tested
     */
    @ParameterizedTest
    @ValueSource(ints = {
            2000,
            2004
    })
    @DisplayName("Test method isLeapYear with correct leap years")
    public void testIsLeapYear(int year) {
        assertTrue(Date.isLeapYear(year));
    }

    /**
     * Test the isLeapYear method with incorrect inputs.
     * Expected : isLeapYear returns false for incorrect leap years
     *
     * @param year  int year of the date to be tested
     */
    @ParameterizedTest
    @ValueSource(ints = {
            2100,
            2003
    })
    @DisplayName("Test method isLeapYear with incorrect leap years")
    public void testIsNotLeapYear(int year) {
        assertFalse(Date.isLeapYear(year));
    }

    /**
     * Test the nextDay method
     * Init : Create the date from inputs and create the next date
     * Expected : the next day must be valid
     *
     * @param day   int day of the date to be tested
     * @param month int month of the date to be tested
     * @param year  int year of the date to be tested
     * @param nextDay  int day of the next day
     */
    @ParameterizedTest
    @CsvSource({
            // day, month, year, nextDay
            "1, 5, 1996, 2",
            "28, 2, 2000, 29",
            "29, 6, 2007, 30",
            "30, 12, 2007, 31",
            "29, 2, 2008, 1",
            "31, 8, 2007, 1",
    })
    @DisplayName("Test method nextDay")
    public void testNextDay(int day, int month, int year, int nextDay) throws WrongDateException {
        Date previousDate = new Date(day, month, year);
        Date result = previousDate.nextDate();
        assertEquals(nextDay, result.getDay());
    }

    /**
     * Test the previousDay method
     * Init : Create the date from inputs and create the previous date
     * Expected : the previous day must be valid
     * @param day   int day of the date to be tested
     * @param month int month of the date to be tested
     * @param year  int year of the date to be tested
     * @param previousDay  int day of the previous day
     */
    @ParameterizedTest
    @CsvSource({
            // day, month, year, previousDay
            "18, 1, 1996, 17",
            "1, 9, 1996, 31",
            "1, 3, 2008, 29",
            "1, 3, 2007, 28",
            "1, 10, 2007, 30",
    })
    @DisplayName("Test method previousDay")
    public void testPreviousDay(int day, int month, int year, int previousDay) throws WrongDateException {
        assertEquals(previousDay, new Date(day, month, year).previousDate().getDay());
    }

    /**
     * Test the compareTO method
     * Init : creates 2 different dates
     * Expected : returns the number of days separating the first date from the second
     */
    @Test
    @DisplayName("Test CompareTo")
    public void testCompareTo() throws WrongDateException {
        Date previous = new Date(3, 1, 1996);
        Date compared = new Date(2, 1, 1996);
        assertEquals(-1, compared.compareTo(previous));
    }
}