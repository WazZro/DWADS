package PO63.Usinov.wdad;

import PO63.Usinov.wdad.learn.xml.Models.User;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        PO63.Usinov.wdad.learn.rmi.Client client = new PO63.Usinov.wdad.learn.rmi.Client();
        var o = client.main();
        User u = new User();
        u.setName("gopnik");
        u.setMail("gop@gop.ru");
        o.setPrivileges("Note2", u, 1);
        o.save();
    }
}
