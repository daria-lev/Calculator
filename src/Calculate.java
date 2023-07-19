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

    public String calculateOne(String val1, String op, String val2){
        double first = Double.parseDouble(val1);
        double second = Double.parseDouble(val2);
        double output = 0;
        if(op.equalsIgnoreCase("+")){
            output = first + second;
        }
        if(op.equalsIgnoreCase("-")){
            output = first - second;
        }
        if(op.equalsIgnoreCase("*")){
            output = first * second;
        }
        if(op.equalsIgnoreCase("/")){
            output = first / second;
        }
        if(op.equalsIgnoreCase("^")){
            output = Math.pow(first, second);
        }
        return Double.toString(output);
    }
}
