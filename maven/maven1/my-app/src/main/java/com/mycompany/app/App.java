package com.mycompany.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String myName()
    {
        return "mycompany-20180530-!";
    }

    public static void show( )
    {
        System.out.println( "Hello World By 20180530-!" );

        System.out.println("A NullPointerException is a drag...");
        Object myObject = null;
        /**
         * Checker prevents this from compiling...
         */
//        System.out.println("myObject: " + myObject.toString());
        /**
         * ... which is simply fantastic. It shows:
         *
         * error: [dereference.of.nullable] dereference of possibly-null
         * reference myObject
         *
         * http://checkerframework.org
         */
        System.out.println("... but thankfully, Checker has our back: http://checkerframework.org");
    }

    public static void main( String[] args )
    {
        show();
    }
}
