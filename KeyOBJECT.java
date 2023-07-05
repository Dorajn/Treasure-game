import java.io.IOException;

import javax.imageio.ImageIO;

public class KeyOBJECT extends Object {
    
    public KeyOBJECT(){
        name = "key";

        try{
            
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/key.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
