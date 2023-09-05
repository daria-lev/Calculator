import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;

public class CalcTreeTests {
    CalculateTree calc;

    @BeforeEach
    public void setUp(){
        calc = new CalculateTree();
    }

    @Test
    @DisplayName("In order, 1 operation")
    public void inOrder1(){
        String input = "1 + 2";
        //Double val = calc.compute(new ArrayList<>(Arrays.asList("1", "+", "2")));
        Double val = calc.compute(new ArrayList<>(Arrays.asList(input.split(" "))));
        assertEquals(3.0, val);
    }

    /*@Test
    @DisplayName("Not valid equation")
    public void badEq(){
        String input = "1 + + 2";
        //Double val = calc.compute(new ArrayList<>(Arrays.asList("1", "+", "2")));
        //boolean val = isValid
        assertEquals(3.0, val);
    }*/

    @Test
    @DisplayName("In order, 2 operations")
    public void inOrder2(){
        Double val = calc.compute(new ArrayList<>(Arrays.asList("1", "*", "2", "+", "3")));
        assertEquals(5.0, val);
    }

    @Test
    @DisplayName("Out of order, 2 operations")
    public void outOrder1(){
        Double val = calc.compute(new ArrayList<>(Arrays.asList("1", "+", "2", "*", "3")));
        assertEquals(7.0, val);
    }

    @Test
    @DisplayName("Out of order, 3 operations")
    public void outOrder2(){
        Double val = calc.compute(new ArrayList<>(Arrays.asList("1", "+", "2", "*", "3", "-", "3")));
        assertEquals(4.0, val);
    }

    /*@Test
    @DisplayName("One operation all in parentheses")
    public void justParen(){
        Double val = calc.compute(new ArrayList<>(Arrays.asList("(", "2", "*", "3", ")")));
        assertEquals(6.0, val);
    }

    @Test
    @DisplayName("One set of parentheses")
    public void oneParen(){
        Double val = calc.compute(new ArrayList<>(Arrays.asList("(", "2", "*", "3", ")", "+", "1")));
        assertEquals(7.0, val);
    }

    @Test
    @DisplayName("Two sets of parentheses")
    public void twoParen(){
        String input = "( 2 * 3 ) + 1 * ( 3 + 2 )";
        Double val = calc.compute(new ArrayList<>(Arrays.asList(input.split(" "))));
        assertEquals(11.0, val);
    }

    @Test
    @DisplayName("Nested sets of parentheses")
    public void twoNestParen(){
        String input = "( 2 - 3 * ( 3 + 2 ) ) * 5";
        Double val = calc.compute(new ArrayList<>(Arrays.asList(input.split(" "))));
        assertEquals(-65.0, val);
    }*/

    @Test
    @DisplayName("One variable")
    public void var1(){
        Double val = calc.compute(new ArrayList<>(Arrays.asList("1", "+", "2", "*", "3", "-", "3")));
        assertEquals(4.0, val);
    }

}
