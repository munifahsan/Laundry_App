package com.tiunida.laundry0.order.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.login.ui.LoginActivity;
import com.tiunida.laundry0.main.MainActivity;
import com.tiunida.laundry0.order.OrderPresenter;
import com.tiunida.laundry0.order.OrderPresenterMvp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import at.markushi.ui.CircleButton;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener,OrderViewMvp {

    private Button normalBtn,expressBtn,kilatBtn,confirmBtn;
    private TextView mForecaseTimeTxt;
    private EditText mDescTxt;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    public String mNormalTime, mExpressTime, mKilatTime;
    private String user_id;

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private Var var;

    private OrderPresenterMvp mOrderPresenterMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mOrderPresenterMvp = new OrderPresenter(this);
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();

        var = new Var();

        mForecaseTimeTxt= (TextView)findViewById(R.id.forecase_done_time);
        mDescTxt        = (EditText) findViewById(R.id.desc_txt);
        mDescTxt.setMovementMethod(new ScrollingMovementMethod());

        /**<type-->**/
        normalBtn       = (Button)findViewById(R.id.button_normal);
        normalBtn.setOnClickListener(this);
        expressBtn      = (Button)findViewById(R.id.button_express);
        expressBtn.setOnClickListener(this);
        kilatBtn        = (Button)findViewById(R.id.button_kilat);
        kilatBtn.setOnClickListener(this);
        /**</type>**/

        /**<confirm>**/
        confirmBtn      = (Button)findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);
        /**</confirm>**/

        /**HEAD**/
        var.mBandanaTxt = (TextView)findViewById(R.id.bandana_txt);
        var.mTopiTxt    = (TextView)findViewById(R.id.topi_txt);
        var.mMaskerTxt  = (TextView)findViewById(R.id.masker_txt);
        var.mKuplukTxt  = (TextView)findViewById(R.id.kupluk_txt);
        var.mKrudungTxt = (TextView)findViewById(R.id.krudung_txt);
        var.mPeciTxt    = (TextView)findViewById(R.id.peci_txt);

        var.mBandanaMin = (CircleButton)findViewById(R.id.bandana_min);
        var.mTopiMin    = (CircleButton)findViewById(R.id.topi_min);
        var.mMaskerMin  = (CircleButton)findViewById(R.id.masker_min);
        var.mKuplukMin  = (CircleButton)findViewById(R.id.kupluk_min);
        var.mKrudungMin = (CircleButton)findViewById(R.id.krudung_min);
        var.mPeciMin    = (CircleButton)findViewById(R.id.peci_min);

        var.mBandanaNum = (TextView)findViewById(R.id.bandana_num);
        var.mTopiNum    = (TextView)findViewById(R.id.topi_num);
        var.mMaskerNum  = (TextView)findViewById(R.id.masker_num);
        var.mKuplukNum  = (TextView)findViewById(R.id.kupluk_num);
        var.mKrudungNum = (TextView)findViewById(R.id.krudung_num);
        var.mPeciNum    = (TextView)findViewById(R.id.peci_num);

        var.mBandanaPls = (CircleButton)findViewById(R.id.bandana_pls);
        var.mTopiPls    = (CircleButton)findViewById(R.id.topi_pls);
        var.mMaskerPls  = (CircleButton)findViewById(R.id.masker_pls);
        var.mKuplukPls  = (CircleButton)findViewById(R.id.kupluk_pls);
        var.mKrudungPls = (CircleButton)findViewById(R.id.krudung_pls);
        var.mPeciPls    = (CircleButton)findViewById(R.id.peci_pls);

        /**BODY**/
        var.mKaosTxt       = (TextView)findViewById(R.id.kaos_txt);
        var.mKaosDalamTxt  = (TextView)findViewById(R.id.kaos_dalam_txt);
        var.mKemejaTxt     = (TextView)findViewById(R.id.kemeja_txt);
        var.mBajuMuslimTxt = (TextView)findViewById(R.id.baju_muslim_txt);
        var.mJaketTxt      = (TextView)findViewById(R.id.jaket_txt);
        var.mSweterTxt     = (TextView)findViewById(R.id.sweter_txt);
        var.mGamisTxt      = (TextView)findViewById(R.id.gamis_txt);
        var.mHandukTxt     = (TextView)findViewById(R.id.handuk_txt);

        var.mKaosMin       = (CircleButton)findViewById(R.id.kaos_min);
        var.mKaosDalamMin  = (CircleButton)findViewById(R.id.kaos_dalam_min);
        var.mKemejaMin     = (CircleButton)findViewById(R.id.kemeja_min);
        var.mBajuMuslimMin = (CircleButton)findViewById(R.id.baju_muslim_min);
        var.mJaketMin      = (CircleButton)findViewById(R.id.jaket_min);
        var.mSweterMin     = (CircleButton)findViewById(R.id.sweter_min);
        var.mGamisMin      = (CircleButton)findViewById(R.id.gamis_min);
        var.mHandukMin     = (CircleButton)findViewById(R.id.handuk_min);

        var.mKaosNum       = (TextView)findViewById(R.id.kaos_num);
        var.mKaosDalamNum  = (TextView)findViewById(R.id.kaos_dalam_num);
        var.mKemejaNum     = (TextView)findViewById(R.id.kemeja_num);
        var.mBajuMuslimNum = (TextView)findViewById(R.id.baju_muslim_num);
        var.mJaketNum      = (TextView)findViewById(R.id.jaket_num);
        var.mSweterNum     = (TextView)findViewById(R.id.sweter_num);
        var.mGamisNum      = (TextView)findViewById(R.id.gamis_num);
        var.mHandukNum     = (TextView)findViewById(R.id.handuk_num);

        var.mKaosPls       = (CircleButton)findViewById(R.id.kaos_pls);
        var.mKaosDalamPls  = (CircleButton)findViewById(R.id.kaos_dalam_pls);
        var.mKemejaPls     = (CircleButton)findViewById(R.id.kemeja_pls);
        var.mBajuMuslimPls = (CircleButton)findViewById(R.id.baju_muslim_pls);
        var.mJaketPls      = (CircleButton)findViewById(R.id.jaket_pls);
        var.mSweterPls     = (CircleButton)findViewById(R.id.sweter_pls);
        var.mGamisPls      = (CircleButton)findViewById(R.id.gamis_pls);
        var.mHandukPls     = (CircleButton)findViewById(R.id.handuk_pls);

        /**HAND**/
        var.mSarungTanganTxt = (TextView)findViewById(R.id.sarung_tangan_txt);
        var.mSapuTanganTxt   = (TextView)findViewById(R.id.sapu_tangan_txt);

        var.mSarungTanganMin = (CircleButton)findViewById(R.id.sarung_tangan_min);
        var.mSapuTanganMin   = (CircleButton)findViewById(R.id.sapu_tangan_min);

        var.mSarungTanganNum = (TextView)findViewById(R.id.sarung_tangan_num);
        var.mSapuTanganNum   = (TextView)findViewById(R.id.sapu_tangan_num);

        var.mSarungTanganPls = (CircleButton)findViewById(R.id.sarung_tangan_pls);
        var.mSapuTanganPls   = (CircleButton)findViewById(R.id.sapu_tangan_pls);

        /**FEET**/
        var.mCelanaTxt          = (TextView)findViewById(R.id.celana_txt);
        var.mCelanaDalamTxt     = (TextView)findViewById(R.id.celana_dalam_txt);
        var.mCelanaPendekTxt    = (TextView)findViewById(R.id.celana_pendek_txt);
        var.mSarungTxt          = (TextView)findViewById(R.id.sarung_txt);
        var.mCelanaOlahragaTxt  = (TextView)findViewById(R.id.celana_olahraga_txt);
        var.mRokTxt             = (TextView)findViewById(R.id.rok_txt);
        var.mCelanaLevisTxt     = (TextView)findViewById(R.id.celana_levis_txt);
        var.mKaosKakiTxt        = (TextView)findViewById(R.id.kaos_kaki_txt);

        var.mCelanaMIn          = (CircleButton)findViewById(R.id.celana_min);
        var.mCelanaDalamMin     = (CircleButton)findViewById(R.id.celana_dalam_min);
        var.mCelanaPendekMin    = (CircleButton)findViewById(R.id.celana_pendek_min);
        var.mSarungMin          = (CircleButton)findViewById(R.id.sarung_min);
        var.mCelanaOlahragaMin  = (CircleButton)findViewById(R.id.celana_olahraga_min);
        var.mRokMin             = (CircleButton)findViewById(R.id.rok_min);
        var.mCelanaLevisMin     = (CircleButton)findViewById(R.id.celana_levis_min);
        var.mKaosKakiMin        = (CircleButton)findViewById(R.id.kaos_kaki_min);

        var.mCelanaNum          = (TextView)findViewById(R.id.celana_num);
        var.mCelanaDalamNum     = (TextView)findViewById(R.id.celana_dalam_num);
        var.mCelanaPendekNum    = (TextView)findViewById(R.id.celana_pendek_num);
        var.mSarungNum          = (TextView)findViewById(R.id.sarung_num);
        var.mCelanaOlahragaNum  = (TextView)findViewById(R.id.celana_olahraga_num);
        var.mRokNum             = (TextView)findViewById(R.id.rok_num);
        var.mCelanaLevisNum     = (TextView)findViewById(R.id.celana_levis_num);
        var.mKaosKakiNum        = (TextView)findViewById(R.id.kaos_kaki_num);

        var.mCelanaPls          = (CircleButton)findViewById(R.id.celana_pls);
        var.mCelanaDalamPls     = (CircleButton)findViewById(R.id.celana_dalam_pls);
        var.mCelanaPendekPls    = (CircleButton)findViewById(R.id.celana_pendek_pls);
        var.mSarungPls          = (CircleButton)findViewById(R.id.sarung_pls);
        var.mCelanaOlahragaPls  = (CircleButton)findViewById(R.id.celana_olahraga_pls);
        var.mRokPls             = (CircleButton)findViewById(R.id.rok_pls);
        var.mCelanaLevisPls     = (CircleButton)findViewById(R.id.celana_levis_pls);
        var.mKaosKakiPls        = (CircleButton)findViewById(R.id.kaos_kaki_pls);

        /**OTHER**/
        var.mJasAlmamaterTxt = (TextView)findViewById(R.id.jas_almamater_txt);
        var.mJasTxt          = (TextView)findViewById(R.id.jas_txt);
        var.mSelimutKecilTxt = (TextView)findViewById(R.id.selimut_kecil_txt);
        var.mSelimutBesarTxt = (TextView)findViewById(R.id.selimut_besar_txt);
        var.mBagCoverTxt     = (TextView)findViewById(R.id.bag_cover_txt);
        var.mGordengKecilTxt = (TextView)findViewById(R.id.gordeng_kecil_txt);
        var.mGordengBesarTxt = (TextView)findViewById(R.id.gordeng_besar_txt);
        var.mSepatuTxt       = (TextView)findViewById(R.id.sepatu_txt);
        var.mBantalTxt       = (TextView)findViewById(R.id.bantal_txt);
        var.mTasKecilTxt     = (TextView)findViewById(R.id.tas_kecil_txt);
        var.mTasBesarTxt     = (TextView)findViewById(R.id.tas_besar_txt);
        var.mSpreiKecilTxt   = (TextView)findViewById(R.id.sprei_kecil_txt);
        var.mSpreiBesarTxt   = (TextView)findViewById(R.id.sprei_besr_txt);

        var.mJasAlmamaterMin = (CircleButton)findViewById(R.id.jas_almamater_min);
        var.mJasMin          = (CircleButton)findViewById(R.id.jas_min);
        var.mSelimutKecilMin = (CircleButton)findViewById(R.id.selimut_kecil_min);
        var.mSelimutBesarMin = (CircleButton)findViewById(R.id.selimut_besar_min);
        var.mBagCoverMin     = (CircleButton)findViewById(R.id.bag_cover_min);
        var.mGordengKecilMIn = (CircleButton)findViewById(R.id.gordeng_kecil_min);
        var.mGordengBesarMin = (CircleButton)findViewById(R.id.gordeng_besar_min);
        var.mSepatuMin       = (CircleButton)findViewById(R.id.sepatu_min);
        var.mBantalMin       = (CircleButton)findViewById(R.id.bantal_min);
        var.mTasKecilMin     = (CircleButton)findViewById(R.id.tas_kecil_min);
        var.mTasBesarMin     = (CircleButton)findViewById(R.id.tas_besar_min);
        var.mSpreiKecilMin   = (CircleButton)findViewById(R.id.sprei_kecil_min);
        var.mSpreiBesarMin   = (CircleButton)findViewById(R.id.sprei_besar_min);

        var.mJasAlmamaterNum = (TextView)findViewById(R.id.jas_almamater_num);
        var.mJasNum          = (TextView)findViewById(R.id.jas_num);
        var.mSelimutKecilNum = (TextView)findViewById(R.id.selimut_kecil_num);
        var.mSelimutBesarNum = (TextView)findViewById(R.id.selimut_besar_num);
        var.mBagCoverNum     = (TextView)findViewById(R.id.bag_cover_num);
        var.mGordengKecilNum = (TextView)findViewById(R.id.gordeng_kecil_num);
        var.mGordengBesarNum = (TextView)findViewById(R.id.gordeng_besar_num);
        var.mSepatuNum       = (TextView)findViewById(R.id.sepatu_num);
        var.mBantalNum       = (TextView)findViewById(R.id.bantal_num);
        var.mTasKecilNum     = (TextView)findViewById(R.id.tas_kecil_num);
        var.mTasBesarNum     = (TextView)findViewById(R.id.tas_besar_num);
        var.mSpreiKecilNum   = (TextView)findViewById(R.id.sprei_kecil_num);
        var.mSpreiBesarNum   = (TextView)findViewById(R.id.sprei_besar_num);

        var.mJasAlmamaterPls = (CircleButton)findViewById(R.id.jas_almamater_pls);
        var.mJasPls          = (CircleButton)findViewById(R.id.jas_pls);
        var.mSelimutKecilPls = (CircleButton)findViewById(R.id.selimut_kecil_pls);
        var.mSelimutBesarPls = (CircleButton)findViewById(R.id.selimut_besar_pls);
        var.mBagCoverPls     = (CircleButton)findViewById(R.id.bag_cover_pls);
        var.mGordengKecilPls = (CircleButton)findViewById(R.id.gordeng_kecil_pls);
        var.mGordengBesarPls = (CircleButton)findViewById(R.id.gordeng_besar_pls);
        var.mSepatuPls       = (CircleButton)findViewById(R.id.sepatu_pls);
        var.mBantalPls       = (CircleButton)findViewById(R.id.bantal_pls);
        var.mTasKecilPls     = (CircleButton)findViewById(R.id.tas_kecil_pls);
        var.mTasBesarPls     = (CircleButton)findViewById(R.id.tas_besar_pls);
        var.mSpreiKecilPls   = (CircleButton)findViewById(R.id.sprei_kecil_pls);
        var.mSpreiBesarPls   = (CircleButton)findViewById(R.id.sprei_besar_pls);

        getNormalTime();
        mForecaseTimeTxt.setText(mNormalTime);



    }

    protected void onDestroy() {
        mOrderPresenterMvp.onDestroy();
        super.onDestroy();
    }

    private void getNormalTime(){
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");
        calendar.add(Calendar.DAY_OF_WEEK, 3);
        mNormalTime = dateFormat.format(calendar.getTime());
    }

    private void getExpressTime(){
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");
        calendar.add(Calendar.DAY_OF_WEEK, 2);
        mExpressTime = dateFormat.format(calendar.getTime());
    }

    private void getKilatTime(){
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        mKilatTime = dateFormat.format(calendar.getTime());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_normal:
                normalBtn.setBackgroundResource(R.drawable.btn_order_background);
                normalBtn.setTextColor(getResources().getColor(R.color.putih));
                expressBtn.setBackgroundResource(R.drawable.btn_order_border);
                expressBtn.setTextColor(getResources().getColor(R.color.biruLaut));
                kilatBtn.setBackgroundResource(R.drawable.btn_order_border);
                kilatBtn.setTextColor(getResources().getColor(R.color.biruLaut));

                getNormalTime();
                mForecaseTimeTxt.setText(mNormalTime);

                break;
            case R.id.button_express:
                normalBtn.setBackgroundResource(R.drawable.btn_order_border);
                normalBtn.setTextColor(getResources().getColor(R.color.biruLaut));
                expressBtn.setBackgroundResource(R.drawable.btn_order_background);
                expressBtn.setTextColor(getResources().getColor(R.color.putih));
                kilatBtn.setBackgroundResource(R.drawable.btn_order_border);
                kilatBtn.setTextColor(getResources().getColor(R.color.biruLaut));

                getExpressTime();
                mForecaseTimeTxt.setText(mExpressTime);
                break;
            case R.id.button_kilat:
                normalBtn.setBackgroundResource(R.drawable.btn_order_border);
                normalBtn.setTextColor(getResources().getColor(R.color.biruLaut));
                expressBtn.setBackgroundResource(R.drawable.btn_order_border);
                expressBtn.setTextColor(getResources().getColor(R.color.biruLaut));
                kilatBtn.setBackgroundResource(R.drawable.btn_order_background);
                kilatBtn.setTextColor(getResources().getColor(R.color.putih));

                getKilatTime();
                mForecaseTimeTxt.setText(mKilatTime);
                break;
            case R.id.confirm_btn:
                showDialog();
                break;
        }
    }

    public void buttonHeadOnclick(View view){
        switch (view.getId()){
            case R.id.bandana_min:
                var.mBandanaInt = var.mBandanaInt - 1;
                if (var.mBandanaInt < 1)
                    var.mBandanaInt = 0;
                var.mBandanaNum.setText(""+var.mBandanaInt);
                break;
            case R.id.bandana_pls:
                var.mBandanaInt = var.mBandanaInt + 1;
                var.mBandanaNum.setText(""+var.mBandanaInt);
                break;

            case R.id.topi_min:
                var.mTopiInt = var.mTopiInt - 1;
                if (var.mTopiInt < 1)
                    var.mTopiInt = 0;
                var.mTopiNum.setText(""+var.mTopiInt);
                break;
            case R.id.topi_pls:
                var.mTopiInt = var.mTopiInt + 1;
                var.mTopiNum.setText(""+var.mTopiInt);
                break;

            case R.id.masker_min:
                var.mMaskerInt = var.mMaskerInt - 1;
                if (var.mMaskerInt < 1)
                    var.mMaskerInt = 0;
                var.mMaskerNum.setText(""+var.mMaskerInt);
                break;
            case R.id.masker_pls:
                var.mMaskerInt = var.mMaskerInt + 1;
                var.mMaskerNum.setText(""+var.mMaskerInt);
                break;

            case R.id.kupluk_min:
                var.mKuplukInt = var.mKuplukInt - 1;
                if (var.mKuplukInt < 1)
                    var.mKuplukInt = 0;
                var.mKuplukNum.setText(""+var.mKuplukInt);
                break;
            case R.id.kupluk_pls:
                var.mKuplukInt = var.mKuplukInt + 1;
                var.mKuplukNum.setText(""+var.mKuplukInt);
                break;

            case R.id.krudung_min:
                var.mKrudungInt = var.mKrudungInt - 1;
                if (var.mKrudungInt < 1)
                    var.mKrudungInt = 0;
                var.mKrudungNum.setText(""+var.mKrudungInt);
                break;
            case R.id.krudung_pls:
                var.mKrudungInt = var.mKrudungInt + 1;
                var.mKrudungNum.setText(""+var.mKrudungInt);
                break;

            case R.id.peci_min:
                var.mPeciInt = var.mPeciInt - 1;
                if (var.mPeciInt < 1)
                    var.mPeciInt = 0;
                var.mPeciNum.setText(""+var.mPeciInt);
                break;
            case R.id.peci_pls:
                var.mPeciInt = var.mPeciInt + 1;
                var.mPeciNum.setText(""+var.mPeciInt);
                break;
        }
    }

    public void buttonBodyOnclick(View view){
        switch (view.getId()){
            case R.id.kaos_min:
                var.mKaosInt = var.mKaosInt - 1;
                if (var.mKaosInt < 1)
                    var.mKaosInt = 0;
                var.mKaosNum.setText(""+var.mKaosInt);
                break;
            case R.id.kaos_pls:
                var.mKaosInt = var.mKaosInt + 1;
                var.mKaosNum.setText(""+var.mKaosInt);
                break;

            case R.id.kaos_dalam_min:
                var.mKaosDalamInt = var.mKaosDalamInt - 1;
                if (var.mKaosDalamInt < 1)
                    var.mKaosDalamInt = 0;
                var.mKaosDalamNum.setText(""+var.mKaosDalamInt);
                break;
            case R.id.kaos_dalam_pls:
                var.mKaosDalamInt = var.mKaosDalamInt + 1;
                var.mKaosDalamNum.setText(""+var.mKaosDalamInt);
                break;

            case R.id.kemeja_min:
                var.mKemejaInt = var.mKemejaInt - 1;
                if (var.mKemejaInt < 1)
                    var.mKemejaInt = 0;
                var.mKemejaNum.setText(""+var.mKemejaInt);
                break;
            case R.id.kemeja_pls:
                var.mKemejaInt = var.mKemejaInt + 1;
                var.mKemejaNum.setText(""+var.mKemejaInt);
                break;

            case R.id.baju_muslim_min:
                var.mBajuMuslimInt = var.mBajuMuslimInt - 1;
                if (var.mBajuMuslimInt < 1)
                    var.mBajuMuslimInt = 0;
                var.mBajuMuslimNum.setText(""+var.mBajuMuslimInt);
                break;
            case R.id.baju_muslim_pls:
                var.mBajuMuslimInt = var.mBajuMuslimInt + 1;
                var.mBajuMuslimNum.setText(""+var.mBajuMuslimInt);
                break;

            case R.id.jaket_min:
                var.mJaketInt = var.mJaketInt - 1;
                if (var.mJaketInt < 1)
                    var.mJaketInt = 0;
                var.mJaketNum.setText(""+var.mJaketInt);
                break;
            case R.id.jaket_pls:
                var.mJaketInt = var.mJaketInt + 1;
                var.mJaketNum.setText(""+var.mJaketInt);
                break;

            case R.id.sweter_min:
                var.mSweterInt = var.mSweterInt - 1;
                if (var.mSweterInt < 1)
                    var.mSweterInt = 0;
                var.mSweterNum.setText(""+var.mSweterInt);
                break;
            case R.id.sweter_pls:
                var.mSweterInt = var.mSweterInt + 1;
                var.mSweterNum.setText(""+var.mSweterInt);
                break;

            case R.id.gamis_min:
                var.mGamisInt = var.mGamisInt - 1;
                if (var.mGamisInt < 1)
                    var.mGamisInt = 0;
                var.mGamisNum.setText(""+var.mGamisInt);
                break;
            case R.id.gamis_pls:
                var.mGamisInt = var.mGamisInt + 1;
                var.mGamisNum.setText(""+var.mGamisInt);
                break;

            case R.id.handuk_min:
                var.mHandukInt = var.mHandukInt - 1;
                if (var.mHandukInt < 1)
                    var.mHandukInt = 0;
                var.mHandukNum.setText(""+var.mHandukInt);
                break;
            case R.id.handuk_pls:
                var.mHandukInt = var.mHandukInt + 1;
                var.mHandukNum.setText(""+var.mHandukInt);
                break;
        }
    }

    public void buttonHandOnclick(View view){
        switch (view.getId()){
            case R.id.sarung_tangan_min:
                var.mSarungTanganInt = var.mSarungTanganInt - 1;
                if (var.mSarungTanganInt < 1)
                    var.mSarungTanganInt = 0;
                var.mSarungTanganNum.setText(""+var.mSarungTanganInt);
                break;
            case R.id.sarung_tangan_pls:
                var.mSarungTanganInt = var.mSarungTanganInt + 1;
                var.mSarungTanganNum.setText(""+var.mSarungTanganInt);
                break;

            case R.id.sapu_tangan_min:
                var.mSapuTanganInt = var.mSapuTanganInt - 1;
                if (var.mSapuTanganInt < 1)
                    var.mSapuTanganInt = 0;
                var.mSapuTanganNum.setText(""+var.mSapuTanganInt);
                break;
            case R.id.sapu_tangan_pls:
                var.mSapuTanganInt = var.mSapuTanganInt + 1;
                var.mSapuTanganNum.setText(""+var.mSapuTanganInt);
                break;
        }
    }

    public void buttonFeetOnclick(View view){
        switch (view.getId()){
            case R.id.celana_min:
                var.mCelanaInt = var.mCelanaInt - 1;
                if (var.mCelanaInt < 1)
                    var.mCelanaInt = 0;
                var.mCelanaNum.setText(""+var.mCelanaInt);
                break;
            case R.id.celana_pls:
                var.mCelanaInt = var.mCelanaInt + 1;
                var.mCelanaNum.setText(""+var.mCelanaInt);
                break;

            case R.id.celana_dalam_min:
                var.mCelanaDalamInt = var.mCelanaDalamInt - 1;
                if (var.mCelanaDalamInt < 1)
                    var.mCelanaDalamInt = 0;
                var.mCelanaDalamNum.setText(""+var.mCelanaDalamInt);
                break;
            case R.id.celana_dalam_pls:
                var.mCelanaDalamInt = var.mCelanaDalamInt + 1;
                var.mCelanaDalamNum.setText(""+var.mCelanaDalamInt);
                break;

            case R.id.celana_pendek_min:
                var.mCelanaPendekInt = var.mCelanaPendekInt - 1;
                if (var.mCelanaPendekInt < 1)
                    var.mCelanaPendekInt = 0;
                var.mCelanaPendekNum.setText(""+var.mCelanaPendekInt);
                break;
            case R.id.celana_pendek_pls:
                var.mCelanaPendekInt = var.mCelanaPendekInt + 1;
                var.mCelanaPendekNum.setText(""+var.mCelanaPendekInt);
                break;

            case R.id.sarung_min:
                var.mSarungInt = var.mSarungInt - 1;
                if (var.mSarungInt < 1)
                    var.mSarungInt = 0;
                var.mSarungNum.setText(""+var.mSarungInt);
                break;
            case R.id.sarung_pls:
                var.mSarungInt = var.mSarungInt + 1;
                var.mSarungNum.setText(""+var.mSarungInt);
                break;

            case R.id.celana_olahraga_min:
                var.mCelanaOlahragaInt = var.mCelanaOlahragaInt - 1;
                if (var.mCelanaOlahragaInt < 1)
                    var.mCelanaOlahragaInt = 0;
                var.mCelanaOlahragaNum.setText(""+var.mCelanaOlahragaInt);
                break;
            case R.id.celana_olahraga_pls:
                var.mCelanaOlahragaInt = var.mCelanaOlahragaInt + 1;
                var.mCelanaOlahragaNum.setText(""+var.mCelanaOlahragaInt);
                break;

            case R.id.rok_min:
                var.mRokInt = var.mRokInt - 1;
                if (var.mRokInt < 1)
                    var.mRokInt = 0;
                var.mRokNum.setText(""+var.mRokInt);
                break;
            case R.id.rok_pls:
                var.mRokInt = var.mRokInt + 1;
                var.mRokNum.setText(""+var.mRokInt);
                break;

            case R.id.celana_levis_min:
                var.mCelanaLevisInt = var.mCelanaLevisInt - 1;
                if (var.mCelanaLevisInt < 1)
                    var.mCelanaLevisInt = 0;
                var.mCelanaLevisNum.setText(""+var.mCelanaLevisInt);
                break;
            case R.id.celana_levis_pls:
                var.mCelanaLevisInt = var.mCelanaLevisInt + 1;
                var.mCelanaLevisNum.setText(""+var.mCelanaLevisInt);
                break;

            case R.id.kaos_kaki_min:
                var.mKaosKakiInt = var.mKaosKakiInt - 1;
                if (var.mKaosKakiInt < 1)
                    var.mKaosKakiInt = 0;
                var.mKaosKakiNum.setText(""+var.mKaosKakiInt);
                break;
            case R.id.kaos_kaki_pls:
                var.mKaosKakiInt = var.mKaosKakiInt + 1;
                var.mKaosKakiNum.setText(""+var.mKaosKakiInt);
                break;
        }
    }

    public void buttonOtherOnclick(View view){
        switch (view.getId()){
            case R.id.jas_almamater_min:
                var.mJasAlmamaterInt = var.mJasAlmamaterInt - 1;
                if (var.mJasAlmamaterInt < 1)
                    var.mJasAlmamaterInt = 0;
                var.mJasAlmamaterNum.setText(""+var.mJasAlmamaterInt);
                break;
            case R.id.jas_almamater_pls:
                var.mJasAlmamaterInt = var.mJasAlmamaterInt + 1;
                var.mJasAlmamaterNum.setText(""+var.mJasAlmamaterInt);
                break;

            case R.id.jas_min:
                var.mJasInt = var.mJasInt - 1;
                if (var.mJasInt < 1)
                    var.mJasInt = 0;
                var.mJasNum.setText(""+var.mJasInt);
                break;
            case R.id.jas_pls:
                var.mJasInt = var.mJasInt + 1;
                var.mJasNum.setText(""+var.mJasInt);
                break;

            case R.id.selimut_kecil_min:
                var.mSelimutKecilInt = var.mSelimutKecilInt - 1;
                if (var.mSelimutKecilInt < 1)
                    var.mSelimutKecilInt = 0;
                var.mSelimutKecilNum.setText(""+var.mSelimutKecilInt);
                break;
            case R.id.selimut_kecil_pls:
                var.mSelimutKecilInt = var.mSelimutKecilInt + 1;
                var.mSelimutKecilNum.setText(""+var.mSelimutKecilInt);
                break;

            case R.id.selimut_besar_min:
                var.mSelimutBesarInt = var.mSelimutBesarInt - 1;
                if (var.mSelimutBesarInt < 1)
                    var.mSelimutBesarInt = 0;
                var.mSelimutBesarNum.setText(""+var.mSelimutBesarInt);
                break;
            case R.id.selimut_besar_pls:
                var.mSelimutBesarInt = var.mSelimutBesarInt + 1;
                var.mSelimutBesarNum.setText(""+var.mSelimutBesarInt);
                break;

            case R.id.bag_cover_min:
                var.mBagCoverInt = var.mBagCoverInt - 1;
                if (var.mBagCoverInt < 1)
                    var.mBagCoverInt = 0;
                var.mBagCoverNum.setText(""+var.mBagCoverInt);
                break;
            case R.id.bag_cover_pls:
                var.mBagCoverInt = var.mBagCoverInt + 1;
                var.mBagCoverNum.setText(""+var.mBagCoverInt);
                break;

            case R.id.gordeng_kecil_min:
                var.mGordengKecilInt = var.mGordengKecilInt - 1;
                if (var.mGordengKecilInt < 1)
                    var.mGordengKecilInt = 0;
                var.mGordengKecilNum.setText(""+var.mGordengKecilInt);
                break;
            case R.id.gordeng_kecil_pls:
                var.mGordengKecilInt = var.mGordengKecilInt + 1;
                var.mGordengKecilNum.setText(""+var.mGordengKecilInt);
                break;

            case R.id.gordeng_besar_min:
                var.mGordengBesarInt = var.mGordengBesarInt - 1;
                if (var.mGordengBesarInt < 1)
                    var.mGordengBesarInt = 0;
                var.mGordengBesarNum.setText(""+var.mGordengBesarInt);
                break;
            case R.id.gordeng_besar_pls:
                var.mGordengBesarInt = var.mGordengBesarInt + 1;
                var.mGordengBesarNum.setText(""+var.mGordengBesarInt);
                break;

            case R.id.sepatu_min:
                var.mSepatuInt = var.mSepatuInt - 1;
                if (var.mSepatuInt < 1)
                    var.mSepatuInt = 0;
                var.mSepatuNum.setText(""+var.mSepatuInt);
                break;
            case R.id.sepatu_pls:
                var.mSepatuInt = var.mSepatuInt + 1;
                var.mSepatuNum.setText(""+var.mSepatuInt);
                break;

            case R.id.bantal_min:
                var.mBantalInt = var.mBantalInt - 1;
                if (var.mBantalInt < 1)
                    var.mBantalInt = 0;
                var.mBantalNum.setText(""+var.mBantalInt);
                break;
            case R.id.bantal_pls:
                var.mBantalInt = var.mBantalInt + 1;
                var.mBantalNum.setText(""+var.mBantalInt);
                break;

            case R.id.tas_kecil_min:
                var.mTasKecilInt = var.mTasKecilInt - 1;
                if (var.mTasKecilInt < 1)
                    var.mTasKecilInt = 0;
                var.mTasKecilNum.setText(""+var.mTasKecilInt);
                break;
            case R.id.tas_kecil_pls:
                var.mTasKecilInt = var.mTasKecilInt + 1;
                var.mTasKecilNum.setText(""+var.mTasKecilInt);
                break;

            case R.id.tas_besar_min:
                var.mTasBesarInt = var.mTasBesarInt - 1;
                if (var.mTasBesarInt < 1)
                    var.mTasBesarInt = 0;
                var.mTasBesarNum.setText(""+var.mTasBesarInt);
                break;
            case R.id.tas_besar_pls:
                var.mTasBesarInt = var.mTasBesarInt + 1;
                var.mTasBesarNum.setText(""+var.mTasBesarInt);
                break;

            case R.id.sprei_kecil_min:
                var.mSpreiKecilInt = var.mSpreiKecilInt - 1;
                if (var.mSpreiKecilInt < 1)
                    var.mSpreiKecilInt = 0;
                var.mSpreiKecilNum.setText(""+var.mSpreiKecilInt);
                break;
            case R.id.sprei_kecil_pls:
                var.mSpreiKecilInt = var.mSpreiKecilInt + 1;
                var.mSpreiKecilNum.setText(""+var.mSpreiKecilInt);
                break;

            case R.id.sprei_besar_min:
                var.mSpreiBesarInt = var.mSpreiBesarInt - 1;
                if (var.mSpreiBesarInt < 1)
                    var.mSpreiBesarInt = 0;
                var.mSpreiBesarNum.setText(""+var.mSpreiBesarInt);
                break;
            case R.id.sprei_besar_pls:
                var.mSpreiBesarInt = var.mSpreiBesarInt + 1;
                var.mSpreiBesarNum.setText(""+var.mSpreiBesarInt);
                break;
        }
    }

    private class Var{
        /**CLOSTHES VAR**/
        private TextView mBandanaTxt, mTopiTxt, mMaskerTxt, mKuplukTxt, mKrudungTxt, mPeciTxt;
        private CircleButton mBandanaMin, mTopiMin, mMaskerMin, mKuplukMin, mKrudungMin, mPeciMin;
        private TextView mBandanaNum, mTopiNum, mMaskerNum, mKuplukNum, mKrudungNum, mPeciNum;
        private CircleButton mBandanaPls, mTopiPls, mMaskerPls, mKuplukPls, mKrudungPls, mPeciPls;
        private int mBandanaInt, mTopiInt, mMaskerInt, mKuplukInt, mKrudungInt, mPeciInt = 0;

        /**BODY VAR**/
        private TextView mKaosTxt, mKaosDalamTxt, mKemejaTxt, mBajuMuslimTxt, mJaketTxt, mSweterTxt, mGamisTxt, mHandukTxt;
        private CircleButton mKaosMin, mKaosDalamMin, mKemejaMin, mBajuMuslimMin, mJaketMin, mSweterMin, mGamisMin, mHandukMin;
        private TextView mKaosNum, mKaosDalamNum, mKemejaNum, mBajuMuslimNum, mJaketNum, mSweterNum, mGamisNum, mHandukNum;
        private CircleButton mKaosPls, mKaosDalamPls, mKemejaPls, mBajuMuslimPls, mJaketPls, mSweterPls, mGamisPls, mHandukPls;
        private int mKaosInt, mKaosDalamInt, mKemejaInt, mBajuMuslimInt, mJaketInt, mSweterInt, mGamisInt, mHandukInt;

        /**HAND VAR**/
        private TextView mSarungTanganTxt, mSapuTanganTxt;
        private CircleButton mSarungTanganMin, mSapuTanganMin;
        private TextView  mSarungTanganNum, mSapuTanganNum;
        private CircleButton mSarungTanganPls, mSapuTanganPls;
        private int mSarungTanganInt, mSapuTanganInt;

        /**FEET VAR**/
        private TextView mCelanaTxt, mCelanaDalamTxt, mCelanaPendekTxt, mSarungTxt, mCelanaOlahragaTxt, mRokTxt, mCelanaLevisTxt, mKaosKakiTxt;
        private CircleButton mCelanaMIn, mCelanaDalamMin, mCelanaPendekMin, mSarungMin, mCelanaOlahragaMin, mRokMin, mCelanaLevisMin, mKaosKakiMin;
        private TextView mCelanaNum, mCelanaDalamNum, mCelanaPendekNum, mSarungNum, mCelanaOlahragaNum, mRokNum, mCelanaLevisNum, mKaosKakiNum;
        private CircleButton mCelanaPls, mCelanaDalamPls, mCelanaPendekPls, mSarungPls, mCelanaOlahragaPls, mRokPls, mCelanaLevisPls, mKaosKakiPls;
        private int mCelanaInt, mCelanaDalamInt, mCelanaPendekInt, mSarungInt, mCelanaOlahragaInt, mRokInt, mCelanaLevisInt, mKaosKakiInt;

        /**OTHER VAR**/
        private TextView mJasAlmamaterTxt, mJasTxt, mSelimutKecilTxt, mSelimutBesarTxt, mBagCoverTxt, mGordengKecilTxt, mGordengBesarTxt, mSepatuTxt, mBantalTxt, mTasKecilTxt, mTasBesarTxt, mSpreiKecilTxt, mSpreiBesarTxt;
        private CircleButton mJasAlmamaterMin, mJasMin, mSelimutKecilMin, mSelimutBesarMin, mBagCoverMin, mGordengKecilMIn, mGordengBesarMin, mSepatuMin, mBantalMin, mTasKecilMin, mTasBesarMin, mSpreiKecilMin, mSpreiBesarMin;
        private TextView mJasAlmamaterNum, mJasNum, mSelimutKecilNum, mSelimutBesarNum, mBagCoverNum, mGordengKecilNum, mGordengBesarNum, mSepatuNum, mBantalNum, mTasKecilNum, mTasBesarNum, mSpreiKecilNum, mSpreiBesarNum;
        private CircleButton mJasAlmamaterPls, mJasPls, mSelimutKecilPls, mSelimutBesarPls, mBagCoverPls, mGordengKecilPls, mGordengBesarPls, mSepatuPls, mBantalPls, mTasKecilPls, mTasBesarPls, mSpreiKecilPls, mSpreiBesarPls;
        private int mJasAlmamaterInt, mJasInt, mSelimutKecilInt, mSelimutBesarInt, mBagCoverInt, mGordengKecilInt, mGordengBesarInt, mSepatuInt, mBantalInt, mTasKecilInt, mTasBesarInt, mSpreiKecilInt, mSpreiBesarInt;
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin jumlah pakaian benar ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Tekan Ya untuk melanjutkan!")
                //.setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        final String desc      = mDescTxt.getText().toString();
                        final String time      = mForecaseTimeTxt.getText().toString();

                        final String bandana   = String.valueOf(var.mBandanaInt);
                        final String topi      = String.valueOf(var.mTopiInt);
                        final String masker    = String.valueOf(var.mMaskerInt);
                        final String kupluk    = String.valueOf(var.mKuplukInt);
                        final String krudung   = String.valueOf(var.mKrudungInt);
                        final String peci      = String.valueOf(var.mPeciInt);

                        final String kaos      = String.valueOf(var.mKaosInt);
                        final String kaosDalam = String.valueOf(var.mKaosDalamInt);
                        final String kemeja    = String.valueOf(var.mKemejaInt);
                        final String bajuMuslim= String.valueOf(var.mBajuMuslimInt);
                        final String jaket     = String.valueOf(var.mJaketInt);
                        final String sweter    = String.valueOf(var.mSweterInt);
                        final String gamis     = String.valueOf(var.mGamisInt);
                        final String handuk    = String.valueOf(var.mHandukInt);

                        final String sarungTangan      = String.valueOf(var.mSarungTanganInt);
                        final String sapuTangan = String.valueOf(var.mSapuTanganInt);

                        final String celana         = String.valueOf(var.mCelanaInt);
                        final String celanaDalam    = String.valueOf(var.mCelanaDalamInt);
                        final String celanaPendek   = String.valueOf(var.mCelanaPendekInt);
                        final String sarung         = String.valueOf(var.mSarungInt);
                        final String celanaOlahraga = String.valueOf(var.mCelanaOlahragaInt);
                        final String rok            = String.valueOf(var.mRokInt);
                        final String celanaLevis    = String.valueOf(var.mCelanaLevisInt);
                        final String kaosKaki       = String.valueOf(var.mKaosKakiInt);

                        final String jasAlmamater = String.valueOf(var.mJasAlmamaterInt);
                        final String jas          = String.valueOf(var.mJasInt);
                        final String selimutKecil = String.valueOf(var.mSelimutKecilInt);
                        final String selimutBesar = String.valueOf(var.mSelimutBesarInt);
                        final String bagCover     = String.valueOf(var.mBagCoverInt);
                        final String gordengKecil = String.valueOf(var.mGordengKecilInt);
                        final String gordengBesar = String.valueOf(var.mGordengBesarInt);
                        final String sepatu       = String.valueOf(var.mSepatuInt);
                        final String bantal       = String.valueOf(var.mBantalInt);
                        final String tasKecil     = String.valueOf(var.mTasKecilInt);
                        final String tasBesar     = String.valueOf(var.mTasBesarInt);
                        final String spreiKecil   = String.valueOf(var.mSpreiKecilInt);
                        final String spreiBesar   = String.valueOf(var.mSpreiBesarInt);

                        //toast();

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            // User is signed in
                            mOrderPresenterMvp.validateInputs(desc,time,
                                    bandana,topi,masker,kupluk,krudung,peci,
                                    kaos,kaosDalam,kemeja,bajuMuslim,jaket,sweter,gamis,handuk,
                                    sarungTangan,sapuTangan,
                                    celana,celanaDalam,celanaPendek,sarung,celanaOlahraga,rok,celanaLevis,kaosKaki,
                                    jasAlmamater,jas,selimutKecil,selimutBesar,bagCover,gordengKecil,gordengBesar,sepatu,bantal,tasKecil,tasBesar,spreiKecil,spreiBesar);
                        } else {
                            // No user is signed in
                        }
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    public void toast(){
        Toast.makeText(OrderActivity.this,"pass kosong : " + var.mBandanaInt , Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void navigateToMainScreen(){
        //startActivity(new Intent(this, MainActivity.class));
        showMessage("masuk navigate");
    }
}
