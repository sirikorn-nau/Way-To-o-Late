package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import object.OBJ_Heart;
import object.SuperObject;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial, pixelBoy, eightBitDragon, endlessBoss, eightBit;
    BufferedImage heart_full, heart_blank; 
    public boolean messageOn = false;
    public String messageDamage = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int commandNum = 0;
    
    public UI(GamePanel gp){
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 40);

        try {
            InputStream is = getClass().getResourceAsStream("/font/Pixeboy-z8XGD.ttf");
            pixelBoy = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/8-BIT WONDER.TTF");
            eightBit = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/EightBitDragon-anqx.ttf");
            eightBitDragon = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/EndlessBossBattleRegular-v7Ey.ttf");
            endlessBoss = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
        // Create heart object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_blank = heart.image2;
        
        
        
        
    }
    
    public void showMessage(String text){
        messageDamage = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        g2.setFont(this.arial);  
        g2.setColor(Color.WHITE);
        
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // Play State
        if(gp.gameState == gp.playState){
           drawPlayerLife();
           
           
            if(gameFinished == true){
            
            String text;
            int textLength;
            int x, y;
            
            text = "Can go to the exam";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // return length of text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);
            
            
            text = "Your time is : " + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 4);
            g2.drawString(text, x, y);
            
            
            g2.setFont(this.arial);
            g2.setColor(Color.ORANGE);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // return length of text
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 3); // move text below three tiles.
            g2.drawString(text, x, y);
            
            gp.gameThread = null;
                 
            
        }
        // game dosen't end
        else{

            //Time
//            playTime += (double)1/60;
//            g2.drawString("Time : "+ dFormat.format(playTime), gp.tileSize*12, 50);
            
            // Message 
            if(messageOn == true){
                g2.setFont(this.eightBitDragon.deriveFont(20F)); // change font for messageDamage especially s
                
                int x = getXforCenteredText(messageDamage);
                int y = gp.screenHeight/2;
                
                g2.drawString(messageDamage, x+35, y-24);
                messageCounter++;
                
                // 2 seconds
                if(messageCounter > 60){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
           
        }
        
        
        // Pause State
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        // Game over
        if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        
        
//       
    }
    
     public void drawTitleScreen() {
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //TITLE NAME
        g2.setFont(this.eightBitDragon.deriveFont(45F));
        String txt = "Way To (o) Late";
        int x = getXforCenteredText(txt);
        int y = gp.tileSize * 3;
        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(txt, x + 3, y + 4);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(txt, x, y);

        //CHARACTER IMG
        x = (int) (gp.screenWidth / 2.3);
        y += gp.tileSize * 1;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        //MENU
        g2.setFont(this.eightBitDragon.deriveFont(32F));

        //Play game
        txt = "Play Game";
        x = getXforCenteredText(txt);
        y += gp.tileSize * 4;
        g2.drawString(txt, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        //Quit
        txt = "Quit";
        x = getXforCenteredText(txt);
        y += gp.tileSize * 1.5;
        g2.drawString(txt, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    
    
    // heart
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        
        // draw blank heart
        while(i< gp.player.maxLife){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x += gp.tileSize;
        }
        
        // reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        
        // draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_full, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        if(gp.player.life == 0){
//            gp.ui.gameFinished = true;
        }
        
    }
    
    // screen that pause time
    public void drawPauseScreen(){
        g2.setColor(new Color(0,0,0, 180));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        g2.setFont(this.eightBitDragon.deriveFont(45F)); // change font for messageDamage especially 
        String text = "Pause";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    
    public int getXforCenteredText(String text){
        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - textLength/2;
        return x;
    }
    
    public void drawGameOverScreen(){
        
        g2.setColor(new Color(0,0,0, 180));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(this.eightBitDragon.deriveFont(Font.BOLD, 45f));
        text = "Game Over";
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        // main
        g2.setColor(Color.white);
        g2.drawString(text, x, y-4);
        
        // retry
        g2.setFont(this.eightBitDragon.deriveFont(30f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x - gp.tileSize , y);
        }
        
        // back to title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x - gp.tileSize , y);
        }
    }
    
    
}
