package ZeldaTheMissingLink.states;

import ZeldaTheMissingLink.utils.KeyHandler;
import ZeldaTheMissingLink.utils.MouseHandler;

import java.awt.*;

public abstract class GameState {

    private GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);

}
