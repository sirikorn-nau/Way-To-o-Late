package object;

import java.io.*;

import javax.imageio.ImageIO;

public class OBJ_10  extends SuperObject{
	
	public OBJ_10() {
		
		name = "";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/10.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
