import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout
import com.yourappname.GameEngine // Update package path as needed

class MainActivity : Activity() {

    private lateinit var gameEngine: GameEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the game engine and add it to the activity's layout
        gameEngine = GameEngine(this)
        setContentView(gameEngine) // Directly setting the GameEngine as the content view

        // Assuming the AssetManager class and methods to download assets
        val assetManager = AssetManager()
        assetManager.downloadAll {
            // Once all assets are downloaded, you can initialize and start the game
            gameEngine.startGame()
        }
    }

    override fun onPause() {
        super.onPause()
        gameEngine.stopGame() // Stop the game when the activity is paused
    }

    override fun onResume() {
        super.onResume()
        gameEngine.startGame() // Resume the game when the activity is resumed
    }

    override fun onDestroy() {
        super.onDestroy()
        gameEngine.stopGame() // Ensure the game is stopped when the activity is destroyed
    }
}
