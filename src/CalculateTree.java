import java.util.*;

public class CalculateTree extends Calculate{
    private Map<String, Integer> variables;

    public CalculateTree(){
        super();
        variables = new HashMap<>(); //any variable found will be prompted for a value and stored here
    }

    public int compute(List<String> parts){
        //find symbol, use that for root node
        CalcNode root = constructTree(parts, 0, parts.size());
        return -1;
    }

    //ignores parentheses for now
    private CalcNode constructTree(List<String> parts, int first, int last){
        CalcNode node;
        int index = findNextSymbol(parts, 0, parts.size());
        if(index != -1){
            String nodeValue = parts.get(index);
            node = new CalcNode(nodeValue);
            node.left = constructTree(parts, first, index);
            node.right = constructTree(parts,index+1, last);
        }
        else{
            node = new CalcNode(parts.get(first)); //assumes the section is now length 1, and we can safely take the value
        }
        return node;
    }

    //finds the next symbol to add to the tree between the given indexes (inclusive, exclusive)
    //if no symbol, returns -1
    private int findNextSymbol(List<String> parts, int first, int last){
        int ind = -1;
        int importance = 10;
        String cur;
        for(int i = first; i < last; i++){
            cur = parts.get(i);
            if(operators.containsKey(cur) && operators.get(cur) <= importance){ //checks if it's an operator
                ind = i;
                importance = operators.get(cur);
            }
        }
        return ind;
    }

    public class CalcNode{
        String value; //can be operator, number, or variable
        CalcNode left;
        CalcNode right;

        public CalcNode(String s, CalcNode left, CalcNode right){
            this.value = s;
            this.left = left;
            this.right = right;

        }

        public CalcNode(String s){
            this(s, null, null);
        }

        public CalcNode(){
            this(null, null, null);
        }
    }
}
