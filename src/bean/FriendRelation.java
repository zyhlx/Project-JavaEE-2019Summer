package bean;

public class FriendRelation {
    private int friendID;
    private int patronID;
    private int clientID;
    private User client;
    private int accepted;

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getClientID() {
        return clientID;
    }

    public int getFriendID() {
        return friendID;
    }

    public int getPatronID() {
        return patronID;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public void setPatronID(int patronID) {
        this.patronID = patronID;
    }
}
