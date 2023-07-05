import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    
    //screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //rozmiar jednego klocka

    final int maxScreenCol = 16; //ile klockow bedzie wyswietlanych an ekranie
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol; //wymiary ekranu w pikselach //768px
    final int screenHeight = tileSize * maxScreenRow; //576px

    //game settings
    final int FPS = 60;

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    CollisionChecker cChecker = new CollisionChecker(this);
    ObjectSetter objectSetter = new ObjectSetter(this);
    MapMenager mapMenager = new MapMenager(this);
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    Object[] object = new Object[14];
    Sound music = new Sound();
    Sound se = new Sound();
    Thread thread;
    UI ui = new UI(this);
    

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGame(){
        thread = new Thread(this);
        thread.start();
    }

    public void setupGame(){
        playMusic(0);
        objectSetter.setObject();
    }


    @Override
    public void run() {

        double interval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        while(thread != null){

            currTime = System.nanoTime();
            delta += (currTime - lastTime) / interval;
            lastTime = currTime;

            if(delta > 1){  
                update();
                repaint();
                delta--;
            }

        }
        
        
    }

    public void update(){
        player.update();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        mapMenager.draw(g2);
        
        for(int i = 0; i < object.length; i++){
            if(object[i] != null){
                object[i].draw(g2, this);
            }
        }

        player.draw(g2);
        ui.draw(g2);

        g2.dispose();
    }


    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }

}
