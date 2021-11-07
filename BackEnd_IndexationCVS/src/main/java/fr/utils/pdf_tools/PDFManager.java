package fr.utils.pdf_tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONObject;

public class PDFManager {

    private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc;
    private COSDocument cosDoc;

    private String Text;
    private String filePath;
    private File file;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @param directory_path: path of the directory containing the PDF files
     * @param file_name:      name of the PDF file in the containg directory
     * @return a String with the raw content of the PDF file
     * @throws IOException
     */
    public String pdfToText(String directory_path, String file_name) {

        try {
            setFilePath(directory_path + file_name);

            this.pdfStripper = null;
            this.pdDoc = null;
            this.cosDoc = null;

            file = new File(filePath);
            parser = new PDFParser(new RandomAccessFile(file, "r"));

            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdDoc.getNumberOfPages();

            pdfStripper.setStartPage(0);
            pdfStripper.setEndPage(pdDoc.getNumberOfPages());

            Text = pdfStripper.getText(pdDoc);

            return Text;
        } catch (IOException e) {
            System.out.println("probleme ici");
            return null;
        }
    }

    /**
     * @param content:  raw text contained in the PDF file
     * @param fileName: name of the PDF file
     * @return unique ID corresponding to the file - if exists, first email address
     *         found in the content - fileName otherwise
     */
    public String renderID(String content, String fileName) {
        for (String word : content.split(" ")) {
            if (word.contains("@")) {
                return word;
            }
        }
        return fileName.substring(0, fileName.length() - 4);
    }

    /**
     * @param directory_path: path of the directory containing the PDF files
     * @param file_name:      name of the PDF file in the containg directory
     * @return JSON object containing the raw data of the PDF file
     * @throws IOException
     */
    public JSONObject pdfToJson(String directory_path, String file_name) throws IOException {

        String content = pdfToText(directory_path, file_name);

        JSONObject json = new JSONObject();

        json.put("id", file_name);
        json.put("content", content);
        json.put("name", file_name);
        return json;
    }

    /**
     * @param directory_path: path of the directory containing the PDF files
     * @return JSON objects contained in a list: 1 file = 1 object
     * @throws IOException
     */
    public ArrayList<JSONObject> dir2Json(String directory_path) throws IOException {

        ArrayList<JSONObject> json_list = new ArrayList<>();
        ArrayList<String> files_list = FileManager.listPdfFilesFromFolder(directory_path);

        for (String file_name : files_list) {
            System.out.println(file_name);
            json_list.add(pdfToJson(directory_path, file_name));
        }

        return json_list;
    }

    /**
     * @param file_in:  complete path of the PDF file to convert
     * @param file_out: complete path of new file to render
     * @throws Exception
     */
    public static void PDFtoJPG(String in, String out) throws Exception {

        PDDocument pd = PDDocument.load(new File(in));
        PDFRenderer pr = new PDFRenderer(pd);
        BufferedImage bi = pr.renderImageWithDPI(0, 300);
        ImageIO.write(bi, "PNG", new File(out.replace(".pdf", ".png")));

    }

}