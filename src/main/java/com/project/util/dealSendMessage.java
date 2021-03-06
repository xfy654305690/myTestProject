package com.project.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class dealSendMessage {

    public static Robot getRobot(){
        // 创建Robot对象
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return robot;
    }

    //发送类型，1是文字，2是图片，3是文件
    public static void searchMyFriendAndSend(String friendNickName,int type,String UrlOrMessage ) throws Exception {
        // 创建Robot对象
        Robot robot = getRobot();
        //打开微信 Ctrl+Alt+W
        assert robot != null;

        //回到桌面
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_WINDOWS);

        robot.delay(8000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_W);
        //释放Ctrl按键，像Ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_ALT);

        // 该延迟不能少，否则无法搜索
        robot.delay(3000);

        // Ctrl + F 搜索指定好友
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.delay(3000);

        // 将好友昵称发送到剪切板
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(friendNickName);
        clip.setContents(tText, null);

        robot.delay(3000);

        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(3000);

        if(type == 1){
            // 发送消息
            sendMessage(UrlOrMessage);
        }
        if(type == 2){
            // 发送图片
            sendPicture(UrlOrMessage);
        }
        if(type == 3){
            // 发送文件
            putFileToSystemClipboard(UrlOrMessage);
        }

        robot.delay(8000);

        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(3000);
        // 回车发送
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(8000);
    }


    //本地读取图片复制到剪切板
    private static void sendMessage(String measge ) throws IOException {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
         //将字符串复制到剪切板
        Transferable tText = new StringSelection(measge);
        clip.setContents(tText, null);
    }

    //本地读取图片复制到剪切板
    private static void sendPicture(String InPutStreamUrl ) throws IOException {
        // 创建Robot对象
        Robot robot = getRobot();

        //Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 将字符串复制到剪切板
        //Transferable tText = new StringSelection(msg);
        //clip.setContents(tText, null);

        //ImageIO.write((RenderedImage)bufferedImage, "png", "D:\\test\\ToImg.png");
        BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(Paths.get(InPutStreamUrl)));
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

    }

    public static void putFileToSystemClipboard(String adress) throws Exception {
        File fileOut = new File(adress);
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final ClipboardOwner clipboardOwner = null;
        final Transferable transferable = new Transferable() {
            public boolean isDataFlavorSupported(final DataFlavor flavor) {
                return false;
            }
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[] { DataFlavor.javaFileListFlavor, DataFlavor.stringFlavor };
            }
            public Object getTransferData(final DataFlavor flavor) {
                if (flavor.equals(DataFlavor.javaFileListFlavor)) {
                    final List list = new ArrayList<>();
                    list.add(fileOut.getAbsolutePath());
                    return list;
                }
                if (flavor.equals(DataFlavor.stringFlavor)) {
                    return fileOut.getAbsolutePath();
                }
                return null;
            }
        };
        clipboard.setContents(transferable, clipboardOwner);
    }

}
