import java.util.*;

public class Calculate {
    protected Map<String, Integer> operators = new HashMap<>();

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

    public double pemdas(List<String> parts){ //parentheses now work
        mainloop:
        while(parts.size() > 1){
            int mostImportant = 0;
            int position = 0; //index of operator
            for(int i = 0; i < parts.size(); i++){
                String cur = parts.get(i);
                if(operators.containsKey(cur)){
                    if(cur.equalsIgnoreCase("(")){
                        //System.out.println("there is a paren");
                        int matchingParen = findParen(i, parts);
                        //System.out.println(matchingParen);
                        double result = pemdas(new ArrayList<>(parts.subList(i+1, matchingParen)));
                        parts.subList(i, matchingParen+1).clear();
                        parts.add(i, Double.toString(result));
                        continue mainloop;
                    }
                    else if(operators.get(cur) > mostImportant){
                        mostImportant = operators.get(cur);
                        position = i;
                    }
                }
            }
            String replacement = calculateOne(parts.get(position-1), parts.get(position), parts.get(position+1));
            parts.subList(position-1, position+2).clear();
            parts.add(position-1, replacement);
        }
        return Double.parseDouble(parts.get(0));
    }

    private int findParen(int openInd, List<String> parts){
        int parenCount = 1;
        openInd++;
        while(parenCount != 0){
            if(parts.get(openInd).equals("(")){
                parenCount++;
            }
            else if(parts.get(openInd).equals(")")){
                parenCount--;
            }
            openInd++;
        }
        return openInd-1;
    }

    //calculates one operation and returns the value
    protected String calculateOne(String val1, String op, String val2){
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
