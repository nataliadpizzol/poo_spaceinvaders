import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien2 extends BasicElement implements Inimigo{
    private Image image;
    private static int count;
    public Alien2(int px,int py){
        super(px,py);
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image( "alien2.png",0,40,true,true );
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setDirH(1);
        setSpeed(7);
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            Game.getInstance().incPontos();
            deactivate();
        }else{
            //if( count > 4){ remover caso queira diminuir a velocidade de queda.
                setPosY(getY() + 1);
                count = 0;
           // }
            count++;
            setPosX(getX() + getDirH() * (getSpeed()/2));
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH() || getX() < getLMinH()){
                // Inverte a direção
                setDirH(getDirH()*-1);
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(5)+5);
                // Se ainda não chegou perto do chão, desce
                /*if (getY() < 450){ */
                    setPosY(getY()+25);
                //}
            }
        }
    }

    // private int getRandomNumber(int min, int max) {
    //     return (int) ((Math.random() * (max - min)) + min);
    // }

    @Override
    public void testaColisao(Character outro){
        if(getY()>=600){
            Game.getInstance().setGameOver();
        }
        if (outro instanceof Inimigo || outro instanceof BlocoDestrutivoBasico){
            return;
        }else{
            super.testaColisao(outro);
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
    }

}