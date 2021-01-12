package hmi;
import org.json.*;


public class ComMessage {

    private JSONObject jsonObject;
    private byte[] data;

    public JSONObject getJsonObject(){
        return jsonObject;
    }

    public byte[] getData(){
        return data;
    }

    private void setJsonObject(JSONObject jsonObject){
        this.jsonObject=jsonObject;
    }

    private void setData(byte[] data){
        this.data=data;
    }

    //TODO
    //Constructor
    public ComMessage() {

    }

}
