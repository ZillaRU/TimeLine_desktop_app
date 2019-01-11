## 运行环境：JDK1.8   MYSQL8.0.11  
修改`ConstantSetting.java`中的`db_user`,`db_passsword`,  运行`DbCreator.java`即可
## 性能测试配置：Loadrunner12.55  
新建脚本，选择`java vuser`，脚本内容见`性能测试脚本`，在`runtime setting`处导入`TimeLine-1.0-SNAPSHOT.jar`，即可创建scenario进行并发测试。


## 一点总结
#### 1. 实现
- JavaFX值得关注的第三方库
- jfoenix引入与简单应用
- 自定义ListCell的流程
- StackPane实现提示框，~~弹出提示被跳转页面覆盖~~
- Timeline实现定时任务，动态更新UI
- ScheduledService 与 Timeline

#### 2. 测试
- TestFX介绍，quick start
- mac os下，clickOn()失效
- Text和Label的区别（测试中体现）
- JUnit4 vs hamcrest：`NoClassDefFoundError: org/hamcrest/MatcherAssert`
##### 原因：junit4自带了一个Hamcrest的jar包，需要在maven或gradle中exclude，以需要的Hamcrest版本为先
- 论id的重要性：fxml中`fx:id` 或 java代码中`setId()`
