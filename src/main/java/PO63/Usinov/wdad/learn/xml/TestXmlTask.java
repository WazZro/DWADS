package PO63.Usinov.wdad.learn.xml;

import PO63.Usinov.wdad.learn.xml.Models.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TestXmlTask {
    public static void main(String[] args) {
        System.setProperty("javax.xml.accessExternalDTD", "all");
        Path path = Paths.get("/home/lirael/IdeaProjects/DWADS/src/main/java/PO63/Usinov/wdad/learn/xml/test.xml");
        System.out.println(path);
        System.out.println(Files.exists(path));
        try {
            XmlTask loader = new XmlTask("/home/lirael/IdeaProjects/DWADS/src/main/java/PO63/Usinov/wdad/learn/xml/test.xml", Notes.class);
            var notes = loader.getNotes();
            out(notes);

            User u = new User();
            u.setName("gopnik");
            u.setMail("gop@gop.ru");
            loader.setPrivileges("Note2", u, 1);

            out(notes);

            XmlTask.serialize(notes, path, Notes.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void out(Notes notes) {
        notes.getNote().stream().forEach(s -> {
            System.out.println(s.getTitle() + ":");
            System.out.println("     Owner: " + s.getOwner().getName() + " " + s.getOwner().getMail());
            System.out.println("     Privileges:");
            s.getPrivileges().getALLOrUser().stream().forEach(p -> {
                if (p instanceof ALL)
                    System.out.println("        ALL = " + ((ALL) p).getRights());
                else
                    System.out.println("        " + ((User) p).getMail() + " = " + ((User) p).getRights());
            });
        });
    }
}
