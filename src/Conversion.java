import sun.tools.java.SyntaxError;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

public class Conversion {
    public static final String OPERAND = "*+/-";

    public static String post2Pre(String expression) throws IOException, SyntaxError {
        if (!expression.equals("")) {
            List<String> expressionToArray = tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();

            for (String token:expressionToArray) {
                if (!isOperator(token)) {
                    operandStack.push(token + " ");
                } else {
                    try {
                        String operandTwo = operandStack.pop();
                        String operandOne = operandStack.pop();
                        String innerExpression = token + " " + operandOne + operandTwo;
                        operandStack.push(innerExpression);
                    } catch (EmptyStackException ex) {
                        throw new SyntaxError();
                    }
                }
            }

            String result = operandStack.pop();

            if (operandStack.empty()) {
                return result;
            } else {
                throw new SyntaxError();
            }
        } else {
            throw new SyntaxError();
        }
    }

    public static String pre2Post(String expression) throws SyntaxError, IOException {
        if (!expression.equals("")) {
            List<String> expressionToArray = tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();

            Collections.reverse(expressionToArray);

            for (String token:expressionToArray) {
                if (!isOperator(token)) {
                    operandStack.push(token + " ");
                } else {
                    try {
                        String operandOne = operandStack.pop();
                        String operandTwo = operandStack.pop();
                        String innerExpression = operandOne + operandTwo + token + " ";
                        operandStack.push(innerExpression);
                    } catch (EmptyStackException ex) {
                        throw new SyntaxError();
                    }
                }
            }
            String result = operandStack.pop();
            if (operandStack.empty()){
                return result;
            } else {
                throw new SyntaxError();
            }

        } else {
            throw new SyntaxError();
        }
    }

    private static List<String> tokenizeExpression(String expression) throws IOException {
        StreamTokenizer tokenizeExpression = new StreamTokenizer(new StringReader(expression));

        tokenizeExpression.ordinaryChar('-');
        tokenizeExpression.ordinaryChar('/');

        List<String> tokenList = new ArrayList<>();

        while (tokenizeExpression.nextToken() != StreamTokenizer.TT_EOF) {

            if (tokenizeExpression.ttype == StreamTokenizer.TT_NUMBER) {
                tokenList.add(String.valueOf((int)tokenizeExpression.nval));
            } else if(tokenizeExpression.ttype == StreamTokenizer.TT_WORD) {
                tokenList.add(tokenizeExpression.sval);
            } else {
                tokenList.add(String.valueOf((char) tokenizeExpression.ttype));
            }
        }

        return tokenList;
    }

    private static boolean isOperator(String c) {
        switch(c.charAt(0)) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(pre2Post("* 2 + 2 - + 12 9 2"));
    }
}
