package Model;

/**
 * Created by Cantekin on 10.1.2017.
 * * {
 * "entity":"Activity",
 * "fields":["Id","Subject"],
 * "sort":{"field":"Subject","dir":"desc"},
 * "filter":{"field":"Subject","op":"contains","val1":"a"},
 * "pageSize":15,
 * "page":1
 * }
 */
public class ListRequestModel {
  public String entity;
  public String[] fields;
  public int page;
  public int pageSize;
  public filter filter;
  public class sort {
    public String field;
    public String dir;
  }


}
