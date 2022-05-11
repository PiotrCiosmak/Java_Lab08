package input;

import exception.MyException;
import org.junit.Test;

import java.util.*;

public class Input
{
    private String whatToCalculate;
    private final Scanner sc;
    private Map<Character, Integer> signs = new HashMap<>();
    public String ONP = "";
    private Double result;
    private int elementCounter;

    public Input(String whatToCalculate) throws MyException
    {
        this.whatToCalculate = whatToCalculate;
        sc = new Scanner(whatToCalculate);
        sc.useLocale(Locale.ENGLISH);
        signs.put('+', 1);
        signs.put('-', 1);
        signs.put('*', 2);
        signs.put('/', 2);
        signs.put('(', 0);
        checkIfCorrect();
        convertToONP();
        calculate();
    }

    private void checkIfCorrect() throws MyException
    {
        MyException ex = new MyException();
        ArrayList<String> elements = new ArrayList<>();
        String left;
        String right;
        String regex = "\\d+";
        Integer countOpeningParentheses = 0;
        Integer countClosingParentheses = 0;

        if (whatToCalculate.charAt(0) == ' ')
            whatToCalculate = whatToCalculate.substring(1);

        for (String elem : whatToCalculate.split("\\s+"))
        {
            ++elementCounter;
            elements.add(elem);
        }


        if (elements.get(0).equals("+") || elements.get(0).equals("*") || elements.get(0).equals("/"))
        {
            ex.setMes("Brak liczby przed operatorem");
            throw ex;
        }

        for (String element : elements)
        {
            if (element.equals("("))
            {
                countOpeningParentheses++;
            } else if (element.equals(")"))
            {
                countClosingParentheses++;
            }
        }

        if (!countOpeningParentheses.equals(countClosingParentheses))
        {
            ex.setMes("Liczba nawiasów otwierających różna od liczby nawiasów zamykających");
            throw ex;
        }

        for (int i = 1; i < elements.size(); ++i)
        {
            left = elements.get(i - 1);
            right = elements.get(i);
            if (left.equals("."))
            {
                ex.setMes("Kropka w niedozwolonym miejsciu");
                throw ex;
            } else if (left.equals("/") && right.equals("0"))
            {
                ex.setMes("Nie wolno dzielić przez 0");
                throw ex;
            } else if (left.equals("(") && (right.equals("+") || right.equals("-") || right.equals("*") || right.equals("/") || right.equals("=")))
            {
                ex.setMes("Brak liczby po nawiasie otwierającym");
                throw ex;
            } else if ((left.equals("+") || left.equals("-") || left.equals("*") || left.equals("/")) && right.equals(")"))
            {
                ex.setMes("Brak liczby przed nawiasem zamykającym");
                throw ex;
            } else if ((left.equals("(") && right.equals(")")) || ((left.equals(")") && right.equals("("))))
            {
                ex.setMes("Niepoprawne ustawienie nawiasów");
                throw ex;
            } else if ((left.equals("+") || left.equals("-") || left.equals("*") || left.equals("/")) && (right.equals("+") || right.equals("-") || right.equals("*") || right.equals("/") || right.equals("=")))
            {
                ex.setMes("Brak liczby pomiędzy operatorami");
                throw ex;
            } else if (left.matches(regex) && right.matches(regex))
            {
                ex.setMes("Brak operatora pomiędzy liczbami");
                throw ex;
            }
        }
    }

    public Double getResult()
    {
        return result;
    }

    private void convertToONP()
    {
        Stack<Character> stackOfCharacters = new Stack<>();
        for (int i = 0; i < elementCounter; ++i)
        {
            try
            {
                Double number = sc.nextDouble();
                ONP += (number + " ");
            } catch (Exception ex)
            {
                Character sign = sc.next().charAt(0);

                if (!stackOfCharacters.empty())
                {
                    if (sign.equals('='))
                    {
                        while (!stackOfCharacters.empty())
                        {
                            ONP += (stackOfCharacters.pop() + " ");
                        }
                    } else if (sign.equals(')'))
                    {
                        while (!stackOfCharacters.empty() && !stackOfCharacters.get(stackOfCharacters.size() - 1).equals('('))
                        {
                            ONP += (stackOfCharacters.pop() + " ");
                        }
                        stackOfCharacters.pop();
                    } else if (sign.equals('(') || signs.get(sign) > signs.get(stackOfCharacters.get(stackOfCharacters.size() - 1)))
                    {
                        stackOfCharacters.push(sign);
                    } else
                    {
                        ONP += (stackOfCharacters.pop() + " ");
                        stackOfCharacters.push(sign);
                    }
                } else
                {
                    stackOfCharacters.push(sign);
                }
            }
        }
    }

    private void calculate()
    {
        Stack<Double> stackONP = new Stack<>();

        for (String element : ONP.split("\\s+"))
        {
            switch (element)
            {
                case "+" -> stackONP.push(stackONP.pop() + stackONP.pop());
                case "-" -> stackONP.push(-stackONP.pop() + stackONP.pop());
                case "*" -> stackONP.push(stackONP.pop() * stackONP.pop());
                case "/" ->
                {
                    double divisor = stackONP.pop();
                    stackONP.push(stackONP.pop() / divisor);
                }
                default -> stackONP.push(Double.parseDouble(element));
            }
        }
        result = stackONP.pop();
    }
}

