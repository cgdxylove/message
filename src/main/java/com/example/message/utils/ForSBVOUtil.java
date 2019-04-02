package com.example.message.utils;

import java.io.*;

public class ForSBVOUtil {

    public static String FILE_CODE = "UTF-8";
    private static FileInputStream fileInputStream = null;
    private static InputStreamReader inputStreamReader = null;
    private static File file = null;

    public static void main(String[] args) throws IOException {
        String pathName_in ="D:\\IdeaProjects\\pangu-tax-declare\\src\\main\\java\\com\\pangu\\taxdeclare\\domain\\declare\\yunzhangfang\\sb29806\\LrbGridlbVO.java";
        String pathName_out ="D:\\ZcfzbGridlbVO2.java";
        file = new File(pathName_in);
        BufferedWriter bufw=new BufferedWriter(new FileWriter(pathName_out));
        fileInputStream = new FileInputStream(file);
        inputStreamReader = new InputStreamReader(fileInputStream, FILE_CODE);
        BufferedReader br = new BufferedReader(inputStreamReader, 60 * 1024 * 1024);
        StringBuffer buffer = new StringBuffer();
        String lineTxt = null;
        int flag =  0 ;
        int no = 0 ;
        while ((lineTxt = br.readLine()) != null) {
            if(lineTxt.contains("XmlAccessorType")){
                flag = 1 ;
            }
            if(flag == 1){//@XmlAccessorType注解以下内容才进行处理
                System.out.println("=="+lineTxt);
                if("".equals(lineTxt)){
                    bufw.write("\n");
                    lineTxt = "@XmlElement";
                    bufw.write(lineTxt);
                    bufw.write("\n");
                }else if(lineTxt.contains("{")||lineTxt.contains(".")||lineTxt.contains("}")){
                    lineTxt = "";
                }else if(lineTxt.contains("XmlElement")){
                    bufw.write("\n");
                    bufw.write(lineTxt);
                    bufw.write("\n");
                }else{
                    bufw.write(lineTxt);
                }
            }
        }
        bufw.flush();
        if(bufw!=null) //不为空才需要关闭
        {
            bufw.close();//关闭缓冲区，就是关闭缓冲区的流对象，所以，只关闭缓冲区即可
        }
    }

}