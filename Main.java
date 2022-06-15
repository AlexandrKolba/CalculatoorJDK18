import java.util.Scanner;
import java.util.stream.Stream;

public class Main
{
    public static String calcArab(String input)
    {
        String string;
        TString s = new TString();

        string = s.DelSpace(input);
        String st = s.FindArguments(string);

        int a = 0;
        int i = 0;
        boolean minus = false;

        StringBuilder str = new StringBuilder(st);

        if( str.charAt(i) == '-' )
        {
            minus = true;
        }

        while(true)
        {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
            {
                a = a * 10 + str.charAt(i) - '0';
            }

            if( str.charAt(i) == ' ')
                break;
            i++;
        }

        if(minus)
            a = a * -1;

        i++;
        minus = false;
        int b = 0;

        if(str.charAt(i) == '-')
            minus = true;

        while(true)
        {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
            {
                b = b * 10 + str.charAt(i) - '0';
            }

            if( i >= str.length()-1 )
                break;

            i++;
        }
        if( minus )
            b = b * -1;

        int res = 0;

        switch( s.ReturnOperator(string) )
        {
            case 0: res = 0; break;
            case 1:
                res = a + b;
                string = ("result operation: " + a + " + " + b + " = " + res);
                break;
            case 2:
                res = a - b;
                string = ("result operation: " + a + " - " + b + " = " + res);
                break;
            case 3:
                res = a / b;
                string = ("result operation: " + a + " / " + b + " = " + res);
                break;
            case 4:
                res = a * b;
                string = ("result operation: " + a + " * " + b + " = " + res);
        }

        return string;
    };

    public static String calcRome(String input)
    {
        TString str = new TString();
        StringBuilder sbA = new StringBuilder();
        StringBuilder sbB = new StringBuilder();
        String s = str.DelSpace(input);
        StringBuilder sb = new StringBuilder(s);

        int i = 0;
        boolean done = true;
        int operator = 0;

        while(true)
        {
            if( sb.charAt(i) == '+')
                operator = 1;
            if( sb.charAt(i) == '-')
                operator = 2;
            if( sb.charAt(i) == '*')
                operator = 3;
            if( sb.charAt(i) == '/')
                operator = 4;

            if( sb.charAt(i) == '+' || sb.charAt(i) == '-' || sb.charAt(i) == '*' || sb.charAt(i)  ==  '/')
            {
                done = false;
                i++;
            }

            if( done )
                sbA.append(sb.charAt(i));

            if( !done )
                sbB.append(sb.charAt(i));

            i++;

            if( i >= sb.length() )
                break;
        }

        int r = 0;
        int n1 = 0, n2 = 0, n3 = 0;

        StringBuilder st = new StringBuilder();
        if( operator == 1 )  // +
        {
            r = str.RomeToArab(sbA.toString()) + str.RomeToArab(sbB.toString());

            if( r > 100 && r < 500 )
            {
                n1 = r / 100;
                n2 = (r / 10%10);
                n3 = n2 * 10 + (r % 10);

                for( i = 0; i < n1; i++ )
                {
                    st.append( str.ArabToRome(100) );
                }

                s = sbA.toString() + " + " + sbB.toString() + " = " + st.toString() + str.ArabToRome(n3);
            }

            if( r > 0 && r < 100 )
            {
                s = sbA.toString() + " + " + sbB.toString() + " = " + st.toString() + str.ArabToRome(r);
            }
        }
        else if ( operator == 2 ) // -
        {
            r = str.RomeToArab(sbA.toString()) - str.RomeToArab(sbB.toString());

            if( r <= 0) return "Sorry programm error";

            if ( r > 0 && r < 100 )
            {
                s = sbA.toString() + " - " + sbB.toString() + " = "+ str.ArabToRome(r);
            }
        }
        else if ( operator == 3 )  // *
        {
            r = str.RomeToArab(sbA.toString()) * str.RomeToArab(sbB.toString());

            if( r > 500 ) return "Sorry program error";

            if( r > 0 && r <= 100 )
            {
                s = sbA.toString() + " * " + sbB.toString() + " = "+ str.ArabToRome(r);
            }

        }
        else if ( operator == 4 ) // /
        {
            r = str.RomeToArab(sbA.toString()) / str.RomeToArab(sbB.toString());

            if( r < 0 ) return "Sorry program error";

            if( r > 0 && r < 100 )
            {
                s = sbA.toString() + " / " + sbB.toString() + " = "+ str.ArabToRome(r);
            }
        }

        return s;
    };
    //----------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Calculator");
        TString tString = new TString();

        while(true)
        {
            System.out.print("Enter(x + y)or(X + XI)or('exit' for exit programm): ");

            String s = scan.nextLine();

            if( s.compareTo("exit") == 0 )
                break;

            StringBuilder strBldr = new StringBuilder( tString.DelSpace(s) );

            if( strBldr.charAt(0) >= 'A' && strBldr.charAt(0) <= 'Z' )
                System.out.println("Res convert: " + calcRome(s));
            else if ( (strBldr.charAt(0) >= '0' && strBldr.charAt(0) <= '9') || (strBldr.charAt(1) >= '0' && strBldr.charAt(1) <= '9'))
                System.out.println("Result: " + calcArab(s));
        }
    }
}
//----------------------------------------------------------------------------------------------------------------------
class TString
{
    //------------------------------------------------------------------------------------------------------------------
    // Delete space in the string;
    String DelSpace(String s)
    {
        StringBuilder sbBuffer = new StringBuilder();
        StringBuilder sb = new StringBuilder(s);

        int i = 0;
        while (true)
        {
            if (i >= sb.length())
                break;
            if (sb.charAt(i) != ' ')
                sbBuffer.append(sb.charAt(i));
            i++;
        }
        return sbBuffer.toString();
    }
    //------------------------------------------------------------------------------------------------------------------
    // Find operation + - / *;
    // '+' == 1   '-' == 2 '/' == 3 '*' == 4
    int ReturnOperator(String s)
    {
        int nOperand = 0;
        StringBuilder sb = new StringBuilder(s);

        int i = 0;
        boolean done = true;
        while(true)
        {
            if( i>= sb.length() )
                break;

            if( sb.charAt(0) >= '0' && sb.charAt(0) <= '9' )
            {
                if (sb.charAt(i) == '+') {
                    nOperand = 1;
                    break;
                } else if (sb.charAt(i) == '-') {
                    nOperand = 2;
                    break;
                } else if (sb.charAt(i) == '/') {
                    nOperand = 3;
                    break;
                } else if (sb.charAt(i) == '*') {
                    nOperand = 4;
                    break;
                }
            }
            else if( sb.charAt(0) == '-')
            {
                if( done )
                {
                    done = false;
                    i++;
                }
                if (sb.charAt(i) == '+') {
                    nOperand = 1;
                    break;
                } else if (sb.charAt(i) == '-') {
                    nOperand = 2;
                    break;
                } else if (sb.charAt(i) == '/') {
                    nOperand = 3;
                    break;
                } else if (sb.charAt(i) == '*') {
                    nOperand = 4;
                    break;
                }
            }

            i++;
        }

        return nOperand;
    }
    //------------------------------------------------------------------------------------------------------------------
    // Find arguments A and B
    String FindArguments(String s)
    {
        StringBuilder sbTemp = new StringBuilder(s);
        StringBuilder sbA = new StringBuilder();
        StringBuilder sbB = new StringBuilder();

        int i = 0;
        boolean bDigit = false;

        if( sbTemp.charAt(0) == '-' )
        {
            sbA.append('-');
        }

        while(true)
        {
            if( sbTemp.charAt(i) >= '0' && sbTemp.charAt(i) <= '9' )
            {
                sbA.append(sbTemp.charAt(i));
                bDigit = true;
            }

            if( bDigit )
            {
                if( sbTemp.charAt(i) >= '*' && sbTemp.charAt(i) >= '/' )
                {
                    i += 2;
                    break;
                }
            }
            i++;

            if( i >= sbTemp.length() )
                break;
        }

        if( sbTemp.charAt(i) == '-')
            sbB.append( sbTemp.charAt(i) );

        while( true )
        {
            if( sbTemp.charAt(i) >= '0' && sbTemp.charAt(i) <= '9' )
            {
                sbB.append( sbTemp.charAt(i) );
            }
            i++;
            if( i >= sbTemp.length() )
                break;
        }


        String str = (sbA + " " + sbB);

        return str;
    }
    //------------------------------------------------------------------------------------------------------------------
    // Convert Rome to Arabic;
    int RomeToArab(String s)
    {
        String[] strArab = {
                " ","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
                "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX",
                "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX",
                "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
                "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
                "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        int i = 0;

        while (true)
        {
            if( strArab[i].compareTo(s) == 0 )
                return i;
            i++;

            if( i >= strArab.length )
            {
                System.out.println("Sorry programm error");
                return 0;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    String ArabToRome( int nIndex)
    {
        String[] strArab = {
                "0","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
                "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX",
                "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX",
                "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
                "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
                "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        if( nIndex >= strArab.length )
        {
            System.out.println("Sorry programm error");
            return "";
        }

        return strArab[nIndex];
    }
}
//----------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------