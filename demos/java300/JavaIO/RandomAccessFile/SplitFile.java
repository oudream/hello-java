package JavaIO.RandomAccessFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 说明：文件的拆分和合并。
 *
 * @Auther: 11432_000
 * @Date: 2018/12/22 16:16
 * @Description:
 */
public class SplitFile {

    private static final int KB = 1024;
    private static final int MB = 1024 * KB;

    private File src;
    private int blockSize;
    private String destDir;
    private List<String> destPath;
    private int size;
    private String fileSuffix;
    private String fileName;

    public File getSrc() {
        return src;
    }

    public void setSrc(File src) {
        this.src = src;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public List<String> getDestPath() {
        return destPath;
    }

    public void setDestPath(List<String> destPath) {
        this.destPath = destPath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void init(int blockSize ,String destDir ,String filePath){
        /**
         *
         * 功能描述: 初始化，参数：分块大小（单位KB），分块路径，想要分块的文件路径。
         *
         * @param: [blockSize, destDir, filePath]
         * @return: void
         * @auther: 11432_000
         * @date: 2018/12/22 17:31
         */
        this.blockSize = blockSize * KB;
        this.destDir = destDir;
        this.src = new File(filePath);
        this.size = (int) Math.ceil( this.src.length() / this.blockSize);
        this.destPath = new ArrayList<>();
        this.fileSuffix = this.src.getName().substring(this.src.getName().lastIndexOf("."));
        this.fileName = this.src.getName().substring(0 ,this.src.getName().lastIndexOf("."));
        File file = new File(destDir);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    public void split()throws IOException{
        /**
         *
         * 功能描述: 将文件分块存储。
         *
         * @param: []
         * @return: void
         * @auther: 11432_000
         * @date: 2018/12/22 17:27
         */
        BufferedOutputStream outputStream = null;
        int start = 0;
        String newFileName;
        for (int i = 0; i < this.size; i++) {
            Data data = readBlock(start, this.blockSize, this.src);
            newFileName = this.destDir + "/" +this.fileName + "-" + i + this.fileSuffix + "data";
            File file = new File(newFileName);
            file.createNewFile();
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(data.getDatas() ,0, (int) data.getLength());
            outputStream.close();
            this.destPath.add(newFileName);
            start += data.getLength();
        }
    }

    public Data readBlock(int start , int size , File file)throws IOException {
        /**
         *
         * 功能描述:从指定位置读取文件的一段指定大小的数据
         *
         * @param: [start, size, file]
         * @return: JavaIO.RandomAccessFile.Data
         * @auther: 11432_000
         * @date: 2018/12/22 17:27
         */
        RandomAccessFile randomAccessFile = new RandomAccessFile(file ,"rw");
        int surplusSize = size;
        int blockStart = 0;
        byte[] flush = new byte[KB];
        byte[] block = new byte[size];
        int len;
        randomAccessFile.seek(start);
        while ((len = randomAccessFile.read(flush)) > -1 && surplusSize > 0){
            if (surplusSize >= len){
                System.arraycopy(flush ,0 ,block ,blockStart ,len);
                surplusSize -= len;
                blockStart += len;
            }else {
                System.arraycopy(flush ,0 ,block ,blockStart ,surplusSize);
                surplusSize = 0;
                blockStart += surplusSize;
            }
        }
        randomAccessFile.close();
        return new Data(block ,blockStart);
    }

    public void merge(String newFileName)throws IOException{
        /**
         *
         * 功能描述: 将文件合并为一个新文件。
         *
         * @param: [newFileName]
         * @return: void
         * @auther: 11432_000
         * @date: 2018/12/24 10:09
         */
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFileName ,true));
        Vector<InputStream> vector = new Vector<>();
        byte[] bytes = new byte[1 * KB];
        int len;
        SequenceInputStream inputStreams = null;
        for (int i = 0; i < this.destPath.size(); i++) {
            vector.add(new FileInputStream(this.destPath.get(i)));
        }
        inputStreams = new SequenceInputStream(vector.elements());
        while ((len = inputStreams.read(bytes)) > -1){
            outputStream.write(bytes , 0 ,len);
        }
        outputStream.flush();
        outputStream.close();
        inputStreams.close();
    }
}
