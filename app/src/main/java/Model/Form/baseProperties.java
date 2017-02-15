package Model.Form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import veribis.veribiscrmdyn.Fragment.EnumFragmentType;

/**
 * Created by Cantekin on 6.2.2017.
 */

public class baseProperties {
    private String formName;
    private String formTitle;
    private String entity;
    private EnumFragmentType formType;
    private String actionButtonLink;
    private boolean actionButtonIsVisible;
    private EnumFragmentType actionButtonFromType;
    private String parentField;
    private String parentFieldId;
    /*
     list prop
     * @return editLink editFormType listPageSize
     */
    private int listPageSize;
    private String searchField;
    private String sortField;


    private String editLink;
    private EnumFragmentType editFormType;

    /*
    list prop
     */
    private String recordId;


    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(Object recordId) {
        if (recordId instanceof Double)
            this.recordId = (String.valueOf(((Double) recordId).intValue()));
        else this.recordId = (String.valueOf(recordId));

    }

    public int getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(int listPageSize) {
        this.listPageSize = listPageSize;
    }

    public String getEditLink() {
        return editLink;
    }

    public void setEditLink(String editLink) {
        this.editLink = editLink;
    }

    public EnumFragmentType getEditFormType() {
        return editFormType;
    }

    public void setEditFormType(EnumFragmentType editFormType) {
        this.editFormType = editFormType;
    }

    public String getParentFieldId() {
        return parentFieldId;
    }

    public void setParentFieldId(String parentFieldId) {
        this.parentFieldId = parentFieldId;
    }

    private List<String> Buttons;

    private ArrayList<Map<String, Object>> widgets;

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public EnumFragmentType getFormType() {
        return formType;
    }

    public void setFormType(EnumFragmentType formType) {
        this.formType = formType;
    }

    public EnumFragmentType getActionButtonFromType() {
        return actionButtonFromType;
    }

    public void setActionButtonFromType(EnumFragmentType actionButtonFromType) {
        this.actionButtonFromType = actionButtonFromType;
    }

    public String getActionButtonLink() {
        return actionButtonLink;
    }

    public void setActionButtonLink(String actionButtonLink) {
        this.actionButtonLink = actionButtonLink;
    }

    public boolean isActionButtonIsVisible() {
        return actionButtonIsVisible;
    }

    public void setActionButtonIsVisible(boolean actionButtonIsVisible) {
        this.actionButtonIsVisible = actionButtonIsVisible;
    }

    public List<String> getButtons() {
        return Buttons;
    }

    public void setButtons(List<String> buttons) {
        Buttons = buttons;
    }

    public String getParentField() {
        return parentField;
    }

    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    public ArrayList<Map<String, Object>> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<Map<String, Object>> widgets) {
        this.widgets = widgets;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortField() {
        return sortField;
    }
}
