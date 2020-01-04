package JavaClassLoader.ClassLoader;

import java.io.*;

/**
 * 说明：自定义文件系统加载器
 *
 * @Auther: 11432_000
 * @Date: 2019/1/22 16:39
 * @Description:
 */
public class FileSystemClassLoader extends ClassLoader{
    private String rootDir;

    public FileSystemClassLoader(String rootDir){
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(name);
        //如果已经加载
        if (loadedClass != null){return loadedClass;}
        else {
            try {
                ClassLoader parent = this.getParent();
                loadedClass = parent.loadClass(name);
            }catch (Exception e){
                System.out.println("双亲委托失败");
            }
            if (loadedClass != null){return loadedClass;}
            else {
                byte[] classData = getClassData(name);
                if (classData == null){
                    throw new ClassNotFoundException();
                }else {
                    loadedClass = defineClass(name, classData, 0, classData.length);
                }
            }
        }
        return loadedClass;
    }

    private byte[] getClassData(String name){
        String filePath = this.rootDir + "/" + name.replace(".","/") + ".class";
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
            bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            byte[] flush = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(flush)) != -1){
                bufferedOutputStream.write(flush ,0 ,len);
            }
            bufferedOutputStream.flush();
            byte[] data = byteArrayOutputStream.toByteArray();
            return data;
        } catch (IOException e){
            return null;
        }finally {
            try {
                if (bufferedInputStream != null){
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
