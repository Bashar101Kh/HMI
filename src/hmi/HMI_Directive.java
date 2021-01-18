package hmi;
import org.json.*;

/**
 * API Commands
 * https://gitlab.ei.htwg-konstanz.de/akrupp/20ws-sysarch/-/wikis/ipc_message.md
 */

public class HMI_Directive {

    //Fields
    private JSONObject header;
    private byte[] data;

    //Methods
    public JSONObject getHeader() {
        return header;
    }
    public byte[] getData() {
        return data;
    }

    private void setHeader(JSONObject jsonObject) {
        this.header = jsonObject;
    }
    private void setData(byte[] data) {
        this.data = data;
    }

    //TODO
    //Constructor
    public HMI_Directive(JSONObject header, byte[] data) {

        this.header = header;
        this.data = data;
    }

    //For Testing ONLY
    public HMI_Directive(byte[] data) {
        this.data = data;
    }

    //
    public JSONObject extractHeaderIPC(HMI_Directive HMIDirective) {

        //extract JSONObject header and read keys
        JSONObject recHeader = new JSONObject();

        //TODO
        return recHeader;
    }
}

