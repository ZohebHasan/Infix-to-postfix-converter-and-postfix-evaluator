package cse214hw2;
import java.util.Stack;

public class PostfixEvaluator implements Evaluator {
    @Override
    public double evaluate(String expressionString) { //Functional //Utilizing Operator class at max.
        Stack<Double> stack = new Stack<>();
        double value;
        String s = "";
        for (int i = 0; i < expressionString.length(); ) {
            char c = expressionString.charAt(i);
            double val1;
            if (c == ' ') {
                i++;
                continue;
            }
            if (Operator.isOperator(c)) {
                if (Operator.of(c) == Operator.ADDITION) {
                    double var = stack.peek();
                    stack.pop();
                    double var2 =  stack.peek();
                    stack.pop();
                    stack.push(var + var2);
                } else if (Operator.of(c) == Operator.SUBTRACTION) {
                    double var =  stack.peek();
                    stack.pop();
                    double var2 = stack.peek();
                    stack.pop();
                    stack.push(var2 - var);
                } else if (Operator.of(c) == Operator.DIVISION) {
                    double var =  stack.peek();
                    stack.pop();
                    double var2 =  stack.peek();
                    stack.pop();
                    stack.push(var2 / var);
                } else if (Operator.of(c) == Operator.MULTIPLICATION) {
                    double var =  stack.peek();
                    stack.pop();
                    double var2 = stack.peek();
                    stack.pop();
                    stack.push(var * var2);
                }
                i++;
            } else {
                for (int j = i; expressionString.charAt(j) != ' '; j++) {
                    s += expressionString.charAt(j);
                    i++;
                }
                val1 = Double.parseDouble(s);
                s = "";
                stack.push(val1);
            }

        }

        value = stack.peek();
        return value;

    }
}
