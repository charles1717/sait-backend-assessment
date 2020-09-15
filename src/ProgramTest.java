import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {
    private static final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    private static File workingFile;
    private static final Program program = new Program("programs_test_file.csv");

    ArrayList<String> pullTestOne;
    ArrayList<String> pullTestTwo;
    ArrayList<String> pullTestThree;
    ArrayList<String> pullTestFour;
    ArrayList<String> pullTestFive;
    ArrayList<String> pullTestSix;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        pullTestOne = Program.pullPrograms("1", "Available");
        pullTestTwo = Program.pullPrograms("2", "Available");
        pullTestThree = Program.pullPrograms("3", "Available");

        pullTestFour = Program.pullPrograms("1", "Not Available");
        pullTestFive = Program.pullPrograms("2", "Not Available");
        pullTestSix = Program.pullPrograms("3", "Not Available");
    }

    @org.junit.jupiter.api.Test
    void pullPrograms() {
        ArrayList<String> expectedResultTestOne = new ArrayList<>();
        expectedResultTestOne.add("Information Technology");
        expectedResultTestOne.add("Dental Assisting");
        expectedResultTestOne.add("Film and Video Production");
        assertArrayEquals(expectedResultTestOne.toArray(), pullTestOne.toArray());

        ArrayList<String> expectedResultTestTwo = new ArrayList<>();
        expectedResultTestTwo.add("Engineering Design and Drafting Technology");
        assertArrayEquals(expectedResultTestTwo.toArray(), pullTestTwo.toArray());

        ArrayList<String> expectedResultTestThree = new ArrayList<>();
        expectedResultTestThree.add("Account Oil and Gas Production");
        assertArrayEquals(expectedResultTestThree.toArray(), pullTestThree.toArray());

        ArrayList<String> expectedResultTestFour = new ArrayList<>();
        expectedResultTestFour.add("Baker Apprentice");
        assertArrayEquals(expectedResultTestFour.toArray(), pullTestFour.toArray());

        ArrayList<String> expectedResultTestFive = new ArrayList<>();
        expectedResultTestFive.add("Welding Engineering Technology");
        expectedResultTestFive.add("Electrician Apprentice");
        expectedResultTestFive.add("Hospitality Management");
        assertArrayEquals(expectedResultTestFive.toArray(), pullTestFive.toArray());

        ArrayList<String> expectedResultTestSix = new ArrayList<>();
        expectedResultTestSix.add("Environmental Technology");
        assertArrayEquals(expectedResultTestSix.toArray(), pullTestSix.toArray());
    }

    @org.junit.jupiter.api.Test
    void addProgram() {
    }

    @org.junit.jupiter.api.Test
    void deleteProgram() {
    }

    @org.junit.jupiter.api.Test
    void getFile() {
        File testFileOne = new File("programs-test-file.csv");
        boolean testCaseOne = testFileOne.exists();
        assertEquals(false, testCaseOne);

        File testFileTwo = new File("programs_test_file.txt");
        boolean testCaseTwo = testFileTwo.exists();
        assertEquals(false, testCaseTwo);

        File testFileThree = new File("programs_test_file.xls");
        boolean testCaseThree = testFileThree.exists();
        assertEquals(false, testCaseThree);

        File testFileFour = new File("program_test_file.csv");
        boolean testCaseFour = testFileFour.exists();
        assertEquals(false, testCaseFour);

        File testFileFive = new File("programs_test_file.csv");
        boolean testCaseFive = testFileFive.exists();
        assertEquals(true, testCaseFive);
    }
}