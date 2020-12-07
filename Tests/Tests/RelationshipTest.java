package Tests;

import JavaClasses.Person;
import JavaClasses.Relationship;
import JavaClasses.TreeGenealogy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipTest {

    private TreeGenealogy t = new TreeGenealogy("FamilyTreeInputTextFileV2.txt");
    private Relationship testRel = t.getRelationship("R4");

    RelationshipTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRelationshipID() {
        String actual = testRel.getRelationshipID();
        String expected = "R4";
        String error = "R6";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getFirstParent() {
        Person p = testRel.getFirstParent();
        String actual = p.getPersonID();
        String expected = "P19";
        String error = "P17";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getSecondParent() {
        Person p = testRel.getSecondParent();
        String actual = p.getPersonID();
        String expected = "P20";
        String error = "P18";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getStartDate() {
        String actual = testRel.getStartDate();
        String expected = "2/12/1912";
        String error = "4/14/1914";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getEndDate() {
        String actual = testRel.getEndDate();
        String expected = null;
        String error = "2/12/1912";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getMarriageLocation() {
        String actual = testRel.getMarriageLocation();
        String expected = "JP Las Vegas";
        String error = "First Methodist Flint";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }
}