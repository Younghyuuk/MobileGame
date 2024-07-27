import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {
    private lateinit var gameLoopHandler: GameLoopHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameLoopHandler = GameLoopHandler {
            // Update your game logic here
        }
        gameLoopHandler.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        gameLoopHandler.stop()
    }
}
