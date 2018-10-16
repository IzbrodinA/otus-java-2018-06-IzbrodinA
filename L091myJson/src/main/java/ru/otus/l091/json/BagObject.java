package ru.otus.l091.json;

import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BagObject {
    private double doubles[];
    private String strings[];
    private List<SimpleObject> list;
    private Queue<String> queue;
    private Set<String> set;

    public void setDoubles(final double[] doubles) {
        this.doubles = doubles;
    }

    public void setStrings(final String[] strings) {
        this.strings = strings;
    }

    public void setList(final List<SimpleObject> list) {
        this.list = list;
    }

    public void setQueue(final Queue<String> queue) {
        this.queue = queue;
    }

    public void setSet(final Set<String> set) {
        this.set = set;
    }


}
