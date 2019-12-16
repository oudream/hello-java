#!/usr/bin/env bash

#!/usr/bin/env bash

export JAVA_HOME=/usr/lib/jvm/java-8-oracle
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export HADOOP_HOME=/usr/local/hadoop
export PATH=$PATH:$HADOOP_HOME/bin



### java
https://www.jianshu.com/p/87637b150026

    java [options] mainclass [args...]
    java [options] -jar jarfile [args...]

-cp classpath
# 指定JAVA搜寻类的类路径，会覆盖CLASSPATH环境变量的值。类路径为指定符号分隔（linux为冒号:，windows为分号;）的
# 目录、jar包路径或zip包路径，用来指定搜索class文件的路径。其详细说明可查看ClassPath详解。

-Dproperty=value
# 指定一个系统属性值。属性和属性值都为字符形式，其中属性名不能含有空白字符，属性值如果需要空白字符，需要使用双引号"包裹。
# 一个正确的示例如下：
-Dfoo="foo bar"
# 该值可以在JAVA程序中使用如下代码获取：
System.getProperty("foo")

-server -client
# 指定JVM的模式，client模式用于桌面应用，server模式用于服务端应用。JVM对两种模式有相应优化，
# client模式加载速度较快，可以快速启动；
# server模式加载速度较慢但运行起来较快。


# 运行jar文件中的class
java -cp test.jar com.ee2ee.test.PackageTest
java xx # 执行class，不需要class后缀，加了报错
java -cp
java -jar # 执行jar文件，需要为可执行jar



