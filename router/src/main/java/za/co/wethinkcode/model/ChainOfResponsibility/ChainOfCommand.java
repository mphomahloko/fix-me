package za.co.wethinkcode.model.ChainOfResponsibility;
import za.co.wethinkcode.model.*
        ;

public abstract class ChainOfCommand {

    //create a decoder that will break up shit here
    //that will recieve sub fixed  messages here
    //to figured out what transaction where made


    public static void HandleChain(String request)
    {
        Decoder decoder = new Decoder();
    }

}
