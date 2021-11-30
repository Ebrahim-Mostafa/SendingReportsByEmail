import java.io.File;

public class SendEmail {

    public static void main(String[] args) {

        CompressFile appZip = new CompressFile();
        File spoonoutput = new File(CompressFile.SPOON_SOURCE_FOLDER);
        File jacocooutput = new File(CompressFile.JACOCO_SOURCE_FOLDER);

        if (spoonoutput.exists()  && spoonoutput.isDirectory() && jacocooutput.exists() && jacocooutput.isDirectory()) {
            appZip.spoonGenerateFileList(new File(CompressFile.SPOON_SOURCE_FOLDER));
            appZip.spoonZipIt(CompressFile.SPOON_OUTPUT_ZIP_FILE);
            SendSpoonReports.sendSpoonReport();
            appZip = new CompressFile();
            appZip.jacocoGenerateFileList(new File(CompressFile.JACOCO_SOURCE_FOLDER));
            appZip.jacocoZipIt(CompressFile.JACOCO_OUTPUT_ZIP_FILE);
            SendJacocoReports.sendJacocoReport();
        }else if(spoonoutput.exists() && spoonoutput.isDirectory()){
            appZip.spoonGenerateFileList(new File(CompressFile.SPOON_SOURCE_FOLDER));
            appZip.spoonZipIt(CompressFile.SPOON_OUTPUT_ZIP_FILE);
            SendSpoonReports.sendSpoonReport();
        }else if (jacocooutput.exists() && jacocooutput.isDirectory()){
            appZip.jacocoGenerateFileList(new File(CompressFile.JACOCO_SOURCE_FOLDER));
            appZip.jacocoZipIt(CompressFile.JACOCO_OUTPUT_ZIP_FILE);
            SendJacocoReports.sendJacocoReport();
        }else {
            System.out.println("There are No Files To Send");
        }
    }
}