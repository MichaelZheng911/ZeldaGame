package ZeldaTheMissingLink.entity;


import ZeldaTheMissingLink.graphics.Animation;
import ZeldaTheMissingLink.graphics.Sprite;
import ZeldaTheMissingLink.utils.AABB;
import ZeldaTheMissingLink.utils.TileCollision;
import ZeldaTheMissingLink.utils.Vector2f;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected final int FALLEN = 4;
    protected final int UP = 3;
    protected final int DOWN = 2;
    protected final int LEFT = 1;
    protected final int RIGHT = 0;
    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;


    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected boolean fallen;

    public boolean xCol = false;
    public boolean yCol = false;

    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 0.3f;

    protected AABB hitBounds;
    protected AABB bounds;

    protected TileCollision tc;

    public Entity(Sprite sprite, Vector2f origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(origin,size, size);
        hitBounds.setXOffSet(size / 2);

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);

        tc = new TileCollision(this);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setFallen(boolean b){ fallen = b;}
    public void setSize(int i){size = i;}
    public void setMaxSpeed(float f) {this.maxSpeed = f; }
    public void setAcc(float f){ acc = f; }
    public void setDeacc(float f) { deacc = f; }

    public float getDeacc(){ return deacc; }
    public float getMaxSpeed(){ return maxSpeed; }
    public float getDx() { return dx; }
    public float getDy() { return dy; }

    public AABB getBounds(){ return bounds; }

    public int getSize () {return size; }
    public Animation getAnimation(){return ani; }

    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void animate(){
        if(up){
            if(currentAnimation != UP || ani.getDelay() == -1){
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }
        else if(down){
            if(currentAnimation != DOWN || ani.getDelay() == -1){
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if(left){
            if(currentAnimation != LEFT || ani.getDelay() == -1){
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else if(right){
            if(currentAnimation != RIGHT || ani.getDelay() == -1){
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }else if (fallen){
            if(currentAnimation != FALLEN || ani.getDelay() == -1){
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
            }
        }
        else{
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection(){
        if(up){
            hitBounds.setYOffSet(-size / 2);
            hitBounds.setXOffSet(0);
        }
        else if(down){
            hitBounds.setYOffSet(size / 2);
            hitBounds.setXOffSet(0);
        }
        else if(left){
            hitBounds.setYOffSet(0);
            hitBounds.setXOffSet(-size /2 );
        }
        else if(right){
            hitBounds.setYOffSet(0);
            hitBounds.setXOffSet(size / 2);
        }
    }

    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);

}
