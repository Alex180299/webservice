package com.read.app.model;

import java.util.List;

public class LayoutLists
{
    private static Layout layout;
    private static Filters filters;

    public static void setLayout(Layout layout)
    {
        LayoutLists.layout = layout;
        sortLayout();
    }

    public static void setFilters(Filters filters)
    {
        LayoutLists.filters = filters;
        sortFilters();
    }

    public static Layout getLayout()
    {
        sortLayout();
        return layout;
    }

    public static Filters getFiltersLayout()
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
        return layout.getLayoutIn().getFields().getField();
    }

    public static List<Field> getLayoutOut()
    {
        return layout.getLayoutOut().getFields().getField();
    }

    public static List<Filter> getFilters()
    {
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
