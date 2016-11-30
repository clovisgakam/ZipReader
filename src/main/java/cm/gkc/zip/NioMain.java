package cm.gkc.zip;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class NioMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        String source = "C:\\Users\\s731817\\Desktop\\CERTIF";
        // String destination =
        // "C:\\Users\\s731817\\Desktop\\CERTIF\\OCP_ZIPED";
        // String password = "password";
        //
        // try {
        //
        // ZipFile zipFile = new ZipFile(source);
        // if (zipFile.isEncrypted()) {
        // zipFile.setPassword(password);
        // }
        // zipFile.extractAll(destination);
        // } catch (ZipException e) {
        // e.printStackTrace();
        // }
        DirVisitor dirVisitor = new DirVisitor();
        Files.walkFileTree(Paths.get(source), dirVisitor);
        List<FileTo> fileTos = dirVisitor.getFileTos();

        for (FileTo fileTo : fileTos) {
            System.out.println(fileTo.toString());
        }
    }

}

class DirVisitor extends SimpleFileVisitor<Path> {
    int count;

    List<FileTo> fileTos = new ArrayList<FileTo>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        FileTo fileTo = new FileTo(dir);
        int pathDepth = FileTo.getPathDepth(fileTos, dir);
        fileTo.setDepth(++pathDepth);
        fileTos.add(fileTo);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileTo fileTo = new FileTo(file);
        int pathDepth = FileTo.getPathDepth(fileTos, file);
        fileTo.setDepth(++pathDepth);
        fileTos.add(fileTo);
        return FileVisitResult.CONTINUE;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FileTo> getFileTos() {
        return fileTos;
    }

    public void setFileTos(List<FileTo> fileTos) {
        this.fileTos = fileTos;
    }

}

class FileTo implements Comparable<FileTo> {

    int depth;

    String parentName;

    String name;

    boolean isFolder;

    public FileTo(Path path) {
        this.parentName = path.getParent().toString();
        this.name = path.getFileName().toString();
        this.isFolder = Files.isDirectory(path);
    }

    public int compareTo(FileTo arg0) {

        return this.depth - arg0.depth;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean isFolder) {
        this.isFolder = isFolder;
    }

    @Override
    public String toString() {
        return parentName + "/" + name + "  " + depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public static int getPathDepth(List<FileTo> fileTos, Path path) {

        int parentDepth = -1;

        String parentName = path.getParent().getFileName().toString();

        for (FileTo fileTo : fileTos) {
            if (fileTo.getName().equals(parentName)) {

                parentDepth = fileTo.depth;
                break;
            }

        }

        return parentDepth;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + depth;
        result = prime * result + (isFolder ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parentName == null) ? 0 : parentName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FileTo other = (FileTo) obj;
        if (depth != other.depth) {
            return false;
        }
        if (isFolder != other.isFolder) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (parentName == null) {
            if (other.parentName != null) {
                return false;
            }
        } else if (!parentName.equals(other.parentName)) {
            return false;
        }
        return true;
    }

}
