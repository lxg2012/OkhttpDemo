package net.listener;

/**
 * Created by Administrator on 2017-3-8.
 */
public class DisponseDataHandle {

    public DisponseDataListener mDisponseDataListener = null;
    public Class<?> mClass = null;

    public DisponseDataHandle(DisponseDataListener disponseDataListener) {
        mDisponseDataListener = disponseDataListener;
    }

    public DisponseDataHandle(DisponseDataListener disponseDataListener, Class<?> aClass) {
        mDisponseDataListener = disponseDataListener;
        mClass = aClass;
    }
}
