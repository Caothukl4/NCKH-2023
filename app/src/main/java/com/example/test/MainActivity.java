
package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    RadioButton rdb1, rdb2, rdb3, rdbcom, rdbchao, rdbbot, TCco, TCkhong, TBco, TBkhong;
    RadioGroup TC, TB, KN;
    private Button btntd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ id
        rdb1 = findViewById(R.id.rdb1);
        rdb2 = findViewById(R.id.rdb2);
        rdb3 = findViewById(R.id.rdb3);
        rdbcom = findViewById(R.id.rdbcom);
        rdbchao = findViewById(R.id.rdbchao);
        rdbbot = findViewById(R.id.rdbbot);
        TCco = findViewById(R.id.TCco);
        TCkhong = findViewById(R.id.TCkhong);
        TBco = findViewById(R.id.TBco);
        TBkhong = findViewById(R.id.TBkhong);
        TC = findViewById(R.id.TC);
        TB = findViewById(R.id.TB);
        KN = findViewById(R.id.KN);
        btntd = findViewById(R.id.btntd);

        btntd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });

        // Thiết lập sự kiện cho các RadioButton
        rdb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRadioButtons();
                String tuoi = "Dưới 1 tuổi";
                setdatatuoi(tuoi);
                rdbcom.setEnabled(false);
                rdbcom.setAlpha(0.5f);
                rdbchao.setEnabled(true);
                rdbchao.setAlpha(1f);
                rdbbot.setEnabled(true);
                rdbbot.setAlpha(1f);
            }
        });

        rdb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRadioButtons();
                String tuoi = "Từ 2-3 tuổi";
                setdatatuoi(tuoi);
                rdbbot.setEnabled(false);
                rdbbot.setAlpha(0.5f);
                rdbcom.setEnabled(true);
                rdbcom.setAlpha(1f);
                rdbchao.setEnabled(true);
                rdbchao.setAlpha(1f);
            }
        });

        rdb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRadioButtons();
                String tuoi = "Trên 3 tuổi";
                setdatatuoi(tuoi);
                rdbbot.setEnabled(false);
                rdbbot.setAlpha(0.5f);
                rdbcom.setEnabled(true);
                rdbcom.setAlpha(1f);
                rdbchao.setEnabled(true);
                rdbchao.setAlpha(1f);
            }
        });

        rdbcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                clearAllRadioButtons();
                String kieunau = "Cơm";
                setdatakieunau(kieunau);

                    TCco.setEnabled(true);
                    TCkhong.setEnabled(true);
                    TBco.setEnabled(true);
                    TBkhong.setEnabled(true);

            }
        });

        rdbchao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                clearAllRadioButtons();
                String kieunau = "Cháo";
                setdatakieunau(kieunau);
                TCco.setEnabled(true);
                TCkhong.setEnabled(true);
                TBco.setEnabled(true);
                TBkhong.setEnabled(true);
            }
        });

        rdbbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                clearAllRadioButtons();
                String kieunau = "Bột";
                setdatakieunau(kieunau);
                    TCco.setEnabled(false);
                    TCkhong.setEnabled(false);
                    TBco.setEnabled(false);
                    TBkhong.setEnabled(false);
            }
        });

        // Thiết lập sự kiện cho các RadioButton trong RadioGroup
        TCco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String benhTC = "Có";
                setdataTC(benhTC);
            }
        });

        TCkhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String benhTC = "Không";
                setdataTC(benhTC);
            }
        });

        TBco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String benhTB = "Có";
                setdataTB(benhTB);
            }
        });

        TBkhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String benhTB = "Không";
                setdataTB(benhTB);
            }
        });

    }

    public void getdata() {

        try {
            Connection connection = connectSQL();
            if (connection != null) {
                String sqlcom1 = ""; // Khởi tạo chuỗi câu lệnh SQL
                String sqlcom2 = ""; // Khai báo chuỗi câu lệnh SQL thứ 1
                String sqlcom3 = ""; // Khai báo chuỗi câu lệnh SQL thứ 2
                String sqlcom4 = ""; // Khởi tạo chuỗi câu lệnh SQL
                String sqlcom5 = ""; // Khai báo chuỗi câu lệnh SQL thứ 1
                String sqlcom6 = ""; // Khai báo chuỗi câu lệnh SQL thứ 2
                String sqlcom7 = ""; // Khởi tạo chuỗi câu lệnh SQL
                String sqlcom8 = ""; // Khai báo chuỗi câu lệnh SQL thứ 1
                String sqlcom9 = ""; // Khai báo chuỗi câu lệnh SQL thứ 2
                String sqlchao1 = ""; // Khởi tạo chuỗi câu lệnh SQL thứ 3
                String sqlchao2 = ""; // Khai báo chuỗi câu lệnh SQL thứ 4
                String sqlchao3 = ""; // Khai báo chuỗi câu lệnh SQL thứ 5
                String sqlchao4 = ""; // Khởi tạo chuỗi câu lệnh SQL thứ 3
                String sqlchao5 = ""; // Khai báo chuỗi câu lệnh SQL thứ 4
                String sqlchao6 = ""; // Khai báo chuỗi câu lệnh SQL thứ 5
                String sqlchao7 = ""; // Khởi tạo chuỗi câu lệnh SQL thứ 3
                String sqlchao8 = ""; // Khai báo chuỗi câu lệnh SQL thứ 4
                String sqlchao9 = ""; // Khai báo chuỗi câu lệnh SQL thứ 5
                String sqlbot1 = ""; // Khởi tạo chuỗi câu lệnh SQL thứ 6
                String sqlbot2 = ""; // Khai báo chuỗi câu lệnh SQL thứ 7
                String sqlbot3 = ""; // Khởi tạo chuỗi câu lệnh SQL thứ 6
                String sqlbot4 = ""; // Khai báo chuỗi câu lệnh SQL thứ 7
                String sqlbot5 = ""; // Khởi tạo chuỗi câu lệnh SQL thứ 6
                String sqlbot6 = ""; // Khai báo chuỗi câu lệnh SQL thứ 7
//                String sql8 = ""; // Khai báo chuỗi câu lệnh SQL thứ 8

                // Khai báo biến để lưu trữ kết quả từ truy vấn SQL
                String resultcom1 = "";
                String resultcom2 = "";
                String resultcom3 = "";
                String resultcom4 = "";
                String resultcom5 = "";
                String resultcom6 = "";
                String resultcom7 = "";
                String resultcom8 = "";
                String resultcom9 = "";
                String resultchao1 = "";
                String resultchao2 = "";
                String resultchao3 = "";
                String resultchao4 = "";
                String resultchao5 = "";
                String resultchao6 = "";
                String resultchao7 = "";
                String resultchao8 = "";
                String resultchao9 = "";
                String resultbot1 = "";
                String resultbot2 = "";
                String resultbot3 = "";
                String resultbot4 = "";
                String resultbot5 = "";
                String resultbot6 = "";

//Dưới 1 tuổi - chọn cháo - có tiền sử bệnh TC-TB
                if(tuoitre.equals("Dưới 1 tuổi")){
                    if(kieunau.equals("Cháo")){
                        if(selectedTC.equals("Có")&& selectedTB.equals("Có")){
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
//Dưới 1 tuổi - chọn cháo - Có tiền sử bênh TC
                        else if (selectedTC.equals("Có")&& selectedTB.equals("Không")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
//Dưới 1 tuổi - chọn cháo - có tiền sử bênh TB
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Có")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Không")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Nếu trẻ không bị bệnh hãy ấn 'Không' ", Toast.LENGTH_SHORT).show();
                        }
//lưu kết quả sql
                        PreparedStatement pschao1 = connection.prepareStatement(sqlchao1);
                        ResultSet rschao1 = pschao1.executeQuery();
                        StringBuilder resultTextchao1 = new StringBuilder();
                        while (rschao1.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao1 = rschao1.getString("Món ăn");
                            resultTextchao1.append(datachao1);
                        }
                        resultchao1 = resultTextchao1.toString(); // Lưu kết quả vào biến result
                        pschao1.close();

//lưu kết quả sql
                        PreparedStatement pschao2 = connection.prepareStatement(sqlchao2);
                        ResultSet rschao2 = pschao2.executeQuery();
                        StringBuilder resultTextchao2 = new StringBuilder();
                        while (rschao2.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao2 = rschao2.getString("Món ăn");
                            resultTextchao2.append(datachao2);
                        }
                        resultchao2 = resultTextchao2.toString(); // Lưu kết quả vào biến result
                        pschao2.close();

//lưu kết quả sql
                        PreparedStatement pschao3 = connection.prepareStatement(sqlchao3);
                        ResultSet rschao3 = pschao3.executeQuery();
                        StringBuilder resultTextchao3 = new StringBuilder();
                        while (rschao3.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao3 = rschao3.getString("Món ăn");
                            resultTextchao3.append(datachao3);
                        }
                        resultchao3 = resultTextchao3.toString(); // Lưu kết quả vào biến result
                        pschao3.close();

//lưu kết quả sql
                        PreparedStatement pschao4 = connection.prepareStatement(sqlchao4);
                        ResultSet rschao4 = pschao4.executeQuery();
                        StringBuilder resultTextchao4 = new StringBuilder();
                        while (rschao4.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao4 = rschao4.getString("Món ăn");
                            resultTextchao4.append(datachao4);
                        }
                        resultchao4 = resultTextchao4.toString(); // Lưu kết quả vào biến result
                        pschao4.close();

//lưu kết quả sql
                        PreparedStatement pschao5 = connection.prepareStatement(sqlchao5);
                        ResultSet rschao5 = pschao5.executeQuery();
                        StringBuilder resultTextchao5 = new StringBuilder();
                        while (rschao5.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao5 = rschao5.getString("Món ăn");
                            resultTextchao5.append(datachao5);
                        }
                        resultchao5 = resultTextchao5.toString(); // Lưu kết quả vào biến result
                        pschao5.close();

//lưu kết quả sql
                        PreparedStatement pschao6 = connection.prepareStatement(sqlchao6);
                        ResultSet rschao6 = pschao6.executeQuery();
                        StringBuilder resultTextchao6 = new StringBuilder();
                        while (rschao6.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao6 = rschao6.getString("Món ăn");
                            resultTextchao6.append(datachao6);
                        }
                        resultchao6 = resultTextchao6.toString(); // Lưu kết quả vào biến result
                        pschao6.close();

//lưu kết quả sql
                        PreparedStatement pschao7 = connection.prepareStatement(sqlchao7);
                        ResultSet rschao7 = pschao7.executeQuery();
                        StringBuilder resultTextchao7 = new StringBuilder();
                        while (rschao7.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao7 = rschao7.getString("Món ăn");
                            resultTextchao7.append(datachao7);
                        }
                        resultchao7 = resultTextchao7.toString(); // Lưu kết quả vào biến result
                        pschao7.close();

//lưu kết quả sql
                        PreparedStatement pschao8 = connection.prepareStatement(sqlchao8);
                        ResultSet rschao8 = pschao8.executeQuery();
                        StringBuilder resultTextchao8 = new StringBuilder();
                        while (rschao8.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao8 = rschao8.getString("Món ăn");
                            resultTextchao8.append(datachao8);
                        }
                        resultchao8 = resultTextchao8.toString(); // Lưu kết quả vào biến result
                        pschao8.close();

//lưu kết quả sql
                        PreparedStatement pschao9 = connection.prepareStatement(sqlchao9);
                        ResultSet rschao9 = pschao9.executeQuery();
                        StringBuilder resultTextchao9 = new StringBuilder();
                        while (rschao9.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao9 = rschao9.getString("Món ăn");
                            resultTextchao9.append(datachao9);
                        }
                        resultchao9 = resultTextchao9.toString(); // Lưu kết quả vào biến result
                        pschao9.close();

                        Intent intent = new Intent(MainActivity.this, ketqua.class);
                        intent.putExtra("resultchao1", resultchao1);
                        intent.putExtra("resultchao2", resultchao2);
                        intent.putExtra("resultchao3", resultchao3);
                        intent.putExtra("resultchao4", resultchao4);
                        intent.putExtra("resultchao5", resultchao5);
                        intent.putExtra("resultchao6", resultchao6);
                        intent.putExtra("resultchao7", resultchao7);
                        intent.putExtra("resultchao8", resultchao8);
                        intent.putExtra("resultchao9", resultchao9);
                        intent.putExtra("tuoitre",tuoitre);
                        intent.putExtra("kieunau",kieunau);
                        intent.putExtra("benhTC",selectedTC);
                        intent.putExtra("benhTB",selectedTB);

                        // Chuyển sang Activity mới
                        startActivity(intent);
                    }
                    else if (kieunau.equals("Bột")) {
                            sqlbot1 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlbot2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 5 OR F.[Nguyên liệu]=6 " +
                                    "ORDER BY NEWID();";
                            sqlbot3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlbot4 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 5 OR F.[Nguyên liệu]=6 " +
                                    "ORDER BY NEWID();";
                            sqlbot5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlbot6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 5 OR F.[Nguyên liệu]=6 " +
                                    "ORDER BY NEWID();";

//                        else{
//                            Toast.makeText(MainActivity.this, "Nếu trẻ không bị bệnh hãy ấn 'Không' ", Toast.LENGTH_SHORT).show();
//                        }

// Lưu kết quả sql
                        PreparedStatement psbot1 = connection.prepareStatement(sqlbot1);
                        ResultSet rsbot1 = psbot1.executeQuery();
                        StringBuilder resultTextbot1 = new StringBuilder();
                        while (rsbot1.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String databot1 = rsbot1.getString("Món ăn");
                            resultTextbot1.append(databot1);
                        }
                        resultbot1 = resultTextbot1.toString(); // Lưu kết quả vào biến resultbot1
                        psbot1.close();

// Tiếp theo cho bot2
                        PreparedStatement psbot2 = connection.prepareStatement(sqlbot2);
                        ResultSet rsbot2 = psbot2.executeQuery();
                        StringBuilder resultTextbot2 = new StringBuilder();
                        while (rsbot2.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String databot2 = rsbot2.getString("Món ăn");
                            resultTextbot2.append(databot2);
                        }
                        resultbot2 = resultTextbot2.toString(); // Lưu kết quả vào biến resultbot2
                        psbot2.close();

// Tiếp theo cho bot3
                        PreparedStatement psbot3 = connection.prepareStatement(sqlbot3);
                        ResultSet rsbot3 = psbot3.executeQuery();
                        StringBuilder resultTextbot3 = new StringBuilder();
                        while (rsbot3.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String databot3 = rsbot3.getString("Món ăn");
                            resultTextbot3.append(databot3);
                        }
                        resultbot3 = resultTextbot3.toString(); // Lưu kết quả vào biến resultbot3
                        psbot3.close();

// Tiếp theo cho bot4
                        PreparedStatement psbot4 = connection.prepareStatement(sqlbot4);
                        ResultSet rsbot4 = psbot4.executeQuery();
                        StringBuilder resultTextbot4 = new StringBuilder();
                        while (rsbot4.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String databot4 = rsbot4.getString("Món ăn");
                            resultTextbot4.append(databot4);
                        }
                        resultbot4 = resultTextbot4.toString(); // Lưu kết quả vào biến resultbot4
                        psbot4.close();

// Tiếp theo cho bot5
                        PreparedStatement psbot5 = connection.prepareStatement(sqlbot5);
                        ResultSet rsbot5 = psbot5.executeQuery();
                        StringBuilder resultTextbot5 = new StringBuilder();
                        while (rsbot5.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String databot5 = rsbot5.getString("Món ăn");
                            resultTextbot5.append(databot5);
                        }
                        resultbot5 = resultTextbot5.toString(); // Lưu kết quả vào biến resultbot5
                        psbot5.close();

// Tiếp theo cho bot6
                        PreparedStatement psbot6 = connection.prepareStatement(sqlbot6);
                        ResultSet rsbot6 = psbot6.executeQuery();
                        StringBuilder resultTextbot6 = new StringBuilder();
                        while (rsbot6.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String databot6 = rsbot6.getString("Món ăn");
                            resultTextbot6.append(databot6);
                        }
                        resultbot6 = resultTextbot6.toString(); // Lưu kết quả vào biến resultbot6
                        psbot6.close();
                        Intent intent = new Intent(MainActivity.this, ketqua.class);

                        // Đính kèm dữ liệu kết quả vào Intent
                        intent.putExtra("benhTC",selectedTC);
                        intent.putExtra("benhTB",selectedTB);
                        intent.putExtra("tuoitre",tuoitre);
                        intent.putExtra("kieunau",kieunau);
                        intent.putExtra("resultbot1", resultbot1);
                        intent.putExtra("resultbot2", resultbot2);
                        intent.putExtra("resultbot3", resultbot3);
                        intent.putExtra("resultbot4", resultbot4);
                        intent.putExtra("resultbot5", resultbot5);
                        intent.putExtra("resultbot6", resultbot6);

                        // Chuyển sang Activity mới
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Lựa chọn cách nấu ăn cho trẻ.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (tuoitre.equals("Từ 2-3 tuổi")) {
                    if(kieunau.equals("Cơm")){
                        if(selectedTC.equals("Có")&& selectedTB.equals("Có")) {
                            sqlcom1 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else if (selectedTC.equals("Có")&& selectedTB.equals("Không")) {
                            sqlcom1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";}
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Có")) {
                            sqlcom1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";}
                        else if (selectedTC.equals("Không") && selectedTB.equals("Không")){
                            sqlcom1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";}
//lưu kết quả sql
                        PreparedStatement pscom1 = connection.prepareStatement(sqlcom1);
                        ResultSet rscom1 = pscom1.executeQuery();
                        StringBuilder resultTextcom1 = new StringBuilder();
                        while (rscom1.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom1 = rscom1.getString("Món ăn");
                            resultTextcom1.append(datacom1);
                        }
                        resultcom1 = resultTextcom1.toString(); // Lưu kết quả vào biến result
                        pscom1.close();
                        //lưu kết quả sql
                        PreparedStatement pscom2 = connection.prepareStatement(sqlcom2);
                        ResultSet rscom2 = pscom2.executeQuery();
                        StringBuilder resultTextcom2 = new StringBuilder();
                        while (rscom2.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom2 = rscom2.getString("Món ăn");
                            resultTextcom2.append(datacom2);
                        }
                        resultcom2 = resultTextcom2.toString(); // Lưu kết quả vào biến result
                        pscom2.close();

//lưu kết quả sql
                        PreparedStatement pscom3 = connection.prepareStatement(sqlcom3);
                        ResultSet rscom3 = pscom3.executeQuery();
                        StringBuilder resultTextcom3 = new StringBuilder();
                        while (rscom3.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom3 = rscom3.getString("Món ăn");
                            resultTextcom3.append(datacom3);
                        }
                        resultcom3 = resultTextcom3.toString(); // Lưu kết quả vào biến result
                        pscom3.close();

//lưu kết quả sql
                        PreparedStatement pscom4 = connection.prepareStatement(sqlcom4);
                        ResultSet rscom4 = pscom4.executeQuery();
                        StringBuilder resultTextcom4 = new StringBuilder();
                        while (rscom4.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom4 = rscom4.getString("Món ăn");
                            resultTextcom4.append(datacom4);
                        }
                        resultcom4 = resultTextcom4.toString(); // Lưu kết quả vào biến result
                        pscom4.close();

//lưu kết quả sql
                        PreparedStatement pscom5 = connection.prepareStatement(sqlcom5);
                        ResultSet rscom5 = pscom5.executeQuery();
                        StringBuilder resultTextcom5 = new StringBuilder();
                        while (rscom5.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom5 = rscom5.getString("Món ăn");
                            resultTextcom5.append(datacom5);
                        }
                        resultcom5 = resultTextcom5.toString(); // Lưu kết quả vào biến result
                        pscom5.close();

//lưu kết quả sql
                        PreparedStatement pscom6 = connection.prepareStatement(sqlcom6);
                        ResultSet rscom6 = pscom6.executeQuery();
                        StringBuilder resultTextcom6 = new StringBuilder();
                        while (rscom6.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom6 = rscom6.getString("Món ăn");
                            resultTextcom6.append(datacom6);
                        }
                        resultcom6 = resultTextcom6.toString(); // Lưu kết quả vào biến result
                        pscom6.close();

//lưu kết quả sql
                        PreparedStatement pscom7 = connection.prepareStatement(sqlcom7);
                        ResultSet rscom7 = pscom7.executeQuery();
                        StringBuilder resultTextcom7 = new StringBuilder();
                        while (rscom7.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom7 = rscom7.getString("Món ăn");
                            resultTextcom7.append(datacom7);
                        }
                        resultcom7 = resultTextcom7.toString(); // Lưu kết quả vào biến result
                        pscom7.close();

//lưu kết quả sql
                        PreparedStatement pscom8 = connection.prepareStatement(sqlcom8);
                        ResultSet rscom8 = pscom8.executeQuery();
                        StringBuilder resultTextcom8 = new StringBuilder();
                        while (rscom8.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom8 = rscom8.getString("Món ăn");
                            resultTextcom8.append(datacom8);
                        }
                        resultcom8 = resultTextcom8.toString(); // Lưu kết quả vào biến result
                        pscom8.close();

//lưu kết quả sql
                        PreparedStatement pscom9 = connection.prepareStatement(sqlcom9);
                        ResultSet rscom9 = pscom9.executeQuery();
                        StringBuilder resultTextcom9 = new StringBuilder();
                        while (rscom9.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom9 = rscom9.getString("Món ăn");
                            resultTextcom9.append(datacom9);
                        }
                        resultcom9 = resultTextcom9.toString(); // Lưu kết quả vào biến result
                        pscom9.close();
                        Intent intent = new Intent(MainActivity.this, ketqua.class);

                        // Đính kèm dữ liệu kết quả vào Intent
                        intent.putExtra("resultcom1", resultcom1);
                        intent.putExtra("resultcom2", resultcom2);
                        intent.putExtra("resultcom3", resultcom3);
                        intent.putExtra("resultcom4", resultcom4);
                        intent.putExtra("resultcom5", resultcom5);
                        intent.putExtra("resultcom6", resultcom6);
                        intent.putExtra("resultcom7", resultcom7);
                        intent.putExtra("resultcom8", resultcom8);
                        intent.putExtra("resultcom9", resultcom9);
                        intent.putExtra("kieunau",kieunau);
                        intent.putExtra("tuoitre",tuoitre);
                        intent.putExtra("benhTC",selectedTC);
                        intent.putExtra("benhTB",selectedTB);
                        //bắt đầu
                        startActivity(intent);
                    }
                    else if (kieunau.equals("Cháo")) {
                        if(selectedTC.equals("Có")&& selectedTB.equals("Có")){
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
//Dưới 1 tuổi - chọn cháo - Có tiền sử bênh TC
                        else if (selectedTC.equals("Có")&& selectedTB.equals("Không")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
//Dưới 1 tuổi - chọn cháo - có tiền sử bênh TB
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Có")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Không")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Nếu trẻ không bị bệnh hãy ấn 'Không' ", Toast.LENGTH_SHORT).show();
                        }
//lưu kết quả sql
                        PreparedStatement pschao1 = connection.prepareStatement(sqlchao1);
                        ResultSet rschao1 = pschao1.executeQuery();
                        StringBuilder resultTextchao1 = new StringBuilder();
                        while (rschao1.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao1 = rschao1.getString("Món ăn");
                            resultTextchao1.append(datachao1);
                        }
                        resultchao1 = resultTextchao1.toString(); // Lưu kết quả vào biến result
                        pschao1.close();

//lưu kết quả sql
                        PreparedStatement pschao2 = connection.prepareStatement(sqlchao2);
                        ResultSet rschao2 = pschao2.executeQuery();
                        StringBuilder resultTextchao2 = new StringBuilder();
                        while (rschao2.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao2 = rschao2.getString("Món ăn");
                            resultTextchao2.append(datachao2);
                        }
                        resultchao2 = resultTextchao2.toString(); // Lưu kết quả vào biến result
                        pschao2.close();

//lưu kết quả sql
                        PreparedStatement pschao3 = connection.prepareStatement(sqlchao3);
                        ResultSet rschao3 = pschao3.executeQuery();
                        StringBuilder resultTextchao3 = new StringBuilder();
                        while (rschao3.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao3 = rschao3.getString("Món ăn");
                            resultTextchao3.append(datachao3);
                        }
                        resultchao3 = resultTextchao3.toString(); // Lưu kết quả vào biến result
                        pschao3.close();

//lưu kết quả sql
                        PreparedStatement pschao4 = connection.prepareStatement(sqlchao4);
                        ResultSet rschao4 = pschao4.executeQuery();
                        StringBuilder resultTextchao4 = new StringBuilder();
                        while (rschao4.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao4 = rschao4.getString("Món ăn");
                            resultTextchao4.append(datachao4);
                        }
                        resultchao4 = resultTextchao4.toString(); // Lưu kết quả vào biến result
                        pschao4.close();

//lưu kết quả sql
                        PreparedStatement pschao5 = connection.prepareStatement(sqlchao5);
                        ResultSet rschao5 = pschao5.executeQuery();
                        StringBuilder resultTextchao5 = new StringBuilder();
                        while (rschao5.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao5 = rschao5.getString("Món ăn");
                            resultTextchao5.append(datachao5);
                        }
                        resultchao5 = resultTextchao5.toString(); // Lưu kết quả vào biến result
                        pschao5.close();

//lưu kết quả sql
                        PreparedStatement pschao6 = connection.prepareStatement(sqlchao6);
                        ResultSet rschao6 = pschao6.executeQuery();
                        StringBuilder resultTextchao6 = new StringBuilder();
                        while (rschao6.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao6 = rschao6.getString("Món ăn");
                            resultTextchao6.append(datachao6);
                        }
                        resultchao6 = resultTextchao6.toString(); // Lưu kết quả vào biến result
                        pschao6.close();

//lưu kết quả sql
                        PreparedStatement pschao7 = connection.prepareStatement(sqlchao7);
                        ResultSet rschao7 = pschao7.executeQuery();
                        StringBuilder resultTextchao7 = new StringBuilder();
                        while (rschao7.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao7 = rschao7.getString("Món ăn");
                            resultTextchao7.append(datachao7);
                        }
                        resultchao7 = resultTextchao7.toString(); // Lưu kết quả vào biến result
                        pschao7.close();

//lưu kết quả sql
                        PreparedStatement pschao8 = connection.prepareStatement(sqlchao8);
                        ResultSet rschao8 = pschao8.executeQuery();
                        StringBuilder resultTextchao8 = new StringBuilder();
                        while (rschao8.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao8 = rschao8.getString("Món ăn");
                            resultTextchao8.append(datachao8);
                        }
                        resultchao8 = resultTextchao8.toString(); // Lưu kết quả vào biến result
                        pschao8.close();

//lưu kết quả sql
                        PreparedStatement pschao9 = connection.prepareStatement(sqlchao9);
                        ResultSet rschao9 = pschao9.executeQuery();
                        StringBuilder resultTextchao9 = new StringBuilder();
                        while (rschao9.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao9 = rschao9.getString("Món ăn");
                            resultTextchao9.append(datachao9);
                        }
                        resultchao9 = resultTextchao9.toString(); // Lưu kết quả vào biến result
                        pschao9.close();

                        Intent intent = new Intent(MainActivity.this, ketqua.class);
                        intent.putExtra("resultchao1", resultchao1);
                        intent.putExtra("resultchao2", resultchao2);
                        intent.putExtra("resultchao3", resultchao3);
                        intent.putExtra("resultchao4", resultchao4);
                        intent.putExtra("resultchao5", resultchao5);
                        intent.putExtra("resultchao6", resultchao6);
                        intent.putExtra("resultchao7", resultchao7);
                        intent.putExtra("resultchao8", resultchao8);
                        intent.putExtra("resultchao9", resultchao9);
                        intent.putExtra("tuoitre",tuoitre);
                        intent.putExtra("kieunau",kieunau);
                        intent.putExtra("benhTC",selectedTC);
                        intent.putExtra("benhTB",selectedTB);

                        // Chuyển sang Activity mới
                        startActivity(intent);
                    }
                }
                else if (tuoitre.equals("Trên 3 tuổi")) {
                    if(kieunau.equals("Cơm")){
                        if(selectedTC.equals("Có")&& selectedTB.equals("Có")) {
                            sqlcom1 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else if (selectedTC.equals("Có")&& selectedTB.equals("Không")) {
                            sqlcom1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";}
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Có")) {
                            sqlcom1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";}
                        else if (selectedTC.equals("Không") && selectedTB.equals("Không")){
                            sqlcom1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlcom3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlcom6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlcom7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlcom8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlcom9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";}
//lưu kết quả sql
                        PreparedStatement pscom1 = connection.prepareStatement(sqlcom1);
                        ResultSet rscom1 = pscom1.executeQuery();
                        StringBuilder resultTextcom1 = new StringBuilder();
                        while (rscom1.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom1 = rscom1.getString("Món ăn");
                            resultTextcom1.append(datacom1);
                        }
                        resultcom1 = resultTextcom1.toString(); // Lưu kết quả vào biến result
                        pscom1.close();
                        //lưu kết quả sql
                        PreparedStatement pscom2 = connection.prepareStatement(sqlcom2);
                        ResultSet rscom2 = pscom2.executeQuery();
                        StringBuilder resultTextcom2 = new StringBuilder();
                        while (rscom2.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom2 = rscom2.getString("Món ăn");
                            resultTextcom2.append(datacom2);
                        }
                        resultcom2 = resultTextcom2.toString(); // Lưu kết quả vào biến result
                        pscom2.close();

//lưu kết quả sql
                        PreparedStatement pscom3 = connection.prepareStatement(sqlcom3);
                        ResultSet rscom3 = pscom3.executeQuery();
                        StringBuilder resultTextcom3 = new StringBuilder();
                        while (rscom3.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom3 = rscom3.getString("Món ăn");
                            resultTextcom3.append(datacom3);
                        }
                        resultcom3 = resultTextcom3.toString(); // Lưu kết quả vào biến result
                        pscom3.close();

//lưu kết quả sql
                        PreparedStatement pscom4 = connection.prepareStatement(sqlcom4);
                        ResultSet rscom4 = pscom4.executeQuery();
                        StringBuilder resultTextcom4 = new StringBuilder();
                        while (rscom4.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom4 = rscom4.getString("Món ăn");
                            resultTextcom4.append(datacom4);
                        }
                        resultcom4 = resultTextcom4.toString(); // Lưu kết quả vào biến result
                        pscom4.close();

//lưu kết quả sql
                        PreparedStatement pscom5 = connection.prepareStatement(sqlcom5);
                        ResultSet rscom5 = pscom5.executeQuery();
                        StringBuilder resultTextcom5 = new StringBuilder();
                        while (rscom5.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom5 = rscom5.getString("Món ăn");
                            resultTextcom5.append(datacom5);
                        }
                        resultcom5 = resultTextcom5.toString(); // Lưu kết quả vào biến result
                        pscom5.close();

//lưu kết quả sql
                        PreparedStatement pscom6 = connection.prepareStatement(sqlcom6);
                        ResultSet rscom6 = pscom6.executeQuery();
                        StringBuilder resultTextcom6 = new StringBuilder();
                        while (rscom6.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom6 = rscom6.getString("Món ăn");
                            resultTextcom6.append(datacom6);
                        }
                        resultcom6 = resultTextcom6.toString(); // Lưu kết quả vào biến result
                        pscom6.close();

//lưu kết quả sql
                        PreparedStatement pscom7 = connection.prepareStatement(sqlcom7);
                        ResultSet rscom7 = pscom7.executeQuery();
                        StringBuilder resultTextcom7 = new StringBuilder();
                        while (rscom7.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom7 = rscom7.getString("Món ăn");
                            resultTextcom7.append(datacom7);
                        }
                        resultcom7 = resultTextcom7.toString(); // Lưu kết quả vào biến result
                        pscom7.close();

//lưu kết quả sql
                        PreparedStatement pscom8 = connection.prepareStatement(sqlcom8);
                        ResultSet rscom8 = pscom8.executeQuery();
                        StringBuilder resultTextcom8 = new StringBuilder();
                        while (rscom8.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom8 = rscom8.getString("Món ăn");
                            resultTextcom8.append(datacom8);
                        }
                        resultcom8 = resultTextcom8.toString(); // Lưu kết quả vào biến result
                        pscom8.close();

//lưu kết quả sql
                        PreparedStatement pscom9 = connection.prepareStatement(sqlcom9);
                        ResultSet rscom9 = pscom9.executeQuery();
                        StringBuilder resultTextcom9 = new StringBuilder();
                        while (rscom9.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datacom9 = rscom9.getString("Món ăn");
                            resultTextcom9.append(datacom9);
                        }
                        resultcom9 = resultTextcom9.toString(); // Lưu kết quả vào biến result
                        pscom9.close();
                        Intent intent = new Intent(MainActivity.this, ketqua.class);

                        // Đính kèm dữ liệu kết quả vào Intent
                        intent.putExtra("resultcom1", resultcom1);
                        intent.putExtra("resultcom2", resultcom2);
                        intent.putExtra("resultcom3", resultcom3);
                        intent.putExtra("resultcom4", resultcom4);
                        intent.putExtra("resultcom5", resultcom5);
                        intent.putExtra("resultcom6", resultcom6);
                        intent.putExtra("resultcom7", resultcom7);
                        intent.putExtra("resultcom8", resultcom8);
                        intent.putExtra("resultcom9", resultcom9);
                        intent.putExtra("kieunau",kieunau);
                        intent.putExtra("tuoitre",tuoitre);
                        intent.putExtra("benhTC",selectedTC);
                        intent.putExtra("benhTB",selectedTB);
                        //bắt đầu
                        startActivity(intent);
                    }
                    else if (kieunau.equals("Cháo")) {
                        if(selectedTC.equals("Có")&& selectedTB.equals("Có")){
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
//Dưới 1 tuổi - chọn cháo - Có tiền sử bênh TC
                        else if (selectedTC.equals("Có")&& selectedTB.equals("Không")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh tiêu chảy] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
//Dưới 1 tuổi - chọn cháo - có tiền sử bênh TB
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Có")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 AND F.[Bệnh táo bón] = 1 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else if (selectedTC.equals("Không")&& selectedTB.equals("Không")) {
                            sqlchao1= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao2 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao3 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao4= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao5 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao6 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                            sqlchao7= "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Cách nấu] = 2 " +
                                    "ORDER BY NEWID();";
                            sqlchao8 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 3 " +
                                    "ORDER BY NEWID();";
                            sqlchao9 = "SELECT Top 1 F.[Món ăn] " +
                                    "FROM MONANFULL F " +
                                    "WHERE F.[Nguyên liệu] = 4 " +
                                    "ORDER BY NEWID();";
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Nếu trẻ không bị bệnh hãy ấn 'Không' ", Toast.LENGTH_SHORT).show();
                        }
//lưu kết quả sql
                        PreparedStatement pschao1 = connection.prepareStatement(sqlchao1);
                        ResultSet rschao1 = pschao1.executeQuery();
                        StringBuilder resultTextchao1 = new StringBuilder();
                        while (rschao1.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao1 = rschao1.getString("Món ăn");
                            resultTextchao1.append(datachao1);
                        }
                        resultchao1 = resultTextchao1.toString(); // Lưu kết quả vào biến result
                        pschao1.close();

//lưu kết quả sql
                        PreparedStatement pschao2 = connection.prepareStatement(sqlchao2);
                        ResultSet rschao2 = pschao2.executeQuery();
                        StringBuilder resultTextchao2 = new StringBuilder();
                        while (rschao2.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao2 = rschao2.getString("Món ăn");
                            resultTextchao2.append(datachao2);
                        }
                        resultchao2 = resultTextchao2.toString(); // Lưu kết quả vào biến result
                        pschao2.close();

//lưu kết quả sql
                        PreparedStatement pschao3 = connection.prepareStatement(sqlchao3);
                        ResultSet rschao3 = pschao3.executeQuery();
                        StringBuilder resultTextchao3 = new StringBuilder();
                        while (rschao3.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao3 = rschao3.getString("Món ăn");
                            resultTextchao3.append(datachao3);
                        }
                        resultchao3 = resultTextchao3.toString(); // Lưu kết quả vào biến result
                        pschao3.close();

//lưu kết quả sql
                        PreparedStatement pschao4 = connection.prepareStatement(sqlchao4);
                        ResultSet rschao4 = pschao4.executeQuery();
                        StringBuilder resultTextchao4 = new StringBuilder();
                        while (rschao4.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao4 = rschao4.getString("Món ăn");
                            resultTextchao4.append(datachao4);
                        }
                        resultchao4 = resultTextchao4.toString(); // Lưu kết quả vào biến result
                        pschao4.close();

//lưu kết quả sql
                        PreparedStatement pschao5 = connection.prepareStatement(sqlchao5);
                        ResultSet rschao5 = pschao5.executeQuery();
                        StringBuilder resultTextchao5 = new StringBuilder();
                        while (rschao5.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao5 = rschao5.getString("Món ăn");
                            resultTextchao5.append(datachao5);
                        }
                        resultchao5 = resultTextchao5.toString(); // Lưu kết quả vào biến result
                        pschao5.close();

//lưu kết quả sql
                        PreparedStatement pschao6 = connection.prepareStatement(sqlchao6);
                        ResultSet rschao6 = pschao6.executeQuery();
                        StringBuilder resultTextchao6 = new StringBuilder();
                        while (rschao6.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao6 = rschao6.getString("Món ăn");
                            resultTextchao6.append(datachao6);
                        }
                        resultchao6 = resultTextchao6.toString(); // Lưu kết quả vào biến result
                        pschao6.close();

//lưu kết quả sql
                        PreparedStatement pschao7 = connection.prepareStatement(sqlchao7);
                        ResultSet rschao7 = pschao7.executeQuery();
                        StringBuilder resultTextchao7 = new StringBuilder();
                        while (rschao7.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao7 = rschao7.getString("Món ăn");
                            resultTextchao7.append(datachao7);
                        }
                        resultchao7 = resultTextchao7.toString(); // Lưu kết quả vào biến result
                        pschao7.close();

//lưu kết quả sql
                        PreparedStatement pschao8 = connection.prepareStatement(sqlchao8);
                        ResultSet rschao8 = pschao8.executeQuery();
                        StringBuilder resultTextchao8 = new StringBuilder();
                        while (rschao8.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao8 = rschao8.getString("Món ăn");
                            resultTextchao8.append(datachao8);
                        }
                        resultchao8 = resultTextchao8.toString(); // Lưu kết quả vào biến result
                        pschao8.close();

//lưu kết quả sql
                        PreparedStatement pschao9 = connection.prepareStatement(sqlchao9);
                        ResultSet rschao9 = pschao9.executeQuery();
                        StringBuilder resultTextchao9 = new StringBuilder();
                        while (rschao9.next()) {
                            // Lấy dữ liệu từ cột "Món ăn" và nối vào chuỗi kết quả
                            String datachao9 = rschao9.getString("Món ăn");
                            resultTextchao9.append(datachao9);
                        }
                        resultchao9 = resultTextchao9.toString(); // Lưu kết quả vào biến result
                        pschao9.close();

                        Intent intent = new Intent(MainActivity.this, ketqua.class);
                        intent.putExtra("resultchao1", resultchao1);
                        intent.putExtra("resultchao2", resultchao2);
                        intent.putExtra("resultchao3", resultchao3);
                        intent.putExtra("resultchao4", resultchao4);
                        intent.putExtra("resultchao5", resultchao5);
                        intent.putExtra("resultchao6", resultchao6);
                        intent.putExtra("resultchao7", resultchao7);
                        intent.putExtra("resultchao8", resultchao8);
                        intent.putExtra("resultchao9", resultchao9);
                        intent.putExtra("tuoitre",tuoitre);
                        intent.putExtra("kieunau",kieunau);
                        intent.putExtra("benhTC",selectedTC);
                        intent.putExtra("benhTB",selectedTB);

                        // Chuyển sang Activity mới
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Lựa chọn tuổi cho trẻ.", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Không kết nối được với Cơ sở dữ liệu.", Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
    }

            // Phương thức này để lưu trữ thông tin của RadioButton được chọn
            private String tuoitre = "";

            // Phương thức này sẽ gọi khi một RadioButton được chọn
            private void setdatatuoi (String tuoi){
                tuoitre = tuoi;
            }

            // Phương thức này để lưu trữ thông tin của RadioButton được chọn
            private String kieunau = "";

            // Phương thức này sẽ gọi khi một RadioButton được chọn
            private void setdatakieunau (String setkieunau){
                kieunau = setkieunau;
            }

            // Phương thức này để lưu trữ thông tin của RadioButton được chọn trong RadioGroup
            private String selectedTC = "";

            // Phương thức này sẽ gọi khi một RadioButton được chọn
            private void setdataTC (String benhTC){
                selectedTC = benhTC;
            }

            // Phương thức này để lưu trữ thông tin của RadioButton được chọn trong RadioGroup
            private String selectedTB = "";

            // Phương thức này sẽ gọi khi một RadioButton được chọn
            private void setdataTB (String benhTB){
                selectedTB = benhTB;
            }

            // Phương thức để xóa bỏ các lựa chọn của các RadioButton trong RadioGroup
// Phương thức để xóa bỏ các lựa chọn của các RadioButton trong RadioGroup
            private void clearAllRadioButtons () {
                rdbcom.setChecked(false);
                rdbchao.setChecked(false);
                rdbbot.setChecked(false);
                TC.clearCheck();
                TB.clearCheck();
                KN.clearCheck();
            }
            private Connection connectSQL () {
                Connection con = null;
                try {
                    StrictMode.ThreadPolicy policy =
                            new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                    String connectString = "jdbc:jtds:sqlserver://172.20.10.5:1433;databasename=DinhDuong;user=sa;password=tuannh123";
                    con = DriverManager.getConnection(connectString);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
                return con;
            }

    }


