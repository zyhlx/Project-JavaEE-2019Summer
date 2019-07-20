package bean;

public class FriendRelation {
    private int friendID;
    public int patronID;
    public int clientID;
    public User client;

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
