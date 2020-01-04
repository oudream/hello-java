package JavaReflection.JavaSsist;

import javassist.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 说明：测试javassist的常用API
 *
 * @Auther: 11432_000
 * @Date: 2019/1/21 13:57
 * @Description:
 */
public class Test02 {
    public static void main(String[] args) throws Exception{
        test07();
    }

    public static void test01()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("JavaReflection.JavaSsist.Emp");
        //获取类的字节码
        byte[] bytes = cc.toBytecode();
        //获取类名
        String name = cc.getName();
        //获取简要类名
        cc.getSimpleName();
        //获取父类
        CtClass superclass = cc.getSuperclass();
        //获取接口
        CtClass[] interfaces = cc.getInterfaces();
        //获取该类的Class对象，该方法将冻结该类
        Class<?> aClass = cc.toClass();
        //解除冻结
        cc.defrost();
    }

    public static void test02()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("JavaReflection.JavaSsist.Emp");
        //----------创建新方法---------
        //1、创建新方法,参数：方法源代码，目标类
        CtMethod method1 = CtMethod.make("public void 方法名(){ System.out.println(\"方法体\"); }", cc);
        //2、创建新方法,参数：方法返回类型，方法名，方法参数类型列表，目标类
        //参数名称为：$1,$2......
        CtMethod method = new CtMethod(CtClass.intType ,"add",
                new CtClass[]{CtClass.intType ,CtClass.intType} ,cc);
        //添加方法体
        method.setBody("{System.out.println(\"hello\");return $1 + $2;}");
        //将方法添加到类中
        cc.addMethod(method);
    }

    public static void test03()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("JavaReflection.JavaSsist.Emp");
        //-----------修改已有方法-----------
        //获取方法:参数：方法名，参数类型列表
        CtMethod say = cc.getDeclaredMethod("say", new CtClass[]{pool.get("java/lang/String")});
        //在方法体前/后添加代码
        say.insertBefore("System.out.println(\"我是前面\");");
        say.insertAfter("System.out.println(\"我是后面\");");
        //在某一行前添加代码,行标为IDE内的行标。
        say.insertAt(31 ,"System.out.println(\"我是中间\");");
        Class<?> aClass = cc.toClass();
        Object o = aClass.newInstance();
        Method say1 = aClass.getMethod("say", String.class);
        say1.invoke(o,"wtmsb");
    }

    public static void test04()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("JavaReflection.JavaSsist.Emp");
        //------------添加新属性----------
        //创建新属性：参数 属性定义原码，目标类。
        CtField private_int_sno = CtField.make("private int Sno;", cc);
        //创建新属性：参数 属性类型，属性名，目标类。
        CtField ctField = new CtField(CtClass.intType ,"Sno" ,cc);
        //添加修饰符
        ctField.setModifiers(Modifier.PRIVATE);
        //添加到类中:参数 CtField ，默认值
        cc.addField(ctField ,"1000");
        //添加getter/setter方法
        CtMethod setSno = CtNewMethod.setter("setSno", ctField);
        CtMethod getSno = CtNewMethod.getter("getSno", ctField);
        cc.addMethod(setSno);
        cc.addMethod(getSno);
    }

    public static void test05()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("JavaReflection.JavaSsist.Emp");
        //------------添加构造器----------
        //创建新的构造器 参数：参数类型列表 ,目标类
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{CtClass.intType},cc);
        //为构造器添加方法体
        ctConstructor.setBody("");
        //设置修饰符
        ctConstructor.setModifiers(Modifier.PRIVATE);
        //将构造器添加到类中
        cc.addConstructor(ctConstructor);
    }

    public static void test06()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("JavaReflection.JavaSsist.Emp");
        //------------修改构造器----------
        //获取所有构造器
        CtConstructor[] declaredConstructors = cc.getDeclaredConstructors();
        CtConstructor declaredConstructor = declaredConstructors[0];
        //在构造器体前添加代码
        declaredConstructor.insertBefore("");
        //在构造器体后添加代码
        declaredConstructor.insertAfter("");
        //在某行前添加代码，参数：行标，代码
        declaredConstructor.insertAt(1,"");
    }

    public static void test07()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("JavaReflection.JavaSsist.Emp");
        //----------操作注解---------
        //获取注解
        Author annotation =(Author) cc.getAnnotation(Author.class);
        //获取注解的属性
        String name = annotation.name();
        int year = annotation.year();
        System.out.println(name + "-->" + year);
    }
}
