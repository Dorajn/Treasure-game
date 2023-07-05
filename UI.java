import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage image;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinish = false;

    double playTime; 

    DecimalFormat dFormat = new DecimalFormat("#0.00");
    

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 35);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        KeyOBJECT key = new KeyOBJECT();
        image = key.image;
    }


    public void showMessage(String text){

        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2){

        if(gameFinish){

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLenght;
            int x;
            int y;

            text = "You found the treasure!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - (textLenght / 2);
            y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Your time: " +  dFormat.format(playTime) + "!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - (textLenght / 2);
            y = gp.screenHeight / 2 + gp.tileSize * 3;
            g2.drawString(text, x, y);


            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);

            text = "Congratulations";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - (textLenght / 2);
            y = gp.screenHeight / 2 + gp.tileSize * 2;

            g2.drawString(text, x, y);
            
            gp.thread = null;
        }
        else{

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(image, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.keys, 80, 60);

            g2.setFont(g2.getFont().deriveFont(25F));
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 12 + 24, 60);

            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, 320, 550);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        }

    }

}   
