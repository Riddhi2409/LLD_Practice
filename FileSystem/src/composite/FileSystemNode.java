package composite;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FileSystemNode {
    private String name;
    private double size;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Map<String, FileSystemNode> children;

    public FileSystemNode(String name, double size) {
        this.name = name;
        this.size = size;
        this.createdAt=LocalDateTime.now();
        this.modifiedAt=LocalDateTime.now();
        this.children=new HashMap<>();
    }

    public void addChild(String name, FileSystemNode child) {
        this.children.put(name, child);
        this.modifiedAt = LocalDateTime.now();
    }

    public boolean hasChild(String name) {
        return this.children.containsKey(name);
    }

    public FileSystemNode getChild(String name) {
        return this.children.get(name);
    }

    public boolean removeChild(String name) {
        if (hasChild(name)) {
            children.remove(name);
            return true;
        }
        return false;
    }

    public abstract boolean isFile();
    public abstract void display(int depth);
    public abstract FileSystemNode cd(String name);

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Collection<FileSystemNode> getChildren() {
        return children.values();
    }

    protected void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }

    public void setSize(double size) {
        this.size = size;
    }
}
