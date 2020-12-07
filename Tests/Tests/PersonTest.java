package Tests;

import JavaClasses.Person;
import JavaClasses.TreeGenealogy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private TreeGenealogy t = new TreeGenealogy("FamilyTreeInputTextFileV2.txt");
    private Person testPerson = t.getPerson("P8");

    PersonTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getLastName() {
        String actual = testPerson.getLastName();
        String expected = "Smith";
        String error = "Johnson";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getFirstName() {
        String actual = testPerson.getFirstName();
        String expected = "Walter Jay";
        String error = "Dick";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getSuffix() {
        String actual = testPerson.getSuffix();
        String expected = "III";
        String error = "Jr";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getChildOfR() {
        String actual = testPerson.getChildOfR();
        String expected = "R5";
        String error = "R7";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getRelations() {
        Set<String> set = testPerson.getRelations();
        int actual = set.size();
        int expected = 2;
        int error = 1;
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }
}