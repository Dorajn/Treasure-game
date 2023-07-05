public class ObjectSetter{

    GamePanel gp;

    public ObjectSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        gp.object[0] = new KeyOBJECT();
        gp.object[0].worldX = 26 * gp.tileSize;
        gp.object[0].worldY = 17 * gp.tileSize;

        gp.object[1] = new ChestOBJECT();
        gp.object[1].worldX = 36 * gp.tileSize;
        gp.object[1].worldY = 16 * gp.tileSize;

        gp.object[2] = new DoorOBJECT();
        gp.object[2].worldX = 36 * gp.tileSize;
        gp.object[2].worldY = 18 * gp.tileSize;
        gp.object[2].collision = true;

        gp.object[3] = new KeyOBJECT();
        gp.object[3].worldX = 19 * gp.tileSize;
        gp.object[3].worldY = 31 * gp.tileSize;

        gp.object[4] = new DoorOBJECT();
        gp.object[4].worldX = 25 * gp.tileSize;
        gp.object[4].worldY = 21 * gp.tileSize;
        gp.object[4].collision = true;

        gp.object[5] = new DoorOBJECT();
        gp.object[5].worldX = 13 * gp.tileSize;
        gp.object[5].worldY = 35 * gp.tileSize;
        gp.object[5].collision = true;

        gp.object[6] = new KeyOBJECT();
        gp.object[6].worldX = 23 * gp.tileSize;
        gp.object[6].worldY = 27 * gp.tileSize;

        gp.object[7] = new DoorOBJECT();
        gp.object[7].worldX = 39 * gp.tileSize;
        gp.object[7].worldY = 21 * gp.tileSize;
        gp.object[7].collision = true;

        gp.object[8] = new KeyOBJECT();
        gp.object[8].worldX = 39 * gp.tileSize;
        gp.object[8].worldY = 43 * gp.tileSize;

        gp.object[9] = new BootsOBJECT();
        gp.object[9].worldX = 39 * gp.tileSize;
        gp.object[9].worldY = 42 * gp.tileSize;

    }

}