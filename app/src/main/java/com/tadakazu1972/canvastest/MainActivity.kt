package com.tadakazu1972.canvastest

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowInsets
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainView : MainView
    private lateinit var myChara : MyChara
    private var height: Int = 0
    private var width: Int = 0

    //カウントダウンタイマー準備
    inner class countDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(millisUntilFinished: Long){
            mainLoop()
        }

        override fun onFinish(){

        }
    }

    //アクティビティ初期化
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = MainView(this)
        setContentView(mainView)

        //端末の画面サイズの取得
        getWindowMetrics()

        //プレイヤー初期化
        myChara = MyChara()

        //CountDownTimer
        val timer = countDownTimer(3 * 60 * 1000, 10)
        timer.start()

        //コルーチン
        GlobalScope.launch(Dispatchers.Main) {
            anotherLoop()
        }
    }

    //View継承クラス
    internal inner class MainView(context: Context) : View(context) {
        private val res: Resources = this.getContext().getResources()
        private val imgZakk: Bitmap = BitmapFactory.decodeResource(res, R.drawable.zakk01)
        private var paint: Paint = Paint()

        init {
        }

        public override fun onDraw(canvas: Canvas) {
            canvas.drawBitmap(imgZakk, myChara.x, myChara.y, paint)
        }
    }

    private fun mainLoop(){
        myChara.count += 1
        myChara.move(height, width)
        mainView.invalidate()
        println("mainLoop run count= ${myChara.count}")
    }

    private fun anotherLoop(){
        myChara.count2 += 1
        println("anotherLoop run count2 = ${myChara.count2}")
    }

    //画面サイズ取得　API30から推奨
    private fun getWindowMetrics(){
        //まずは画面たてよこ
        val windowMetrics = windowManager.getCurrentWindowMetrics()
        height = windowMetrics.bounds.height()
        width  = windowMetrics.bounds.width()

        println("height = ${height}")
        println("width  = ${width}")

        //Status Bar, Navigation Bar *Title Barは res/values/themes/thems.xmlに追記することで非表示設定にしている
        val insets: Insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        val StatusBar = insets.top
        val NavigationBar = insets.bottom

        println("StatusBar = ${StatusBar}")
        println("NavigationBwr = ${NavigationBar}")

    }

}

