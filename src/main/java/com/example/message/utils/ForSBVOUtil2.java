package com.example.message.utils;

import java.io.*;

public class ForSBVOUtil2 {

    public static String FILE_CODE = "UTF-8";
    private static FileInputStream fileInputStream = null;
    private static InputStreamReader inputStreamReader = null;
    private static File file = null;

    public static void main(String[] args) throws IOException {
        String pathName_in ="C:\\Users\\Lenovo\\Desktop\\A.txt";
        String pathName_out ="C:\\Users\\Lenovo\\Desktop\\AB.txt";
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
            }else if(lineTxt.contains("--")){
                bufw.write("\n");
                lineTxt = "";
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