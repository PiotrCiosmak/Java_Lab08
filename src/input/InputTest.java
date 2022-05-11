package input;

import exception.MyException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest
{
    double epsilon = 0.000001d;

    @Test
    public void calculate_sum_four() throws MyException
    {
        Input input = new Input("2 + 2 =");
        Double proper = 4.0;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_sum_threeHundredAndEighteen() throws MyException
    {
        Input input = new Input("100 + 218 =");
        Double proper = 318.0;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_minus_negativeFivePointFive() throws MyException
    {
        Input input = new Input("2.5 - 8 =");
        Double proper = -5.5;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_minus_zero() throws MyException
    {
        Input input = new Input("8.777 - 8.777 =");
        Double proper = 0.0;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_multiplication_twenty() throws MyException
    {
        Input input = new Input("2.5 * 8 =");
        Double proper = 20.0;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_multiplication_twentyAndOneHundredth() throws MyException
    {
        Input input = new Input("8.7 * 2.3 =");
        Double proper = 20.01;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_division_twenty() throws MyException
    {
        Input input = new Input("100 / 5 =");
        Double proper = 20.0;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_division_fourAndThreeFourth() throws MyException
    {
        Input input = new Input("9.5 / 2 =");
        Double proper = 4.75;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_parenthesis_five() throws MyException
    {
        Input input = new Input("( 3 + 2 ) =");
        Double proper = 5.0;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_parenthesis_ten() throws MyException
    {
        Input input = new Input("( 5 * ( 2 ) ) =");
        Double proper = 10.0;
        Assert.assertEquals(proper, input.getResult(), epsilon);
    }

    @Test
    public void calculate_noNumberInFrontOfOperator_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(" *  5 - 2 =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Brak liczby przed operatorem";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_toMuchClosingParenthesis_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(" ( ( 5 + 2 ) ) ) =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Liczba nawiasów otwierających różna od liczby nawiasów zamykających";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_toMuchOpeningParenthesis_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(" ( ( ( ( 5 + 2 ) ) ) =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Liczba nawiasów otwierających różna od liczby nawiasów zamykających";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_dotInWrongPlace_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(" 5 . + 2 =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Kropka w niedozwolonym miejsciu";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_divisionByZero_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(" 5.281 / 0 =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Nie wolno dzielić przez 0";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_noNumberAfterOpeningParenthesis_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(" ( * 5 + 2 ) =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Brak liczby po nawiasie otwierającym";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_noNumberBeforeClosingParenthesis_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(" ( 5 + ) - 2 =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Brak liczby przed nawiasem zamykającym";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_incorrectSettingOfParentheses_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input(") ( 5 - 2 =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Niepoprawne ustawienie nawiasów";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_noNumberBetweenOperators_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input("5 - + 2 =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Brak liczby pomiędzy operatorami";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void calculate_noOperatorBetweenNumbers_MyException() throws MyException
    {
        String actualMessage = new String();
        try
        {
            Input input = new Input("5 - 2 22 =");
        } catch (MyException ex)
        {
            actualMessage = ex.getMes();
        }
        String expectedMessage = "Brak operatora pomiędzy liczbami";

        Assert.assertTrue(actualMessage.equals(expectedMessage));
    }
}