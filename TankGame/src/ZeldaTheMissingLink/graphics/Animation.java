package ZeldaTheMissingLink.graphics;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrames;
    private int numFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public Animation(BufferedImage[] frames){
        timesPlayed = 0;
        setFrames(frames);
    }

    public Animation(){
        timesPlayed = 0;
    }

    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrames = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void setDelay(int i){delay = i;}
    public void setFrames(int i){currentFrames = i;}
    public void setNumFrames (int i){ numFrames = i;}

    public void update(){
        if(delay == -1) {
            return;
        }
            count++;
        if(count == delay){
            currentFrames++;
            count = 0;
        }
        if(currentFrames == numFrames){
            currentFrames = 0;
            timesPlayed++;
        }
    }
    public int getDelay(){return delay;}
    public int getFrames(){return currentFrames;}
    public int getCount(){return count;}
    public BufferedImage getImage(){return frames[currentFrames];}
    public boolean hasPlayedOnce() {return timesPlayed > 0;}
    public boolean hasPlayed(int i) {return timesPlayed == i;}
}
