import java.util.Stack;

public class BalancedWord {
    private final String word;

    public BalancedWord(String word) {
        if (isBalanced(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a balanced word.", word));
    }

    private static boolean isBalanced(String word) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '(')
                stack.push(word.charAt(i));
            if(word.charAt(i) == ')' && stack.isEmpty())
                return false;
            else if (word.charAt(i) == ')')
                stack.pop();
        }
        if (stack.isEmpty()) {
            return true;
        }
        else
             return false;
    }

    public String getWord() {
        return word;
    }

    public static void main(String[] args) {
        System.out.println(isBalanced(" (((5+2)) "));
    }
}
