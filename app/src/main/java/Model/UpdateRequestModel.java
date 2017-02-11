package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cantekin on 10.1.2017.
 * *"entity":"Activity",
 * "Data":{"Id":103,"Description":null}
 * }
 */
public class UpdateRequestModel {
  public String entity;
  public Map<String, Object> Data;

  public UpdateRequestModel() {
    Data = new HashMap<>();
  }
}
