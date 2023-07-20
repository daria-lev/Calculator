import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;

public class CalcTests {
    Calculate calc;

    @BeforeEach
    public void setUp(){
        calc = new Calculate();
    }

    @Test
    @DisplayName("In order, 1 operation")
    public void inOrder1(){
        Double val = calc.pemdas(new ArrayList<>(Arrays.asList("1", "+", "2")));
        assertEquals(3.0, val);
    }

    @Test
    @DisplayName("In order, 2 operations")
    public void inOrder2(){
        Double val = calc.pemdas(new ArrayList<>(Arrays.asList("1", "*", "2", "+", "3")));
        assertEquals(5.0, val);
    }

    @Test
    @DisplayName("Out of order, 2 operations")
    public void outOrder1(){
        Double val = calc.pemdas(new ArrayList<>(Arrays.asList("1", "+", "2", "*", "3")));
        assertEquals(7.0, val);
    }

    @Test
    @DisplayName("Out of order, 3 operations")
    public void outOrder2(){
        Double val = calc.pemdas(new ArrayList<>(Arrays.asList("1", "+", "2", "*", "3", "-", "3")));
        assertEquals(4.0, val);
    }
}
