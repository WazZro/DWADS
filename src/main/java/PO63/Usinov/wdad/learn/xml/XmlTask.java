package PO63.Usinov.wdad.learn.xml;

import PO63.Usinov.wdad.learn.xml.Models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlTask{
    private Path xmlPath;

    public XmlTask(String path){
        xmlPath = Paths.get(path);
        try {
            var xml = Files.readAllBytes(xmlPath);
            String x = new String(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNoteText(User owner, String title) {
        return null;
    }

    public void updateNote(User owner, String title, String newText) {

    }

    public void setPrivileges(String noteTitle, User user, int newRight) {

    }
}
