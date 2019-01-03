package home;

import com.sun.glass.ui.Size;

/**
 * @author: zilla0148
 * @date: 2018/12/19 14:59
 */
public class ConstantSetting {
    public static final String TITLE = "Timeline:)";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    public static final String DB_NAME = "timelineSys";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";
    public static final int POST_NUM_PAGE = 5;
    public static final String POST_IMAGE_PATH = "./post_images/";
    public static final double UPDATE_PERIOD_MIN = 0.1;
    public static final String ALERT_TITLE = "Prompt";
    static final Size START_UP_SCENE_SIZE = new Size( 480, 650 );
}
