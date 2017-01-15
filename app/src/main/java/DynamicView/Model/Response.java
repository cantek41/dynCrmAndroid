package DynamicView.Model;

public class Response {
  private int Id;
  private String Subject;
  private String OpenOrClose;

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getOpenOrClose() {
    return OpenOrClose;
  }

  public void setOpenOrClose(String openOrClose) {
    OpenOrClose = openOrClose;
  }

  public String getSubject() {
    return Subject;
  }

  public void setSubject(String subject) {
    Subject = subject;
  }
}
