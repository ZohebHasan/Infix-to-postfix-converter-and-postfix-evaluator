import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ArithmeticExpression {

    private static final String INPUT_PATH = "D:\\CS\\IntelliJ IDEA Community Edition 2022.2.1\\Projects\\CSE214\\Fall2022\\Fall2022\\CSE214\\Homework\\src\\cse214hw2\\MathTest.txt";
    private BalancedWord expression;
    public ArithmeticExpression(String expression) throws IllegalArgumentException {
        this.expression = new BalancedWord(expression);
    }
    public String getExpression() {
        return expression.getWord();
    }
    public static void main(String... args) {
        File input = new File(INPUT_PATH);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.printf("Input: %s%n", line);
                try {
                    ArithmeticExpression a = new ArithmeticExpression(line.trim());
                    Converter converter = new ToPostfixConverter();
                    String postfix = converter.convert(a);
                    System.out.printf("\tPostfix: %s%n", postfix);
                    Evaluator evaluator = new PostfixEvaluator();
                    double result = evaluator.evaluate(postfix);
                    System.out.printf("\tValue: %.2f%n", result);
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
