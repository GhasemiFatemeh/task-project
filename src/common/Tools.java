package common;

public class Tools {
    private static Tools tools = new Tools();
    public static Tools getInstance() {return tools;}
    private Tools(){}

    public String convertToNormalBreakLine(String inputTextQuery)
    {
        return inputTextQuery ;// inputTextQuery.replace("/n/r",System.lineSeparator());
    }
}
