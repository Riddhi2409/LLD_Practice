package composite.entity;

import composite.FileSystemNode;


public class Directory extends FileSystemNode{
    public Directory(String name,double size) {
        super(name,size);
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void display(int depth) {
        // Example: For a directory at path "/document/cwa_lld" at depth 2
        // indent = "    " (4 spaces: depth 2 * 2 spaces per depth)
        // Let's say it has 3 child items
        // Output would be: "    📁 cwa_lld (3 items)"
        // Then it will recursively display each child with depth 3


        // For our example, if depth is 2 (meaning this directory is at the 2nd level)
        // Generate indent string of 4 spaces (2*2)
        String indent = " ".repeat(depth * 2);

        // Print the directory name with appropriate indentation, emoji and number of children
        // e.g., "    📁 cwa_lld (3 items)"
        System.out.println(indent + "📁 " + getName() + " (" + getChildren().size() + " items , size: " + getSize() +  " Bytes )" );
        // Then for each child (let's say we have "design_file_system" directory,
        // "requirements.txt" file, and "notes.md" file)
        // We recursively call display with depth+1 (3 in this case)
        // This will produce:
        //     "      📁 design_file_system (0 items)" (if empty directory)
        //     "      📄 requirements.txt"
        //     "      📄 notes.md"
        for (FileSystemNode child : getChildren()) {
            child.display(depth + 1);
        }
    }

    @Override
    public FileSystemNode cd(String name) {
        if (name.contains("/")) {
            throw new IllegalArgumentException("cd() expects a single path segment, not a full path: " + name);
        }
        if (!hasChild(name)) return null;
        FileSystemNode child = getChild(name);
        return child.isFile() ? null : child;
    }
}
