package pipeline_final;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.reflect.Constructor;
class MyThread_1 implements Runnable{
    Thread t;
    int count;

    private final Lock queueLock = new ReentrantLock();
    int D;
    String docx,pdf,txt,search;;
    public MyThread_1(int i){
        count=i;
        t=new Thread(this);
        t.start();

    }
    @Override
    public void run() {
        long startTime = System.nanoTime();
        Class<?> b = null;
        Class<?> c = null;
        Class<?> d = null;
        try {

            b= Class.forName("pipeline_final.ConvertPdf2Word");
            d= Class.forName("pipeline_final.FileSearch");
            c= Class.forName("pipeline_final.Test");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("111111111111      yo "+ count);
        acacaac file=new acacaac(count);
        System.out.println("222222222222222   yo "+ count);
        D=0;

        File pdf = new File("/home/user/Desktop/nishank/pdf");
        File[] PDFFiles = pdf.listFiles();
        File docx = new File("/home/user/Desktop/nishank/doc");
        File[] DocxFiles = docx.listFiles();
        File Txt = new File("/home/user/Desktop/nishank/text");
        File Search = new File("/home/user/Desktop/nishank/Search");
        //File Docx = file.filechoose(D);
        System.out.println("input: "+ docx);
        D+=1;
        //File Pdf = file.filechoose(D);
        D+=1;

//        File Text =file.filechoose(D);
        D+=1;
        //File Search = file.filechoose(D);
        if(count==1){
            //File[] DocxFiles = Docx.listFiles();
            final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();
            String Key=file.input3();
            for (int i = 0; i < PDFFiles.length; i++) {
                if (PDFFiles[i].isFile()) {
                    String Docx_path = docx +"/"+i+".docx";
                    String Pdf_path = pdf +"/"+PDFFiles[i].getName().toString();
                    String Txt_path = Txt +"/"+i+".txt";
                    String Search_path = Search +"/"+i+".txt";
                    System.out.println("File path " + Docx_path );
                    //ConvertPdf2Word f2 = null;
                    //Object f2 = null;

                    //Thread[] threads = new Thread[1];
                    //get all constructors
                    Constructor<? extends Thread> cons[] = (Constructor<? extends Thread>[]) b.getConstructors();
                    try {
                        new Thread(cons[0].newInstance(Pdf_path,Docx_path,synchronousQueue)).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //ConvertPdf2Word pdf2word=new ConvertPdf2Word(Pdf_path,Docx_path,synchronousQueue);



                    Constructor<? extends Thread> con[] = (Constructor<? extends Thread>[]) c.getConstructors();
                    try {
                        new Thread(con[0].newInstance(Txt_path,synchronousQueue)).start();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    Constructor<? extends Thread> co[] = (Constructor<? extends Thread>[]) d.getConstructors();
                    try {
                        new Thread(co[0].newInstance(Search_path,Key,synchronousQueue)).start();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(count==2){
            //File[] DocxFiles = Docx.listFiles();
            final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();
            String Key=file.input3();
            for (int i = 0; i < PDFFiles.length; i++) {
                if (PDFFiles[i].isFile()) {
                    String Docx_path = docx +"/"+i+".docx";
                    String Pdf_path = pdf +"/"+PDFFiles[i].getName().toString();
                    String Txt_path = Txt +"/"+i+".txt";
                    String Search_path = Search +"/"+i+".txt";
                    System.out.println("File path " + Docx_path );

                    Constructor<? extends Thread> co[] = (Constructor<? extends Thread>[]) d.getConstructors();
                    try {
                        new Thread(co[1].newInstance(Search_path,Key,synchronousQueue,Pdf_path,Txt_path));
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    Constructor<?extends Thread> cons[] = (Constructor<? extends Thread>[]) b.getConstructors();
                    try {
                        new Thread(cons[1].newInstance(Pdf_path,synchronousQueue));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(count==3){
            final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();
            String Key=file.input3();
            for (int i = 0; i < DocxFiles.length; i++) {
                if (DocxFiles[i].isFile()) {
                    String Docx_path = docx + "/" + DocxFiles[i].getName().toString();
                    String Pdf_path = pdf + "/" + i + ".pdf";
                    String Txt_path = Txt + "/" + i + ".txt";
                    String Search_path = Search + "/" + i + ".txt";
                    System.out.println("File path " + Docx_path);

                    try {
                        Constructor<?> con[] = c.getConstructors();
                        new Thread((Runnable) con[0].newInstance(Txt_path,Docx_path,synchronousQueue)).start();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    Constructor<? extends Thread> co[] = (Constructor<? extends Thread>[]) d.getConstructors();
                    try {
                        new Thread(co[1].newInstance(Search_path,Key,synchronousQueue)).start();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        else if(count==4){
            final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();
            for (int i = 0; i < PDFFiles.length; i++) {
                if (PDFFiles[i].isFile()) {
                    String Docx_path = docx +"/"+i+".docx";
                    String Pdf_path = pdf +"/"+PDFFiles[i].getName().toString();
                    String Txt_path = Txt +"/"+i+".txt";
                    String Search_path = Search +"/"+i+".txt";
                    System.out.println("File path " + Docx_path );


                    //get all constructors
                    Constructor<?extends Thread> cons[] = (Constructor<? extends Thread>[]) b.getConstructors();
                    try {
                        new Thread(cons[0].newInstance(Pdf_path,Docx_path,synchronousQueue)).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //ConvertPdf2Word pdf2word=new ConvertPdf2Word(Pdf_path,Docx_path,synchronousQueue);


                    try {
                        synchronousQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        long endtime = System.nanoTime();
        long totalTime = endtime - startTime;

        double seconds = (double)totalTime / 1000000000.0;
        System.out.println("Time in sec: ");
        System.out.println(seconds);
    }
}

public class acacaac {
    int count;
    int zxc=0;
    public acacaac(int i){
        count=i;
    }
    BlockingQueue<String> synchronousQueue;
    public acacaac() {}
    public File filechoose(int q1){
        String path=null;
        JFileChooser chooser = new JFileChooser();
        if(q1==0){
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("word file location");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
        }
        if(q1==1){
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("pdf folder location");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
        }
        if(q1==2){
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("txt folder location");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
        }
        if(q1==3){
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("search folder location");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
        }
        File f = new File(chooser.getCurrentDirectory().getAbsolutePath());
        System.out.println(path);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }
        return f;
    }
    public int input2(){
        int cinput = Integer.parseInt((JOptionPane.showInputDialog("choose how many process you want to implement")));
        System.out.println("I choose " + cinput);
        return cinput;
    }
    public String input3(){
        String cinput = JOptionPane.showInputDialog("key to search");
        System.out.println("I choose " + cinput);
        return cinput;
    }
    public int choice(){
        String output = "";
        String input = JOptionPane.showInputDialog("\n Please choose an option" +
                "\n 1. p1(pdf2word) -> p2(search && extract)" +
                "\n 2. p2->p1" +
                "\n 3. search" +
                "\n 4. convert pdf2word" +
                "\n 5. exit");
        int in = Integer.parseInt(input);
        Scanner kybd = new Scanner(System.in);
        switch(in) {
            case 1:
                System.out.println("yo");
                break;
            case 2:
                System.out.println("yo2");
                break;
            case 3:
                System.out.println("yo3");
                break;
            case 4:
                System.out.println("yo4");
                break;
            default:
                System.out.println("end");
                break;
        }
        return in;
    }

    public static void main(String s[]) {
        int a;
        int choose;
        acacaac file=new acacaac();
        int count=file.input2();
        for(a=0;a<count;a++){
            System.out.println("33333333333   yo "+ count);
            choose=file.choice();
            MyThread_1 thread=new MyThread_1(choose);
        }
    }
}
