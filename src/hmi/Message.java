package hmi;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.*;

public class Message {


    private String msgUuid;
    private String senderID;
    private String receiverID;
    private Date timestamp;
    private String strDate;
    private String dataType;
    private int dataLenByte;
    private byte[] content;

    HMI_utilities hmiUtils = new HMI_utilities();

    //Constructor
    public Message(){

        msgUuid = hmiUtils.generateUUID();
        senderID = "Test_sender";
        receiverID = "Test_receiver";
        timestamp = new Date();
        String pattern = "E dd.mm.yyyy HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("de", "DE"));
        strDate = simpleDateFormat.format(new Date());
        dataType = "utf_8/text";
        content = "Sample Text".getBytes(StandardCharsets.UTF_8);
        dataLenByte=content.length;
    }

    public String getMsgUuid(){
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
    public String getStrDate() {return strDate; };
    public String getDataType(){
        return dataType;
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
    public void setStrDate(String strDate) { this.strDate = strDate; }
    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public JSONObject createJSONFromMessage(Message message){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("msgUuid",message.msgUuid);
        jsonObject.put("senderID",message.senderID);
        jsonObject.put("receiverID",message.receiverID);
        jsonObject.put("timestamp",message.strDate);
        jsonObject.put("datatype",message.dataType);
        jsonObject.put("dataLenByte",message.dataLenByte);

        return jsonObject;
    }

    public void createMessageFromJSON(JSONObject jsonObject){

        this.msgUuid = jsonObject.getString("msgUuid");
        this.senderID = jsonObject.getString("senderID");
        this.receiverID = jsonObject.getString("receiverID");
        this.strDate = jsonObject.getString("timestamp");
        this.dataType = jsonObject.getString("datatype");
        this.dataLenByte = jsonObject.getInt("dataLenByte");
    }

    public byte[] messageToByteArray(JSONObject jsonObject, Message message){

        byte[] json_data;
        byte[] message_data;

        json_data = convertJSONToByte(jsonObject);
        message_data = message.content;

        byte[] combinedByteArray = new byte[json_data.length + message_data.length];
        ByteBuffer buff = ByteBuffer.wrap(combinedByteArray);
        buff.put(json_data);
        buff.put(message_data);
        byte[] data = buff.array();

        return data;
    }

    /****************************************************************
     * FOR TESTING
     ****************************************************************/

    public void testJSON(){

        byte[] receivedByteArray;
        HMI_utilities hmiUtil = new HMI_utilities();
        System.out.println(hmiUtil.BinToString(convertJSONToByte(createJSONFromMessage(this))));
    }

    public void testMessage(Message message){

        System.out.println(message.msgUuid +" "+ message.senderID +" "+ message.receiverID);
        System.out.println(message.timestamp);
        System.out.println(message.dataType +" "+ message.dataLenByte);
    }

    public JSONObject editJSONKey(JSONObject jsonObject){

        jsonObject.put("dataLenByte",10);

        return jsonObject;
    }

}



