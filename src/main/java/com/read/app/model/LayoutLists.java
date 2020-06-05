package com.read.app.model;

public class LayoutLists
{
    private static Layout layout;
    private static Filters filters;

    public static Layout getLayout()
    {
        return layout;
    }

    public static void setLayout(Layout layout)
    {
        LayoutLists.layout = layout;
    }

    public static Filters getFilters()
    {
        return filters;
    }

    public static void setFilters(Filters filters)
    {
        LayoutLists.filters = filters;
    }
}
