package com.example.memeryactivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memeryactivity.room.User
import com.example.memeryactivity.room.UserRoomDatabase
import java.io.File
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var decodeResource: Bitmap? =
            BitmapFactory.decodeResource(resources, R.mipmap.goolgetestcsee)
        val iv = findViewById<ImageView>(R.id.iv)
        findViewById<TextView>(R.id.tvBtn).setOnClickListener {
//            Thread.sleep(2000)
//            startActivity(Intent(this, MainActivity4::class.java))
//            openFile()
//            if (decodeResource == null) {
//                iv.setImageBitmap(null)
//            } else if (!decodeResource!!.isRecycled) {
//                iv.setImageBitmap(decodeResource)
//            }

            thread {
                val userDao = UserRoomDatabase.getDatabase(this).userDao()
                userDao.insertUser(User("lida1", 18))
                Log.e("tag1", "${userDao.getUserByName("lida1")}")
                userDao.updateUser(User("lida1", 22))
                Log.e("tag1", "${userDao.getUserByName("lida1")}")
//                userDao.deleteUser(User("lida", 0))
//                Log.e("tag1", "${userDao.getUserByName("lida")}")
            }
        }
        findViewById<TextView>(R.id.tvBtn3).setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("data", "abc")
            startActivity(intent)
        }
//        decodeResource=null
//        test1()
//        test2()
//        test3()

        Log.e("tag1", "onCrate")
    }

    fun openFile() {
        try {
            val filePath =
                "/storage/emulated/0/Android/data/com.example.memeryactivity/files"
            val createFile = createFile(filePath, "aaa.bin")
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.action = Intent.ACTION_VIEW
            intent.setDataAndType(
                Uri.fromFile(File(filePath, "aaa.bin")),
                "application/octet-stream"
            )
            startActivity(intent)
            Intent.createChooser(intent, "请选择对应的软件打开")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun createFile(dirPath: String?, fileName: String?): File? {
        return try {
            val dirFile = File(dirPath)
            if (!dirFile.exists()) {
                if (!createFileDir(dirFile)) {
                    return null
                }
            } else if (!dirFile.isDirectory) {
                val delete = dirFile.delete()
                return if (delete) {
                    createFile(dirPath, fileName)
                } else {
                    null
                }
            }
            val file = File(dirPath, fileName)
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return null
                }
            }
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }


    /**
     * 创建文件夹---之所以要一层层创建，是因为一次性创建多层文件夹可能会失败！
     */
    fun createFileDir(dirFile: File?): Boolean {
        if (dirFile == null) return true
        if (dirFile.exists()) {
            return true
        }
        val parentFile = dirFile.parentFile
        return if (parentFile != null && !parentFile.exists()) {
            //父文件夹不存在，则先创建父文件夹，再创建自身文件夹
            createFileDir(parentFile) && createFileDir(dirFile)
        } else {
            val mkdirs = dirFile.mkdirs()
            val isSuccess = mkdirs || dirFile.exists()
            if (!isSuccess) {
                Log.e("FileUtil", "createFileDir fail $dirFile")
            }
            isSuccess
        }
    }


    override fun onStart() {
        super.onStart()
        Log.e("tag1", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("tag1", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("tag1", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("tag1", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("tag1", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("tag1", "onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("tag1", "onNewIntent")
    }


    private fun test1() {
        Log.e("tag1", "111111111")
        Handler().post {
            Log.e("tag1", "22222222")
        }
        Log.e("tag1", "3333333333")
    }

    private fun test2() {
        thread {
            Thread.sleep(1000)
            Looper.prepare()
            Handler().post {
                //哪个线程？
                Log.e("tag1", "=====thread = ${Thread.currentThread().name}")
//                Toast.makeText(this@MainActivity,"aaaa",Toast.LENGTH_SHORT).show()
                findViewById<TextView>(R.id.tvBtn).requestLayout()
            }
            Looper.loop()
        }
    }

    private fun test3() {
//        MyHandler().sendEmptyMessageAtTime(1, System.currentTimeMillis())
//        MyHandler().sendEmptyMessageAtTime(2, System.currentTimeMillis())
//        MyHandler().sendEmptyMessageAtTime(3, System.currentTimeMillis())
//
//        MyHandler().sendEmptyMessageAtTime(1, 100)
//        MyHandler().sendEmptyMessageAtTime(2, 100)
//        MyHandler().sendEmptyMessageAtTime(3, 100)

        MyHandler().sendEmptyMessageAtTime(1, 0)
        MyHandler().sendEmptyMessageAtTime(2, 0)
        MyHandler().sendEmptyMessageAtTime(3, 0)
    }

    class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                Log.e("tag1", "111")
            } else if (msg.what == 2) {
                Log.e("tag1", "222")
            } else if (msg.what == 3) {
                Log.e("tag1", "333")
            }
        }
    }
}