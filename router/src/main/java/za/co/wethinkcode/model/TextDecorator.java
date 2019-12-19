package za.co.wethinkcode.model;

public class TextDecorator {

    public String blue = "co:blue";
    public String green = "co:green";
    public String grey = "co:grey";
    public String purple = "co:purple";
    public String red = "co:red";
    public String teal = "co:teal";
    public String orange = "co:orange";
    public String reset = "co:reset";

    public String viewMessage(String message, String type) {

        green    = "\033[1;38;2;161;221;112m";
        grey     = "\033[1;38;2;120;120;120m";
        red      = "\033[1;38;2;199;12;58m";
        blue     = "\033[1;38;2;40;150;150m";
        purple   = "\033[1;38;2;133;89;165m";
        teal     = "\033[1;38;2;0;189;170m";
        orange   = "\033[1;38;2;244;89;4m";
        reset    = "\u001B[0m";

        String text;
        String mesType = "";
        String replaceString = message;

        if (type.toLowerCase().contains("error") || type.toLowerCase().contains("normal"))
            mesType = (type.toLowerCase().contains("error") ? ("[" + red + "ERROR" + reset + "] ") : ("[" + teal + "FIX-ME" + reset + "] "));

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
