package com.example.inventory.data

import androidx.room.Dao//impor @Dao dari library Room
import androidx.room.Delete//impor Delete untuk menghapus data dari database
import androidx.room.Insert//impor Insert untuk memasukkan data ke database
import androidx.room.OnConflictStrategy//impor strategi konflik untuk menangani duplikasi
import androidx.room.Query//impor Query untuk menuliskan perintah SQL
import androidx.room.Update//impor Update untuk memperbarui data
import kotlinx.coroutines.flow.Flow//impor Flow untuk mendukung pemrosesan data yang asinkron

@Dao//memberi tanda bahwa interface ini sebagai Data Access Object (DAO) untuk Room
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)//menyisipkan data baru dan mengabaikan jika ada duplikasi data
    suspend fun insert(item: Item)

    @Update//memperbarui data yang ada
    suspend fun update(item: Item)

    @Delete//menghapus data yang ada
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id")//mengambil item dari database berdasarkan id yang diberikan
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")//mengambil semua item dari database dan mengurutkan berdasarkan nama secara ascending
    fun getAllItems(): Flow<List<Item>>
}