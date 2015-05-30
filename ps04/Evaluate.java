/** File: Evaluate.java
  * Name: Jason Morse
  * Date: 10/10/13
  * CS102: Problem Set 4
**/

public class Evaluate {
    
    public static void main(String[] args) {
        
        // Creates a stack of strings for operations and a stack of doubles for values
        Stack<String> ops  = new ResizingArrayStack<String>();
        Stack<Double> vals = new ResizingArrayStack<Double>();
        
        // While there are still tokens to be read get the next token
        while (!StdIn.isEmpty()) {    
            String s = StdIn.readString();
            
            // If the token is a left paren: push it onto the operator stack
            if (s.equals("("))    ops.push(s);
            
            // If the token is a right paren...
            else if (s.equals(")")) {
                
                // While the thing on top of the operator stack is not a left paren
                while (!ops.peek().equals("(")) {
                  
                    // Pop the operator from the operator stack and pop the value stack twice, getting two operands
                    String operator = ops.pop();
                    Double value1 = vals.pop();
                    Double value2 = vals.pop();
                    Double result = null;
                    
                    // Apply the operator to the operands
                    if      (operator.equals("+"))    {result = value1 + value2;}
                    else if (operator.equals("-"))    {result = value2 - value1;}
                    else if (operator.equals("*"))    {result = value1 * value2;}
                    else if (operator.equals("/"))    {result = value2 / value1;}
                    else if (operator.equals("^"))    {result = Math.pow(value2, value1);}
                    
                    // Push the result onto the value stack
                    vals.push(result);
                }
                
                // Pop the left parenthesis from the operator stack and disgard
                ops.pop();
            }
            
            // If the token is an operator
            else if ((s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^"))) {
                
                // While the operator stack is not empty...
                while (!ops.isEmpty()) {
                  
                    // ...and  the top thing on the operator stack has the same or greater precedence as the token
                    if (getPrecedence(ops.peek()) >= getPrecedence(s)) {
                      
                        // Pop the operator from the operator stack and pop the value stack twice, getting two operands
                        String operator = ops.pop();
                        Double value1 = vals.pop();
                        Double value2 = vals.pop();
                        Double result = null;
                        
                        // Apply the operator to the operands
                        if      (operator.equals("+"))    {result = value1 + value2;}
                        else if (operator.equals("-"))    {result = value2 - value1;}
                        else if (operator.equals("*"))    {result = value1 * value2;}
                        else if (operator.equals("/"))    {result = value1 / value2;}
                        else if (operator.equals("^"))    {result = Math.pow(value2, value1);}
                        
                        // Push the result onto the value stack
                        vals.push(result);
                    }
                    else break;
                }
                // Push the token onto the operator stack
                ops.push(s);
            }  
            // If the token is a number: push it onto the value stack
            else vals.push(Double.parseDouble(s));
        }
        
        // Print last value remaining in value stack
        System.out.println(vals.pop());
        
    }
    
    // Helper method that determines precedence of operators
    public static int getPrecedence(String operator) {
        if      (operator.equals("+"))    return 1;
        else if (operator.equals("-"))    return 1;
        else if (operator.equals("*"))    return 2;
        else if (operator.equals("/"))    return 2;
        else if (operator.equals("^"))    return 3;
        else                              return 0;
    }
    
}