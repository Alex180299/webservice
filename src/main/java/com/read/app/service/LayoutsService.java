package com.read.app.service;

import com.read.app.model.LayoutLists;

public class LayoutsService
{
    private static ReadLayouts readLayouts = new ReadLayouts();

    public static void start()
    {
        LayoutLists.setLayout(readLayouts.readLayout());
        LayoutLists.setFilters(readLayouts.readFilters());
    }

    public static void updateLayoutXml()
    {
        readLayouts.writeLayout(LayoutLists.getLayout());
    }

    public static void updateFiltersXml()
    {
        readLayouts.writeFilters(LayoutLists.getFiltersLayout());
    }
}
