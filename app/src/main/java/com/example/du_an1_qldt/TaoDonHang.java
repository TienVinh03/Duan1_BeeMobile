package com.example.du_an1_qldt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.du_an1_qldt.Adapter.OrderAdapter;
import com.example.du_an1_qldt.Adapter.VoucherSpinnerAdapter;
import com.example.du_an1_qldt.DAO.CustomerDao;
import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.DAO.TaiKhoanDAO;
import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.model.Customer;
import com.example.du_an1_qldt.model.Order;
import com.example.du_an1_qldt.model.OrderDetail;
import com.example.du_an1_qldt.model.Voucher_DTO;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaoDonHang extends AppCompatActivity {
    VoucherDAO voucherDAO;
    Toolbar toolbar;
    TextView sl, price, name, color, rom, quantity1, ship, priceTotal, priceVoucher;
    Spinner spnVoucher;
    VoucherSpinnerAdapter voucherSpinnerAdapter;

    ArrayList<Voucher_DTO> voucherDtos;
    int quantityPr = 0, pr;
    double discount, total = 0, discountAmount, priceShip = 20000, priceDouble;
    String pricePr, namePr, romPr, colorPr;
    Button btnOrder;
    OrderDAO orderDAO;
    int idPR;
    TextInputEditText nameUser, numberPhone, addressUser;
    Date date;
    DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_don_hang);
        ImageView btnplus = findViewById(R.id.btnPlus);
        ImageView btnTru = findViewById(R.id.btnApart);
        sl = findViewById(R.id.tvQuantity);
        name = findViewById(R.id.nameProduct);
        color = findViewById(R.id.colorProduct);
        rom = findViewById(R.id.ramProduct);
        price = findViewById(R.id.priceProduct);
        quantity1 = findViewById(R.id.priceProduct2);
        ship = findViewById(R.id.priceShip);
        priceTotal = findViewById(R.id.priceTotal);
        priceVoucher = findViewById(R.id.priceVoucher);
        nameUser = findViewById(R.id.username);
        numberPhone = findViewById(R.id.numberphone);
        addressUser = findViewById(R.id.address);
        spnVoucher = findViewById(R.id.spnVoucher);
        btnOrder = findViewById(R.id.btnDatHang);
        voucherDAO = new VoucherDAO(this);
        Intent intent = getIntent();
        formatter = new DecimalFormat("#,###,###");

        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                idPR = bundle.getInt("id");
                namePr = bundle.getString("name");
                pricePr = bundle.getString("price");
                colorPr = bundle.getString("color");
                romPr = bundle.getString("ram");
                name.setText(namePr);
                color.setText(colorPr);
                priceDouble = Double.parseDouble(pricePr);
                String yourFormattedString1 = formatter.format(priceDouble);
                price.setText(yourFormattedString1 + "VND");
                quantity1.setText(yourFormattedString1 + " đ");
                rom.setText("," + romPr + "Gb");
            }
        }
        loadData();
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityPr++;
                pr = quantityPr * Integer.parseInt(pricePr);
                String yourFormattedString = formatter.format(pr);
                price.setText(yourFormattedString + "VND");
                sl.setText(String.valueOf(quantityPr));
                quantity1.setText(yourFormattedString + " đ");
                discountAmount = discount * pr;
                total = pr - priceShip - discountAmount;
                priceTotal.setText(formatter.format(total) + "đ");
                priceVoucher.setText("-" + formatter.format(discountAmount) + "đ");
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityPr > 1) {
                    quantityPr--;
                    pr = quantityPr * Integer.parseInt(pricePr);
                    String yourFormattedString = formatter.format(pr);
                    price.setText(yourFormattedString + "VND");
                    sl.setText(String.valueOf(quantityPr));
                    quantity1.setText(yourFormattedString + " đ");
                    discountAmount = discount * pr;
                    total = pr - priceShip - discountAmount;
                    priceTotal.setText(formatter.format(total) + "đ");
                    priceVoucher.setText("-" + formatter.format(discountAmount) + "đ");
                }
            }
        });
        double shipdouble = priceShip;
        String yourFormattedString2 = formatter.format(shipdouble);
        ship.setText("-" + yourFormattedString2 + " đ");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaoDonHang.this, FragMentContainer.class));
            }
        });
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        ship.setTextColor(Color.parseColor("#FF0000"));
        priceVoucher.setTextColor(Color.parseColor("#FF0000"));
        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("manguoidung", "");
        String name = sharedPreferences.getString("hoTen", "");
        String sdt = sharedPreferences.getString("sodienthoai", "");
        String address = sharedPreferences.getString("diachi", "");
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(TaoDonHang.this);

        nameUser.setText(name);
        numberPhone.setText(sdt);
        addressUser.setText(address);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VoucherDAO voucherDAO1 = new VoucherDAO(TaoDonHang.this);
                ArrayList<Voucher_DTO> listVoucher = voucherDAO1.getListVoucher();
                Voucher_DTO voucher_dto = listVoucher.get(spnVoucher.getSelectedItemPosition());



                // Sử dụng Singleton để đặt giá trị và chuyển sang AnotherClass
                SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                editor1.putInt("value", spnVoucher.getSelectedItemPosition());
                editor1.apply();





                if (TextUtils.isEmpty(nameUser.getText().toString().trim())) {
                    Toast.makeText(TaoDonHang.this, "Không để họ tên trống", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(addressUser.getText().toString().trim())) {
                    Toast.makeText(TaoDonHang.this, "Không để địa chỉ trống", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(numberPhone.getText().toString().trim())) {
                    Toast.makeText(TaoDonHang.this, "Không để số điện thoại trống", Toast.LENGTH_SHORT).show();
                } else if (!isValidPhoneNumber(numberPhone.getText().toString())) {
                    Toast.makeText(TaoDonHang.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    orderDAO = new OrderDAO(TaoDonHang.this);
                    Order order = new Order();
                    order.setIdUser(Integer.parseInt(id));
                    order.setStatusOrder(0);
                    order.setDateOrder(formattedDate);
                    long orderId = orderDAO.createOrder2(order);
                    OrderDetail orderDetail= new OrderDetail();
                    orderDetail.setIdDonHang((int) orderId);
                    orderDetail.setQuantity(quantityPr);
                    orderDetail.setIdProduct(idPR);
                    orderDetail.setPrice(total);
                    orderDAO.createOrderDetail(orderDetail);
                    SanPhamDAO sanPhamDAO= new SanPhamDAO(TaoDonHang.this);
                    int getQuantity=sanPhamDAO.getProductQuantityFromDatabase(idPR);
                    if(getQuantity<quantityPr){
                        Toast.makeText(TaoDonHang.this, "Đặt hàng không thành công do số lượng sản phẩm trong kho không đủ", Toast.LENGTH_SHORT).show();
                    } else if (voucher_dto.getSoLuong()<=0) {
                        Toast.makeText(TaoDonHang.this, "Đặt hàng không thành công do số lượng Voucher không đủ", Toast.LENGTH_SHORT).show();
                    } else {
                        if (orderId > 0) {
                            Toast.makeText(TaoDonHang.this, "ĐÃ TẠO ĐƠN HÀNG", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(TaoDonHang.this,FragMentContainer.class));
                        } else {
                            Toast.makeText(TaoDonHang.this, "TẠO ĐƠN HÀNG THẤT BẠI", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if (!taiKhoanDAO.isUserExists(Integer.parseInt(id))) {
                        Customer customer = new Customer();
                        customer.setNumberPhone(numberPhone.getText().toString());
                        customer.setAddress(addressUser.getText().toString());
                        customer.setName(nameUser.getText().toString());
                        CustomerDao customerDao = new CustomerDao(TaoDonHang.this);
                        customerDao.addCustomer(customer);
                    }
                }
            }
        });
    }

    public void loadData() {

        voucherDtos = voucherDAO.selectAll();
        voucherSpinnerAdapter = new VoucherSpinnerAdapter(this, voucherDtos);
        spnVoucher.setAdapter(voucherSpinnerAdapter);
        spnVoucher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Voucher_DTO voucherDto = voucherDtos.get(position);

                if (voucherDto.getSoLuong()<=0){
                    discount = 0;
                }else {
                    discount = voucherDto.getGiaTriGiam() / 100.0; // Chuyển phần trăm giảm giá thành số thực
                    discountAmount = discount * pr;
                    total = pr - priceShip - discountAmount;
                    priceTotal.setText(formatter.format(total) + "đ");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        return phoneNumber.matches(regex);
    }

}