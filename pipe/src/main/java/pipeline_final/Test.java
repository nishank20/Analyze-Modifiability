package pipeline_final;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.BlockingQueue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;


public class Test implements Runnable
{
    String PREFACE;
    String RESULT;
    protected BlockingQueue<String> blockingQueue;
    public Test(String b,String g,BlockingQueue<String> queue) {
        RESULT=b;
        PREFACE=g;
        this.blockingQueue=queue;
    }
    public Test(String b,BlockingQueue<String> queue) throws InterruptedException {
        RESULT=b;
        this.blockingQueue=queue;
        PREFACE=this.blockingQueue.take();

    }
    public void run(){

        try {
            //System.out.println("---------------"+PREFACE+"--------------------------------");
            File file = new File(PREFACE);
            String fileType = null;
            fileType = Files.probeContentType(file.toPath());

            //System.out.println(fileType);
            switch(fileType)
            {
                case "application/pdf" : parsePdf();
                    break;
                case "application/msword" : parseDoc();
                    break;
                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document" : parseDocx();
                    break;
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parsePdf()
    {
        try {
            PdfReader reader = new PdfReader(PREFACE);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            PrintWriter out = new PrintWriter(new FileOutputStream(RESULT));
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                out.println(strategy.getResultantText());
            }

            out.flush();
            out.close();
            reader.close();
            //System.out.println("---------------"+RESULT+"247829379087977890--------------------------------");
            blockingQueue.put(RESULT);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parseDoc()
    {
        POIFSFileSystem fs = null;
        try {

            //System.out.println("---------------"+PREFACE+"247829379087977890--------------------------------");
            fs = new POIFSFileSystem(new FileInputStream(PREFACE));
            HWPFDocument doc = new HWPFDocument(fs);
            WordExtractor we = new WordExtractor(doc);
            String text = we.getText();
            File fil = new File(RESULT);
            Writer output = new BufferedWriter(new FileWriter(fil));
            output.write(text);
            output.close();
            //System.out.println("---------------"+PREFACE+"247829379087977890--------------------------------");
            blockingQueue.put(RESULT);

        } catch (Exception exep) {
            System.out.println(exep);
        }
    }

    public void parseDocx(){

        File file = null;
        try {

            //System.out.println("---------------"+PREFACE+"247829379087977890--------------------------------");
            file = new File(PREFACE);
            String fileType = Files.probeContentType(file.toPath());
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor ex = new XWPFWordExtractor(doc);
            String text = ex.getText();
            File fil = new File(RESULT);
            Writer output = new BufferedWriter(new FileWriter(fil));
            output.write(text);
            output.close();
            //System.out.println("---------------"+RESULT+"247829379087977890--------------------------------");
            blockingQueue.put(RESULT);
        } catch (Exception exep) {
            System.out.println(exep);
        }
    }
}