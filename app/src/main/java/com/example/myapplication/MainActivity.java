package com.example.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {

    Socket s=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onConnect(View view) throws Exception {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            s = new Socket("192.168.1.38", 5845);
            System.out.println("Connection Established");
        } catch (UnknownHostException uh) {
            System.out.println(uh);
        } catch (IOException io) {
            System.out.println(io);
        }
    }

    public void onBtn(View view) throws Exception{
        EditText e1=(EditText) findViewById(R.id.editText);
        TextView t1=(TextView) findViewById(R.id.textView);
        String message=e1.getText().toString();
        DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
        DataInputStream dataIn=new DataInputStream(s.getInputStream());
        try {
            dataOut.writeUTF(message);
            String msgRcv=dataIn.readUTF();
            t1.setText(msgRcv);
        }
        catch(IOException io) {
            System.out.println(io);
        }



    }





}