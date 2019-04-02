package com.example.message.utils;

import java.io.*;

public class ForSBVOUtil2 {

    public static String FILE_CODE = "UTF-8";
    private static FileInputStream fileInputStream = null;
    private static InputStreamReader inputStreamReader = null;
    private static File file = null;

    public static void main(String[] args) throws IOException {
        String pathName_in ="C:\\Users\\Lenovo\\Desktop\\方欣\\开放平台税务报表接口对接资料\\文化事业建设费报表结构体.txt";
        String pathName_out ="D:\\文化事业建设费报表结构体.txt";
        file = new File(pathName_in);
        BufferedWriter bufw=new BufferedWriter(new FileWriter(pathName_out));
        fileInputStream = new FileInputStream(file);
        inputStreamReader = new InputStreamReader(fileInputStream, FILE_CODE);
        BufferedReader br = new BufferedReader(inputStreamReader, 60 * 1024 * 1024);
        StringBuffer buffer = new StringBuffer();
        String lineTxt = null;
        int no = 0 ;
        while ((lineTxt = br.readLine()) != null) {
            System.out.println("=="+lineTxt);
            if("".equals(lineTxt)){
                bufw.write("\n");
            }else if(lineTxt.contains("-")){
                bufw.write("\n");
                lineTxt = lineTxt.substring(0,lineTxt.indexOf("-"));
                bufw.write(lineTxt);
            }else{
                bufw.write(lineTxt);
            }
        }
        bufw.flush();
        if(bufw!=null) //不为空才需要关闭
        {
            bufw.close();//关闭缓冲区，就是关闭缓冲区的流对象，所以，只关闭缓冲区即可
        }
    }

}