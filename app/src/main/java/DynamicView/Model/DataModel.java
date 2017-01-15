package DynamicView.Model;

import java.util.ArrayList;

/**
 * Created by Cantekin on 8.1.2017.
 */
public class DataModel {
  public ArrayList<Response> Data;
  public int Total;
  public Status Status;
}
class Status{
  public int ErrCode;
  public String Message;
}
