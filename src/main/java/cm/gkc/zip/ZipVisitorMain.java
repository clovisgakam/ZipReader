package cm.gkc.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ZipVisitorMain {

    public static void main(String[] args) throws IOException {
        String source = "C:\\Users\\s731817\\Desktop\\CERTIF";
        String destination = "C:\\Users\\s731817\\Desktop\\CERTIF\\OCP_ZIPED";
        FileDetail fileDetail = ZipUtil.unzip(source, destination);
        Path path = Paths.get(fileDetail.getTarget());
        DirectoryVisitor directoryVisitor = new DirectoryVisitor();
        Files.walkFileTree(path, directoryVisitor);

        List<Node> nodes = directoryVisitor.getNodes();
        Collections.sort(nodes);
        String root = nodes.get(0).getParentName();
        for (Node node : nodes) {
            System.out.println(StringUtils.substringAfter(node.toString(), root));
        }
    }
}
