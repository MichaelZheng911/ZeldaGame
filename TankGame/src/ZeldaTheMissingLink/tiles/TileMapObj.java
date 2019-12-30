package ZeldaTheMissingLink.tiles;

import java.awt.Graphics2D;
import ZeldaTheMissingLink.graphics.Sprite;
import ZeldaTheMissingLink.tiles.blocks.Block;
import ZeldaTheMissingLink.tiles.blocks.HoleBlock;
import ZeldaTheMissingLink.tiles.blocks.ObjBlock;
import ZeldaTheMissingLink.utils.AABB;
import ZeldaTheMissingLink.utils.Vector2f;

public class TileMapObj extends TileMap{

    public static Block[] event_blocks;

  //  public static HashMap<String, Block> two_blocks;

    public int tileWidth;
    public int tileHeight;

    public static int width;
    public static int height;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        Block tempBlock;
        event_blocks = new Block[width * height];

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        TileMapObj.width = width;
        TileMapObj.height = height;

       // two_blocks = new HashMap<String, Block>();

        String [] block = data.split(",");
        for(int i = 0; i < (width * height);i++){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if(temp != 0){
                if(temp == 172){
                    tempBlock = new HoleBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1)/ tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight),tileWidth, tileHeight);
                }else{
                    tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1)/ tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight),tileWidth, tileHeight);
                }
               // two_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock); replaced Hash with array to make it load better
                event_blocks[i] = tempBlock;
            }
        }
    }

    public void render(Graphics2D g, AABB cam){
        //this makes it load faster than using HashMaps
        int x = (int) ((cam.getPos().getCamVar().x) / tileWidth);
        int y = (int) ((cam.getPos().getCamVar().y) / tileHeight);
        for(int i = x; i < x + (cam.getWidth() / tileWidth); i++){
            for(int j = y; j < y + (cam.getHeight() / tileHeight); j++){
                if(event_blocks[i + (j * height)] != null){
                    event_blocks[i + (j * height)].render(g);
                }
            }
        }

//        for(Block block: two_blocks.values()){
//            block.render(g);

    }
}

