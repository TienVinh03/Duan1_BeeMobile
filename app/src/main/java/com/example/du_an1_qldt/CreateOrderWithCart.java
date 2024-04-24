package com.example.du_an1_qldt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an1_qldt.Adapter.CartAdapter;
import com.example.du_an1_qldt.Adapter.CartAdapter2;
import com.example.du_an1_qldt.Adapter.VoucherSpinnerAdapter;
import com.example.du_an1_qldt.DAO.CartDao;
import com.example.du_an1_qldt.DAO.CustomerDao;
import com.example.du_an1_qldt.DAO.OrderDAO;
import com.example.du_an1_qldt.DAO.SanPhamDAO;
import com.example.du_an1_qldt.DAO.TaiKhoanDAO;
import com.example.du_an1_qldt.DAO.VoucherDAO;
import com.example.du_an1_qldt.model.Cart;
import com.example.du_an1_qldt.model.Customer;
import com.example.du_an1_qldt.model.Order;
import com.example.du_an1_qldt.model.OrderDetail;
import com.example.du_an1_qldt.model.Voucher_DTO;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateOrderWithCart extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Cart> cartArrayList;
    CartDao cartDao;
    Toolbar toolbar;
    CartAdapter2 cartAdapter;
    TextView tv_price, tv_priceShip, tv_priceVoucher, tvTotal;
    TextInputEditText nameUser, numberPhone, addressUser;
    LinearLayoutManager linearLayoutManager;
    OrderDAO orderDAO;
    int getQuantity;
    Date date;
    VoucherSpinnerAdapter voucherSpinnerAdapter;
    Spinner spinner;
    ArrayList<Voucher_DTO> voucherDtos;
    VoucherDAO voucherDAO;
    Button btnOrder;
    double priceShip = 20000, priceVoucher, priceTotal;


    DecimalFormat formatter = new DecimalFormat("#,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order_with_cart);
        recyclerView = findViewById(R.id.rcvOrder);
        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("manguoidung", "");
        tv_price = findViewById(R.id.priceProduct);
        tv_priceShip = findViewById(R.id.priceShip);
        tv_priceVoucher = findViewById(R.id.priceVoucher);
        tvTotal = findViewById(R.id.priceTotal);
        spinner = findViewById(R.id.spnVoucher);
        toolbar = findViewById(R.id.toolbar);
        btnOrder = findViewById(R.id.btnDatHang);
        nameUser = findViewById(R.id.username);
        numberPhone = findViewById(R.id.numberphone);
        addressUser = findViewById(R.id.address);
        cartDao = new CartDao(CreateOrderWithCart.this);
        voucherDAO = new VoucherDAO(CreateOrderWithCart.this);
        cartArrayList = cartDao.getlistCart(Integer.parseInt(id));
        cartAdapter = new CartAdapter2(this, cartArrayList);
        recyclerView.setAdapter(cartAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        String formattedTotalPrice = formatter.format(caculatorPrice());
        tv_price.setText(formattedTotalPrice + "đ");
        tv_priceShip.setText("-" + priceShip + "đ");
        cartAdapter.notifyDataSetChanged();
        loadData();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateOrderWithCart.this, FragMentContainer.class));
            }
        });

    }

    public double caculatorPrice() {
        double price = 0;
        for (Cart carts : cartArrayList) {
            price += carts.getPrice() * carts.getQuantity();
        }
        return price;
    }

    public void loadData() {

        voucherDtos = voucherDAO.selectAll();
        voucherSpinnerAdapter = new VoucherSpinnerAdapter(this, voucherDtos);
        spinner.setAdapter(voucherSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Voucher_DTO voucherDto = voucherDtos.get(position);
                priceVoucher = voucherDto.getGiaTriGiam();
                tv_priceVoucher.setText("-" + caculatorPrice() * priceVoucher / 100 + "đ");
                priceTotal = caculatorPrice() - priceShip - (caculatorPrice() * priceVoucher / 100);
                String formattedTotalPrice = formatter.format(priceTotal);
                tvTotal.setText(formattedTotalPrice + "đ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        tv_priceShip.setTextColor(Color.parseColor("#FF0000"));
        tv_priceVoucher.setTextColor(Color.parseColor("#FF0000"));
        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("hoTen", "");
        String sdt = sharedPreferences.getString("sodienthoai", "");
        String address = sharedPreferences.getString("diachi", "");
        nameUser.setText(name);
        numberPhone.setText(sdt);
        addressUser.setText(address);
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(CreateOrderWithCart.this);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkProductQuantities(cartArrayList)) {
                    if (TextUtils.isEmpty(nameUser.getText().toString().trim())) {
                        Toast.makeText(CreateOrderWithCart.this, "Không để họ tên trống", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(addressUser.getText().toString().trim())) {
                        Toast.makeText(CreateOrderWithCart.this, "Không để địa chỉ trống", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(numberPhone.getText().toString().trim())) {
                        Toast.makeText(CreateOrderWithCart.this, "Không để số điện thoại trống", Toast.LENGTH_SHORT).show();
                    } else if (!isValidPhoneNumber(numberPhone.getText().toString())) {
                        Toast.makeText(CreateOrderWithCart.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", Context.MODE_PRIVATE);
                        String id = sharedPreferences.getString("manguoidung", "");
                        orderDAO = new OrderDAO(CreateOrderWithCart.this);
                        Order order = new Order();
                        Boolean check = true;
                        order.setIdUser(Integer.parseInt(id));
                        order.setStatusOrder(0);
                        order.setDateOrder(formattedDate);
                        long orderId = orderDAO.createOrder2(order);
                        for (Cart cart : cartArrayList) {
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setIdDonHang((int) orderId);
                            orderDetail.setPrice((cart.getPrice()-cart.getPrice()*priceVoucher/100)-20000);
                            orderDetail.setIdProduct(cart.getIdPhone());
                            orderDetail.setQuantity(cart.getQuantity());
                            orderDAO.createOrderDetail(orderDetail);
                            cartDao.deleteRowCart(cart);
                        }
                        for (Cart cart : cartArrayList) {
                            if(cart.getQuantity()<=0){
                                check=false;
                                break;
                            }

                        }

                        if (orderId < 0) {
                            Toast.makeText(CreateOrderWithCart.this, "TẠO ĐƠN HÀNG THẤT BẠI", Toast.LENGTH_SHORT).show();

                        } else if (!check) {
                            Toast.makeText(CreateOrderWithCart.this, "TẠO ĐƠN HÀNG THẤT BẠI DO SO LUONG KHONG DU", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(CreateOrderWithCart.this, "ĐÃ TẠO ĐƠN HÀNG", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateOrderWithCart.this,FragMentContainer.class));
                        }
                        if (!taiKhoanDAO.isUserExists(Integer.parseInt(id))) {
                            Customer customer = new Customer();
                            customer.setNumberPhone(sdt);
                            customer.setAddress(address);
                            customer.setName(name);
                            CustomerDao customerDao = new CustomerDao(CreateOrderWithCart.this);
                            customerDao.addCustomer(customer);
                        }
                    }
                } else {
                    // Nếu có sản phẩm vượt quá số lượng trong kho, không đặt đơn hàng và thông báo cho người dùng
                    Toast.makeText(CreateOrderWithCart.this, "Số lượng sản phẩm trong giỏ hàng vượt quá số lượng trong kho.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        return phoneNumber.matches(regex);
    }
    public boolean checkProductQuantities(ArrayList<Cart> cartItems) {
        SanPhamDAO sanPhamDAO= new SanPhamDAO(this);
        boolean allProductsValid = true;
        for (Cart cartItem : cartItems) {
            int productId = cartItem.getIdPhone();
            int quantityInCart = cartItem.getQuantity();
            int quantityInStock = sanPhamDAO.getProductQuantityFromDatabase(productId);
            if (quantityInCart > quantityInStock) {
                allProductsValid = false;
                // Hiển thị thông báo cảnh báo cho người dùng
                String productName = sanPhamDAO.getProductById(productId).getName();
                String message = "Số lượng của sản phẩm " + productName + " vượt quá số lượng trong kho.";
                // Hiển thị thông báo cảnh báo
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return allProductsValid;
    }
}
