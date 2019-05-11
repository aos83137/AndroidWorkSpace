package com.example.yongseok.workwithserver;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    class Task extends AsyncTask<URL, Integer, String> {
        private WeakReference<MainActivity> activityWeakReference;
        public Task(MainActivity activity){
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() { // 여기에 프로그레스바를 많이 만듬 (로딩중!)
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {// 배열이다 ...은 ㅇㅇㅋ?ㅇㅋㅇㅋ //doInBackground 중간중간에 실행되는 함수임
            super.onProgressUpdate(values);
            if(values.length >0){
                Log.i("http", String.valueOf(values[0]));
            }
        }

        @Override
        protected String doInBackground(URL... urls) { // 반환값이 onPostExecute로 간다.
            int i=0;
            String result = new String();
            if(urls == null || urls.length < 1) return null;
            try{
                publishProgress(i++); // 이게 onPregressupdate(progress)로 간다!
                HttpURLConnection connection = (HttpURLConnection)urls[0].openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setDefaultUseCaches(false);
                publishProgress(i++);
                InputStream is = connection.getInputStream();
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String line;
                while ((line = reader.readLine()) != null){
                    builder.append(line+"\n");
                    publishProgress(i++);
                }
                result = builder.toString();
                Log.i("http", "result=" + result);
                publishProgress(i++);
            }catch (IOException e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
            //변수가 있어야 하는 이유 doin이 끝날 때 메인에 없을 경우가 있음
            // 메인화면을 일반화면으로 변수 설정해두면 가비지 컬랙터가 이걸 안지움 그래서 메모리에 남아 있다
            //이걸 회피하는 방법이 weakReference를 사용하게 된것이다.

            MainActivity activity = activityWeakReference.get(); //WeakReference 죽은지 살은지 알수있는 친구
            if(activity == null || activity.isFinishing())return; //activity가 죽은지 죽는 중인지를 확인 해야됨
            textView.setText(s);
            //이유 : 서버에서 가져오고있을때 사용자가 빽키를 누르면 메인 엑티비티는 죽어버림 그런데 task는 실행중이고 끝난 다음 activity가없으니 오류남 체크해야됨
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //excute하나 호출하면 차례대로 불러준다 다른건 내가 부르는거 아니다
        textView = findViewById(R.id.textview);

        try{
            URL url = new URL("http://172.26.3.94:3000/");
            new Task(this).execute(url); // 여기의 인수가 doitbackgroud로 간다.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
