package com.read.app.model;

import java.util.List;

public class LayoutLists
{
    private static final Layout layout = new Layout();
    private static final Filters filters = new Filters();

    public static synchronized void setLayout(Layout layout)
    {
        LayoutLists.layout.getLayoutIn().getFields().getField().clear();
        LayoutLists.layout.getLayoutIn().getFields().getField().addAll(layout.getLayoutIn().getFields().getField());

        LayoutLists.layout.getLayoutOut().getFields().getField().clear();
        LayoutLists.layout.getLayoutOut().getFields().getField().addAll(layout.getLayoutOut().getFields().getField());
        sortLayout();
    }

    public static synchronized void setFilters(Filters filters)
    {
        LayoutLists.filters.getFilter().clear();
        LayoutLists.filters.getFilter().addAll(filters.getFilter());
        sortFilters();
    }

    public static synchronized Layout getLayout()
    {
        sortLayout();
        return layout;
    }

    public static synchronized Filters getFiltersLayout()
    {
        sortFilters();
        return filters;
    }

    public static void sortLayout()
    {
        getLayoutIn().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
        getLayoutOut().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
    }

    public static void sortFilters()
    {
        getFilters().sort((o1, o2) -> (o1.getId() < o2.getId()) ? -1 : (o1.getId() == o2.getId()) ? 0 : 1);
    }

    public static List<Field> getLayoutIn()
    {
        sortLayout();
        return layout.getLayoutIn().getFields().getField();
    }

    public static List<Field> getLayoutOut()
    {
        sortLayout();
        return layout.getLayoutOut().getFields().getField();
    }

    public static List<Filter> getFilters()
    {
        sortFilters();
        return filters.getFilter();
    }

    public static Long getNextFilterId()
    {
        return getFilters().get(getFilters().size() - 1).getId() + 1L;
    }

    public static Long getNextLayoutInId()
    {
        return getLayoutIn().get(getLayoutIn().size() - 1).getId() + 1L;
    }

    public static Long getNextLayoutOutId()
    {
        return getLayoutOut().get(getLayoutOut().size() - 1).getId() + 1L;
    }

}
