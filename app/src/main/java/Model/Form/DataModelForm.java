package Model.Form;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cantekin on 8.1.2017.
 * form ekranlarında servise giden ve gelen datanın barındığı model
 */
public class DataModelForm {
  public Map<String, Object> Data;
  public Model.Status Status;
  public DataModelForm() {
    Data = new HashMap<>();
  }
}
