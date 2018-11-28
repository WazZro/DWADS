package PO63.Usinov.wdad.data.managers;

import PO63.Usinov.wdad.learn.xml.Models.Notes;
import PO63.Usinov.wdad.learn.xml.Models.User;
import PO63.Usinov.wdad.learn.xml.XmlTask;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class XmlDataManagerImpl implements XmlDataManager {
    private XmlTask task;

    public void init() throws IOException {
        task = new XmlTask("rmi.xml", Notes.class);
    }

    @Override
    public String getNoteText(User owner, String title) {
        return task.getNoteText(owner, title);
    }

    @Override
    public void updateNote(User owner, String title, String newText) {
        task.updateNote(owner, title, newText);
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRight) {
        task.setPrivileges(noteTitle, user, newRight);
    }

    @Override
    public Notes getNotes() {
        return task.getNotes();
    }

    @Override
    public void save() throws IOException {
        task.save();
    }
}
