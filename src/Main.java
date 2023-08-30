import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        String input = "";
        Calculate calc = new Calculate();
        CalculateTree calc2 = new CalculateTree();
        Map<Integer, Set<Integer>> valids = new HashMap<>();
        fillValids(valids);
        System.out.println("Hello! Welcome to the calculator.");

        //asking:
        while(!input.equalsIgnoreCase("Q")){
            System.out.println("Enter your operation below (or q to quit).");
            input = user.nextLine();
            if(!input.equalsIgnoreCase("Q")){
                String[] parts = input.split(" "); //temp probably, creates list of nums and ops
                if(!isValidStatement(parts, calc, valids)){
                    System.out.println("invalid statement");
                    continue;
                }
                System.out.println("valid statement");
                //System.out.println(calc.calculateOne(parts[0], parts[1], parts[2]));
                System.out.println(Arrays.asList(parts));

                Double output = calc.pemdas(new ArrayList<>(Arrays.asList(parts)));
                System.out.println(output);

                calc2.compute(new ArrayList<>(Arrays.asList(parts)));
            }
        }
    }

    //checks if it's a computable statement
    public static boolean isValidStatement(String[] parts, Calculate calc, Map<Integer, Set<Integer>> valids){
        //checking that it is a valid expression
        int prev = 1; //0 means integer, 1 means op, 2 means open paren, 3 means close
        int cur = 0;
        int numParen = 0;
        for (String s : parts) {
            /*if(s.matches("-?\\d+")){ //if it's an integer //need to change to allow doubles eventually
                cur = 0;
            }*/
            cur = 0;
            try
            {
                Double.parseDouble(s);
            }
            catch(NumberFormatException e)
            {
                //not a double
                if(calc.validOp(s)){ //if it's an operator
                    if(s.equalsIgnoreCase("(")){
                        cur = 2;
                        numParen++;
                    }
                    else if(s.equalsIgnoreCase(")")){
                        cur = 3;
                        numParen--;
                    }
                    else{ //operator and not parentheses
                        cur = 1;
                    }
                }
                else{
                    System.out.println(s + " -invalid character");
                    return false;
                }
            }

            if(numParen < 0){ // ) without matching (
                System.out.println(s + " -bad paren");
                return false;
            }
            if(!valids.get(prev).contains(cur)){
                System.out.println(s + " -bad prev");
                return false;
            }
            prev = cur;
        }
        if(numParen != 0 || cur == 1){ return false; } // can simplify but then i will be confused
        return true;
    }

    private static void fillValids(Map<Integer, Set<Integer>> valids){
        valids.put(0, new HashSet<>(Arrays.asList(1, 3))); //num
        valids.put(1, new HashSet<>(Arrays.asList(0, 2))); //op
        valids.put(2, new HashSet<>(Arrays.asList(0, 2))); // (
        valids.put(3, new HashSet<>(Arrays.asList(1, 3))); // )
    }
}