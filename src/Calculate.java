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

    public double pemdas(List<String> parts){ //no parentheses for now
        while(parts.size() > 1){
            int mostImportant = 0;
            int position = 0; //index of operator
            for(int i = 0; i < parts.size(); i++){
                String cur = parts.get(i);
                if(operators.containsKey(cur)){
                    if(operators.get(cur) > mostImportant){
                        mostImportant = operators.get(cur);
                        position = i;
                    }
                }
            }
            String replacement = calculateOne(parts.get(position-1), parts.get(position), parts.get(position+1));
            /*for(int i = 0; i < 3; i++){
                parts.remove(position-1);
            }*/
            parts.subList(position-1, position+2).clear();
            parts.add(position-1, replacement);
        }
        return Double.parseDouble(parts.get(0));
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
