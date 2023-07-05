import java.io.IOException;

import javax.imageio.ImageIO;

public class BootsOBJECT extends Object {
    
    public BootsOBJECT(){
        name = "boots";

        try{
            
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/boots.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
