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

    public void save() throws IOException {
        try {
            serialize(notes, file, Notes.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String getNoteText(User owner, String title) {
        var note = this.notes.find(owner, title);

        if (note != null)
            return note.getText();

        return null;
    }

    public void updateNote(User owner, String title, String newText) {
        var note = this.notes.find(owner, title);

        if (note != null)
            note.update(newText);
    }

    public void setPrivileges(String noteTitle, User user, int newRight) {
        notes.setPrivileges(noteTitle, user, newRight);
    }
}
