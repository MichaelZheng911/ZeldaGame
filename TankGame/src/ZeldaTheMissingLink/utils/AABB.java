package ZeldaTheMissingLink.utils;

import ZeldaTheMissingLink.entity.Entity;

public class AABB {

    private Vector2f pos;
    private float xOffSet = 0;
    private float yOffSet = 0;
    private float w;
    private float h;
    private float r;
    private int size;

    public AABB (Vector2f pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public AABB(Vector2f pos, int r){
        this.pos = pos;
        this.r= r;

        size = r;
    }

    public Vector2f getPos(){return pos;}

    public float getRadius(){return r; }
    public float getWidth(){return w; }
    public float getHeight(){return h; }

    public void setBox(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public void setCircle (Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth(float f){ w = f; }
    public void setHeight(float f){ h = f; }

    public void setXOffSet(float f){ xOffSet = f; }
    public void setYOffSet(float f){ yOffSet = f;}
    public float getXOffset() { return xOffSet; }
    public float getYOffset(){ return yOffSet; }

    public boolean collides(AABB bBox){
        float ax = ((pos.getWorldVar().x + (xOffSet))+( this.w / 2));
        float ay = ((pos.getWorldVar().y + (yOffSet))+( this.h / 2));
        float bx = ((bBox.getPos().getWorldVar().x + (bBox.xOffSet))+( bBox.getWidth() / 2));
        float by = ((bBox.getPos().getWorldVar().y + (bBox.yOffSet))+( bBox.getHeight() / 2));

        if(Math.abs(ax - bx) < (this.w / 2) + (bBox.getWidth() /2)){
            if(Math.abs(ay - by) < (this.h / 2) + (bBox.getHeight() /2)){
                return true;
            }
        }
        return false;
    }

    public boolean colCircleBox(AABB aBox){

        float dx = Math.max(aBox.getPos().getWorldVar().x + aBox.getXOffset(), Math.min(pos.getWorldVar().x + (r/2), aBox.getPos().getWorldVar().x + aBox.getXOffset() + aBox.getWidth()));
        float dy = Math.max(aBox.getPos().getWorldVar().y + aBox.getYOffset(), Math.min(pos.getWorldVar().y + (r/2), aBox.getPos().getWorldVar().y + aBox.getYOffset() + aBox.getHeight()));

        dx = pos.getWorldVar().x + (r / 2) - dx;
        dy = pos.getWorldVar().y + (r / 2) - dy;

        if(Math.sqrt(dx * dx + dy * dy) < r / 2){
            return true;
        }
        return false;
    }

//    public boolean colCircleBox(AABB aBox) {
//
//        float cx = (float) (pos.getWorldVar().x + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
//        float cy = (float) (pos.getWorldVar().y + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
//
//        float xDelta = cx - Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth() / 2) , Math.min (cx , aBox.pos.getWorldVar().x));
//        float yDelta = cy - Math.max(aBox.pos.getWorldVar().y + (aBox.getWidth() / 2) , Math.min (cy , aBox.pos.getWorldVar().y));
//
//        if((xDelta * xDelta + yDelta * yDelta) < ((this.r / Math.sqrt(2)) * (this.r / Math.sqrt(2)))) {
//            return true;
//        }
//
//        return false;
//    }

}
