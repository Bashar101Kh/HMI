package hmi;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.*;

public class ThreadMessage {

    //Fields
    private String msgUuid;
    private String senderID;
    private String senderName;
    private String threadID;
    private String threadName;
    private Date genDate;
    private long timestampMillis;
    private String timestampHr;
    private String dataType;
    private int dataLenByte;

    //Fields for data exchange
    private JSONObject header;
    private byte[] content;

    HMI_utilities hmiUtils = new HMI_utilities();

    //Constructor //TODO Sender ID, receiverID, dataType,
    public ThreadMessage(){

    }
    public ThreadMessage(String argMsg, String argThreadID, String argThreadMessage, String argSenderID, String argSenderName){

        msgUuid = hmiUtils.generateUUID();
        senderID = argSenderID;
        senderName = argSenderName;
        threadID = argThreadID;
        threadName = argThreadMessage;
        genDate = new Date();
        timestampMillis = genDate.getTime();
        String pattern = "dd.MM.yy HH:mm";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("de", "DE"));
        timestampHr = simpleDateFormat.format(genDate);
        dataType = "text";
        content = argMsg.getBytes(StandardCharsets.UTF_8);
        dataLenByte=content.length;
        header = new JSONObject();
    }

    //Get and Set Methods
    public String getMsgUuid(){
        return msgUuid;
    }
    public String getSenderID(){
        return senderID;
    }
    public String getThreadID(){
        return threadID;
    }
    public Date getGenDate(){
        return genDate;
    }
    public String getTimestampHr() {
        return timestampHr;
    }
    public String getDataType(){
        return dataType;
    }
    public int getDataLenByte(){
        return dataLenByte;
    }
    public JSONObject getHeader() {
        return header;
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
    public void setThreadID(String threadID) {
        this.threadID = threadID;
    }
    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }
    public void setTimestampHr(String timestampHr) {
        this.timestampHr = timestampHr;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public void setDataLenByte(int dataLenByte) {
        this.dataLenByte = dataLenByte;
    }
    public void setHeader(JSONObject header){
        this.header = header;
    }
    public void setContent(byte[] content){
        this.content = content;
    }


    //Message strukturiert ausdrucken
    public void print() {
        System.out.println(this.senderID + "@" + this.timestampHr + ":\n"
                + hmiUtils.bytesToString(content));
    }

    //TODO Message an Directive übergeben, ggf. methodenaufruf um an socket zu übergeben, tbd
    public void send(ThreadMessage threadMessage){

    }

    public void createJSONFromMessage(){
        JSONObject argsJSON = new JSONObject();
        JSONObject vclocksJSON = new JSONObject();

        vclocksJSON.put("user",0);
        vclocksJSON.put("device",0);
        argsJSON.put("path","dummy text");

        this.header.put("msgUuid",this.msgUuid);
        this.header.put("senderID",this.senderID);
        this.header.put("senderName",this.senderName);
        this.header.put("threadID",this.threadID);
        this.header.put("threadName",this.threadName);
        this.header.put("vclocks",vclocksJSON);
        this.header.put("timestampHr",this.timestampHr);
        this.header.put("timestampMillis",this.timestampMillis);
        this.header.put("type",this.dataType);
        this.header.put("args",argsJSON);
    }

    public void createMessageFromJSON(JSONObject jsonObject){

        if(jsonObject!=null) {
            this.msgUuid = jsonObject.getString("msgUuid");
            this.senderID = jsonObject.getString("senderID");
            this.senderName = jsonObject.getString("sendereName");
            this.threadID = jsonObject.getString("threadID");
            this.threadName = jsonObject.getString("threadName");
            this.timestampHr = jsonObject.getString("timestampHr");
            this.timestampMillis = jsonObject.getLong("timestampMillis");
            this.dataType = jsonObject.getString("type");
            this.header = jsonObject;
        }
        else
            System.out.println("Error in createMessageFromJSON: jsonObject = null");
    }

    //Create a Message Object from the comMessage byte[] data block
    public void genThreadMessageFromHmiDirectory(HMI_Directive hmiDirective){

        JSONObject  messageJSON = null;
        byte[] dirData;
        String dirDataString;
        String threadMessageHeaderString;
        String threadMessageDataString;
        int arrayPos;

        //Get data and convert data byte array to string
        dirData = hmiDirective.getData();
        dirDataString = hmiUtils.bytesToString(dirData);

        //Get last index of JSON String ('}'), Split dirDataString at this index into two separate strings
        arrayPos = hmiUtils.getJSONIndexFromString(dirDataString);
        threadMessageHeaderString = dirDataString.substring(0,arrayPos+1);
        //TODO if empty byte array is sent, make sure no null string is read
        threadMessageDataString = dirDataString.substring(arrayPos+1,dirDataString.length());

        //Convert header String into JSONObject
        //TODO try catch, proper catch logic
        try {
            messageJSON = new JSONObject(threadMessageHeaderString);
        }catch(JSONException err){
            System.out.println("Exception while creating JSONObject from comMessage");
        }

        //Set the Message parameters according to JSONObject
        this.createMessageFromJSON(messageJSON);
        this.setContent(threadMessageDataString.getBytes(StandardCharsets.UTF_8));

    }

    //Generate  byte[] data block to hand over to the Directive
    public byte[] messageToByte(ThreadMessage threadMessage){

        byte[] json_data;
        byte[] message_data;

        json_data = jsonToByte(threadMessage.header);
        message_data = threadMessage.content;

        byte[] combinedByteArray = new byte[json_data.length + message_data.length];
        ByteBuffer buff = ByteBuffer.wrap(combinedByteArray);
        buff.put(json_data);
        buff.put(message_data);
        byte[] data = buff.array();

        return data;
    }

    //Convert JSONObject String to byte[]
    public byte[] jsonToByte(JSONObject jsonObject){

        byte[] byteArray;
        HMI_utilities hmiUtil = new HMI_utilities();
        byteArray = hmiUtil.stringToBytes(jsonObject.toString());
        return byteArray;
    }


}



