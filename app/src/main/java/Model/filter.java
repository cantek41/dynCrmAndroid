package Model;

public class Filter {
  private String op;
  private String field;
  private Object val1;

  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public Object getVal1() {
    return val1;
  }

  public void setVal1(Object val1) {
    this.val1 = val1;
  }
}
