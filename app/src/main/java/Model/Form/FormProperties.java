package Model.Form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import veribis.veribiscrmdyn.Fragment.EnumFragmentType;

/**
 * Created by Cantekin on 24.1.2017.
 */
public class FormProperties {
  private int recordId;
  private String parentField;
  private int parentId;
  private List<String> fields;
  private ArrayList<Map<String, Object>> widgets;
  private List<String> Buttons;
  private int listPageSize;
  private String formName;
  private String formTitle;
  private String entity;
  private EnumFragmentType fragmentType;
  private boolean visibleAddButton;


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

  public EnumFragmentType getFragmentType() {
    return fragmentType;
  }

  public void setFragmentType(EnumFragmentType fragmentType) {
    this.fragmentType = fragmentType;
  }

  public String getFormTitle() {
    return formTitle;
  }

  public void setFormTitle(String formTitle) {
    this.formTitle = formTitle;
  }

  public boolean isVisibleAddButton() {
    return visibleAddButton;
  }

  public void setVisibleAddButton(boolean visibleAddButton) {
    this.visibleAddButton = visibleAddButton;
  }

  public FormProperties() {
  }

  public FormProperties(String formName, int Id) {
    this.setRecordId(Id);
    this.setFormName(formName);
  }


  public int getRecordId() {
    return recordId;
  }

  public void setRecordId(int id) {
    recordId = id;
  }

  public String getParentField() {
    return parentField;
  }

  public void setParentField(String parentField) {
    this.parentField = parentField;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public List<String> getFields() {
    return fields;
  }

  public void setFields(List<String> fields) {
    this.fields = fields;
  }

  public int getListPageSize() {
    return listPageSize;
  }

  public void setListPageSize(int listPageSize) {
    this.listPageSize = listPageSize;
  }

  public List<String> getButtons() {
    return Buttons;
  }

  public void setButtons(List<String> buttons) {
    Buttons = buttons;
  }

  public ArrayList<Map<String, Object>> getWidgets() {
    return widgets;
  }

  public void setWidgets(ArrayList<Map<String, Object>> widgets) {
    this.widgets = widgets;
  }
}
