import android.content.Context//impor Context untuk mendapatkan akses ke konteks aplikasi
import androidx.room.Database//impor Database dari Room untuk mendefinisikan database
import androidx.room.Room//impor Room untuk membuat instance database
import androidx.room.RoomDatabase//impor RoomDatabase sebagai kelas dasar dari database Room
import com.example.inventory.data.Item//impor kelas Item untuk digunakan sebagai entitas dalam database
import com.example.inventory.data.ItemDao//impor interface ItemDao untuk akses ke operasi database

@Database(entities = [Item::class], version = 1, exportSchema = false)//menentukan entitas dalam database, versi, dan mengatur agar schema tidak diekspor
abstract class InventoryDatabase : RoomDatabase() {//mendeklarasikan InventoryDatabase sebagai kelas abstrak yang merupakan subclass dari RoomDatabase

    abstract fun itemDao(): ItemDao//fungsi abstract untuk mendapatkan instance ItemDao

    companion object {//membuat InventoryDatabase sebagai singleton
        @Volatile//memastikan tiap perubahan langsung terlihat oleh thread
        private var Instance: InventoryDatabase? = null//menyimpan instance database dalam variabel yang aman untuk akses bersama antar-thread

        fun getDatabase(context: Context): InventoryDatabase {//mendapatkan instance database
            //jika instance sudah ada, maka dikembalikan, jika tidak, maka buat instance baru
            return Instance ?: synchronized(this) {//mensinkronisasi agar hanya satu instance yang dibuat pada satu waktu
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")//membuat instance database baru
                    .build()//membangun instance database
                    .also { Instance = it }//menyimpan instance yang dibuat ke dalam variabel Instance
            }
        }
    }
}