package cm.gkc.zip;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Node implements Comparable<Node> {

    int depth;

    String parentName;

    String name;

    boolean isFolder;

    public Node(Path path) {
        this.parentName = path.getParent().toString();
        this.name = path.getFileName().toString();
        this.isFolder = Files.isDirectory(path);
    }

    public int compareTo(Node node) {

        return this.depth - node.depth;
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

    public static int getPathDepth(List<Node> nodes, Path path) {

        int parentDepth = -1;

        String parentName = path.getParent().getFileName().toString();

        for (Node node : nodes) {
            if (node.getName().equals(parentName)) {

                parentDepth = node.depth;
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
