package customview.yh.com.annotationdemo;


import android.widget.TextView;

@InjectLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.tv4)
    TextView tv4;
    @InjectView(R.id.tv2)
    TextView tv2;
    @InjectView(R.id.tv3)
    TextView tv3;

    @Override
    void initView() {
        tv.setText("tests");
        tv4.setText("this is tv 4  welcome");
        tv2.setText("this is tv 2 welcome");
        tv3.setText("this is tv 3  welcome");
    }
}
