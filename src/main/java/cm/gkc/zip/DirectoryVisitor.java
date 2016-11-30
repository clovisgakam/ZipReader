package cm.gkc.zip;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DirectoryVisitor extends SimpleFileVisitor<Path> {

    String ParentDir = null;

    List<Node> nodes = new ArrayList<Node>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        visitFile(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        visitFile(file);
        return FileVisitResult.CONTINUE;
    }

    private void visitFile(Path file) {
        Node node = new Node(file);
        int pathDepth = Node.getPathDepth(nodes, file);
        node.setDepth(++pathDepth);
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

}
