package customview.yh.com.annotationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRootView();
        bindWeight();
        initView();
    }

    private void setRootView() {
        InjectLayout annotation = getClass().getAnnotation(InjectLayout.class);
        int id = annotation.value();
        try {
            Method setContentView = getClass().getMethod("setContentView", int.class);
            setContentView.invoke(this, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindWeight() {
        Method findViewById = null;
        try {
            findViewById = getClass().getMethod("findViewById", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            InjectView annotationInjectView = field.getAnnotation(InjectView.class);
            int id1 = annotationInjectView.value();
            try {
                field.set(this, findViewById.invoke(this, id1));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    abstract void initView();
}
