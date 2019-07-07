package com.tiunida.laundry0.order.biasa.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private BiasaPresenterMvp mOrderPresenterMvp;

    @BindView(R.id.confirm_btn)
    Button confirmBtn;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biasa);

        mOrderPresenterMvp = new BiasaPresenter(this);
        mOrderPresenterMvp.onCreate();

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();

        ButterKnife.bind(this);

        mDescTxt = (EditText) findViewById(R.id.desc_txt);
        mDescTxt.setMovementMethod(new ScrollingMovementMethod());

//        getNowTime();
//        mWaktuPesananValue.setText(mNowTime);
        getDoneTime();
        mWaktuSelesaiValue.setText(mDoneTime);
//        /**HEAD**/
//        var.mBandanaTxt = (TextView) findViewById(R.id.bandana_txt);
//        var.mTopiTxt = (TextView) findViewById(R.id.topi_txt);
//        var.mMaskerTxt = (TextView) findViewById(R.id.masker_txt);
//        var.mKuplukTxt = (TextView) findViewById(R.id.kupluk_txt);
//        var.mKrudungTxt = (TextView) findViewById(R.id.krudung_txt);
//        var.mPeciTxt = (TextView) findViewById(R.id.peci_txt);

//        var.mBandanaMin = (CircleButton) findViewById(R.id.bandana_min);
//        var.mTopiMin = (CircleButton) findViewById(R.id.topi_min);
//        var.mMaskerMin = (CircleButton) findViewById(R.id.masker_min);
//        var.mKuplukMin = (CircleButton) findViewById(R.id.kupluk_min);
//        var.mKrudungMin = (CircleButton) findViewById(R.id.krudung_min);
//        var.mPeciMin = (CircleButton) findViewById(R.id.peci_min);

//        var.mBandanaNum = (TextView) findViewById(R.id.bandana_num);
//        var.mTopiNum = (TextView) findViewById(R.id.topi_num);
//        var.mMaskerNum = (TextView) findViewById(R.id.masker_num);
//        var.mKuplukNum = (TextView) findViewById(R.id.kupluk_num);
//        var.mKrudungNum = (TextView) findViewById(R.id.krudung_num);
//        var.mPeciNum = (TextView) findViewById(R.id.peci_num);

//        var.mBandanaPls = (CircleButton) findViewById(R.id.bandana_pls);
//        var.mTopiPls = (CircleButton) findViewById(R.id.topi_pls);
//        var.mMaskerPls = (CircleButton) findViewById(R.id.masker_pls);
//        var.mKuplukPls = (CircleButton) findViewById(R.id.kupluk_pls);
//        var.mKrudungPls = (CircleButton) findViewById(R.id.krudung_pls);
//        var.mPeciPls = (CircleButton) findViewById(R.id.peci_pls);

//        /**BODY**/
//        var.mKaosTxt = (TextView) findViewById(R.id.kaos_txt);
//        var.mKaosDalamTxt = (TextView) findViewById(R.id.kaos_dalam_txt);
//        var.mKemejaTxt = (TextView) findViewById(R.id.kemeja_txt);
//        var.mBajuMuslimTxt = (TextView) findViewById(R.id.baju_muslim_txt);
//        var.mJaketTxt = (TextView) findViewById(R.id.jaket_txt);
//        var.mSweterTxt = (TextView) findViewById(R.id.sweter_txt);
//        var.mGamisTxt = (TextView) findViewById(R.id.gamis_txt);
//        var.mHandukTxt = (TextView) findViewById(R.id.handuk_txt);

//        var.mKaosMin = (CircleButton) findViewById(R.id.kaos_min);
//        var.mKaosDalamMin = (CircleButton) findViewById(R.id.kaos_dalam_min);
//        var.mKemejaMin = (CircleButton) findViewById(R.id.kemeja_min);
//        var.mBajuMuslimMin = (CircleButton) findViewById(R.id.baju_muslim_min);
//        var.mJaketMin = (CircleButton) findViewById(R.id.jaket_min);
//        var.mSweterMin = (CircleButton) findViewById(R.id.sweter_min);
//        var.mGamisMin = (CircleButton) findViewById(R.id.gamis_min);
//        var.mHandukMin = (CircleButton) findViewById(R.id.handuk_min);
//
//        var.mKaosNum = (TextView) findViewById(R.id.kaos_num);
//        var.mKaosDalamNum = (TextView) findViewById(R.id.kaos_dalam_num);
//        var.mKemejaNum = (TextView) findViewById(R.id.kemeja_num);
//        var.mBajuMuslimNum = (TextView) findViewById(R.id.baju_muslim_num);
//        var.mJaketNum = (TextView) findViewById(R.id.jaket_num);
//        var.mSweterNum = (TextView) findViewById(R.id.sweter_num);
//        var.mGamisNum = (TextView) findViewById(R.id.gamis_num);
//        var.mHandukNum = (TextView) findViewById(R.id.handuk_num);
//
//        var.mKaosPls = (CircleButton) findViewById(R.id.kaos_pls);
//        var.mKaosDalamPls = (CircleButton) findViewById(R.id.kaos_dalam_pls);
//        var.mKemejaPls = (CircleButton) findViewById(R.id.kemeja_pls);
//        var.mBajuMuslimPls = (CircleButton) findViewById(R.id.baju_muslim_pls);
//        var.mJaketPls = (CircleButton) findViewById(R.id.jaket_pls);
//        var.mSweterPls = (CircleButton) findViewById(R.id.sweter_pls);
//        var.mGamisPls = (CircleButton) findViewById(R.id.gamis_pls);
//        var.mHandukPls = (CircleButton) findViewById(R.id.handuk_pls);

//        /**HAND**/
//        var.mSarungTanganTxt = (TextView) findViewById(R.id.sarung_tangan_txt);
//        var.mSapuTanganTxt = (TextView) findViewById(R.id.sapu_tangan_txt);

//        var.mSarungTanganMin = (CircleButton) findViewById(R.id.sarung_tangan_min);
//        var.mSapuTanganMin = (CircleButton) findViewById(R.id.sapu_tangan_min);
//
//        var.mSarungTanganNum = (TextView) findViewById(R.id.sarung_tangan_num);
//        var.mSapuTanganNum = (TextView) findViewById(R.id.sapu_tangan_num);
//
//        var.mSarungTanganPls = (CircleButton) findViewById(R.id.sarung_tangan_pls);
//        var.mSapuTanganPls = (CircleButton) findViewById(R.id.sapu_tangan_pls);

//        /**FEET**/
//        var.mCelanaTxt = (TextView) findViewById(R.id.celana_txt);
//        var.mCelanaDalamTxt = (TextView) findViewById(R.id.celana_dalam_txt);
//        var.mCelanaPendekTxt = (TextView) findViewById(R.id.celana_pendek_txt);
//        var.mSarungTxt = (TextView) findViewById(R.id.sarung_txt);
//        var.mCelanaOlahragaTxt = (TextView) findViewById(R.id.celana_olahraga_txt);
//        var.mRokTxt = (TextView) findViewById(R.id.rok_txt);
//        var.mCelanaLevisTxt = (TextView) findViewById(R.id.celana_levis_txt);
//        var.mKaosKakiTxt = (TextView) findViewById(R.id.kaos_kaki_txt);

//        var.mCelanaMIn = (CircleButton) findViewById(R.id.celana_min);
//        var.mCelanaDalamMin = (CircleButton) findViewById(R.id.celana_dalam_min);
//        var.mCelanaPendekMin = (CircleButton) findViewById(R.id.celana_pendek_min);
//        var.mSarungMin = (CircleButton) findViewById(R.id.sarung_min);
//        var.mCelanaOlahragaMin = (CircleButton) findViewById(R.id.celana_olahraga_min);
//        var.mRokMin = (CircleButton) findViewById(R.id.rok_min);
//        var.mCelanaLevisMin = (CircleButton) findViewById(R.id.celana_levis_min);
//        var.mKaosKakiMin = (CircleButton) findViewById(R.id.kaos_kaki_min);
//
//        var.mCelanaNum = (TextView) findViewById(R.id.celana_num);
//        var.mCelanaDalamNum = (TextView) findViewById(R.id.celana_dalam_num);
//        var.mCelanaPendekNum = (TextView) findViewById(R.id.celana_pendek_num);
//        var.mSarungNum = (TextView) findViewById(R.id.sarung_num);
//        var.mCelanaOlahragaNum = (TextView) findViewById(R.id.celana_olahraga_num);
//        var.mRokNum = (TextView) findViewById(R.id.rok_num);
//        var.mCelanaLevisNum = (TextView) findViewById(R.id.celana_levis_num);
//        var.mKaosKakiNum = (TextView) findViewById(R.id.kaos_kaki_num);
//
//        var.mCelanaPls = (CircleButton) findViewById(R.id.celana_pls);
//        var.mCelanaDalamPls = (CircleButton) findViewById(R.id.celana_dalam_pls);
//        var.mCelanaPendekPls = (CircleButton) findViewById(R.id.celana_pendek_pls);
//        var.mSarungPls = (CircleButton) findViewById(R.id.sarung_pls);
//        var.mCelanaOlahragaPls = (CircleButton) findViewById(R.id.celana_olahraga_pls);
//        var.mRokPls = (CircleButton) findViewById(R.id.rok_pls);
//        var.mCelanaLevisPls = (CircleButton) findViewById(R.id.celana_levis_pls);
//        var.mKaosKakiPls = (CircleButton) findViewById(R.id.kaos_kaki_pls);

//        /**OTHER**/
//        var.mJasAlmamaterTxt = (TextView) findViewById(R.id.jas_almamater_txt);
//        var.mJasTxt = (TextView) findViewById(R.id.jas_txt);
//        var.mSelimutKecilTxt = (TextView) findViewById(R.id.selimut_kecil_txt);
//        var.mSelimutBesarTxt = (TextView) findViewById(R.id.selimut_besar_txt);
//        var.mBagCoverTxt = (TextView) findViewById(R.id.bag_cover_txt);
//        var.mGordengKecilTxt = (TextView) findViewById(R.id.gordeng_kecil_txt);
//        var.mGordengBesarTxt = (TextView) findViewById(R.id.gordeng_besar_txt);
//        var.mSepatuTxt = (TextView) findViewById(R.id.sepatu_txt);
//        var.mBantalTxt = (TextView) findViewById(R.id.bantal_txt);
//        var.mTasKecilTxt = (TextView) findViewById(R.id.tas_kecil_txt);
//        var.mTasBesarTxt = (TextView) findViewById(R.id.tas_besar_txt);
//        var.mSpreiKecilTxt = (TextView) findViewById(R.id.sprei_kecil_txt);
//        var.mSpreiBesarTxt = (TextView) findViewById(R.id.sprei_besr_txt);
//
//        var.mJasAlmamaterMin = (CircleButton) findViewById(R.id.jas_almamater_min);
//        var.mJasMin = (CircleButton) findViewById(R.id.jas_min);
//        var.mSelimutKecilMin = (CircleButton) findViewById(R.id.selimut_kecil_min);
//        var.mSelimutBesarMin = (CircleButton) findViewById(R.id.selimut_besar_min);
//        var.mBagCoverMin = (CircleButton) findViewById(R.id.bag_cover_min);
//        var.mGordengKecilMIn = (CircleButton) findViewById(R.id.gordeng_kecil_min);
//        var.mGordengBesarMin = (CircleButton) findViewById(R.id.gordeng_besar_min);
//        var.mSepatuMin = (CircleButton) findViewById(R.id.sepatu_min);
//        var.mBantalMin = (CircleButton) findViewById(R.id.bantal_min);
//        var.mTasKecilMin = (CircleButton) findViewById(R.id.tas_kecil_min);
//        var.mTasBesarMin = (CircleButton) findViewById(R.id.tas_besar_min);
//        var.mSpreiKecilMin = (CircleButton) findViewById(R.id.sprei_kecil_min);
//        var.mSpreiBesarMin = (CircleButton) findViewById(R.id.sprei_besar_min);
//
//        var.mJasAlmamaterNum = (TextView) findViewById(R.id.jas_almamater_num);
//        var.mJasNum = (TextView) findViewById(R.id.jas_num);
//        var.mSelimutKecilNum = (TextView) findViewById(R.id.selimut_kecil_num);
//        var.mSelimutBesarNum = (TextView) findViewById(R.id.selimut_besar_num);
//        var.mBagCoverNum = (TextView) findViewById(R.id.bag_cover_num);
//        var.mGordengKecilNum = (TextView) findViewById(R.id.gordeng_kecil_num);
//        var.mGordengBesarNum = (TextView) findViewById(R.id.gordeng_besar_num);
//        var.mSepatuNum = (TextView) findViewById(R.id.sepatu_num);
//        var.mBantalNum = (TextView) findViewById(R.id.bantal_num);
//        var.mTasKecilNum = (TextView) findViewById(R.id.tas_kecil_num);
//        var.mTasBesarNum = (TextView) findViewById(R.id.tas_besar_num);
//        var.mSpreiKecilNum = (TextView) findViewById(R.id.sprei_kecil_num);
//        var.mSpreiBesarNum = (TextView) findViewById(R.id.sprei_besar_num);
//
//        var.mJasAlmamaterPls = (CircleButton) findViewById(R.id.jas_almamater_pls);
//        var.mJasPls = (CircleButton) findViewById(R.id.jas_pls);
//        var.mSelimutKecilPls = (CircleButton) findViewById(R.id.selimut_kecil_pls);
//        var.mSelimutBesarPls = (CircleButton) findViewById(R.id.selimut_besar_pls);
//        var.mBagCoverPls = (CircleButton) findViewById(R.id.bag_cover_pls);
//        var.mGordengKecilPls = (CircleButton) findViewById(R.id.gordeng_kecil_pls);
//        var.mGordengBesarPls = (CircleButton) findViewById(R.id.gordeng_besar_pls);
//        var.mSepatuPls = (CircleButton) findViewById(R.id.sepatu_pls);
//        var.mBantalPls = (CircleButton) findViewById(R.id.bantal_pls);
//        var.mTasKecilPls = (CircleButton) findViewById(R.id.tas_kecil_pls);
//        var.mTasBesarPls = (CircleButton) findViewById(R.id.tas_besar_pls);
//        var.mSpreiKecilPls = (CircleButton) findViewById(R.id.sprei_kecil_pls);
//        var.mSpreiBesarPls = (CircleButton) findViewById(R.id.sprei_besar_pls);
        getDataProfile();

    }

    @Override
    public void getDataProfile() {
        mOrderPresenterMvp.getProfileData();
    }

    @Override
    public void setRoomDormitory(String room, String dormitory) {
        mAlamatPengirimanValueDormitory.setText(dormitory);
        mAlamatPengirimanValueRoom.setText(room);
    }

    @Override
    protected void onDestroy() {
        mOrderPresenterMvp.onDestroy();
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

//    private void getExpressTime() {
//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");
//        calendar.add(Calendar.DAY_OF_WEEK, 2);
//        mExpressTime = dateFormat.format(calendar.getTime());
//    }

//    private void getKilatTime() {
//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");
//        calendar.add(Calendar.DAY_OF_WEEK, 1);
//        mKilatTime = dateFormat.format(calendar.getTime());
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
////            case R.id.button_normal:
////                normalBtn.setBackgroundResource(R.drawable.btn_order_background);
////                normalBtn.setTextColor(getResources().getColor(R.color.putih));
////                expressBtn.setBackgroundResource(R.drawable.btn_order_border);
////                expressBtn.setTextColor(getResources().getColor(R.color.biruLaut));
////                kilatBtn.setBackgroundResource(R.drawable.btn_order_border);
////                kilatBtn.setTextColor(getResources().getColor(R.color.biruLaut));
////
////                getDoneTime();
////                mForecaseTimeTxt.setText(mDoneTime);
////
////                break;
////            case R.id.button_express:
////                normalBtn.setBackgroundResource(R.drawable.btn_order_border);
////                normalBtn.setTextColor(getResources().getColor(R.color.biruLaut));
////                expressBtn.setBackgroundResource(R.drawable.btn_order_background);
////                expressBtn.setTextColor(getResources().getColor(R.color.putih));
////                kilatBtn.setBackgroundResource(R.drawable.btn_order_border);
////                kilatBtn.setTextColor(getResources().getColor(R.color.biruLaut));
////
////                getExpressTime();
////                mForecaseTimeTxt.setText(mExpressTime);
////                break;
////            case R.id.button_kilat:
////                normalBtn.setBackgroundResource(R.drawable.btn_order_border);
////                normalBtn.setTextColor(getResources().getColor(R.color.biruLaut));
////                expressBtn.setBackgroundResource(R.drawable.btn_order_border);
////                expressBtn.setTextColor(getResources().getColor(R.color.biruLaut));
////                kilatBtn.setBackgroundResource(R.drawable.btn_order_background);
////                kilatBtn.setTextColor(getResources().getColor(R.color.putih));
////
////                getKilatTime();
////                mForecaseTimeTxt.setText(mKilatTime);
////                break;
//
//
//                break;
//        }
//    }

    @Override
    @OnClick(R.id.confirm_btn)
    public void confirmOnClick() {
        showDialog();
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
//    public void buttonBodyOnclick(View view) {
//        switch (view.getId()) {
//            case R.id.kaos_min:
//                mKaosInt = mKaosInt - 1;
//                if (mKaosInt < 1)
//                    mKaosInt = 0;
//                mKaosNum.setText("" + mKaosInt);
//                break;
//            case R.id.kaos_pls:
//                mKaosInt = mKaosInt + 1;
//                mKaosNum.setText("" + mKaosInt);
//                break;
//
//            case R.id.kaos_dalam_min:
//                mKaosDalamInt = mKaosDalamInt - 1;
//                if (mKaosDalamInt < 1)
//                    mKaosDalamInt = 0;
//                mKaosDalamNum.setText("" + mKaosDalamInt);
//                break;
//            case R.id.kaos_dalam_pls:
//                mKaosDalamInt = mKaosDalamInt + 1;
//                mKaosDalamNum.setText("" + mKaosDalamInt);
//                break;
//
//            case R.id.kemeja_min:
//                mKemejaInt = mKemejaInt - 1;
//                if (mKemejaInt < 1)
//                    mKemejaInt = 0;
//                mKemejaNum.setText("" + mKemejaInt);
//                break;
//            case R.id.kemeja_pls:
//                mKemejaInt = mKemejaInt + 1;
//                mKemejaNum.setText("" + mKemejaInt);
//                break;
//
//            case R.id.baju_muslim_min:
//                mBajuMuslimInt = mBajuMuslimInt - 1;
//                if (mBajuMuslimInt < 1)
//                    mBajuMuslimInt = 0;
//                mBajuMuslimNum.setText("" + mBajuMuslimInt);
//                break;
//            case R.id.baju_muslim_pls:
//                mBajuMuslimInt = mBajuMuslimInt + 1;
//                mBajuMuslimNum.setText("" + mBajuMuslimInt);
//                break;
//
//            case R.id.jaket_min:
//                mJaketInt = mJaketInt - 1;
//                if (mJaketInt < 1)
//                    mJaketInt = 0;
//                mJaketNum.setText("" + mJaketInt);
//                break;
//            case R.id.jaket_pls:
//                mJaketInt = mJaketInt + 1;
//                mJaketNum.setText("" + mJaketInt);
//                break;
//
//            case R.id.sweter_min:
//                mSweterInt = mSweterInt - 1;
//                if (mSweterInt < 1)
//                    mSweterInt = 0;
//                mSweterNum.setText("" + mSweterInt);
//                break;
//            case R.id.sweter_pls:
//                mSweterInt = mSweterInt + 1;
//                mSweterNum.setText("" + mSweterInt);
//                break;
//
//            case R.id.gamis_min:
//                mGamisInt = mGamisInt - 1;
//                if (mGamisInt < 1)
//                    mGamisInt = 0;
//                mGamisNum.setText("" + mGamisInt);
//                break;
//            case R.id.gamis_pls:
//                mGamisInt = mGamisInt + 1;
//                mGamisNum.setText("" + mGamisInt);
//                break;
//
//            case R.id.handuk_min:
//                mHandukInt = mHandukInt - 1;
//                if (mHandukInt < 1)
//                    mHandukInt = 0;
//                mHandukNum.setText("" + mHandukInt);
//                break;
//            case R.id.handuk_pls:
//                mHandukInt = mHandukInt + 1;
//                mHandukNum.setText("" + mHandukInt);
//                break;
//        }
//    }

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

//    public void buttonHandOnclick(View view) {
//        switch (view.getId()) {
//            case R.id.sarung_tangan_min:
//                mSarungTanganInt = mSarungTanganInt - 1;
//                if (mSarungTanganInt < 1)
//                    mSarungTanganInt = 0;
//                mSarungTanganNum.setText("" + mSarungTanganInt);
//                break;
//            case R.id.sarung_tangan_pls:
//                mSarungTanganInt = mSarungTanganInt + 1;
//                mSarungTanganNum.setText("" + mSarungTanganInt);
//                break;
//
//            case R.id.sapu_tangan_min:
//                mSapuTanganInt = mSapuTanganInt - 1;
//                if (mSapuTanganInt < 1)
//                    mSapuTanganInt = 0;
//                mSapuTanganNum.setText("" + mSapuTanganInt);
//                break;
//            case R.id.sapu_tangan_pls:
//                mSapuTanganInt = mSapuTanganInt + 1;
//                mSapuTanganNum.setText("" + mSapuTanganInt);
//                break;
//        }
//    }

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
//    public void buttonFeetOnclick(View view) {
//        switch (view.getId()) {
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case:
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//        }
//    }

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
//    public void buttonOtherOnclick(View view) {
//        switch (view.getId()) {
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case:
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//
//            case :
//
//                break;
//            case :
//
//                break;
//        }
//    }

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
    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin jumlah pakaian benar ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Tekan Ya untuk melanjutkan!")
                //.setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
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

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            // User is signed in
                            mOrderPresenterMvp.validateInputs(desc, timeNow, uniqNumTime, timeDone,
                                    bandana, topi, masker, kupluk, krudung, peci,
                                    kaos, kaosDalam, kemeja, bajuMuslim, jaket, sweter, gamis, handuk,
                                    sarungTangan, sapuTangan,
                                    celana, celanaDalam, celanaPendek, sarung, celanaOlahraga, rok, celanaLevis, kaosKaki,
                                    jasAlmamater, jas, selimutKecil, selimutBesar, bagCover, gordengKecil, gordengBesar, sepatu, bantal, tasKecil, tasBesar, spreiKecil, spreiBesar);
                        } else {
                            // No user is signed in
                        }
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
