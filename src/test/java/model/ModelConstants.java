package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ModelConstants {

    public static final String postID = "1546865280738978";
    public static final String userID = "qqq123";
    public static final Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    public static final boolean withImgs = true;
    public static final boolean withNoImg = false;
    public static final String content = "bvfkdnv!234341@#$测试数据";
    public static final List<String> imgFiles = new ArrayList<String>();

    public ModelConstants() {
        imgFiles.add("1546880553916850.png");
    }

}
