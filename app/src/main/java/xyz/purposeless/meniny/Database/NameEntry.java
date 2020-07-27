package xyz.purposeless.meniny.Database;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import xyz.purposeless.meniny.R;

@Entity(tableName = "names")
public class NameEntry {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "important")
    public boolean isImportant;

    public NameEntry(String name, Date date, boolean isImportant) {
        this.name = name;
        this.date = date;
        this.isImportant = isImportant;
    }

    public NameEntry(String name, Date date) {
        this.name = name;
        this.date = date;
        this.isImportant = false;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    @NonNull
    @Override
    public String toString() {
        DateFormat dateFormat= new SimpleDateFormat("dd/MM");
        return Resources.getSystem().getString(R.string.hasNameday,
                this.name, dateFormat.format(this.date));
    }
}
