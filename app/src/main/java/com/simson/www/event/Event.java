package com.simson.www.event;

/**
 * 事件类型
 */

public class Event {
    public enum Type {
        LOGIN
    }

    public Type type;
    public Object object;


    public Event(Type type, Object object) {
        this.type = type;
        this.object = object;
    }
}
