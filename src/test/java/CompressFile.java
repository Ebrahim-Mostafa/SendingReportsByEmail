import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFile {

    private List<String> fileList; 
    public static String SPOON_OUTPUT_ZIP_FILE = System.getProperty("user.dir")+ File.separator+"android"+File.separator+"app"+ File.separator+"build"+ File.separator+"spoon-output.zip";
    ///Users/imostafa/instore-security-app/android/app/build/spoon-output.zip

    public static String SPOON_SOURCE_FOLDER =System.getProperty("user.dir")+ File.separator+"android"+File.separator+"app"+ File.separator+"build"+ File.separator+"spoon-output";
    ///Users/imostafa/instore-security-app/android/app/build/spoon-output

    public static String JACOCO_SOURCE_FOLDER=System.getProperty("user.dir")+ File.separator+"android"+File.separator+"app"+ File.separator+"build"+ File.separator+"reports";
    ///Users/imostafa/instore-security-app/android/app/build/reports

    public static String JACOCO_OUTPUT_ZIP_FILE= System.getProperty("user.dir")+ File.separator+"android"+File.separator+"app"+ File.separator+"build"+ File.separator+"reports.zip";

    public CompressFile() {
        fileList = new ArrayList< String >();
    }

    public void spoonZipIt (String zipFile){
        byte[] buffer = new byte[1024];
        String source = new File(SPOON_SOURCE_FOLDER).getName();
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file : this.fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(SPOON_SOURCE_FOLDER + File.separator + file);
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
            System.out.println("Folder successfully compressed");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void jacocoZipIt (String zipFile){
        byte[] buffer = new byte[1024];
        String source = new File(JACOCO_SOURCE_FOLDER).getName();
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file : this.fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(JACOCO_SOURCE_FOLDER + File.separator + file);
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
            System.out.println("Folder successfully compressed");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void spoonGenerateFileList (File node){
        // add file only
        if (node.isFile()) {
            fileList.add(spoonGenerateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                spoonGenerateFileList(new File(node, filename));
            }
        }
    }

    public void jacocoGenerateFileList (File node){
        // add file only
        if (node.isFile()) {
            fileList.add(jacocoGenerateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                jacocoGenerateFileList(new File(node, filename));
            }
        }
    }
    private String spoonGenerateZipEntry (String file){
        return file.substring(SPOON_SOURCE_FOLDER.length() + 1, file.length());
    }
    private String jacocoGenerateZipEntry (String file){
        return file.substring(JACOCO_SOURCE_FOLDER.length() + 1, file.length());
    }
}

