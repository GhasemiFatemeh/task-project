package controller;

public class Validation {
    private Validation(){}
    private static Validation validation = new Validation();
    public static Validation getInstance()
    {
        return validation;
    }

    public  String protectFromHtmlInjection(String input) {
        if (input == null) {
            return null;
        } else {
            int length = input.length();
            StringBuilder output = allocateStringBuilder(length);

            for(int i = 0; i < length; ++i) {
                char c = input.charAt(i);
                switch(c) {
                    case '"':
                        output.append("&;");
                        break;
                    case '&':
                        output.append("&amp;");
                        break;
                    case '\'':
                        output.append("&#39;");
                        break;
                    case '<':
                        output.append("&lt;");
                        break;
                    case '>':
                        output.append("&gt;");
                        break;
                    default:
                        output.append(c);
                }
            }

            return output.toString();
        }
    }

    private  StringBuilder allocateStringBuilder(int length) {
        int buflen = length;
        if (length * 2 > 0) {
            buflen = length * 2;
        }
        return new StringBuilder(buflen);
    }
}
