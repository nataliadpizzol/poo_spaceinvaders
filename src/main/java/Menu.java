import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Menu {
    private static Menu menu = null;
    private Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private ArrayList<Botao> botoesMenu;

    private Menu() { // Constrói o Menu
        mainPane = new Pane();
        mainScene = new Scene(mainPane, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        botoesMenu = new ArrayList<>();
        tituloCena();
        criaBotoes();
        fundo();
        logo();
        mainStage.show();
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void showMenu() {
        mainStage.show();
    }

    private void adicionaBotoes(Botao botao) { // Método para organizar a criação de botões do Menu
        botao.setLayoutX(300);
        botao.setLayoutY(250 + botoesMenu.size() * 100);
        botoesMenu.add(botao);
        mainPane.getChildren().add(botao);
    }

    public void criaBotoes() {
        botaoJogar();
        botaoRanking();
        botaoSair();
    }

    private void botaoJogar() { // Método para criar o Botão Jogar do Menu
        Botao botao = new Botao("PLAY");
        adicionaBotoes(botao);

        botao.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent evento) {
                UserLogin.getInstance().showLogin();
                mainStage.close();
            }

        });

    }

    private void botaoRanking() { // Método para criar o Botão Ranking do Menu
        Botao botao = new Botao("SCORE");
        adicionaBotoes(botao);

        botao.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent evento) {
                Ranking.getInstance();
                mainStage.close();
            }

        });

    }

    private void botaoSair() { // Método para criar o Botão Sair do Menu
        Botao botao = new Botao("EXIT");
        adicionaBotoes(botao);

        botao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evento) {
                mainStage.close();
            }
        });

    }

    private void fundo() { // Método para criar o fundo da tela inicial
        Image imagemFundo = new Image("backgroundMenu.jpg", 800, 600, false, true);
        BackgroundImage fundo = new BackgroundImage(imagemFundo, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(fundo));
    }

    private void logo() { // Método para criar a logo "Space Invaders" na tela inicial
        ImageView logo = new ImageView("spaceinvadersLogo.png");
        logo.setFitHeight(400);
        logo.setFitWidth(400);
        logo.setPreserveRatio(true);
        logo.setOpacity(100);
        logo.setLayoutX(190);
        logo.setLayoutY(18);
        mainPane.getChildren().add(logo);
    }

    private void tituloCena() {
        mainStage.setTitle("Space Invaders Main Menu");
        mainStage.setScene(mainScene);
    }

}
