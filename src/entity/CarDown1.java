package entity;
import java.util.Random;
import main.GamePanel;

public class CarDown1 extends Entity{
    
    
    
    public CarDown1(GamePanel gp){
        super(gp);
        collisionOn = false;
        direction = "down";
        speed = 2;
        getImage();
        type = 3;
        
    }
    public void getImage() {
//        red = setUp("/npc/57");
        up1 = setUp("/npc/up2");
        up2 = setUp("/npc/up2");
        down1 = setUp("/npc/down1");
        down2 = setUp("/npc/down1");
     
    }

    @Override
    public void setAction() {
        // set cat behavior

        actionLockCounter++;

        if (actionLockCounter < 120) {
            direction = "down";
        } else if (actionLockCounter >= 120 && actionLockCounter < 240) {
            // direction won't change for the next 120 seconds
//            Random random = new Random();
//            int i = random.nextInt(100)+1; // pick up num from 1-100
            direction = "up";

        } else if (actionLockCounter == 240) {
            actionLockCounter = 0;
        }
    }
    
    
    
}
