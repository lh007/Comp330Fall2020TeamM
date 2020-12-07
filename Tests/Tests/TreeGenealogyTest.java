package Tests;

import JavaClasses.Person;
import JavaClasses.Relationship;
import JavaClasses.TreeGenealogy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TreeGenealogyTest {
    private TreeGenealogy t = new TreeGenealogy("FamilyTreeInputTextFileV2.txt");

    TreeGenealogyTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPeople() {
        Map<String, Person> map = t.getPeople();
        Person person = map.get("P1");
        String actual = person.getBirthDate();
        String expected = "9/1/1940";
        String error = "8/2/1990";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getRelations() {
        Map<String, Relationship> map = t.getRelations();
        Relationship relationship = map.get("R1");
        String actual = relationship.getStartDate();
        String expected = "6/7/1938";
        String error = "8/4/1901";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void searchFirstName() {
        List<Person> list = t.searchFirstName("Dick");
        int actual = list.size();
        int expected = 4;
        int error = 6;
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void searchLastName() {
        List<Person> list = t.searchLastName("Smith");
        int actual = list.size();
        int expected = 10;
        int error = 6;
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getRelationship() {
        Relationship r = t.getRelationship("R1");
        String actual = r.getStartDate();
        String expected = "6/7/1938";
        String error = "8/2/1990";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }

    @Test
    void getPerson() {
        Person person = t.getPerson("P1");
        String actual = person.getBirthDate();
        String expected = "9/1/1940";
        String error = "8/2/1990";
        assertEquals(actual, expected);
        assertNotEquals(actual, error);
    }
}