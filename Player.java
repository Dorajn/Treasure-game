import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Player extends Entitiy {

    GamePanel gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;
    public int keys = 0;

    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;

        getImage();
        setDefaultValues(); 

        solidArea = new Rectangle(14, 14, 20, 20);

        direction = "down";
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidAreaDefaultX = 14;
        solidAreaDefaultY = 14;
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
    }

    public void getImage() {

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Barry/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Barry/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Barry/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Barry/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Barry/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Barry/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Barry/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Barry/right2.png"));
            standing = ImageIO.read(getClass().getResourceAsStream("/Barry/standing.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(kh.up == true || kh.down == true || kh.right == true || kh.left == true){

            if(kh.up == true){
                direction = "up";
            }
            else if(kh.down == true){
                direction = "down";
            }
            else if(kh.left == true){
                direction = "left";
            }
            else if(kh.right == true){
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int ObjIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(ObjIndex);

            if(collisionOn == false){
                
                if(direction.equals("up")){
                    worldY -= speed;
                }
                else if(direction.equals("down")){
                    worldY += speed;
                }
                else if(direction.equals("left")){
                    worldX -= speed;
                }
                else if(direction.equals("right")){
                    worldX += speed;
                }

            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
        else{
            direction = "standing";
        }

    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
            case "up":

                if(spriteNum == 1){
                    image = up1;
                }
                else{
                    image = up2;
                }

                break;

            case "down":

                if(spriteNum == 1){
                    image = down1;
                }
                else{
                    image = down2;
                }

                break;

            case "left":

                if(spriteNum == 1){
                    image = left1;
                }
                else{
                    image = left2;
                }

                break;

            case "right":

                if(spriteNum == 1){
                    image = right1;
                }
                else{
                    image = right2;
                }

                break;
            
            case "standing":
                image = standing;
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }


    public void pickUpObject(int i){

        if(i != 999){
            String objectName = gp.object[i].name;

            switch(objectName){
                case "key":
                    keys++;
                    gp.object[i] = null;
                    gp.playSE(1);
                    gp.ui.showMessage("You got the key");

                    break;

                case "door":

                    if(keys > 0){
                        gp.object[i] = null;
                        keys--;
                        gp.playSE(2);
                        gp.ui.showMessage("Door opened");
                    }
                    else{
                        gp.ui.showMessage("Door locked!");
                    }

                    break;

                case "boots":

                    speed += 2;
                    gp.object[i] = null;
                    gp.playSE(4);

                    gp.ui.showMessage("Speed up!!!");
                    break;

                case "chest":

                    gp.ui.gameFinish = true;
                    gp.stopMusic();
                    gp.playSE(3);


                    break;
            }
        }

    }
}
