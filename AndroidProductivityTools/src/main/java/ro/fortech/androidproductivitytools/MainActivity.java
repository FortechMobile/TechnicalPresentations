package ro.fortech.androidproductivitytools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView mDemoText;
    private ImageView mDemoImage;
    private Button mGoToListButton;

//    @InjectView(R.id.demoActivityTextView) TextView mDemoText;
//    @InjectView(R.id.imageView) ImageView mDemoImage;
//    @InjectView(R.id.goToList) Button mGoToListButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // inject the activity layout (fetch all the annotated views)
        ButterKnife.inject(this);

//        mDemoText = (TextView) findViewById(R.id.demoActivityTextView);
//        mDemoImage = (ImageView) findViewById(R.id.imageView);
//        mGoToListButton = (Button) findViewById(R.id.goToList);
//
//        mGoToListButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // start a new activity for the list demo
//                startActivity(new Intent(MainActivity.this, ListActivity.class));
//
//            }
//        });

//          Load an image from the given url
        Picasso.with(this).load("http://www.androidcentral.com/sites/androidcentral.com/files/postimages/684/lloyd.jpg").into(mDemoImage);

    }

    // add on click listener using injection
    @OnClick(R.id.goToList) void onGoToListButtonClick(){

        // start a new activity for the list demo
        startActivity(new Intent(MainActivity.this, ListActivity.class));

    }



}
