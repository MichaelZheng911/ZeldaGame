package ZeldaTheMissingLink.Launcher;

import ZeldaTheMissingLink.states.GameStateManager;
import ZeldaTheMissingLink.utils.KeyHandler;
import ZeldaTheMissingLink.utils.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;
    public static int oldFrameCount;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;

    public GamePanel(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();

        if(thread == null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {
        running = true;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }

    public void run() {
        init();

        final double GAME_HERIZ = 60.0;
        final double TIME_BEFORE_UPDATE = 1000000000/GAME_HERIZ; //TIME BEFORE UPDATE

        final int MOST_UPDATE_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 200;
        final double TOTAL_TIME_BEFORE_RENDER = 1000000000/TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime/1000000000);
        oldFrameCount = 0;

        while (running){
            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > TIME_BEFORE_UPDATE) && (updateCount < MOST_UPDATE_BEFORE_RENDER) ){
                update();
                input(mouse , key);
                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
            }

                if(now - lastUpdateTime > TIME_BEFORE_UPDATE) {
                    lastUpdateTime = now - TIME_BEFORE_UPDATE;
                }

            input(mouse , key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if(thisSecond > lastSecondTime){
                if(frameCount != oldFrameCount){
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while(now - lastRenderTime < TOTAL_TIME_BEFORE_RENDER && now - lastUpdateTime < TIME_BEFORE_UPDATE){
                Thread.yield();

                try{
                    Thread.sleep(1);
                }catch (Exception e){
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }


        }
    }

    private int x = 0;


    public void update(){
        gsm.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        gsm.input(mouse, key);
    }
    public void render(){
        if(g != null){
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0,0,width,height);
            gsm.render(g);
        }
    }
    public void draw(){
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0 , 0 , width, height , null);
        g2.dispose();
    }
}
