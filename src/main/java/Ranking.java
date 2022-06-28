import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class Ranking {
    private static Ranking ranking = null;
    private Pane rankPane;
    private Scene rankScene;
    private Stage rankStage;

    private Ranking() {
        rankPane = new Pane();
        rankScene = new Scene(rankPane, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        rankStage = new Stage();
        rankStage.setScene(rankScene);
        fundo();
        tituloCena();
        logo();
        rankStage.show();
    }

    public static Ranking getInstance() {
        if (ranking == null) {
            ranking = new Ranking();
        }
        return ranking;
    }

    private void mostraRanking() {
        ArrayList<String> list = getRanking(); 



        String dir = Paths.get("").toAbsolutePath().toString();
        Charset charset = Charset.forName("UTF-8");
        String nameComplete = dir + "/" + "ranking.txt";
        Path path = Paths.get(nameComplete);
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, charset))) {
            for (int i = 0; i < list.size(); i++) {
                String jogador = list.get(i);
                writer.println(jogador);
            }
        } catch (IOException e) {
            System.err.format("Erro", e);
        }

    }

    private ArrayList<String> getRanking() {
        ArrayList<String> colocacao = new ArrayList<>(10);
        Charset charset = Charset.forName("UTF-8");
        String dir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = dir + "/" + "ranking.txt";
        Path path = Paths.get(nameComplete);
        int cont = 0;
        try (Scanner txt = new Scanner(Files.newBufferedReader(path, charset))) {
            while (txt.hasNext() && cont < 10) {
                String jogador = txt.nextLine();
                colocacao.add(jogador);
                cont++;
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do ranking!", e);
        }
        return colocacao;
    }

    private void fundo() {
        Image imagemFundo = new Image("backgroundGame.jpg", 800, 600, false, true);
        BackgroundImage fundo = new BackgroundImage(imagemFundo, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        rankPane.setBackground(new Background(fundo));
    }

    private void logo(){
        Label sceneTitle = new Label("Scoreboard");
        Font font = Font.font("Verdana", FontWeight.BLACK, 30);
        sceneTitle.setFont(font);
        sceneTitle.setTextFill(Color.YELLOW);
        sceneTitle.setTranslateX(300);
        sceneTitle.setTranslateY(80);
        rankPane.getChildren().add(sceneTitle);
    }

    private void tituloCena() {
        rankStage.setTitle("Space Invaders Score Board");
        rankStage.setScene(rankScene);
    }

}
