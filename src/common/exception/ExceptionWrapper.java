package common.exception;

public class ExceptionWrapper {
    private ExceptionWrapper(){
    }
    public static String  getMessage(Exception exception){
        if(exception instanceof TaskNotFound){
            return "101: Task not found";
        }
        else {
            return "Please call our support";
        }
    }
}
