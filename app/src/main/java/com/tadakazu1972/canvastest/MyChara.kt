package com.tadakazu1972.canvastest

class MyChara {
    var count: Int
    var count2 : Int
    var x : Float
    var y: Float
    var vx : Float
    var vy : Float
    constructor() {
        this.count = 0
        this.count2 = 0
        this.x = 100f
        this.y = 300f
        this.vx = 8f
        this.vy = 8f
    }

    public fun move(){
        x = x + vx
        y = y + vy

        if ( x < 0f || x > 1000f ) {
            vx = vx * -1
        }

        if ( y < 0f || y > 900f ) {
            vy = vy * -1
        }
    }
}