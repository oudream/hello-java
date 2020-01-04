package JavaOther;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

/**
 * 说明：java脚本引擎只一------js脚本引擎常用
 *
 * @Auther: 11432_000
 * @Date: 2019/1/19 15:11
 * @Description:
 */
public class Rhino {

    public static void main(String[] args) throws Exception{
        //获取脚本引擎对象
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine javascript = scriptEngineManager.getEngineByName("javascript");
        //将变量msg写入脚本，存储到上下文中
        javascript.put("msg" ,"19970510");
        String str = "var user = {name:'mayao',age:21,position:['学生','谁知道']};" +
                "print(user.name);";
        //执行脚本
        javascript.eval(str);
        javascript.eval("msg = '6666'");
        //获取存储在上下文中的参数
        System.out.println(javascript.get("msg"));

        //定义函数
        javascript.eval("function add(a,b){ var sum = a + b; return sum;}");
        //执行函数
        Invocable invocable = (Invocable) javascript;
        Object o = invocable.invokeFunction("add", 12, 23);
        System.out.println(o);

        //导入其他java包，使用其他包的java类
        String jsCode = " var list = java.util.Arrays.asList([1,2,34,54])";
        javascript.eval(jsCode);
        List<Integer> o1 = (List) javascript.get("list");
        for (int s : o1){
            System.out.println(s);
        }


        //加载外部js文件
        URL url = Rhino.class.getClassLoader().getResource("a.js");
        if (url == null){
            System.out.println(456456456);
        }
        FileReader fr = new FileReader(url.getPath());
        javascript.eval(fr);
        fr.close();

    }
}
