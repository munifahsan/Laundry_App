package com.tiunida.laundry0.order.biasa.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.main.MainActivity;
import com.tiunida.laundry0.order.biasa.BiasaPresenter;
import com.tiunida.laundry0.order.biasa.BiasaPresenterMvp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BiasaActivity extends AppCompatActivity implements BiasaViewMvp {

    private EditText mDescTxt;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    public String mDoneTime, mNowTime, mUniqNumByTime;
    private String user_id;

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private Var var;

    private BiasaPresenterMvp mBiasaPresenterMvp;

    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;

    @BindView(R.id.kilat_progress)
    ProgressBar progressBar;

    @BindView(R.id.tipeLaundryValue)
    TextView mTipeLaundryValue;
//    @BindView(R.id.waktuPemesananValue)
//    TextView mWaktuPesananValue;
    @BindView(R.id.waktuSelesaiValue)
    TextView mWaktuSelesaiValue;
    @BindView(R.id.alamatPengirimanValueDormitory)
    TextView mAlamatPengirimanValueDormitory;
    @BindView(R.id.alamatPengirimanValueRoom)
    TextView mAlamatPengirimanValueRoom;

    @BindView(R.id.bandana_min)
    CircleButton mBandanaMin;
    @BindView(R.id.topi_min)
    CircleButton mTopiMin;
    @BindView(R.id.masker_min)
    CircleButton mMaskerMin;
    @BindView(R.id.kupluk_min)
    CircleButton mKuplukMin;
    @BindView(R.id.krudung_min)
    CircleButton mKrudungMin;
    @BindView(R.id.peci_min)
    CircleButton mPeciMin;
    @BindView(R.id.bandana_num)
    TextView mBandanaNum;
    @BindView(R.id.topi_num)
    TextView mTopiNum;
    @BindView(R.id.masker_num)
    TextView mMaskerNum;
    @BindView(R.id.kupluk_num)
    TextView mKuplukNum;
    @BindView(R.id.krudung_num)
    TextView mKrudungNum;
    @BindView(R.id.peci_num)
    TextView mPeciNum;
    @BindView(R.id.bandana_pls)
    CircleButton mBandanaPls;
    @BindView(R.id.topi_pls)
    CircleButton mTopiPls;
    @BindView(R.id.masker_pls)
    CircleButton mMaskerPls;
    @BindView(R.id.kupluk_pls)
    CircleButton mKuplukPls;
    @BindView(R.id.krudung_pls)
    CircleButton mKrudungPls;
    @BindView(R.id.peci_pls)
    CircleButton mPeciPls;
    private int mBandanaInt, mTopiInt, mMaskerInt, mKuplukInt, mKrudungInt, mPeciInt = 0;


    @BindView(R.id.kaos_min)
    CircleButton mKaosMin;
    @BindView(R.id.kaos_dalam_min)
    CircleButton mKaosDalamMin;
    @BindView(R.id.kemeja_min)
    CircleButton mKemejaMin;
    @BindView(R.id.baju_muslim_min)
    CircleButton mBajuMuslimMin;
    @BindView(R.id.jaket_min)
    CircleButton mJaketMin;
    @BindView(R.id.sweter_min)
    CircleButton mSweterMin;
    @BindView(R.id.gamis_min)
    CircleButton mGamisMin;
    @BindView(R.id.handuk_min)
    CircleButton mHandukMin;
    @BindView(R.id.kaos_num)
    TextView mKaosNum;
    @BindView(R.id.kaos_dalam_num)
    TextView mKaosDalamNum;
    @BindView(R.id.kemeja_num)
    TextView mKemejaNum;
    @BindView(R.id.baju_muslim_num)
    TextView mBajuMuslimNum;
    @BindView(R.id.jaket_num)
    TextView mJaketNum;
    @BindView(R.id.sweter_num)
    TextView mSweterNum;
    @BindView(R.id.gamis_num)
    TextView mGamisNum;
    @BindView(R.id.handuk_num)
    TextView mHandukNum;
    @BindView(R.id.kaos_pls)
    CircleButton mKaosPls;
    @BindView(R.id.kaos_dalam_pls)
    CircleButton mKaosDalamPls;
    @BindView(R.id.kemeja_pls)
    CircleButton mKemejaPls;
    @BindView(R.id.baju_muslim_pls)
    CircleButton mBajuMuslimPls;
    @BindView(R.id.jaket_pls)
    CircleButton mJaketPls;
    @BindView(R.id.sweter_pls)
    CircleButton mSweterPls;
    @BindView(R.id.gamis_pls)
    CircleButton mGamisPls;
    @BindView(R.id.handuk_pls)
    CircleButton mHandukPls;
    private int mKaosInt, mKaosDalamInt, mKemejaInt, mBajuMuslimInt, mJaketInt, mSweterInt, mGamisInt, mHandukInt;

    @BindView(R.id.sarung_tangan_min)
    CircleButton mSarungTanganMin;
    @BindView(R.id.sapu_tangan_min)
    CircleButton mSapuTanganMin;
    @BindView(R.id.sarung_tangan_num)
    TextView mSarungTanganNum;
    @BindView(R.id.sapu_tangan_num)
    TextView mSapuTanganNum;
    @BindView(R.id.sarung_tangan_pls)
    CircleButton mSarungTanganPls;
    @BindView(R.id.sapu_tangan_pls)
    CircleButton mSapuTanganPls;
    private int mSarungTanganInt, mSapuTanganInt;

    @BindView(R.id.celana_min)
    CircleButton mCelanaMIn;
    @BindView(R.id.celana_dalam_min)
    CircleButton mCelanaDalamMin;
    @BindView(R.id.celana_pendek_min)
    CircleButton mCelanaPendekMin;
    @BindView(R.id.sarung_min)
    CircleButton mSarungMin;
    @BindView(R.id.celana_olahraga_min)
    CircleButton mCelanaOlahragaMin;
    @BindView(R.id.rok_min)
    CircleButton mRokMin;
    @BindView(R.id.celana_levis_min)
    CircleButton mCelanaLevisMin;
    @BindView(R.id.kaos_kaki_min)
    CircleButton mKaosKakiMin;
    @BindView(R.id.celana_num)
    TextView mCelanaNum;
    @BindView(R.id.celana_dalam_num)
    TextView mCelanaDalamNum;
    @BindView(R.id.celana_pendek_num)
    TextView mCelanaPendekNum;
    @BindView(R.id.sarung_num)
    TextView mSarungNum;
    @BindView(R.id.celana_olahraga_num)
    TextView mCelanaOlahragaNum;
    @BindView(R.id.rok_num)
    TextView mRokNum;
    @BindView(R.id.celana_levis_num)
    TextView mCelanaLevisNum;
    @BindView(R.id.kaos_kaki_num)
    TextView mKaosKakiNum;
    @BindView(R.id.celana_pls)
    CircleButton mCelanaPls;
    @BindView(R.id.celana_dalam_pls)
    CircleButton mCelanaDalamPls;
    @BindView(R.id.celana_pendek_pls)
    CircleButton mCelanaPendekPls;
    @BindView(R.id.sarung_pls)
    CircleButton mSarungPls;
    @BindView(R.id.celana_olahraga_pls)
    CircleButton mCelanaOlahragaPls;
    @BindView(R.id.rok_pls)
    CircleButton mRokPls;
    @BindView(R.id.celana_levis_pls)
    CircleButton mCelanaLevisPls;
    @BindView(R.id.kaos_kaki_pls)
    CircleButton mKaosKakiPls;
    private int mCelanaInt, mCelanaDalamInt, mCelanaPendekInt, mSarungInt, mCelanaOlahragaInt, mRokInt, mCelanaLevisInt, mKaosKakiInt;

    @BindView(R.id.jas_almamater_min)
    CircleButton mJasAlmamaterMin;
    @BindView(R.id.jas_min)
    CircleButton mJasMin;
    @BindView(R.id.selimut_kecil_min)
    CircleButton mSelimutKecilMin;
    @BindView(R.id.selimut_besar_min)
    CircleButton mSelimutBesarMin;
    @BindView(R.id.bag_cover_min)
    CircleButton mBagCoverMin;
    @BindView(R.id.gordeng_kecil_min)
    CircleButton mGordengKecilMIn;
    @BindView(R.id.gordeng_besar_min)
    CircleButton mGordengBesarMin;
    @BindView(R.id.sepatu_min)
    CircleButton mSepatuMin;
    @BindView(R.id.bantal_min)
    CircleButton mBantalMin;
    @BindView(R.id.tas_kecil_min)
    CircleButton mTasKecilMin;
    @BindView(R.id.tas_besar_min)
    CircleButton mTasBesarMin;
    @BindView(R.id.sprei_kecil_min)
    CircleButton mSpreiKecilMin;
    @BindView(R.id.sprei_besar_min)
    CircleButton mSpreiBesarMin;
    @BindView(R.id.jas_almamater_num)
    TextView mJasAlmamaterNum;
    @BindView(R.id.jas_num)
    TextView mJasNum;
    @BindView(R.id.selimut_kecil_num)
    TextView mSelimutKecilNum;
    @BindView(R.id.selimut_besar_num)
    TextView mSelimutBesarNum;
    @BindView(R.id.bag_cover_num)
    TextView mBagCoverNum;
    @BindView(R.id.gordeng_kecil_num)
    TextView mGordengKecilNum;
    @BindView(R.id.gordeng_besar_num)
    TextView mGordengBesarNum;
    @BindView(R.id.sepatu_num)
    TextView mSepatuNum;
    @BindView(R.id.bantal_num)
    TextView mBantalNum;
    @BindView(R.id.tas_kecil_num)
    TextView mTasKecilNum;
    @BindView(R.id.tas_besar_num)
    TextView mTasBesarNum;
    @BindView(R.id.sprei_kecil_num)
    TextView mSpreiKecilNum;
    @BindView(R.id.sprei_besar_num)
    TextView mSpreiBesarNum;
    @BindView(R.id.jas_almamater_pls)
    CircleButton mJasAlmamaterPls;
    @BindView(R.id.jas_pls)
    CircleButton mJasPls;
    @BindView(R.id.selimut_kecil_pls)
    CircleButton mSelimutKecilPls;
    @BindView(R.id.selimut_besar_pls)
    CircleButton mSelimutBesarPls;
    @BindView(R.id.bag_cover_pls)
    CircleButton mBagCoverPls;
    @BindView(R.id.gordeng_kecil_pls)
    CircleButton mGordengKecilPls;
    @BindView(R.id.gordeng_besar_pls)
    CircleButton mGordengBesarPls;
    @BindView(R.id.sepatu_pls)
    CircleButton mSepatuPls;
    @BindView(R.id.bantal_pls)
    CircleButton mBantalPls;
    @BindView(R.id.tas_kecil_pls)
    CircleButton mTasKecilPls;
    @BindView(R.id.tas_besar_pls)
    CircleButton mTasBesarPls;
    @BindView(R.id.sprei_kecil_pls)
    CircleButton mSpreiKecilPls;
    @BindView(R.id.sprei_besar_pls)
    CircleButton mSpreiBesarPls;
    private int mJasAlmamaterInt, mJasInt, mSelimutKecilInt, mSelimutBesarInt, mBagCoverInt, mGordengKecilInt, mGordengBesarInt, mSepatuInt, mBantalInt, mTasKecilInt, mTasBesarInt, mSpreiKecilInt, mSpreiBesarInt;

    @BindView(R.id.rel1)
    RelativeLayout rel1;
    @BindView(R.id.rel2)
    RelativeLayout rel2;
    @BindView(R.id.rel3)
    RelativeLayout rel3;
    @BindView(R.id.rel4)
    RelativeLayout rel4;
    @BindView(R.id.rel5)
    RelativeLayout rel5;
    @BindView(R.id.rel6)
    RelativeLayout rel6;
    @BindView(R.id.rel7)
    RelativeLayout rel7;
    @BindView(R.id.rel8)
    RelativeLayout rel8;
    @BindView(R.id.rel9)
    RelativeLayout rel9;
    @BindView(R.id.rel11)
    RelativeLayout rel11;
    @BindView(R.id.rel12)
    RelativeLayout rel12;
    @BindView(R.id.rel13)
    RelativeLayout rel13;
    @BindView(R.id.rel14)
    RelativeLayout rel14;
    @BindView(R.id.rel15)
    RelativeLayout rel15;
    @BindView(R.id.rel10)
    RelativeLayout rel10;

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.txt5)
    TextView txt5;
    @BindView(R.id.txt6)
    TextView txt6;
    @BindView(R.id.txt7)
    TextView txt7;
    @BindView(R.id.txt8)
    TextView txt8;
    @BindView(R.id.txt9)
    TextView txt9;
    @BindView(R.id.txt10)
    TextView txt10;
    @BindView(R.id.txt11)
    TextView txt11;
    @BindView(R.id.txt12)
    TextView txt12;
    @BindView(R.id.txt13)
    TextView txt13;
    @BindView(R.id.txt14)
    TextView txt14;
    @BindView(R.id.txt15)
    TextView txt15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biasa);

        mBiasaPresenterMvp = new BiasaPresenter(this);
        mBiasaPresenterMvp.onCreate();

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();

        ButterKnife.bind(this);

        mDescTxt = (EditText) findViewById(R.id.desc_txt);
        mDescTxt.setMovementMethod(new ScrollingMovementMethod());

        getDoneTime();
        mWaktuSelesaiValue.setText(mDoneTime);

        getDataProfile();
    }

    @Override
    public void getDataProfile() {
        mBiasaPresenterMvp.getData();
    }

    @Override
    public void setRoomDormitory(String room, String dormitory) {
        mAlamatPengirimanValueDormitory.setText(dormitory);
        mAlamatPengirimanValueRoom.setText(room);
    }

    @Override
    public void setTextAkad1(String text) {
        Log.d("akad1 activity",""+text);
        txt1.setText(text);
    }

    @Override
    public void setTextAkad2(String text) {
        txt2.setText(text);
    }

    @Override
    public void setTextAkad3(String text) {
        txt3.setText(text);
    }

    @Override
    public void setTextAkad4(String text) {
        txt4.setText(text);
    }

    @Override
    public void setTextAkad5(String text) {
        txt5.setText(text);
    }

    @Override
    public void setTextAkad6(String text) {
        txt6.setText(text);
    }

    @Override
    public void setTextAkad7(String text) {
        txt7.setText(text);
    }

    @Override
    public void setTextAkad8(String text) {
        txt8.setText(text);
    }

    @Override
    public void setTextAkad9(String text) {
        txt9.setText(text);
    }

    @Override
    public void setTextAkad10(String text) {
        txt10.setText(text);
    }

    @Override
    public void setTextAkad11(String text) {
        txt11.setText(text);
    }

    @Override
    public void setTextAkad12(String text) {
        txt12.setText(text);
    }

    @Override
    public void setTextAkad13(String text) {
        txt13.setText(text);
    }

    @Override
    public void setTextAkad14(String text) {
        txt14.setText(text);
    }

    @Override
    public void setTextAkad15(String text) {
        txt15.setText(text);
    }

    @Override
    public void setAkad1Gone() {
        rel1.setVisibility(View.GONE);
    }

    @Override
    public void setAkad2Gone() {
        rel2.setVisibility(View.GONE);
    }

    @Override
    public void setAkad3Gone() {
        rel3.setVisibility(View.GONE);
    }

    @Override
    public void setAkad4Gone() {
        rel4.setVisibility(View.GONE);
    }

    @Override
    public void setAkad5Gone() {
        rel5.setVisibility(View.GONE);
    }

    @Override
    public void setAkad6Gone() {
        rel6.setVisibility(View.GONE);
    }

    @Override
    public void setAkad7Gone() {
        rel7.setVisibility(View.GONE);
    }

    @Override
    public void setAkad8Gone() {
        rel8.setVisibility(View.GONE);
    }

    @Override
    public void setAkad9Gone() {
        rel9.setVisibility(View.GONE);
    }

    @Override
    public void setAkad10Gone() {
        rel10.setVisibility(View.GONE);
    }

    @Override
    public void setAkad11Gone() {
        rel11.setVisibility(View.GONE);
    }

    @Override
    public void setAkad12Gone() {
        rel12.setVisibility(View.GONE);
    }

    @Override
    public void setAkad13Gone() {
        rel13.setVisibility(View.GONE);
    }

    @Override
    public void setAkad14Gone() {
        rel14.setVisibility(View.GONE);
    }

    @Override
    public void setAkad15Gone() {
        rel15.setVisibility(View.GONE);
    }

    @Override
    public void setAkad1Visible() {
        rel1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad2Visible() {
        rel2.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad3Visible() {
        rel3.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad4Visible() {
        rel4.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad5Visible() {
        rel5.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad6Visible() {
        rel6.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad7Visible() {
        rel7.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad8Visible() {
        rel8.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad9Visible() {
        rel9.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad10Visible() {
        rel10.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad11Visible() {
        rel11.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad12Visible() {
        rel12.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad13Visible() {
        rel13.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad14Visible() {
        rel14.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad15Visible() {
        rel15.setVisibility(View.VISIBLE);
    }

    @Override
    public void enableInputs(){
        setInputs(true);
    }

    @Override
    public void disableInputs(){
        setInputs(false);
    }

    @Override
    public void setInputs(Boolean enabeled) {
        mDescTxt.setEnabled(enabeled);
        mConfirmBtn.setEnabled(enabeled);

        mBandanaMin.setEnabled(enabeled);
        mTopiMin.setEnabled(enabeled);
        mMaskerMin.setEnabled(enabeled);
        mKuplukMin.setEnabled(enabeled);
        mKrudungMin.setEnabled(enabeled);
        mPeciMin.setEnabled(enabeled);

        mBandanaPls.setEnabled(enabeled);
        mTopiPls.setEnabled(enabeled);
        mMaskerPls.setEnabled(enabeled);
        mKuplukPls.setEnabled(enabeled);
        mKrudungPls.setEnabled(enabeled);
        mPeciPls.setEnabled(enabeled);

        mKaosMin.setEnabled(enabeled);
        mKaosDalamMin.setEnabled(enabeled);
        mKemejaMin.setEnabled(enabeled);
        mBajuMuslimMin.setEnabled(enabeled);
        mJaketMin.setEnabled(enabeled);
        mSweterMin.setEnabled(enabeled);
        mGamisMin.setEnabled(enabeled);
        mHandukMin.setEnabled(enabeled);

        mKaosPls.setEnabled(enabeled);
        mKaosDalamPls.setEnabled(enabeled);
        mKemejaPls.setEnabled(enabeled);
        mBajuMuslimPls.setEnabled(enabeled);
        mJaketPls.setEnabled(enabeled);
        mSweterPls.setEnabled(enabeled);
        mGamisPls.setEnabled(enabeled);
        mHandukPls.setEnabled(enabeled);

        mSarungTanganMin.setEnabled(enabeled);
        mSapuTanganMin.setEnabled(enabeled);

        mSarungTanganPls.setEnabled(enabeled);
        mSapuTanganPls.setEnabled(enabeled);

        mCelanaMIn.setEnabled(enabeled);
        mCelanaDalamMin.setEnabled(enabeled);
        mCelanaPendekMin.setEnabled(enabeled);
        mSarungMin.setEnabled(enabeled);
        mCelanaOlahragaMin.setEnabled(enabeled);
        mRokMin.setEnabled(enabeled);
        mCelanaLevisMin.setEnabled(enabeled);
        mKaosKakiMin.setEnabled(enabeled);

        mCelanaPls.setEnabled(enabeled);
        mCelanaDalamPls.setEnabled(enabeled);
        mCelanaPendekPls.setEnabled(enabeled);
        mSarungPls.setEnabled(enabeled);
        mCelanaOlahragaPls.setEnabled(enabeled);
        mRokPls.setEnabled(enabeled);
        mCelanaLevisPls.setEnabled(enabeled);
        mKaosKakiPls.setEnabled(enabeled);

        mJasAlmamaterMin.setEnabled(enabeled);
        mJasMin.setEnabled(enabeled);
        mSelimutKecilMin.setEnabled(enabeled);
        mSelimutBesarMin.setEnabled(enabeled);
        mBagCoverMin.setEnabled(enabeled);
        mGordengKecilMIn.setEnabled(enabeled);
        mGordengBesarMin.setEnabled(enabeled);
        mSepatuMin.setEnabled(enabeled);
        mBantalMin.setEnabled(enabeled);
        mTasKecilMin.setEnabled(enabeled);
        mTasBesarMin.setEnabled(enabeled);
        mSpreiKecilMin.setEnabled(enabeled);
        mSpreiBesarMin.setEnabled(enabeled);

        mJasAlmamaterPls.setEnabled(enabeled);
        mJasPls.setEnabled(enabeled);
        mSelimutKecilPls.setEnabled(enabeled);
        mSelimutBesarPls.setEnabled(enabeled);
        mBagCoverPls.setEnabled(enabeled);
        mGordengKecilPls.setEnabled(enabeled);
        mGordengBesarPls.setEnabled(enabeled);
        mSepatuPls.setEnabled(enabeled);
        mBantalPls.setEnabled(enabeled);
        mTasKecilPls.setEnabled(enabeled);
        mTasBesarPls.setEnabled(enabeled);
        mSpreiKecilPls.setEnabled(enabeled);
        mSpreiBesarPls.setEnabled(enabeled);
    }

    @Override
    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        mBiasaPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @Override
    public void getDoneTime() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");
        calendar.add(Calendar.DAY_OF_WEEK, 3);
        mDoneTime = dateFormat.format(calendar.getTime());
    }

    @Override
    public void getNowTime() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");
        mNowTime = dateFormat.format(calendar.getTime());
    }

    @Override
    public void getUniqNumByTime() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MMddyyy");
        mUniqNumByTime = dateFormat.format(calendar.getTime());
    }


    @Override
    @OnClick(R.id.confirm_btn)
    public void confirmOnClick() {
        getNowTime();
        getUniqNumByTime();

        final String desc = mDescTxt.getText().toString();
        final String timeNow = mNowTime;
        final String uniqNumTime = mUniqNumByTime;
        final String timeDone = mWaktuSelesaiValue.getText().toString();

        final String bandana = String.valueOf(mBandanaInt);
        final String topi = String.valueOf(mTopiInt);
        final String masker = String.valueOf(mMaskerInt);
        final String kupluk = String.valueOf(mKuplukInt);
        final String krudung = String.valueOf(mKrudungInt);
        final String peci = String.valueOf(mPeciInt);

        final String kaos = String.valueOf(mKaosInt);
        final String kaosDalam = String.valueOf(mKaosDalamInt);
        final String kemeja = String.valueOf(mKemejaInt);
        final String bajuMuslim = String.valueOf(mBajuMuslimInt);
        final String jaket = String.valueOf(mJaketInt);
        final String sweter = String.valueOf(mSweterInt);
        final String gamis = String.valueOf(mGamisInt);
        final String handuk = String.valueOf(mHandukInt);

        final String sarungTangan = String.valueOf(mSarungTanganInt);
        final String sapuTangan = String.valueOf(mSapuTanganInt);

        final String celana = String.valueOf(mCelanaInt);
        final String celanaDalam = String.valueOf(mCelanaDalamInt);
        final String celanaPendek = String.valueOf(mCelanaPendekInt);
        final String sarung = String.valueOf(mSarungInt);
        final String celanaOlahraga = String.valueOf(mCelanaOlahragaInt);
        final String rok = String.valueOf(mRokInt);
        final String celanaLevis = String.valueOf(mCelanaLevisInt);
        final String kaosKaki = String.valueOf(mKaosKakiInt);

        final String jasAlmamater = String.valueOf(mJasAlmamaterInt);
        final String jas = String.valueOf(mJasInt);
        final String selimutKecil = String.valueOf(mSelimutKecilInt);
        final String selimutBesar = String.valueOf(mSelimutBesarInt);
        final String bagCover = String.valueOf(mBagCoverInt);
        final String gordengKecil = String.valueOf(mGordengKecilInt);
        final String gordengBesar = String.valueOf(mGordengBesarInt);
        final String sepatu = String.valueOf(mSepatuInt);
        final String bantal = String.valueOf(mBantalInt);
        final String tasKecil = String.valueOf(mTasKecilInt);
        final String tasBesar = String.valueOf(mTasBesarInt);
        final String spreiKecil = String.valueOf(mSpreiKecilInt);
        final String spreiBesar = String.valueOf(mSpreiBesarInt);

        mBiasaPresenterMvp.validateInputs(desc, timeNow, uniqNumTime, timeDone,
                bandana, topi, masker, kupluk, krudung, peci,
                kaos, kaosDalam, kemeja, bajuMuslim, jaket, sweter, gamis, handuk,
                sarungTangan, sapuTangan,
                celana, celanaDalam, celanaPendek, sarung, celanaOlahraga, rok, celanaLevis, kaosKaki,
                jasAlmamater, jas, selimutKecil, selimutBesar, bagCover, gordengKecil, gordengBesar, sepatu, bantal, tasKecil, tasBesar, spreiKecil, spreiBesar);
    }

    @OnClick(R.id.opsionalInfo)
    public void opsionalInfoOnClick() {
        showOpsionalInfo();
    }

    public void showOpsionalInfo() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("INFO");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Apabila anda tidak mengisi jumlah pakaian, jumlah pakaian anda akan dihitung oleh staff laundry")
                .setCancelable(false)
                .setPositiveButton("Ok", null);

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    public void showDialogEmptyData(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin tidak mengisi jumlah pakaian ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Pakain akan dihitung oleh staff")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        mBiasaPresenterMvp.inputs(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
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

    public void showDialogConfirmData(String desc, String time, String uniqId, String timeDone, String bandana, String topi, String masker, String kupluk, String krudung, String peci, String kaos, String kaos_dalam, String kemeja, String baju_muslim, String jaket, String sweter, String gamis, String handuk, String sarung_tangan, String sapu_tangan, String celana, String celana_dalam, String celana_pendek, String sarung, String celana_olahraga, String rok, String celana_levis, String kaos_kaki, String jas_almamater, String jas, String selimut_kecil, String selimut_besar, String bag_cover, String gordeng_kecil, String gordeng_besar, String sepatu, String bantal, String tas_kecil, String tas_besar, String sprei_kecil, String sprei_besar) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin jumlah pakaian benar ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Ya untuk melanjutkan!")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        mBiasaPresenterMvp.inputs(desc, time, uniqId, timeDone, bandana, topi, masker, kupluk, krudung, peci, kaos, kaos_dalam, kemeja, baju_muslim, jaket, sweter, gamis, handuk, sarung_tangan, sapu_tangan, celana, celana_dalam, celana_pendek, sarung, celana_olahraga, rok, celana_levis, kaos_kaki, jas_almamater, jas, selimut_kecil, selimut_besar, bag_cover, gordeng_kecil, gordeng_besar, sepatu, bantal, tas_kecil, tas_besar, sprei_kecil, sprei_besar);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
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

    @Override
    @OnClick(R.id.bandana_min)
    public void bandanaMinOnclick() {
        mBandanaInt = mBandanaInt - 1;
        if (mBandanaInt < 1)
            mBandanaInt = 0;
        mBandanaNum.setText("" + mBandanaInt);
    }

    @Override
    @OnClick(R.id.bandana_pls)
    public void bandanaPlsOnClick() {
        mBandanaInt = mBandanaInt + 1;
        mBandanaNum.setText("" + mBandanaInt);
    }

    @Override
    @OnClick(R.id.topi_min)
    public void topiMinOnclick() {
        mTopiInt = mTopiInt - 1;
        if (mTopiInt < 1)
            mTopiInt = 0;
        mTopiNum.setText("" + mTopiInt);
    }

    @Override
    @OnClick(R.id.topi_pls)
    public void topiPlsOnclick() {
        mTopiInt = mTopiInt + 1;
        mTopiNum.setText("" + mTopiInt);
    }

    @Override
    @OnClick(R.id.masker_min)
    public void maskerMinOnClick() {
        mMaskerInt = mMaskerInt - 1;
        if (mMaskerInt < 1)
            mMaskerInt = 0;
        mMaskerNum.setText("" + mMaskerInt);
    }

    @Override
    @OnClick(R.id.masker_pls)
    public void maskerPlsOnClick() {
        mMaskerInt = mMaskerInt + 1;
        mMaskerNum.setText("" + mMaskerInt);
    }

    @Override
    @OnClick(R.id.kupluk_min)
    public void kuplukMinOnClick() {
        mKuplukInt = mKuplukInt - 1;
        if (mKuplukInt < 1)
            mKuplukInt = 0;
        mKuplukNum.setText("" + mKuplukInt);
    }

    @Override
    @OnClick(R.id.kupluk_pls)
    public void kuplukPlsOnClick() {
        mKuplukInt = mKuplukInt + 1;
        mKuplukNum.setText("" + mKuplukInt);
    }

    @Override
    @OnClick(R.id.krudung_min)
    public void krudungMinOnClick() {
        mKrudungInt = mKrudungInt - 1;
        if (mKrudungInt < 1)
            mKrudungInt = 0;
        mKrudungNum.setText("" + mKrudungInt);
    }

    @Override
    @OnClick(R.id.krudung_pls)
    public void krudungPlsOnClick() {
        mKrudungInt = mKrudungInt + 1;
        mKrudungNum.setText("" + mKrudungInt);
    }


    @Override
    @OnClick(R.id.peci_min)
    public void peciMinOnClick() {
        mPeciInt = mPeciInt - 1;
        if (mPeciInt < 1)
            mPeciInt = 0;
        mPeciNum.setText("" + mPeciInt);
    }

    @Override
    @OnClick(R.id.peci_pls)
    public void peciPlsOnclick() {
        mPeciInt = mPeciInt + 1;
        mPeciNum.setText("" + mPeciInt);
    }
//    public void buttonHeadOnclick(View view) {
//        switch (view.getId()) {
//            case R.id.bandana_min:
//                mBandanaInt = mBandanaInt - 1;
//                if (mBandanaInt < 1)
//                    mBandanaInt = 0;
//                mBandanaNum.setText("" + mBandanaInt);
//                break;
//            case R.id.bandana_pls:
//                mBandanaInt = mBandanaInt + 1;
//                mBandanaNum.setText("" + mBandanaInt);
//                break;
//
//            case R.id.topi_min:
//                mTopiInt = mTopiInt - 1;
//                if (mTopiInt < 1)
//                    mTopiInt = 0;
//                mTopiNum.setText("" + mTopiInt);
//                break;
//            case R.id.topi_pls:
//                mTopiInt = mTopiInt + 1;
//                mTopiNum.setText("" + mTopiInt);
//                break;
//
//            case R.id.masker_min:
//                mMaskerInt = mMaskerInt - 1;
//                if (mMaskerInt < 1)
//                    mMaskerInt = 0;
//                mMaskerNum.setText("" + mMaskerInt);
//                break;
//            case R.id.masker_pls:
//                mMaskerInt = mMaskerInt + 1;
//                mMaskerNum.setText("" + mMaskerInt);
//                break;
//
//            case R.id.kupluk_min:
//                mKuplukInt = mKuplukInt - 1;
//                if (mKuplukInt < 1)
//                    mKuplukInt = 0;
//                mKuplukNum.setText("" + mKuplukInt);
//                break;
//            case R.id.kupluk_pls:
//                mKuplukInt = mKuplukInt + 1;
//                mKuplukNum.setText("" + mKuplukInt);
//                break;
//
//            case R.id.krudung_min:
//                mKrudungInt = mKrudungInt - 1;
//                if (mKrudungInt < 1)
//                    mKrudungInt = 0;
//                mKrudungNum.setText("" + mKrudungInt);
//                break;
//            case R.id.krudung_pls:
//                mKrudungInt = mKrudungInt + 1;
//                mKrudungNum.setText("" + mKrudungInt);
//                break;
//
//            case R.id.peci_min:
//                mPeciInt = mPeciInt - 1;
//                if (mPeciInt < 1)
//                    mPeciInt = 0;
//                mPeciNum.setText("" + mPeciInt);
//                break;
//            case R.id.peci_pls:
//                mPeciInt = mPeciInt + 1;
//                mPeciNum.setText("" + mPeciInt);
//                break;
//        }
//    }

    @Override
    @OnClick(R.id.kaos_min)
    public void kaosMinOnClick() {
        mKaosInt = mKaosInt - 1;
        if (mKaosInt < 1)
            mKaosInt = 0;
        mKaosNum.setText("" + mKaosInt);
    }

    @Override
    @OnClick(R.id.kaos_pls)
    public void kaosPlsOnClick() {
        mKaosInt = mKaosInt + 1;
        mKaosNum.setText("" + mKaosInt);
    }

    @Override
    @OnClick(R.id.kaos_dalam_min)
    public void kaosDalamOnClick() {
        mKaosDalamInt = mKaosDalamInt - 1;
        if (mKaosDalamInt < 1)
            mKaosDalamInt = 0;
        mKaosDalamNum.setText("" + mKaosDalamInt);
    }

    @Override
    @OnClick(R.id.kaos_dalam_pls)
    public void kaosDalamPlsOnClick() {
        mKaosDalamInt = mKaosDalamInt + 1;
        mKaosDalamNum.setText("" + mKaosDalamInt);
    }

    @Override
    @OnClick(R.id.kemeja_min)
    public void kemejaMinOnClick() {
        mKemejaInt = mKemejaInt - 1;
        if (mKemejaInt < 1)
            mKemejaInt = 0;
        mKemejaNum.setText("" + mKemejaInt);
    }

    @Override
    @OnClick(R.id.kemeja_pls)
    public void kemejaPlsOnClick() {
        mKemejaInt = mKemejaInt + 1;
        mKemejaNum.setText("" + mKemejaInt);
    }

    @Override
    @OnClick(R.id.baju_muslim_min)
    public void bajuMuslimOnClick() {
        mBajuMuslimInt = mBajuMuslimInt - 1;
        if (mBajuMuslimInt < 1)
            mBajuMuslimInt = 0;
        mBajuMuslimNum.setText("" + mBajuMuslimInt);
    }

    @Override
    @OnClick(R.id.baju_muslim_pls)
    public void bajuMuslimPlsOnClick() {
        mBajuMuslimInt = mBajuMuslimInt + 1;
        mBajuMuslimNum.setText("" + mBajuMuslimInt);
    }

    @Override
    @OnClick(R.id.jaket_min)
    public void jaketMinOnClick() {
        mJaketInt = mJaketInt - 1;
        if (mJaketInt < 1)
            mJaketInt = 0;
        mJaketNum.setText("" + mJaketInt);
    }

    @Override
    @OnClick(R.id.jaket_pls)
    public void jaketPlsOnClick() {
        mJaketInt = mJaketInt + 1;
        mJaketNum.setText("" + mJaketInt);
    }

    @Override
    @OnClick(R.id.sweter_min)
    public void sweterMinOnClick() {
        mSweterInt = mSweterInt - 1;
        if (mSweterInt < 1)
            mSweterInt = 0;
        mSweterNum.setText("" + mSweterInt);
    }

    @Override
    @OnClick(R.id.sweter_pls)
    public void sweterPlsOnClick() {
        mSweterInt = mSweterInt + 1;
        mSweterNum.setText("" + mSweterInt);
    }

    @Override
    @OnClick(R.id.gamis_min)
    public void gamisMinOnClick() {
        mGamisInt = mGamisInt - 1;
        if (mGamisInt < 1)
            mGamisInt = 0;
        mGamisNum.setText("" + mGamisInt);
    }

    @Override
    @OnClick(R.id.gamis_pls)
    public void gamisPlsOnClick() {
        mGamisInt = mGamisInt + 1;
        mGamisNum.setText("" + mGamisInt);
    }

    @Override
    @OnClick(R.id.handuk_min)
    public void handukMinOnClick() {
        mHandukInt = mHandukInt - 1;
        if (mHandukInt < 1)
            mHandukInt = 0;
        mHandukNum.setText("" + mHandukInt);
    }

    @Override
    @OnClick(R.id.handuk_pls)
    public void handukPlsOnClick() {
        mHandukInt = mHandukInt + 1;
        mHandukNum.setText("" + mHandukInt);
    }

    @Override
    @OnClick(R.id.sarung_tangan_min)
    public void sarungTanganMinOnClick() {
        mSarungTanganInt = mSarungTanganInt - 1;
        if (mSarungTanganInt < 1)
            mSarungTanganInt = 0;
        mSarungTanganNum.setText("" + mSarungTanganInt);
    }

    @Override
    @OnClick(R.id.sarung_tangan_pls)
    public void sarungTanganPlsOnClick() {
        mSarungTanganInt = mSarungTanganInt + 1;
        mSarungTanganNum.setText("" + mSarungTanganInt);
    }

    @Override
    @OnClick(R.id.sapu_tangan_min)
    public void sapuTanganMinOnClick() {
        mSapuTanganInt = mSapuTanganInt - 1;
        if (mSapuTanganInt < 1)
            mSapuTanganInt = 0;
        mSapuTanganNum.setText("" + mSapuTanganInt);
    }

    @Override
    @OnClick(R.id.sapu_tangan_pls)
    public void sapuTanganPlsOnClick() {
        mSapuTanganInt = mSapuTanganInt + 1;
        mSapuTanganNum.setText("" + mSapuTanganInt);
    }

    @Override
    @OnClick(R.id.celana_min)
    public void cenaMinOnClick() {
        mCelanaInt = mCelanaInt - 1;
        if (mCelanaInt < 1)
            mCelanaInt = 0;
        mCelanaNum.setText("" + mCelanaInt);
    }

    @Override
    @OnClick(R.id.celana_pls)
    public void cenalaPlsOnClick() {
        mCelanaInt = mCelanaInt + 1;
        mCelanaNum.setText("" + mCelanaInt);
    }

    @Override
    @OnClick(R.id.celana_dalam_min)
    public void celanaDalamMinOnClick() {
        mCelanaDalamInt = mCelanaDalamInt - 1;
        if (mCelanaDalamInt < 1)
            mCelanaDalamInt = 0;
        mCelanaDalamNum.setText("" + mCelanaDalamInt);
    }

    @Override
    @OnClick(R.id.celana_dalam_pls)
    public void celanaDalamPlsOnClick() {
        mCelanaDalamInt = mCelanaDalamInt + 1;
        mCelanaDalamNum.setText("" + mCelanaDalamInt);
    }

    @Override
    @OnClick(R.id.celana_pendek_min)
    public void celanaPendekMinOnClick() {
        mCelanaPendekInt = mCelanaPendekInt - 1;
        if (mCelanaPendekInt < 1)
            mCelanaPendekInt = 0;
        mCelanaPendekNum.setText("" + mCelanaPendekInt);
    }

    @Override
    @OnClick(R.id.celana_pendek_pls)
    public void celanaPendekOnClick() {
        mCelanaPendekInt = mCelanaPendekInt + 1;
        mCelanaPendekNum.setText("" + mCelanaPendekInt);
    }

    @Override
    @OnClick(R.id.sarung_min)
    public void sarungMInOnClick() {
        mSarungInt = mSarungInt - 1;
        if (mSarungInt < 1)
            mSarungInt = 0;
        mSarungNum.setText("" + mSarungInt);
    }

    @Override
    @OnClick(R.id.sarung_pls)
    public void sarungPlsOnClick() {
        mSarungInt = mSarungInt + 1;
        mSarungNum.setText("" + mSarungInt);
    }

    @Override
    @OnClick(R.id.celana_olahraga_min)
    public void celanaOlahragaMinOnClick() {
        mCelanaOlahragaInt = mCelanaOlahragaInt - 1;
        if (mCelanaOlahragaInt < 1)
            mCelanaOlahragaInt = 0;
        mCelanaOlahragaNum.setText("" + mCelanaOlahragaInt);
    }

    @Override
    @OnClick(R.id.celana_olahraga_pls)
    public void celanaOlahragaPlsOnClick() {
        mCelanaOlahragaInt = mCelanaOlahragaInt + 1;
        mCelanaOlahragaNum.setText("" + mCelanaOlahragaInt);
    }

    @Override
    @OnClick(R.id.rok_min)
    public void rokMinOnClick() {
        mRokInt = mRokInt - 1;
        if (mRokInt < 1)
            mRokInt = 0;
        mRokNum.setText("" + mRokInt);
    }

    @Override
    @OnClick(R.id.rok_pls)
    public void rokPlsOnClick() {
        mRokInt = mRokInt + 1;
        mRokNum.setText("" + mRokInt);
    }

    @Override
    @OnClick(R.id.celana_levis_min)
    public void celanaLevisMinOnClick() {
        mCelanaLevisInt = mCelanaLevisInt - 1;
        if (mCelanaLevisInt < 1)
            mCelanaLevisInt = 0;
        mCelanaLevisNum.setText("" + mCelanaLevisInt);
    }

    @Override
    @OnClick(R.id.celana_levis_pls)
    public void celanaLevisPlsOnClick() {
        mCelanaLevisInt = mCelanaLevisInt + 1;
        mCelanaLevisNum.setText("" + mCelanaLevisInt);
    }

    @Override
    @OnClick(R.id.kaos_kaki_min)
    public void kaosKakiMinOnClick() {
        mKaosKakiInt = mKaosKakiInt - 1;
        if (mKaosKakiInt < 1)
            mKaosKakiInt = 0;
        mKaosKakiNum.setText("" + mKaosKakiInt);
    }

    @Override
    @OnClick(R.id.kaos_kaki_pls)
    public void kaosKakiPlsOnClick() {
        mKaosKakiInt = mKaosKakiInt + 1;
        mKaosKakiNum.setText("" + mKaosKakiInt);
    }

    @Override
    @OnClick(R.id.jas_almamater_min)
    public void jasAlmamaterMinOnClick() {
        mJasAlmamaterInt = mJasAlmamaterInt - 1;
        if (mJasAlmamaterInt < 1)
            mJasAlmamaterInt = 0;
        mJasAlmamaterNum.setText("" + mJasAlmamaterInt);
    }

    @Override
    @OnClick(R.id.jas_almamater_pls)
    public void jasAlmamaterPlsOnClick() {
        mJasAlmamaterInt = mJasAlmamaterInt + 1;
        mJasAlmamaterNum.setText("" + mJasAlmamaterInt);
    }

    @Override
    @OnClick(R.id.jas_min)
    public void jasMinOnClick() {
        mJasInt = mJasInt - 1;
        if (mJasInt < 1)
            mJasInt = 0;
        mJasNum.setText("" + mJasInt);
    }

    @Override
    @OnClick(R.id.jas_pls)
    public void jasPlsOnClick() {
        mJasInt = mJasInt + 1;
        mJasNum.setText("" + mJasInt);
    }

    @Override
    @OnClick(R.id.selimut_kecil_min)
    public void selimutKecilMinOnClick() {
        mSelimutKecilInt = mSelimutKecilInt - 1;
        if (mSelimutKecilInt < 1)
            mSelimutKecilInt = 0;
        mSelimutKecilNum.setText("" + mSelimutKecilInt);
    }

    @Override
    @OnClick(R.id.selimut_kecil_pls)
    public void selimutKecilPlsOnClick() {
        mSelimutKecilInt = mSelimutKecilInt + 1;
        mSelimutKecilNum.setText("" + mSelimutKecilInt);
    }

    @Override
    @OnClick(R.id.selimut_besar_min)
    public void selimutBesarMinOnClick() {
        mSelimutBesarInt = mSelimutBesarInt - 1;
        if (mSelimutBesarInt < 1)
            mSelimutBesarInt = 0;
        mSelimutBesarNum.setText("" + mSelimutBesarInt);
    }

    @Override
    @OnClick(R.id.selimut_besar_pls)
    public void selimutBesarPlsOnClick() {
        mSelimutBesarInt = mSelimutBesarInt + 1;
        mSelimutBesarNum.setText("" + mSelimutBesarInt);
    }

    @Override
    @OnClick(R.id.bag_cover_min)
    public void bagCoverMinOnClick() {
        mBagCoverInt = mBagCoverInt - 1;
        if (mBagCoverInt < 1)
            mBagCoverInt = 0;
        mBagCoverNum.setText("" + mBagCoverInt);
    }

    @Override
    @OnClick(R.id.bag_cover_pls)
    public void bagCoverPlsOnClick() {
        mBagCoverInt = mBagCoverInt + 1;
        mBagCoverNum.setText("" + mBagCoverInt);
    }

    @Override
    @OnClick(R.id.gordeng_kecil_min)
    public void gordengKecilMinOnClick() {
        mGordengKecilInt = mGordengKecilInt - 1;
        if (mGordengKecilInt < 1)
            mGordengKecilInt = 0;
        mGordengKecilNum.setText("" + mGordengKecilInt);
    }

    @Override
    @OnClick(R.id.gordeng_kecil_pls)
    public void gordengKecilPlsOnClick() {
        mGordengKecilInt = mGordengKecilInt + 1;
        mGordengKecilNum.setText("" + mGordengKecilInt);
    }

    @Override
    @OnClick(R.id.gordeng_besar_min)
    public void gordengBesarMinOnClick() {
        mGordengBesarInt = mGordengBesarInt - 1;
        if (mGordengBesarInt < 1)
            mGordengBesarInt = 0;
        mGordengBesarNum.setText("" + mGordengBesarInt);
    }

    @Override
    @OnClick(R.id.gordeng_besar_pls)
    public void gordengBesarPlsOnClick() {
        mGordengBesarInt = mGordengBesarInt + 1;
        mGordengBesarNum.setText("" + mGordengBesarInt);
    }

    @Override
    @OnClick(R.id.sepatu_min)
    public void sepatuMinOnClick() {
        mSepatuInt = mSepatuInt - 1;
        if (mSepatuInt < 1)
            mSepatuInt = 0;
        mSepatuNum.setText("" + mSepatuInt);
    }

    @Override
    @OnClick(R.id.sepatu_pls)
    public void sepatuPlsOnClick() {
        mSepatuInt = mSepatuInt + 1;
        mSepatuNum.setText("" + mSepatuInt);
    }

    @Override
    @OnClick(R.id.bantal_min)
    public void bantalMinOnClick() {
        mBantalInt = mBantalInt - 1;
        if (mBantalInt < 1)
            mBantalInt = 0;
        mBantalNum.setText("" + mBantalInt);
    }

    @Override
    @OnClick(R.id.bantal_pls)
    public void bantalPlsOnClick() {
        mBantalInt = mBantalInt + 1;
        mBantalNum.setText("" + mBantalInt);
    }

    @Override
    @OnClick(R.id.tas_kecil_min)
    public void tasKecilMinOnClick() {
        mTasKecilInt = mTasKecilInt - 1;
        if (mTasKecilInt < 1)
            mTasKecilInt = 0;
        mTasKecilNum.setText("" + mTasKecilInt);
    }

    @Override
    @OnClick(R.id.tas_kecil_pls)
    public void tasKecilPlsOnClick() {
        mTasKecilInt = mTasKecilInt + 1;
        mTasKecilNum.setText("" + mTasKecilInt);
    }

    @Override
    @OnClick(R.id.tas_besar_min)
    public void tasBesarMinOnClick() {
        mTasBesarInt = mTasBesarInt - 1;
        if (mTasBesarInt < 1)
            mTasBesarInt = 0;
        mTasBesarNum.setText("" + mTasBesarInt);
    }

    @Override
    @OnClick(R.id.tas_besar_pls)
    public void tasBesarPlsOnClick() {
        mTasBesarInt = mTasBesarInt + 1;
        mTasBesarNum.setText("" + mTasBesarInt);
    }

    @Override
    @OnClick(R.id.sprei_kecil_min)
    public void spreiKecilMinOnClick() {
        mSpreiKecilInt = mSpreiKecilInt - 1;
        if (mSpreiKecilInt < 1)
            mSpreiKecilInt = 0;
        mSpreiKecilNum.setText("" + mSpreiKecilInt);
    }

    @Override
    @OnClick(R.id.sprei_kecil_pls)
    public void spreiKecilPlsOnClick() {
        mSpreiKecilInt = mSpreiKecilInt + 1;
        mSpreiKecilNum.setText("" + mSpreiKecilInt);
    }

    @Override
    @OnClick(R.id.sprei_besar_min)
    public void spreiBesarMinOnClick() {
        mSpreiBesarInt = mSpreiBesarInt - 1;
        if (mSpreiBesarInt < 1)
            mSpreiBesarInt = 0;
        mSpreiBesarNum.setText("" + mSpreiBesarInt);
    }

    @Override
    @OnClick(R.id.sprei_besar_pls)
    public void spreiBesarPlsOnClick() {
        mSpreiBesarInt = mSpreiBesarInt + 1;
        mSpreiBesarNum.setText("" + mSpreiBesarInt);
    }


    private class Var {
//        /**
//         * CLOSTHES VAR
//         **/
//        private TextView mBandanaTxt, mTopiTxt, mMaskerTxt, mKuplukTxt, mKrudungTxt, mPeciTxt;
//        private CircleButton mBandanaMin, mTopiMin, mMaskerMin, mKuplukMin, mKrudungMin, mPeciMin;
//        private TextView mBandanaNum, mTopiNum, mMaskerNum, mKuplukNum, mKrudungNum, mPeciNum;
//        private CircleButton mBandanaPls, mTopiPls, mMaskerPls, mKuplukPls, mKrudungPls, mPeciPls;
//        private int mBandanaInt, mTopiInt, mMaskerInt, mKuplukInt, mKrudungInt, mPeciInt = 0;

        /**
         * BODY VAR
         **/
//        private TextView mKaosTxt, mKaosDalamTxt, mKemejaTxt, mBajuMuslimTxt, mJaketTxt, mSweterTxt, mGamisTxt, mHandukTxt;
//        private CircleButton mKaosMin, mKaosDalamMin, mKemejaMin, mBajuMuslimMin, mJaketMin, mSweterMin, mGamisMin, mHandukMin;
//        private TextView mKaosNum, mKaosDalamNum, mKemejaNum, mBajuMuslimNum, mJaketNum, mSweterNum, mGamisNum, mHandukNum;
//        private CircleButton mKaosPls, mKaosDalamPls, mKemejaPls, mBajuMuslimPls, mJaketPls, mSweterPls, mGamisPls, mHandukPls;
//        private int mKaosInt, mKaosDalamInt, mKemejaInt, mBajuMuslimInt, mJaketInt, mSweterInt, mGamisInt, mHandukInt;

        /**
         * HAND VAR
         **/
//        private TextView mSarungTanganTxt, mSapuTanganTxt;
//        private CircleButton mSarungTanganMin, mSapuTanganMin;
//        private TextView mSarungTanganNum, mSapuTanganNum;
//        private CircleButton mSarungTanganPls, mSapuTanganPls;
//        private int mSarungTanganInt, mSapuTanganInt;

        /**
         * FEET VAR
         **/
//        private TextView mCelanaTxt, mCelanaDalamTxt, mCelanaPendekTxt, mSarungTxt, mCelanaOlahragaTxt, mRokTxt, mCelanaLevisTxt, mKaosKakiTxt;
//        private CircleButton mCelanaMIn, mCelanaDalamMin, mCelanaPendekMin, mSarungMin, mCelanaOlahragaMin, mRokMin, mCelanaLevisMin, mKaosKakiMin;
//        private TextView mCelanaNum, mCelanaDalamNum, mCelanaPendekNum, mSarungNum, mCelanaOlahragaNum, mRokNum, mCelanaLevisNum, mKaosKakiNum;
//        private CircleButton mCelanaPls, mCelanaDalamPls, mCelanaPendekPls, mSarungPls, mCelanaOlahragaPls, mRokPls, mCelanaLevisPls, mKaosKakiPls;
//        private int mCelanaInt, mCelanaDalamInt, mCelanaPendekInt, mSarungInt, mCelanaOlahragaInt, mRokInt, mCelanaLevisInt, mKaosKakiInt;

        /**
         * OTHER VAR
         **/
//        private TextView mJasAlmamaterTxt, mJasTxt, mSelimutKecilTxt, mSelimutBesarTxt, mBagCoverTxt, mGordengKecilTxt, mGordengBesarTxt, mSepatuTxt, mBantalTxt, mTasKecilTxt, mTasBesarTxt, mSpreiKecilTxt, mSpreiBesarTxt;
//        private CircleButton mJasAlmamaterMin, mJasMin, mSelimutKecilMin, mSelimutBesarMin, mBagCoverMin, mGordengKecilMIn, mGordengBesarMin, mSepatuMin, mBantalMin, mTasKecilMin, mTasBesarMin, mSpreiKecilMin, mSpreiBesarMin;
//        private TextView mJasAlmamaterNum, mJasNum, mSelimutKecilNum, mSelimutBesarNum, mBagCoverNum, mGordengKecilNum, mGordengBesarNum, mSepatuNum, mBantalNum, mTasKecilNum, mTasBesarNum, mSpreiKecilNum, mSpreiBesarNum;
//        private CircleButton mJasAlmamaterPls, mJasPls, mSelimutKecilPls, mSelimutBesarPls, mBagCoverPls, mGordengKecilPls, mGordengBesarPls, mSepatuPls, mBantalPls, mTasKecilPls, mTasBesarPls, mSpreiKecilPls, mSpreiBesarPls;
//        private int mJasAlmamaterInt, mJasInt, mSelimutKecilInt, mSelimutBesarInt, mBagCoverInt, mGordengKecilInt, mGordengBesarInt, mSepatuInt, mBantalInt, mTasKecilInt, mTasBesarInt, mSpreiKecilInt, mSpreiBesarInt;
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMainScreen() {
        //startActivity(new Intent(this, MainActivity.class));
        Intent mainIntent = new Intent(BiasaActivity.this, MainActivity.class);
        mainIntent.putExtra("tab", 1);
        startActivity(mainIntent);
        showMessage("masuk navigate");
    }
}
