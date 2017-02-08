package com.cantekinandroidlib.customJson.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cantekin on 6.2.2017.
 */

public abstract class _baseProperties {
    private String formName;
    private String formTitle;
    private String entity;
    private EnumFragmentType formType;
    private String actionButtonLink;
    private boolean actionButtonIsVisible;
    private EnumFragmentType actionButtonFromType;
    private String parentField;
    private String parentFieldId;

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
}
