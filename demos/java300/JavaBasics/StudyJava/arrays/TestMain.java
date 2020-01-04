package JavaBasics.StudyJava.arrays;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/11/30 09:14
 * @Description:
 */
public class TestMain {

    public static void main(String[] args){
        //必须指定最外层数组的大小。（多维数组即为数组的嵌套）
        int[][] array01 = new int[2][];
        String str = "mayao";
        char[] chars = new char[6];
        char[] chars1 = new char[6];
        str.getChars(0,str.length(),chars,0);
        //数组的拷贝
        System.arraycopy(chars ,0 , chars1 , 0 , chars.length);
        chars1 = insert(chars1 , '3',2);
        for (char s : chars1){
            System.out.print(s);
        }
    }

    public static char[] remove(char[] chars ,int index){
        System.arraycopy(chars ,index + 1 ,chars , index  ,(chars.length - index - 1));
        return chars;
    }

    public static char[] extendRange(char[] chars){
        char[] newChars = new char[chars.length * 2];
        System.arraycopy(chars , 0 ,newChars ,0 ,chars.length);
        return newChars;
    }

    public static char[] insert(char[] chars ,char ele ,int index){
        if (chars[chars.length -1] != 0){
            chars = extendRange(chars);
        }
        System.arraycopy(chars ,index ,chars ,index + 1, chars.length - index - 1);
        chars[index] = ele;
        return chars;
    }
}
