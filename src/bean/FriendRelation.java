package bean;

import dao.factory.DAOFactory;

import java.util.List;

public class FriendRelation {
    private int friendID;
    private int patronID;
    private int clientID;
    private User client;
    private User patron;
    private int accepted;

    public User getPatron() {
        return patron;
    }

    public void setPatron(User patron) {
        this.patron = patron;
    }

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

    public void acceptRequest() {
        accepted = 1;
        DAOFactory.getIFriendRelationDAOInstance().update(this);
        List<FriendRelation> otherFriends = DAOFactory.getIFriendRelationDAOInstance().getFriends("SELECT * FROM friends WHERE patronID=" + "'" + clientID + "'" + " AND clientID='" + patronID + "'");
        if (otherFriends.isEmpty()) {
            FriendRelation newFriend = new FriendRelation();
            newFriend.setPatronID(clientID);
            newFriend.setClientID(patronID);
            newFriend.setAccepted(1);
            DAOFactory.getIFriendRelationDAOInstance().insert(newFriend);
        } else {
            FriendRelation newFriend = otherFriends.get(0);
            newFriend.setAccepted(1);
            DAOFactory.getIFriendRelationDAOInstance().update(newFriend);
        }
    }
}
