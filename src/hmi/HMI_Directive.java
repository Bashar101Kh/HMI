package hmi;
import org.json.*;

/**
 * API Commands
 *  this commands in the list are to be sent with each ComMessage
 *  to specify to the other Groups what we are requesting
 *  0x10    send
 *  0x20    Sync
 *  0x30    get
 *  0x40    userLoggedIn
 *  0x50    newUser_registered
 */

public class HMI_Directive {

    //Fields
    private JSONObject header;
    private byte[] data;

    //Methods
    public JSONObject getHeader(){
        return header;
    }
    public byte[] getData(){
        return data;
    }

    private void setHeader(JSONObject jsonObject){
        this.header=jsonObject;
    }
    private void setData(byte[] data){ this.data=data; }

    //TODO
    //Constructor
    public HMI_Directive(JSONObject header, byte[] data) {

        this.header=header;
        this.data=data;

    }

    //For Testing ONLY
    public HMI_Directive(byte[] data) {
        this.data=data;
    }

    //
    public JSONObject extractHeaderIPC(HMI_Directive HMIDirective){

        //extract JSONObject header and read keys
        JSONObject recHeader = new JSONObject();

        //TODO

        return recHeader;

    }




}


/***
 * call
 * ComMessage comMessage = new ComMessage(sendMessage.getHeader(),sendMessage.getContent());
 ***/