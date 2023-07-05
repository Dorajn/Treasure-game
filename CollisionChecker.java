public class CollisionChecker {
    
    GamePanel gp;

    CollisionChecker(GamePanel gp){
        this.gp = gp;
    }


    public void checkTile(Entitiy entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.mapMenager.tileMap[entityLeftCol][entityTopRow];
                tileNum2 = gp.mapMenager.tileMap[entityRightCol][entityTopRow];

                if(gp.mapMenager.tile[tileNum1].colision || gp.mapMenager.tile[tileNum2].colision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.mapMenager.tileMap[entityLeftCol][entityBottomRow];
                tileNum2 = gp.mapMenager.tileMap[entityRightCol][entityBottomRow];

                if(gp.mapMenager.tile[tileNum1].colision || gp.mapMenager.tile[tileNum2].colision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.mapMenager.tileMap[entityLeftCol][entityTopRow];
                tileNum2 = gp.mapMenager.tileMap[entityLeftCol][entityBottomRow];

                if(gp.mapMenager.tile[tileNum1].colision || gp.mapMenager.tile[tileNum2].colision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.mapMenager.tileMap[entityRightCol][entityTopRow];
                tileNum2 = gp.mapMenager.tileMap[entityRightCol][entityBottomRow];

                if(gp.mapMenager.tile[tileNum1].colision || gp.mapMenager.tile[tileNum2].colision){
                    entity.collisionOn = true;
                }
                break;
            
                
        }

    }


    public int checkObject(Entitiy entity, boolean player){

        int index = 999;

        for(int i = 0; i < gp.object.length; i++){
            if(gp.object[i] != null){

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.object[i].solidArea.x = gp.object[i].worldX + gp.object[i].solidArea.x;
                gp.object[i].solidArea.y = gp.object[i].worldY + gp.object[i].solidArea.y;

                switch(entity.direction){
                    case "up":

                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;

                    case "down":

                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                            
                        }
                        break;

                    case "left":

                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                            
                        }
                        break;

                    case "right":

                    entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                             
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.object[i].solidArea.x = gp.object[i].solidAreaDefaultX;
                gp.object[i].solidArea.y = gp.object[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
