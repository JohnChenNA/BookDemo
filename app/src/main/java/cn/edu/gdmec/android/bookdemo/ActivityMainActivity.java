package cn.edu.gdmec.android.bookdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import cn.edu.gdmec.android.bookdemo.View.IBookView;
import cn.edu.gdmec.android.bookdemo.present.BookPresenter;

public class ActivityMainActivity extends Activity implements IBookView,View.OnClickListener {

    private TextView tvBookName;
    private TextView tvBookAuthor;
    private ProgressDialog progressDialog;
    private BookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_search_name).setOnClickListener(this);
        findViewById(R.id.btn_search_author).setOnClickListener(this);
        tvBookName = (TextView) findViewById(R.id.tv_book_name);
        tvBookAuthor = (TextView) findViewById(R.id.tv_book_author);
        presenter = new BookPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_name:
                presenter.loadBook();
                break;
            case R.id.btn_search_author:
                presenter.loadBook();
                break;
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog !=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
            progressDialog = progressDialog.show(ActivityMainActivity.this,"","正在获取");
    }

    @Override
    public void hideProgress() {
        if (progressDialog !=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showBookData(final BookBean bookBean) {
        runOnUiThread((new TimerTask() {
            @Override
            public void run() {


                    tvBookName.setText("名称："+ bookBean.getBooks().get(0).getSubtitle());
                    tvBookAuthor.setText("作者："+bookBean.getBooks().get(0).getAuthor());

            }
        }));
    }

    @Override
    public void showLoadFailMsg( final Exception e) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tvBookName.setText("加载数据失败"+e.toString());
            }
        });
    }
}
