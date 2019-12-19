package za.co.wethinkcode.model;

public class TextDecorator {

    public String green = "co:green";
    public String grey = "co:grey";
    public String red = "co:red";
    public String blue = "co:blue";
    public String purple = "co:purple";
    public String reset = "co:reset";

    public String viewMessage(String message, String type) {

        green    = "\033[1;38;2;162;184;36m";
        grey     = "\033[1;38;2;120;120;120m";
        red      = "\033[1;38;2;255;107;104m";
        blue     = "\033[1;38;2;83;148;236m";
        purple   = "\033[1;38;2;169;125;250m";
        reset    = "\u001B[0m";

        String text;
        String mesType = "";
        String replaceString = message;

        if (type.toLowerCase().contains("error") || type.toLowerCase().contains("normal"))
            mesType = (type.toLowerCase().contains("error") ? ("[" + red + "ERROR" + reset + "] ") : ("[" + blue + "FIX-ME" + reset + "] "));

        replaceString = replaceString.replace("co:blue", blue);
        replaceString = replaceString.replace("co:grey", grey);
        replaceString = replaceString.replace("co:green", green);
        replaceString = replaceString.replace("co:purple", purple);
        replaceString = replaceString.replace("co:red", red);
        replaceString = replaceString.replace("co:reset", reset);


        text = mesType + replaceString;

        return text;

    }
}
