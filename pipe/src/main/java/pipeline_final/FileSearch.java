package pipeline_final;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class FileSearch implements Runnable {

    private String Result;
    String path,key,input,T;
    int Check=0;
    protected BlockingQueue<String> blockingQueue;
    public FileSearch(String b,String c,BlockingQueue<String> blockingQueue) throws InterruptedException {

        key=c;
        Result=b;
        this.blockingQueue=blockingQueue;
        Check=0;
        path=this.blockingQueue.take();

    }
    public FileSearch(String b,String c,BlockingQueue<String> queue,String P,String t) throws InterruptedException{


        key=c;
        Result=b;
        path=t;
        Check=1;
        input=P;
        this.blockingQueue=queue;

        //T=blockingQueue.take();

    }

    @Override
    public void run() {
        Scanner scan = null;
        System.out.println("zsexrdcfvghbzsexdrcfvgbhsxedcfgvbhdxrcftgvbh");
        File fil = new File(Result);
        String Text_search = null;
        try {
            scan = new Scanner(new File(path));
            Writer output = new BufferedWriter(new FileWriter(fil));
        while(scan.hasNext()) {
            String line = scan.nextLine().toLowerCase().toString();
            if (line.contains(key)) {
                output.write(line);
            }

        }
        if(Check==1){
            blockingQueue.put(input);
        }
        output.close();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException{

        //fileSearch.parseFile("D:\\text\\hello.txt", "am");
    }

}
