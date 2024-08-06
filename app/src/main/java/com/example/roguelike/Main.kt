import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout
import com.yourappname.GameEngine // Ensure this is the correct path to your GameEngine class

class MainActivity : Activity() {
    private lateinit var gameEngine: GameEngine
    private lateinit var gameLoopHandler: GameLoopHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize the game engine and add it to the activity's layout
        gameEngine = GameEngine(this)
        setContentView(gameEngine) // Set the GameEngine as the content view

        // Initialize the GameLoopHandler
        gameLoopHandler = GameLoopHandler {
            // Here you could invoke gameEngine's update method if separate from gameEngine.startGame
            gameEngine.update() // Assuming gameEngine has an update method to handle game logic
        }

        // Assuming the AssetManager class and methods to download assets
        val assetManager = AssetManager()
        assetManager.downloadAll {
            // Once all assets are downloaded, you can initialize and start the game
            gameEngine.startGame()
            gameLoopHandler.start() // Start the game loop after assets are loaded and game is initialized
        }
    }

    override fun onPause() {
        super.onPause()
        gameEngine.stopGame() // Stop the game when the activity is paused
        gameLoopHandler.stop() // Stop the game loop handler as well
    }

    override fun onResume() {
        super.onResume()
        gameEngine.startGame() // Resume the game when the activity is resumed
        gameLoopHandler.start() // Resume the game loop handler as well
    }

    override fun onDestroy() {
        super.onDestroy()
        gameEngine.stopGame() // Ensure the game is stopped to clean up resources
        gameLoopHandler.stop() // Stop the game loop handler to clean up resources
    }
}

