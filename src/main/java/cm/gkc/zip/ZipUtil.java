package cm.gkc.zip;

import java.nio.file.Files;
import java.nio.file.Paths;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipUtil {

    public static FileDetail unzip(String source, String destination) {
        FileDetail fileDetail = new FileDetail();
        try {
            ZipFile zipFile = new ZipFile(source);
            if (!Files.exists(Paths.get(destination))) {
                zipFile.extractAll(destination);
            }
            fileDetail.setSource(source);
            fileDetail.setTarget(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }

        return fileDetail;
    }

}
