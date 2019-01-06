package pipeline_final;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import sun.security.provider.NativePRNG;

public class ConvertPdf2Word implements Runnable {
    private String input_123;
    private String Result;
    int count=0;
    int check;
    String path,Key;
    protected BlockingQueue<String> blockingQueue;
    private final Lock queueLock = new ReentrantLock();
    public ConvertPdf2Word(String a,String b,BlockingQueue<String> queue){
        input_123 = a;
        Result=b;
        count=1;
        this.blockingQueue = queue;
    }
    public ConvertPdf2Word(String a,BlockingQueue<String> blockingQueue) throws InterruptedException {
        input_123 = a;

        count=0;
        this.blockingQueue = blockingQueue;
        Result=this.blockingQueue.take();
    }
    public void run(){
        PdfReader reader = null;
        System.out.println("Document converted started");

        try {
            reader = new PdfReader(input_123);

            XWPFDocument doc = new XWPFDocument();
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                TextExtractionStrategy strategy = parser.processContent(i,new SimpleTextExtractionStrategy());
                String text = strategy.getResultantText();
                XWPFParagraph p = doc.createParagraph();
                XWPFRun run = p.createRun();
                run.setText(text);
                run.addBreak(BreakType.PAGE);
            }
            FileOutputStream out = new FileOutputStream(Result);
            doc.write(out);
            out.close();
            reader.close();
            //System.out.println("Document converted successfully");
            //System.out.println("---------------------------"+Result+"---------------------------------");
            //System.out.println("--------blocked_Queue_put------");
            if(count==1){
                blockingQueue.put(Result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

    }
}