package hmi;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.json.*;


public class Message {

    private String msgUuid;

    private String senderID;

    private String receiverID;

    private Date timestamp;

    private String datatype;

    private int dataLenByte;

    private byte[] content;

    public Message(){
        msgUuid ="0";
        senderID="Test_sender";
        receiverID="Test_receiver";
        timestamp= new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("E dd.MM.yyyy 'at' hh:mm:ss a zzz");
        datatype="utf_8/text";
        dataLenByte=0;
        content="Sample Text".getBytes(StandardCharsets.UTF_8);
    }



    public String getUUID(){
        return msgUuid;
    }

    public String getSenderID(){
        return senderID;
    }

    public String getReceiverID(){
        return receiverID;
    }

    public Date getTimestamp(){
        return timestamp;
    }

    public String getDatatype(){
        return datatype;
    }

    public int getDataLenByte(){
        return dataLenByte;
    }

    public byte[] getContent(){
        return content;
    }

    public void setMsgUuid(String msgUuid) {
        this.msgUuid = msgUuid;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public void setDataLenByte(int dataLenByte) {
        this.dataLenByte = dataLenByte;
    }

    public void setContent(byte[] content){
        this.content = content;
    }

    //   public Message sendMessage(String message){

    //       Message theMessage = new Message();

    //   return theMessage;
    //   }

    //   public String print(Message message){

    //   }

    //  public void send(Message message){

    //  }

    public byte[] convertJSONToByte(JSONObject jObject){

        byte[] byteArray;
        HMI_utilities hmiUtil = new HMI_utilities();

        byteArray = hmiUtil.StringToBin(jObject.toString());

        return byteArray;
    }

    public JSONObject createJSONfromMessage(Message message){

        JSONObject jObject = new JSONObject();

        jObject.put("datatype",message.datatype);

        convertJSONToByte(jObject);

        return jObject;
    }

    public void testJSON(JSONObject jObject){

        byte[] receivedByteArray;
        HMI_utilities hmiUtil = new HMI_utilities();

        jObject.put("key1","theKey1");
        jObject.put("key2","theKey2");

        receivedByteArray = convertJSONToByte(jObject);

    }
}



