package JavaBasics.StudyJava.Exception;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/12/13 16:05
 * @Description:
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
class MyException extends Exception{

}
