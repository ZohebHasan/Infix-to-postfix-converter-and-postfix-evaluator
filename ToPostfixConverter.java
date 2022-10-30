import java.util.Stack;
public class ToPostfixConverter implements Converter {

    @Override
    public String convert(ArithmeticExpression expression) { //Functional //Utilizing Converter at max.
        Stack<Character> stack = new Stack<>();
        TokenBuilder postFix = new TokenBuilder();
        String myExpression = expression.getExpression().replaceAll(" ", "");

        for (int i = 0; i < myExpression.length() ; ) {
              char c = myExpression.charAt(i);
              if(c == Operator.LEFT_PARENTHESIS.getSymbol()){
                  stack.push(c);
                  i++;
              }
              else if (c== Operator.RIGHT_PARENTHESIS.getSymbol()) {
                  while(!stack.isEmpty() && stack.peek() != Operator.LEFT_PARENTHESIS.getSymbol()){
                      postFix.append(stack.peek());
                      postFix.append(' ');
                      stack.pop();
                  }
                  stack.pop();
                  i++;
              }
              else if (Operator.isOperator(c)) {
                  while(!stack.isEmpty()&& Operator.of(c).getRank() >= Operator.of(stack.peek()).getRank() && stack.peek() != Operator.LEFT_PARENTHESIS.getSymbol()){
                      postFix.append( stack.peek());
                      postFix.append(' ');
                      stack.pop();
                  }
                  stack.push(c);
                  i++;
              }
              else {
                 for(int j = i ; j < myExpression.length() && !Operator.isOperator(myExpression.charAt(j)) && myExpression.charAt(j)  !=  Operator.RIGHT_PARENTHESIS.getSymbol() ; j++ ){
                     postFix.append(myExpression.charAt(j));
                     i++;
                 }
                 postFix.append(' ');
              }
        }
        while(!stack.isEmpty()){
            postFix.append(stack.peek());
            postFix.append(' ');
            stack.pop();
        }
        return postFix.build();
    }
    @Override
    public String nextToken(String s, int start) { //Functional
        s = s.replaceAll(" ", "");
        int count = 0;
        TokenBuilder token = new TokenBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (count == start) {
                //if it's a digit
                if (s.charAt(i) >= 48 && s.charAt(i) <= 57)
                    while(i < s.length() && (s.charAt(i) >= 48 && s.charAt(i) <= 57 || s.charAt(i) == '.')) {
                        token.append(s.charAt(i));
                        i++;
                    }
                //if it's a symbol
                else{
                    token.append(s.charAt(i));
                }
                break;
            }

            //if it's a digit
            if (s.charAt(i) >= 48 && s.charAt(i) <= 57 ) {
                while( i < s.length() && (s.charAt(i) >= 48 && s.charAt(i) <= 57 || s.charAt(i) == '.')) {
                    i++;
                }
                i--;
                count++;
            }
            //if it's a symbol
            else{
                count++;
            }
        }
        return token.build();
    }

    @Override
    public boolean isOperand(String s) { //Functional
        for(int i = 0; i < s.length(); i++){
            if(Operator.isOperator(s.charAt(i)) || s.charAt(i) == Operator.LEFT_PARENTHESIS.getSymbol() || s.charAt(i) == Operator.RIGHT_PARENTHESIS.getSymbol()) {
                    return false;
            }
        }
        return true;
    }
}

