import java.io.IOException;

import javax.imageio.ImageIO;

public class DoorOBJECT extends Object {
    
    public DoorOBJECT(){
        name = "door";

        try{
            
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}
