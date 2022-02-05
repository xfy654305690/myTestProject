package com.project.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class testSendMessage {


    public static void main(String[] args) throws Exception {

        // 好友昵称
//        String friendNickName = "文件传输助手";
        String friendNickName = "aiaiai";

        searchMyFriendAndSend(friendNickName);

         }

    private static void searchMyFriendAndSend(String friendNickName) throws InterruptedException, IOException, ClassNotFoundException {
        // 创建Robot对象
        Robot robot = getRobot();
        //打开微信 Ctrl+Alt+W
        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_W);
        //释放Ctrl按键，像Ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ALT);

        // 该延迟不能少，否则无法搜索
        robot.delay(1000);

        // Ctrl + F 搜索指定好友
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // 将好友昵称发送到剪切板
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(friendNickName);
        clip.setContents(tText, null);
        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(1000);

        // 发送消息
        sendOneMsg();
    }

    /*private static void sendMsg() throws InterruptedException, IOException, ClassNotFoundException {
        String[] mottoes = {
                "我只爱你四天，春天夏天秋天冬天！",
                "我只爱你三天，昨天,今天,明天！",
                "我只爱你两天，白天，黑天！",
                "我只爱你一天，每一天！",
                "[玫瑰]爱你么么哒！",
                "[呲牙][坏笑]",
                "[奸笑]"
        };
    //        for (String motto : mottoes) {
    //            sendOneMsg(motto);
    //        }
        Thread.sleep(2000);

        //sendOneMsg("[得意]就问你，腻不腻害！");
    }*/

    //本地读取图片复制到剪切板
    private static void sendOneMsg() throws IOException, ClassNotFoundException {
        // 创建Robot对象
        Robot robot = getRobot();

        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 将字符串复制到剪切板
        //Transferable tText = new StringSelection(msg);
        //clip.setContents(tText, null);

        //ImageIO.write((RenderedImage)bufferedImage, "png", "D:\\test\\ToImg.png");
        BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(Paths.get("D:\\test\\ToImg.png")));
        Transferable trans = new Transferable() {
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[] { DataFlavor.imageFlavor };
            }

            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            public Object getTransferData(DataFlavor flavor)
                    throws UnsupportedFlavorException, IOException {
                if (isDataFlavorSupported(flavor))
                    return bufferedImage;
                throw new UnsupportedFlavorException(flavor);
            }

        };
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans,null);

        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // 回车发送
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(1000);
    }

    private static Robot getRobot(){
        // 创建Robot对象
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return robot;
    }

    }
