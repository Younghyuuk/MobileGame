import android.content.Context
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.os.Handler

class GameEngine(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val gameLoopHandler = GameLoopHandler(this::updateAndDraw)
    private val entities = mutableListOf<Entity>()

    init {
        holder.addCallback(this)
        isFocusable = true
    }

    fun startGame() {
        gameLoopHandler.start()
    }

    fun stopGame() {
        gameLoopHandler.stop()
    }

    fun addEntity(entity: Entity) {
        entities.add(entity)
    }

    private fun updateAndDraw() {
        val canvas = holder.lockCanvas()
        canvas?.let {
            update(it)
            draw(it)
            holder.unlockCanvasAndPost(it)
        }
    }

    private fun update(canvas: Canvas) {
        entities.forEach { entity ->
            entity.update()
            entity.draw(canvas)
        }
        // Remove entities that are marked for removal
        entities.removeAll { it.shouldRemove }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        startGame()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Handle surface size or format changes if necessary
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        stopGame()
    }
}

class GameLoopHandler(private val updateAndDraw: () -> Unit) {
    private var running = true
    private val handler = Handler()

    fun start() {
        if (!running) {
            running = true
            handler.post(object : Runnable {
                override fun run() {
                    if (running) {
                        updateAndDraw()
                        handler.postDelayed(this, 16) // Targeting approximately 60 FPS
                    }
                }
            })
        }
    }

    fun stop() {
        running = false
        handler.removeCallbacksAndMessages(null)
    }
}

interface Entity {
    fun update()
    fun draw(canvas: Canvas)
    val shouldRemove: Boolean
}
