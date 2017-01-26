package Model;

import java.util.HashMap;
import java.util.Map;

public class Response {
  private Map<String, Object> data;

  public Response() {
    data = new HashMap<String, Object>();
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void addData(String key) {
    this.data.put(key, null);
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }
  //  private int Id;
//  private String Subject;
//  private String OpenOrClose;
//
//  public int getId() {
//    return Id;
//  }
//
//  public void setId(int id) {
//    Id = id;
//  }
//
//  public String getOpenOrClose() {
//    return OpenOrClose;
//  }
//
//  public void setOpenOrClose(String openOrClose) {
//    OpenOrClose = openOrClose;
//  }
//
//  public String getSubject() {
//    return Subject;
//  }
//
//  public void setSubject(String subject) {
//    Subject = subject;
//  }
}
