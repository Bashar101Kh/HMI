package hmi;
import org.json.*;


public class ComMessage {

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
    public ComMessage(JSONObject header, byte[] data) {

    }
}


/***
 * call
 * ComMessage comMessage = new ComMessage(sendMessage.getHeader(),sendMessage.getContent());
 ***/