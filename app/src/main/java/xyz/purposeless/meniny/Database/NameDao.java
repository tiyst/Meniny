package xyz.purposeless.meniny.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface NameDao {

    @Query("SELECT * FROM names")
    List<NameEntry> getAll();

    @Query("SELECT * FROM names ORDER BY date ASC")
    List<NameEntry> getAllOrdered();

    @Query("SELECT * FROM names WHERE name in (:names)")
    List<NameEntry> getByNames(String[] names);

    @Query("SELECT * FROM names WHERE date IS (:sDate)")
    NameEntry getByDate(Date sDate);

    @Query("SELECT * FROM names WHERE important")
    List<NameEntry> getImportant();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(NameEntry... names);

    @Delete
    void delete(NameEntry name);

    @Query("DELETE FROM names")
    void deleteAll();

}
