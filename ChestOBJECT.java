import java.io.IOException;

import javax.imageio.ImageIO;

public class ChestOBJECT extends Object {
    
    public ChestOBJECT(){
        name = "chest";

        try{
            
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
