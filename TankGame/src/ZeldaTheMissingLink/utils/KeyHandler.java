package ZeldaTheMissingLink.utils;

import ZeldaTheMissingLink.Launcher.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<Key>();

    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key(){
            keys.add(this);
        }

        public void toggle(boolean pressed){ //how many times it's been pressed or if it's been pressed down
            if(pressed != down){
                down = pressed;
            }
            if(pressed){
                presses++;
            }
        }

        public void tick(){ //has it been clicked so quick compared to pressed
            if(absorbs < presses){
                absorbs++;
                clicked= true;
            }else{
                clicked = false;
            }
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key up2 = new Key();
    public Key down2 = new Key();
    public Key left2 = new Key();
    public Key right2 = new Key();
    public Key attack2 = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();

    public KeyHandler (GamePanel game) {
        game.addKeyListener(this);
    }

    public void releaseAll(){
        for(int i = 0; i < keys.size(); i++){
            keys.get(i).down = false;
        }
    }

    public void tick (){
        for(int i = 0; i < keys.size();i++){
            keys.get(i).tick();
        }
    }

    public void toggle (KeyEvent e, boolean pressed){
        if(e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_UP) up2.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_DOWN) down2.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left2.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right2.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_NUMPAD0) attack2.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_E) menu.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //does nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e,false);
    }
}
