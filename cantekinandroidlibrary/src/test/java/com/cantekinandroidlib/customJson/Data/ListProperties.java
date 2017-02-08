package com.cantekinandroidlib.customJson.Data;

import java.util.Map;

/**
 * Created by Cantekin on 6.2.2017.
 */

public class ListProperties extends _baseProperties {

    private int listPageSize;
    private String editLink;
    private EnumFragmentType editFormType;



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
}
