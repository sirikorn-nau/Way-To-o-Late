package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_111  extends SuperObject{

		public OBJ_111() {
                    name = "walk";
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/objects/111.png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
