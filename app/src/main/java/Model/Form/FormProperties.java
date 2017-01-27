package Model.Form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cantekin on 24.1.2017.
 */
public class FormProperties {
  private int Id;
  private String formName;
  private String entity;
  public ArrayList<Map<String,Object>> widgets=new ArrayList<Map<String,Object>>();
  public List<String> Buttons=new ArrayList<String>();
  public FormProperties() {

  }
  public FormProperties(String formName, int Id) {
    this.setId(Id);
    this.setFormName(formName);
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }


  public void setEntity(String entity) {
    this.entity = entity;
  }

  public String getEntity() {
    return entity;
  }
}
