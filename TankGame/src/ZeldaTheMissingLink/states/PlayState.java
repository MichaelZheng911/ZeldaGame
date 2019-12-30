package ZeldaTheMissingLink.states;

import ZeldaTheMissingLink.Launcher.GamePanel;
import ZeldaTheMissingLink.entity.Enemy;
import ZeldaTheMissingLink.entity.Player;
import ZeldaTheMissingLink.graphics.Font;
import ZeldaTheMissingLink.graphics.Sprite;
import ZeldaTheMissingLink.tiles.TileManager;
import ZeldaTheMissingLink.utils.*;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private Enemy enemy;
    private TileManager tm;
    private Camera cam;

    public static Vector2f map;

    public PlayState (GameStateManager gsm){
        super(gsm);

        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        cam = new Camera(new AABB(new Vector2f(0,0) ,GamePanel.width + 64 ,GamePanel.height + 64 ));

        tm = new TileManager("tile/map.v2.xml" , cam);
        font = new Font("font/font.png", 10, 10);

        enemy = new Enemy(new Sprite("entity/littlegirl.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + 150, 0 + (GamePanel.height / 2) - 32 + 150), 64);
        player = new Player(new Sprite("entity/linkformatted.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 64);
        cam.target(player);
    }

    public void update() {
        Vector2f.setWorldVar(map.x , map.y);
        player.update(enemy);
        enemy.update(player);
        cam.update();
    }

    public void input (MouseHandler mouse, KeyHandler key) {
        player.input(mouse,key);
        cam.input(mouse, key);
    }

    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + "FPS", new Vector2f(GamePanel.width - 192, 32), 32, 32);

        player.render(g);
        enemy.render(g);
        cam.render(g);
    }

}
