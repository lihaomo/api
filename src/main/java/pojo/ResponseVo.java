package pojo;

/**
 * @author: wudagai
 * @date: 2022/10/8 17:29
 * @description:
 */
public class ResponseVo<T> {
    private boolean flag;
    private String message;
    private T t;

    public ResponseVo() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return t;
    }

    public void setResult(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                ", t=" + t +
                '}';
    }
}
