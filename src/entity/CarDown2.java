package entity;

import java.util.Random;
import main.GamePanel;

public class CarDown2 extends Entity {

    public CarDown2(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 2;
        getImage();
        type = 3;

    }

    public void getImage() {
//        red = setUp("/npc/56");
        up1 = setUp("/npc/up1");
        up2 = setUp("/npc/up1");
        down1 = setUp("/npc/down2");
        down2 = setUp("/npc/down2");

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
