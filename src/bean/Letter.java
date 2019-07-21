package bean;

import java.sql.Timestamp;


public class Letter {
    private int letterID;
    private int senderID;
    private String senderName;
    private int receiverID;
    private String receiveName;
    private Timestamp timeReleased;
    private String contents;
    private int status;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public int getLetterID() {
        return letterID;
    }

    public void setLetterID(int letterID) {
        this.letterID = letterID;
    }

    public Letter() {
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public Timestamp getTimeReleased() {
        return timeReleased;
    }

    public void setTimeReleased(Timestamp timeReleased) {
        this.timeReleased = timeReleased;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }


}
