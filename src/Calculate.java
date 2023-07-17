import java.util.*;

public class Calculate {
    Map<String, Integer> operators = new HashMap<>();

    public Calculate() {
        operators.put("+", 1);
        operators.put("-", 1);
        operators.put("*", 2);
        operators.put("/", 2);
        operators.put("^", 3);
        operators.put("(", 4);
    }

    public boolean validOp(String op){
        return operators.containsKey(op) || op.equalsIgnoreCase(")");
    }
}
