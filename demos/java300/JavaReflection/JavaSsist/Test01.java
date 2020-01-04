package JavaReflection.JavaSsist;

import javassist.*;

/**
 * 说明：JavaSsist测试类
 *
 * @Auther: 11432_000
 * @Date: 2019/1/21 12:58
 * @Description:
 */
public class Test01 {
    public static void main(String[] args)throws Exception {
        //获取Class池
        ClassPool classPool = ClassPool.getDefault();
        //创建一个类
        CtClass ctClass = classPool.makeClass("JavaReflection.JavaSsist");
        //创建属性
        CtField ctField01 = CtField.make("private int empno;", ctClass);
        CtField ctField02 = CtField.make("private String ename;", ctClass);
        //添加到新建的类中
        ctClass.addField(ctField01);
        ctClass.addField(ctField02);
        //创建方法
        CtMethod ctMethod01 = CtMethod.make("public int getEmpno(){return this.empno;}", ctClass);
        CtMethod ctMethod02 = CtMethod.make("public void setEmpno(int empno){this.empno = empno;}", ctClass);
        //将方法添加到新建的类中
        ctClass.addMethod(ctMethod01);
        ctClass.addMethod(ctMethod02);
        //添加构造器 参数：参数类型数组，目标类
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{CtClass.intType ,classPool.get("java/lang/String")},ctClass);
        //设置构造方法体
        ctConstructor.setBody("{this.empno = empno; this.ename = ename;}");
        //添加构造器到类
        ctClass.addConstructor(ctConstructor);
        //写入创建的类写入文件,将冻结该类
        ctClass.writeFile("C:\\Users\\11432_000.000.000\\Desktop\\图片\\javassist");
    }
}
