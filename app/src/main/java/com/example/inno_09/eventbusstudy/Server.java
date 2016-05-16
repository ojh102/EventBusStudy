package com.example.inno_09.eventbusstudy;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INNO-09 on 2016-05-16.
 */
public class Server {

    List<String> serverItems = new ArrayList<>();

    private Server() {}

    private static  class Singleton {
        private static final Server instance = new Server();
    }

    public static Server getInstance() {
        return Singleton.instance;
    }

    public void saveItem(String item) {
        serverItems.add(item);
        requestItem();
    }

    public void requestItem() {
        Update update = new Update();
        update.updateItems = serverItems;
        EventBus.getDefault().post(update);
    }
}
