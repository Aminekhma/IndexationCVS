package fr.utils.pdf_tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    /**
     * @param fileName
     * @return true if the file's extension is '.pdf', false otherwise
     */
    public static boolean isPdf(String fileName) {
        return fileName.lastIndexOf(".pdf") != -1;
    }

    /**
     * @param directory_path: path of the containg directory
     * @return list of all the PDF files containd in the directory
     */
    public static ArrayList<String> listPdfFilesFromFolder(String directory_path) {
        File folder = new File(directory_path);
        File[] listOfFiles = folder.listFiles();
        /*
         * for (File f: listOfFiles){ System.out.println(f.getName()); }
         */
        ArrayList<String> files_list = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (isPdf(file.getName())) {
                    files_list.add(file.getName());
                }
            }
        }
        return files_list;
    }

    public static void changeFileName(String directory_path, String newName) {
        Path source = Paths.get(directory_path);

        try {

            Files.move(source, source.resolveSibling(newName));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return path toward the root of the project (the one conataining
     *         indexationcvs/)
     */
    public static String getProjectRoot() {
        String path = FileManager.class.getResource("FileManager.class").toString();
        path = path.replace("file:/", "");
        path = path.substring(0, path.indexOf("/BackEnd_IndexationCVS"));
        return path;
    }

    /**
     * @param path: path of the file to test
     * @return true if the file exists, false otherwise
     */
    public static boolean fileExists(String path) {
        return (new File(path)).isFile();
    }

    public static void movePDF(String path1, String path2) {

        String fromFile = path1;
        String toFile = path2;

        Path source = Paths.get(fromFile);
        Path target = Paths.get(toFile);

        try {

            // rename or move a file to other path
            // if target exists, throws FileAlreadyExistsException
            Files.move(source, target);

            // if target exists, replace it.
            // Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

            // multiple CopyOption
            /*
             * CopyOption[] options = { StandardCopyOption.REPLACE_EXISTING,
             * StandardCopyOption.COPY_ATTRIBUTES, LinkOption.NOFOLLOW_LINKS };
             * 
             * Files.move(source, target, options);
             */

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
