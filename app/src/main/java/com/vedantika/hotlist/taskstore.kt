package com.vedantika.hotlist
import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class taskstore {
    val filename="tasklist.dat"
    fun writedata(tasklistitem:ArrayList<taskitem>,context: Context){
        val fos: FileOutputStream = context.openFileOutput(filename,Context.MODE_PRIVATE)
        var oas = ObjectOutputStream(fos)
        oas.writeObject(tasklistitem)
        oas.close()
    }
    fun readData(context: Context):ArrayList<taskitem>{
        var taskitemlist:ArrayList<taskitem>
        return try {
            var fis: FileInputStream= context.openFileInput(filename)
            var ois= ObjectInputStream(fis)
            taskitemlist= ois.readObject() as ArrayList<taskitem>
            ois.close()
            taskitemlist
        }catch (e: FileNotFoundException){
            e.printStackTrace()
            ArrayList()
        }catch (e:Exception){
            e.printStackTrace()
            context.deleteFile(filename)
            ArrayList()
        }
    }
}