package com.example.parcial
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "book_table")
class Book(@ColumnInfo(name = "title") var title: String,
            @ColumnInfo(name="description") var description:String
           ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
