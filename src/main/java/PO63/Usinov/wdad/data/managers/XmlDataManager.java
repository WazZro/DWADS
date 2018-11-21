package PO63.Usinov.wdad.data.managers;

import PO63.Usinov.wdad.learn.xml.Models.Notes;
import PO63.Usinov.wdad.learn.xml.Models.User;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface XmlDataManager extends Remote {
    String getNoteText(User owner, String title) throws RemoteException;
    void updateNote(User owner, String title, String newText) throws RemoteException;
    void setPrivileges(String noteTitle, User user, int newRight) throws RemoteException;
    Notes getNotes() throws RemoteException;
    void save() throws IOException;
}
