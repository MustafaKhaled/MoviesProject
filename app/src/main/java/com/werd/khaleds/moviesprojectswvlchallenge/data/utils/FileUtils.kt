package com.werd.khaleds.moviesprojectswvlchallenge.data.utils

import android.R.attr.key
import android.content.Context
import android.util.Log
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import java.io.*


class FileUtils {
    private val TAG = javaClass.simpleName
    private val fileName = "movies.txt"
    public fun writeFile(moviesLocalResult: MoviesLocalResult){
        val fos: FileOutputStream = MyApplication.appContext.openFileOutput(fileName, Context.MODE_PRIVATE)
        val oos = ObjectOutputStream(fos)
        oos.writeObject(moviesLocalResult)
        oos.close()
        fos.close()
        Log.d(TAG,"File saved")

    }

    public fun readFile(): MoviesLocalResult{
            val fis: FileInputStream = MyApplication.appContext.openFileInput(fileName)
            val ois = ObjectInputStream(fis)
        Log.d(TAG,"File retrieved")
        return ois.readObject() as MoviesLocalResult
    }

}