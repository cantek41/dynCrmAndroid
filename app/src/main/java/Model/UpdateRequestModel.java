package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cantekin on 10.1.2017.
 * *"entity":"Activity",
 * "data":{"Id":103,"Description":null}
 * }
 */
public class UpdateRequestModel {
  public String entity;
  public Map<String, Object> data;

  public UpdateRequestModel() {
    data = new HashMap<String, Object>();
  }
}
