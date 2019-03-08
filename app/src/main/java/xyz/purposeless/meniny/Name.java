package xyz.purposeless.meniny;

import java.text.Normalizer;

public class Name {

    private String date;
    private String name;
    private boolean isEvent; //End of year etc

    //These names will be coloured and on top of all names in ListView
    private boolean important; //TODO - implement this

    public Name(String dat, String name) {
        this.date = convertDate(dat);
        this.name = name;
        this.isEvent = false;
    }

    public Name(String dat, String name, boolean isEvent) {
        this.date = convertDate(dat);
        this.name = name;
        this.isEvent = true;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean evaluate(String nam) {
        nam = Normalizer.normalize(nam, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
        String tName = Normalizer.normalize(this.name, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();


        return tName.contains(nam);
        /*if (tName.contains(nam)) {
            return true;
        }
        return false;*/
    }

    public String convertDate(String foreignDate) {
        String[] dat = foreignDate.split("-");

        return dat[1] + "/" + dat[0];
    }

    @Override
    public String toString() {
        return (this.name + " má meniny dňa: " + this.date);
    }

    public void setEvent(boolean event) {
        isEvent = event;
    }

    public boolean isEvent() {
        return isEvent;
    }
}
