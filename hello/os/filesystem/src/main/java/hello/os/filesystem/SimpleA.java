package hello.os.filesystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.*;

class SimpleA{
    public static void main(String args[]) throws IOException {
//        helloPath1();
//        helloJion1();
        helloFile1();
//        helloIndexof1();
//        helloFileSave1();
    }

    private static void helloFileSave1() {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void helloIndexof1() {
        String path1 = "image/00/01/0001c1fb367a70da6df3301f5acb6692.jpg";
        System.out.println(path1.substring(path1.lastIndexOf('.')));
        System.out.println(path1.substring(0, path1.lastIndexOf('.')));
    }

    private static void helloFile1() {
        String currentDirectory = System.getProperty("user.dir");
        String path3 = "image/00/01/0001c1fb367a70da6df3301f5acb6692.jpg";
        File file1 = new File(Paths.get(currentDirectory, path3).toString());
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getParent());
    }

    private static void helloPath1() {
        Path path = Paths.get("D:/workspace/ContentW/Saurav_CV.docx");
        FileSystem fs =  path.getFileSystem();
        System.out.println(fs.toString());
        System.out.println(path.isAbsolute());
        System.out.println(path.getFileName());
        System.out.println(path.toAbsolutePath().toString());
        System.out.println(path.getRoot());
        System.out.println(path.getParent());
        System.out.println(path.getNameCount());
        System.out.println(path.getName(0));
        System.out.println(path.subpath(0, 2));
        System.out.println(path.toString());
        System.out.println(path.getNameCount());
//        Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
//        System.out.println(realPath.toString());
        String originalPath = "d:\\data\\projects\\a-project\\..\\another-project";
        Path path1 = Paths.get(originalPath);
        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);
    }

    private static void helloJion1() {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
        String path3 = "image/00/01/0001c1fb367a70da6df3301f5acb6692.jpg";
        System.out.println(Paths.get(currentDirectory, path3).toString());
    }
}  