package hmi;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.*;

public class Message {

    //Fields
    private String msgID;
    private String senderID;
    private String receiverID;
    private Date timestampCr;
    private String timestampHr;
    private String dataType;
    private int dataLenByte;
    private JSONObject header;
    private String plainTextContent;
    private byte[] content;

    HMI_utilities hmiUtils = new HMI_utilities();

    //Constructor //TODO Sender ID, receiverID, dataType,
    public Message(String argUser,String argMsg){

        msgID = hmiUtils.generateUUID();
        senderID = "Test_sender";
        receiverID = argUser;
        timestampCr = new Date();
        String pattern = "dd.MM.yy HH:mm";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("de", "DE"));
        timestampHr = simpleDateFormat.format(timestampCr);
        dataType = "utf_8/text";
        plainTextContent = argMsg;
        content = argMsg.getBytes(StandardCharsets.UTF_8);
        dataLenByte=content.length;
        header = new JSONObject();
    }
    public Message(){

    }

/*    //jo851hil TODO check if necessary
    public Message(){

        msgID = hmiUtils.generateUUID();
        senderID = "Test_sender";
        receiverID = "Test_receiver";
        genDate = new Date();
        String pattern = "E dd.mm.yyyy HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("de", "DE"));
        timestamp = simpleDateFormat.format(new Date());
        dataType = "utf_8/text";
        //plainTextContent = "";
        content = argMsg.getBytes(StandardCharsets.UTF_8);
        dataLenByte=content.length;
        header = new JSONObject();
   }
*/
    public String getMsgID(){
        return msgID;
    }
    public String getSenderID(){
        return senderID;
    }
    public String getReceiverID(){
        return receiverID;
    }
    public Date getTimestampCr(){
        return timestampCr;
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
    public String getPlainTextContent() {
        return plainTextContent;
    }
    public JSONObject getHeader() {
        return header;
    }
    public byte[] getContent(){
        return content;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }
    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }
    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }
    public void setTimestampCr(Date timestampCr) {
        this.timestampCr = timestampCr;
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
    public void setPlainTextContent(String plainTextContent) {
        this.plainTextContent = plainTextContent;
    }
    public void setHeader(JSONObject header){
        this.header = header;
    }
    public void setContent(byte[] content){
        this.content = content;
    }


    //TODO Message strukturiert ausdrucken
    public void print(Message message) {

    }

    //TODO Message an ComMessage übergeben, ggf. methodenaufruf um an socket zu übergeben, tbd
    public void send(Message message){

    }

    public byte[] convertJSONToByte(JSONObject jObject){

        byte[] byteArray;
        HMI_utilities hmiUtil = new HMI_utilities();
        byteArray = hmiUtil.StringToBytes(jObject.toString());
        return byteArray;
    }

    public void createJSONFromMessage(){

        this.header.put("msgUuid",this.msgID);
        this.header.put("senderID",this.senderID);
        this.header.put("receiverID",this.receiverID);
        this.header.put("timestamp",this.timestampHr);
        this.header.put("datatype",this.dataType);
        this.header.put("dataLenByte",this.dataLenByte);
    }

    public void createMessageFromJSON(JSONObject jsonObject){

        if(jsonObject!=null) {
            this.msgID = jsonObject.getString("msgUuid");
            this.senderID = jsonObject.getString("senderID");
            this.receiverID = jsonObject.getString("receiverID");
            this.timestampHr = jsonObject.getString("timestamp");
            this.dataType = jsonObject.getString("datatype");
            this.dataLenByte = jsonObject.getInt("dataLenByte");
        }
        else
            System.out.println("Error in createMessageFromJSON: jsonObject = null");
    }

    //Used to generate the byte[] data block to hand over to the comMessage
    public byte[] messageToByteArray(Message message){

        byte[] json_data;
        byte[] message_data;

        json_data = convertJSONToByte(message.header);
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
    }

    public void testMessage(Message message){

        System.out.println(message.msgID +" "+ message.senderID +" "+ message.receiverID);
        System.out.println(message.timestampCr);
        System.out.println(message.dataType +" "+ message.dataLenByte);
    }

    public JSONObject editJSONKey(JSONObject jsonObject){

        jsonObject.put("dataLenByte",10);

        return jsonObject;
    }

    public void print(){
        System.out.println(this.senderID+"@"+this.timestampHr +":\n"
                        +plainTextContent);
    }

}



