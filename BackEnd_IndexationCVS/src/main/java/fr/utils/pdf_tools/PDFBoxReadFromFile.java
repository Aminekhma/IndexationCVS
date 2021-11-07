package fr.utils.pdf_tools;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

// this is an auxiliary class in order to test the json rendering functions
public class PDFBoxReadFromFile {
    public static void main(String[] args) throws Exception {
        PDFManager pdfManager = new PDFManager();
        String directory_path = FileManager.getProjectRoot()
                + "/BackEnd_IndexationCVS/src/main/java/fr/sorbonne/IndexationCVS/service/assets/CV2.pdf";
        String jpgpath = FileManager.getProjectRoot() + "/FrontEnd_IndexationCVS/";
        try {
            ArrayList<JSONObject> json_list = pdfManager.dir2Json(directory_path);
            for (JSONObject obj : json_list) {
                System.out.println(System.getProperty("user.dir"));
                System.out.println("====================== id : " + obj.getString("id"));
                System.out.println(obj.getString("content"));
                PDFManager.PDFtoJPG(directory_path, jpgpath);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
