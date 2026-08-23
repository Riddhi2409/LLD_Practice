package composite.entity;

import composite.FileSystemNode;

public class File extends FileSystemNode {

    private String content;
    private String extension;

    public File(String name, double size) {
        super(name, size);
        extractExtension(name);
    }

    public String getContent() {
        return content;
    }

    public String getExtension() {
        return extension;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private void extractExtension(String name){
        int index=name.lastIndexOf('.');
        this.extension=(index>0) ? name.substring(index) : "";
    }

    @Override
    public boolean isFile() {
        return true;
    }


    public void display(int depth) {
        // Example: For a file at path "/document/cwa_lld/requirements.txt" at depth 3
        // indent = "      " (6 spaces: depth 3 * 2 spaces per depth)
        // Output would be: "      📄 requirements.txt"
        // For our example, if depth is 3 (meaning this file is at the 3rd level)
        // Generate indent string of 6 spaces (3*2)
        String indent = " ".repeat(depth * 2);
        // Print the file with appropriate indentation and emoji
        // e.g., "      📄 requirements.txt"
        System.out.println(indent + "📄 " + getName() + " - " + getSize() + "Bytes");
    }

    @Override
    public FileSystemNode cd(String name) {
        return null;
    }
}
