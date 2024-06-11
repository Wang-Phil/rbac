package com.wangweicheng.rbac.util;


import org.apache.commons.codec.binary.Base64;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 验证码工具类
 */
public class VerifyCodeUtil {
    public static final int WIDTH = 60; //验证码图片宽度
    public static final int HEIGHT= 20; //验证码图片高度
    public static final String CHARS="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 种子数据
    public static final int CODE_LENGTH=4; // 验证码长度
    public static final int BG_COLOR= 0xDCDCDC; // 验证码图片背景颜色
    public static Map<String,String> generateVerifyCode(){
        Map<String,String> map =new HashMap<>();
        //创建内存图像并获得图形上下文
        BufferedImage image=new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        /*
         * 产生随机验证码
         * 定义验证码的字符表
         */
        char[] rands=new char[CODE_LENGTH];
        for(int i=0;i<CODE_LENGTH;i++){
            int rand = new Random().nextInt(CHARS.length());
            rands[i]=CHARS.charAt(rand);
        }
        map.put("code",new String(rands));
        // 存储验证到
        /*
         * 产生图像
         * 画背景
         */
        g.setColor(new Color(BG_COLOR));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        /*
         * 随机产生120个干扰点
         */
        int len = 120;
        for(int i=0;i< len ;i++){
            int x=(int)(Math.random()*WIDTH);
            int y=(int)(Math.random()*HEIGHT);
            //int red=(int)(Math.random()*255);
            int red = new Random().nextInt(255);
            //int green=(int)(Math.random()*255);
            int green = new Random().nextInt(255);
            //int blue=(int)(Math.random()*255);
            int blue = new Random().nextInt(255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x, y, 1, 0);
        }
        g.setColor(Color.GRAY);
        g.setFont(new Font(null, Font.ITALIC|Font.BOLD,18));
        //在不同高度输出验证码的不同字符
        g.drawString(""+rands[0], 1, 17);
        g.drawString(""+rands[1], 16, 15);
        g.drawString(""+rands[2], 31, 18);
        g.drawString(""+rands[3], 46, 16);
        g.dispose();
        //将图像传到客户端
        try {
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", baos);
            byte[] buffer=baos.toByteArray();
            String uuid = UUID.randomUUID().toString();
            map.put("uuid",uuid);
            map.put("img", Base64.encodeBase64String(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 校验验证码是否正确
     *
     * @param code
     * @param ignoreCase
     * @return
     */
    public static boolean verification(String code,String verifyCode, boolean ignoreCase){
        if(code != null && !code.trim().equals("")){
            return ignoreCase ? verifyCode.equalsIgnoreCase(code) : verifyCode.equals(code);
        }
        return false;
    }

}
