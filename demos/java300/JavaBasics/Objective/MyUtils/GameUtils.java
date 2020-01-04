package JavaBasics.Objective.MyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/30 10:47
 * @Description:
 */
public class GameUtils {

    private GameUtils() {
    }

    /** 获取图片 */
    public static Image getImage(String imagePath){
        BufferedImage io = null;
        URL resource = GameUtils.class.getClassLoader().getResource(imagePath);
        try {
            io = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return io;
    }
}
