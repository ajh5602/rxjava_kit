package com.ash.retrofit.http;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reqNo = new String();
    private String resultCode = new String();
    private String resultMessage = new String();
    private Map<String, String> resultDetail = new HashMap<String, String>();

    public ResData() {
    }

    public ResData(String reqNo, String resultCode, String resultMessage) {
        this.reqNo = reqNo;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeUTF(reqNo);
        out.writeUTF(resultCode);
        out.writeUTF(resultMessage);
        out.writeObject(resultDetail);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        reqNo = in.readUTF();
        resultCode = in.readUTF();
        resultMessage = in.readUTF();
        resultDetail = (Map<String, String>)in.readObject();
    }

    private void readObjectNoData() throws ObjectStreamException {
    }

    public String getReqNo() {
        return reqNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public Map<String, String> getResultDetail() {
        return resultDetail;
    }
}

/*
public class Data {
    private final int userId;
    private final int id;
    private final String title;
    private final String body;

    public Data(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
*/
