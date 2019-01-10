package model;

import utils.IdGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ModelConstants {

    public static String postID = IdGenerator.getId();
    public static final String userID = "qqq123";
    public static final Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    public static final boolean withImgs = true;
    public static final boolean withNoImg = false;
    public static final String content = "bvfkdnv!234341@#$测试数据";

    public static final String IMG1 = "1546880553916850.png";
    public static final String IMG2 = "1546874188848269.jpg";
    public static final List<String> imgFiles = new ArrayList<String>();

    public static void setImgFiles() {
        imgFiles.add(IMG1);
        imgFiles.add(IMG2);
    }

    public static void setId() {
        postID = IdGenerator.getId();
    }
}
