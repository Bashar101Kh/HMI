package hmi;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.*;

public class HMI_ThreadMessage {

    //Fields
    private HMI_User sender;   //jo851hil: TODO Ensure that all ThreadMessage references to SenderID and SenderName are references to the HMI_User object
    private String msgUuid;
    private String senderID;
    private String senderName; //jo851hil: currently for test purposes only TODO export senderName to HMI_User class; Implement functionality to share & store HMI_User class
    private String threadID;
    private String threadName; //jo851hil: currently for test purposes only TODO export threadName to Thread class; Implement functionality to share & store Thread class
    private Date genDate;
    private long timestampMillis;
    private String timestamp;
    private String type;
    private int dataLenByte;

    //Fields for data exchange
    private JSONObject header;
    private byte[] content;

    //Constructor
    public HMI_ThreadMessage(){
    }
    public HMI_ThreadMessage(String argMsg, String argThreadID, String argThreadMessage, String argSenderID, String argSenderName){

        msgUuid = HMI_utilities.generateUUID();
        senderID = argSenderID;
        senderName = argSenderName;
        threadID = argThreadID;
        threadName = argThreadMessage;
        genDate = new Date();
        timestampMillis = genDate.getTime();
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("de", "DE"));
        timestamp = simpleDateFormat.format(genDate);
        type = "text";
        content = argMsg.getBytes(StandardCharsets.UTF_8);
        dataLenByte=content.length;
        header = new JSONObject();
    }

    //Constructor for testing
    public HMI_ThreadMessage(String argMsg){

        msgUuid = HMI_utilities.generateUUID();
        sender = Console_Chat.getCurrentHMIUser();
        senderID = "3194345f-5e53-4ae0-8d58-caba45866413";
        senderName = "testSender";
        threadID = "bb008d13-66cc-432f-ab75-f5010eef9163";
        threadName = "testThread";
        genDate = new Date();
        timestampMillis = genDate.getTime();
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("de", "DE"));
        timestamp = simpleDateFormat.format(genDate);
        type = "text";
        content = argMsg.getBytes(StandardCharsets.UTF_8);
        dataLenByte=content.length;
        header = new JSONObject();
        createJSONFromMessage();

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
    public String getTimestamp() {
        return timestamp;
    }
    public String getType(){
        return type;
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
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setType(String type) {
        this.type = type;
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
        System.out.println(this.sender.getName() + "@" + this.timestamp + ":\n"
                + HMI_utilities.byteToString(content));
        System.out.println("");
    }

    //TODO Message an Directive übergeben, ggf. methodenaufruf um an socket zu übergeben, tbd
    public void send(HMI_ThreadMessage HMIThreadMessage){

    }

    public void createJSONFromMessage(){
        JSONObject argsJSON = new JSONObject();

        argsJSON.put("path","/dummy/path");

        this.header.put("msgUuid",this.msgUuid);
        this.header.put("senderID",this.senderID);
        this.header.put("senderName",this.senderName);
        this.header.put("threadID",this.threadID);
        this.header.put("threadName",this.threadName);
        this.header.put("timestamp",this.timestamp);
        this.header.put("timestampMillis",this.timestampMillis);
        this.header.put("type",this.type);
        this.header.put("args",argsJSON);
    }

    public void createMessageFromJSON(JSONObject jsonObject){

        if(jsonObject!=null) {
            this.msgUuid = jsonObject.getString("msgUuid");
            this.senderID = jsonObject.getString("senderID");
            this.senderName = jsonObject.getString("senderName");
            this.threadID = jsonObject.getString("threadID");
            this.threadName = jsonObject.getString("threadName");
            this.timestamp = jsonObject.getString("timestamp");
            this.timestampMillis = jsonObject.getLong("timestampMillis");
            this.type = jsonObject.getString("type");
            this.header = jsonObject;
        }
        else
            System.out.println("Error in createMessageFromJSON: jsonObject = null");
    }

    //Create a Message Object from the comMessage byte[] data block
    public void getThreadMessageFromHmiDirective(HMI_Directive hmiDirective){

        JSONObject  messageJSON = null;
        byte[] dirData;
        String dirDataString;
        String threadMessageHeaderString;
        String threadMessageDataString;
        int arrayPos;

        //Get data and convert data byte array to string
        dirData = hmiDirective.getData();
        dirDataString = HMI_utilities.byteToString(dirData);

        //Get last index of JSON String ('}'), Split dirDataString at this index into two separate strings
        arrayPos = HMI_utilities.getJSONIndexFromString(dirDataString);
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
    public byte[] messageToByte(){

        byte[] json_data;
        byte[] message_data;

        json_data = jsonToByte(this.header);
        message_data = this.content;

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
        byteArray = HMI_utilities.stringToBytes(jsonObject.toString());
        return byteArray;
    }


}



