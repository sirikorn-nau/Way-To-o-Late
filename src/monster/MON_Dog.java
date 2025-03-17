package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;

public class MON_Dog extends Entity {

    public MON_Dog(GamePanel gp) {
        super(gp);

        type = 2;
        name = "dog";
        speed = 2;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 3;
        solidArea.width = 42;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {

        up1 = setUp("/monsters/mushroom");
        up2 = setUp("/monsters/mushroom");
        down1 = setUp("/monsters/mushroom");
        down2 = setUp("/monsters/mushroom");
        left1 = setUp("/monsters/mushroom");
        left2 = setUp("/monsters/mushroom");
        right1 = setUp("/monsters/mushroom");
        right2 = setUp("/monsters/mushroom");
        
        
//        up1 = setUp("/monsters/dogup1");
//        up2 = setUp("/monsters/dogup2");
//        down1 = setUp("/monsters/dogdown1");
//        down2 = setUp("/monsters/dogdown2");
//        left1 = setUp("/monsters/dogleft1");
//        left2 = setUp("/monsters/dogleft2");
//        right1 = setUp("/monsters/dogright");
//        right2 = setUp("/monsters/dogright2");

//        up1 = setUp("/npc/catup1");
//        up2 = setUp("/npc/catup2");
//        down1 = setUp("/npc/catdown1");
//        down2 = setUp("/npc/catdown2");
//        left1 = setUp("/npc/catleft1");
//        left2 = setUp("/npc/catleft2");
//        right1 = setUp("/npc/catright1");
//        right2 = setUp("/npc/catright2");

    }

    public void setAction() {
        // behavior of this dog
        actionLockCounter++;

        if (actionLockCounter == 120) {
            // direction won't change for the next 120 seconds
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up num from 1-100
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;

        }
    }

}
