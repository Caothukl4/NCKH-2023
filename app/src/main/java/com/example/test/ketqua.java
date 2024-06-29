package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ketqua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        TextView goiy = findViewById(R.id.goiy);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView txnhacnho = findViewById(R.id.txnhacnho);
        String tuoitre = getIntent().getStringExtra("tuoitre");
        String kieunau = getIntent().getStringExtra("kieunau");
        String selectedTC= getIntent().getStringExtra("benhTC");
        String selectedTB= getIntent().getStringExtra("benhTB");
//        lấy kết quả intent
        if(tuoitre.equals("Dưới 1 tuổi")){
            if (selectedTC.equals("Có") || selectedTB.equals("Có") ){
                txnhacnho.setText("Với tình trạng sức khỏe của trẻ sau đây là thực đơn phù hợp.");
            }
            else{
                txnhacnho.setText("Sức khỏe của trẻ bình thường, thực đơn dinh dưỡng như sau: ");
            }
            goiy.setText("GỢI Ý THỰC ĐƠN DÀNH CHO TRẺ DƯỚI 1 TUỔI ");
            if(kieunau.equals("Cháo")){
                String resultchao1 = getIntent().getStringExtra("resultchao1");
                String resultchao2 = getIntent().getStringExtra("resultchao2");
                String resultchao3 = getIntent().getStringExtra("resultchao3");
                String resultchao4 = getIntent().getStringExtra("resultchao4");
                String resultchao5 = getIntent().getStringExtra("resultchao5");
                String resultchao6 = getIntent().getStringExtra("resultchao6");
                String resultchao7 = getIntent().getStringExtra("resultchao7");
                String resultchao8 = getIntent().getStringExtra("resultchao8");
                String resultchao9 = getIntent().getStringExtra("resultchao9");

                tv1.setText(" - "+resultchao1 + ".\n"+" - "+resultchao2 + ".\n" + " - "+resultchao3 + ".\n" );
                tv2.setText(" - "+resultchao4 + ".\n"+" - "+resultchao5 + ".\n" + " - "+resultchao6 + ".\n" );
                tv3.setText(" - "+resultchao7 + ".\n"+" - "+resultchao8 + ".\n" + " - "+resultchao9 + ".\n" );
            }
            else{
                String resultbot1 = getIntent().getStringExtra("resultbot1");
                String resultbot2 = getIntent().getStringExtra("resultbot2");
                String resultbot3 = getIntent().getStringExtra("resultbot3");
                String resultbot4 = getIntent().getStringExtra("resultbot4");
                String resultbot5 = getIntent().getStringExtra("resultbot5");
                String resultbot6 = getIntent().getStringExtra("resultbot6");

                tv1.setText(" - "+resultbot1 + ".\n"+" - "+resultbot2 + ".\n"  );
                tv2.setText(" - "+resultbot3 + ".\n"+" - "+resultbot4 + ".\n"  );
                tv3.setText(" - "+resultbot5 + ".\n"+" - "+resultbot6 + ".\n"  );
            }
        } else if (tuoitre.equals("Từ 2-3 tuổi")) {
            goiy.setText("GỢI Ý THỰC ĐƠN DÀNH CHO TRẺ TỪ 2-3 TUỔI ");
            if (selectedTC.equals("Có") || selectedTB.equals("Có") ){
                txnhacnho.setText("Với tình trạng sức khỏe của trẻ sau đây là thực đơn phù hợp.");
            }
            else{
                txnhacnho.setText("Sức khỏe của trẻ bình thường, thực đơn dinh dưỡng như sau: ");
            }
            if(kieunau.equals("Cơm")){
                String resultcom1 = getIntent().getStringExtra("resultcom1");
                String resultcom2 = getIntent().getStringExtra("resultcom2");
                String resultcom3 = getIntent().getStringExtra("resultcom3");
                String resultcom4 = getIntent().getStringExtra("resultcom4");
                String resultcom5 = getIntent().getStringExtra("resultcom5");
                String resultcom6 = getIntent().getStringExtra("resultcom6");
                String resultcom7 = getIntent().getStringExtra("resultcom7");
                String resultcom8 = getIntent().getStringExtra("resultcom8");
                String resultcom9 = getIntent().getStringExtra("resultcom9");

                tv1.setText(" - "+resultcom1 + ".\n"+" - "+resultcom2 + ".\n" + " - "+resultcom3 + ".\n" );
                tv2.setText(" - "+resultcom4 + ".\n"+" - "+resultcom5 + ".\n" + " - "+resultcom6 + ".\n" );
                tv3.setText(" - "+resultcom7 + ".\n"+" - "+resultcom8 + ".\n" + " - "+resultcom9 + ".\n" );
            }
            else{
                String resultchao1 = getIntent().getStringExtra("resultchao1");
                String resultchao2 = getIntent().getStringExtra("resultchao2");
                String resultchao3 = getIntent().getStringExtra("resultchao3");
                String resultchao4 = getIntent().getStringExtra("resultchao4");
                String resultchao5 = getIntent().getStringExtra("resultchao5");
                String resultchao6 = getIntent().getStringExtra("resultchao6");
                String resultchao7 = getIntent().getStringExtra("resultchao7");
                String resultchao8 = getIntent().getStringExtra("resultchao8");
                String resultchao9 = getIntent().getStringExtra("resultchao9");

                tv1.setText(" - "+resultchao1 + ".\n"+" - "+resultchao2 + ".\n" + " - "+resultchao3 + ".\n" );
                tv2.setText(" - "+resultchao4 + ".\n"+" - "+resultchao5 + ".\n" + " - "+resultchao6 + ".\n" );
                tv3.setText(" - "+resultchao7 + ".\n"+" - "+resultchao8 + ".\n" + " - "+resultchao9 + ".\n" );
            }
        }
        else {
            goiy.setText("GỢI Ý THỰC ĐƠN DÀNH CHO TRẺ TRÊN 3 TUỔI ");
            if (selectedTC.equals("Có") || selectedTB.equals("Có") ){
                txnhacnho.setText("Với tình trạng sức khỏe của trẻ sau đây là thực đơn phù hợp.");
            }
            else{
                txnhacnho.setText("Sức khỏe của trẻ bình thường, thực đơn dinh dưỡng như sau: ");
            }
            if(kieunau.equals("Cơm")){
                String resultcom1 = getIntent().getStringExtra("resultcom1");
                String resultcom2 = getIntent().getStringExtra("resultcom2");
                String resultcom3 = getIntent().getStringExtra("resultcom3");
                String resultcom4 = getIntent().getStringExtra("resultcom4");
                String resultcom5 = getIntent().getStringExtra("resultcom5");
                String resultcom6 = getIntent().getStringExtra("resultcom6");
                String resultcom7 = getIntent().getStringExtra("resultcom7");
                String resultcom8 = getIntent().getStringExtra("resultcom8");
                String resultcom9 = getIntent().getStringExtra("resultcom9");

                tv1.setText(" - "+resultcom1 + ".\n"+" - "+resultcom2 + ".\n" + " - "+resultcom3 + ".\n" );
                tv2.setText(" - "+resultcom4 + ".\n"+" - "+resultcom5 + ".\n" + " - "+resultcom6 + ".\n" );
                tv3.setText(" - "+resultcom7 + ".\n"+" - "+resultcom8 + ".\n" + " - "+resultcom9 + ".\n" );
            }
            else{
                String resultchao1 = getIntent().getStringExtra("resultchao1");
                String resultchao2 = getIntent().getStringExtra("resultchao2");
                String resultchao3 = getIntent().getStringExtra("resultchao3");
                String resultchao4 = getIntent().getStringExtra("resultchao4");
                String resultchao5 = getIntent().getStringExtra("resultchao5");
                String resultchao6 = getIntent().getStringExtra("resultchao6");
                String resultchao7 = getIntent().getStringExtra("resultchao7");
                String resultchao8 = getIntent().getStringExtra("resultchao8");
                String resultchao9 = getIntent().getStringExtra("resultchao9");

                tv1.setText(" - "+resultchao1 + ".\n"+" - "+resultchao2 + ".\n" + " - "+resultchao3 + ".\n" );
                tv2.setText(" - "+resultchao4 + ".\n"+" - "+resultchao5 + ".\n" + " - "+resultchao6 + ".\n" );
                tv3.setText(" - "+resultchao7 + ".\n"+" - "+resultchao8 + ".\n" + " - "+resultchao9 + ".\n" );
            }
        }
    }
}