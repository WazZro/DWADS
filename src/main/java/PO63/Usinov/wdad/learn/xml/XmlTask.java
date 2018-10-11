package PO63.Usinov.wdad.learn.xml;

import PO63.Usinov.wdad.learn.xml.Models.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlTask {
    private Path file;
    private boolean isLoaded;
    private Notes notes;

    public Path getFile() {
        return file;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public Notes getNotes() {
        return notes;
    }

    public XmlTask(String path, Class c) throws IOException {
        this.file = Paths.get(path);
        var reader = new StringReader(new String(Files.readAllBytes(file)));


        try {
            this.notes = (Notes) deserialize(reader, Notes.class);
            this.isLoaded = true;
        } catch (JAXBException e) {
            isLoaded = false;
            e.printStackTrace();
        }
    }

    public static Object deserialize(StringReader reader, Class c) throws JAXBException {
        var context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(reader);
    }

    public static void serialize(Object o, Path path, Class c) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(o, Files.newBufferedWriter(path));
    }

    public String getNoteText(Owner owner, String title) {
        var notes = this.notes.getNote();
        for (var n : notes) {
            if (n.getOwner() == owner)
                if (title.equals(n.getTitle()))
                    return n.getText();
        }

        return null;
    }

    public void updateNote(Owner owner, String title, String newText) {
        var notes = this.notes.getNote();

        for (var n : notes) {
            if (n.getOwner() == owner)
                if (title.equals(n.getTitle())) {
                    n.setText(newText);
                    return;
                }
        }
    }

    public void setPrivileges(String noteTitle, User user, int newRight) {
        var notes = this.notes.getNote();
        switch (newRight) {
            case 1:
                user.setRights("R");
                break;
            case 3:
                user.setRights("RW");
                break;
        }

        System.out.println("----------------------" + user.getRights());

        if (newRight > 0) {
            for (var note : notes) {
                if (note.getTitle().equals(noteTitle)) {
                    var privileges = note.getPrivileges().getALLOrUser();
                    for (var p : privileges) {
                        if (user.equals(p)) {
                            ((User) p).setRights(user.getRights());
                            return;
                        }
                    }

                    privileges.add(user);
                    return;
                }
            }
        } else {
            for (var note : notes) {
                if (note.getTitle().equals(noteTitle)) {
                    var privileges = note.getPrivileges().getALLOrUser();
                    int i = 0;
                    for (var p : privileges) {
                        i++;
                        if (user.equals(p))
                            break;
                    }

                    privileges.remove(i);
                    return;
                }
            }
        }
    }
}
