package PO63.Usinov.wdad.learn.xml.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "note"
})
@XmlRootElement(name = "notes")
public class Notes implements Serializable {

    protected List<Note> note;

    /**
     * Gets the value of the note property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the note property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNote().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     */
    public List<Note> getNote() {
        if (note == null) {
            note = new ArrayList<>();
        }
        return this.note;
    }

    public Note find(User owner, String title) {
        for (var n : note) {
            if (owner.equals(n.owner))
                if (title.equals(n.getTitle()))
                    return n;
        }

        return null;
    }

    public Note find(String title) {
        for (var n : note) {
            if (title.equals(n.getTitle()))
                return n;
        }

        return null;
    }

    public void setPrivileges(String title, User user, int newRight) {
        switch (newRight) {
            case 1:
                user.setRights("R");
                break;
            case 3:
                user.setRights("RW");
                break;
        }

        if (newRight > 0) {
            var note = find(title);
            if (note != null) {
                var privileges = note.getPrivileges().getALLOrUser();
                for (var p : privileges) {
                    if (user.equals(p)) {
                        ((User) p).setRights(user.getRights());
                        return;
                    }
                }

                privileges.add(user);
            }
        } else {
            var note = find(title);
            if (note != null) {
                var privileges = note.getPrivileges().getALLOrUser();
                int i = 0;
                for (var p : privileges) {
                    i++;
                    if (user.equals(p))
                        break;
                }

                privileges.remove(i);
            }
        }
    }

}
