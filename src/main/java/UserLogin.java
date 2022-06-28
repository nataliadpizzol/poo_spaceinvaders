import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserLogin {
    private static UserLogin telaLogin = null;
    private Pane loginPane;
    private Scene loginScene;
    private Stage loginStage;
    private TextField username;
    private ArrayList<Botao> botoesLogin;

    private UserLogin() {
        loginPane = new Pane();
        loginScene = new Scene(loginPane, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        loginStage = new Stage();
        loginStage.setScene(loginScene);
        botoesLogin = new ArrayList<>();
        tituloCena();
        fundo();
        caixaLogin();
        criaBotoes();
        loginStage.show();
    }

    public static UserLogin getInstance() {
        if (telaLogin == null) {
            telaLogin = new UserLogin();
        }
        return telaLogin;
    }

    public void showLogin() {
        loginStage.show();
    }

    private void adicionaBotoes(Botao botao) { // Método para organizar a criação de botões do Menu
        botao.setLayoutX(300);
        botao.setLayoutY(100 + botoesLogin.size() * 100);
        botoesLogin.add(botao);
        loginPane.getChildren().add(botao);
    }

    public void criaBotoes() {
        botaoConfirmar();
        botaoVoltar();
        
    }

    private void botaoConfirmar() { // Método para criar o Botão Jogar do Menu
        Botao botao = new Botao("START");
        adicionaBotoes(botao);

        botao.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent evento) {
                SpaceInvadersGame.getInstance();
                loginStage.close();
            }

        });
    }

    private void botaoVoltar() { // Método para criar o Botão Jogar do Menu
        Botao botao = new Botao("MENU");
        adicionaBotoes(botao);

        botao.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent evento) {
                Menu.getInstance().showMenu();
                loginStage.close();
            }

        });
    }


    private void caixaLogin() {
        Label label = new Label("Username:");
        label.setTextFill(Color.YELLOW);
        label.setFont(Font.font("Verdana",FontWeight.NORMAL,20));
        label.setLayoutX(255);
        label.setLayoutY(40);
        username = new TextField();
        username.setLayoutX(370);
        username.setLayoutY(40);
        loginPane.getChildren().addAll(username, label);
    }

    private void fundo() { // Método para criar o fundo da tela inicial
        Image imagemFundo = new Image("backgroundLogin.jpg", 800, 600, false, true);
        BackgroundImage fundo = new BackgroundImage(imagemFundo, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        loginPane.setBackground(new Background(fundo));
    }

    private void tituloCena() {
        loginStage.setTitle("Space Invaders Login Page");
        loginStage.setScene(loginScene);
    }

}
