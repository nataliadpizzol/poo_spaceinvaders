import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SpaceInvadersGame {
    private static SpaceInvadersGame jogo = null;
    private Pane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    public SpaceInvadersGame() {
        gamePane = new Pane();
        gameScene = new Scene(gamePane, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        tituloCena();
        fundo();
        iniciaJogo();
        gameStage.show();
    }

    public static SpaceInvadersGame getInstance() {
        if (jogo == null) {
            jogo = new SpaceInvadersGame();
        }
        return jogo;
    }

    private void iniciaJogo() {
        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );
        gamePane.getChildren().add(canvas);
    
        Game.getInstance().Start();

        // Register User Input Handler
        gameScene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        gameScene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer(){
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                Game.getInstance().Draw(gc);

                lastNanoTime = currentNanoTime;

                if (Game.getInstance().isGameOver()){
                    
                    stop();
                }
            }

        }.start();
        
    }

    private void fundo() { // Método para criar o fundo da tela inicial
        Image imagemFundo = new Image("backgroundGame.jpg", 800, 600, false, true);
        BackgroundImage fundo = new BackgroundImage(imagemFundo, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        gamePane.setBackground(new Background(fundo));
    }

    private void tituloCena() {
        gameStage.setTitle("Space Invaders");
        gameStage.setScene(gameScene);
    }

}
