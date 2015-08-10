
public class Result <T>{
private final int resultCode;
private final String resultText;
private T content;

    public Result(int resultCode,String resultText, T content) {
        this.content = content;
        this.resultCode=resultCode;
        this.resultText=resultText;
    
    }

    public Result(int resultCode, String resultText) {
        this.resultCode = resultCode;
        this.resultText = resultText;
    }

    

}
