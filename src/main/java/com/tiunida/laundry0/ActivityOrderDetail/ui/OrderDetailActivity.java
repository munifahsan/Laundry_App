package com.tiunida.laundry0.ActivityOrderDetail.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityOrderDetail.OrderDetailPresenter;
import com.tiunida.laundry0.ActivityOrderDetail.OrderDetailPresenterMvp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailViewMvp {
    private OrderDetailPresenterMvp mOrderDetailPresenterMvp;

    @BindView(R.id.detailOrderToolbar)
    Toolbar mDetailOrderToolbar;
    @BindView(R.id.laudnryType)
    TextView laundryType;
    @BindView(R.id.laudryWeight)
    TextView laundryWeight;
    @BindView(R.id.originalPrice)
    TextView originalPrice;
    @BindView(R.id.laudryDiskon)
    TextView laundryDiskon;
    @BindView(R.id.laundryPrice)
    TextView laundryPrice;
    @BindView(R.id.acceptIndicator)
    View acceptIndicator;
    @BindView(R.id.acceptLine)
    View acceptLine;
    @BindView(R.id.prosesIndicator)
    View prosesIndicator;
    @BindView(R.id.prosesLine)
    View prosesLine;
    @BindView(R.id.doneIndicator)
    View doneIndicator;
    @BindView(R.id.doneLine)
    View doneLine;
    @BindView(R.id.paidIndicator)
    View paidIndicator;
    @BindView(R.id.paidLine)
    View paidLine;
    @BindView(R.id.deliveredIndicator)
    View deliveredIndicator;
    @BindView(R.id.tglPesan)
    TextView tglPesan;
    @BindView(R.id.tglSelesai)
    TextView tglSelesai;

    @BindView(R.id.bandanaNum)
    TextView bandanaNum;
    @BindView(R.id.topiNum)
    TextView topiNum;
    @BindView(R.id.maskerNum)
    TextView maskerNum;
    @BindView(R.id.kuplukNum)
    TextView kuplukNum;
    @BindView(R.id.krudungNum)
    TextView krudungNum;
    @BindView(R.id.peciNum)
    TextView peciNum;
    @BindView(R.id.kaosNum)
    TextView kaosNum;
    @BindView(R.id.kaosDalamNum)
    TextView koasDalamNum;
    @BindView(R.id.kemejaNum)
    TextView kemejaNum;
    @BindView(R.id.bajuMuslimNum)
    TextView bajuMuslimNum;
    @BindView(R.id.jaketNum)
    TextView jaketNum;
    @BindView(R.id.sweterNum)
    TextView sweterNum;
    @BindView(R.id.gamisNum)
    TextView gamisNum;
    @BindView(R.id.handukNum)
    TextView handukNum;
    @BindView(R.id.sarungTanganNum)
    TextView sarungTanganNum;
    @BindView(R.id.sapuTanganNum)
    TextView sapuTanganNum;
    @BindView(R.id.celanaNum)
    TextView celanaNum;
    @BindView(R.id.celanaDalamNum)
    TextView celanaDalamNum;
    @BindView(R.id.celanaPendekNum)
    TextView celanaPendekNum;
    @BindView(R.id.sarungNum)
    TextView sarungNum;
    @BindView(R.id.celanaOlahRagaNum)
    TextView celanaOlahRagaNum;
    @BindView(R.id.rokNum)
    TextView rokNum;
    @BindView(R.id.celanaLevisNum)
    TextView celanaLevisNum;
    @BindView(R.id.kaosKakiNum)
    TextView kaosKakiNum;
    @BindView(R.id.jasAlmamaterNum)
    TextView jasAlmamaterNum;
    @BindView(R.id.jasNum)
    TextView jasNum;
    @BindView(R.id.selimutKecilNum)
    TextView selimutKecilNum;
    @BindView(R.id.selimutBesarNum)
    TextView selimutBesarNum;
    @BindView(R.id.bagCoverNum)
    TextView bagCoverNum;
    @BindView(R.id.gordengKecilNum)
    TextView gordengKecilNum;
    @BindView(R.id.gordengBesarNum)
    TextView gordengBesarNum;
    @BindView(R.id.sepatuNum)
    TextView sepatuNum;
    @BindView(R.id.bantalNum)
    TextView bantalNum;
    @BindView(R.id.taskecilNum)
    TextView tasKecilNum;
    @BindView(R.id.tasBesarNum)
    TextView tasBesarNum;
    @BindView(R.id.spreiKecilNum)
    TextView spreiKecilNum;
    @BindView(R.id.spreiBesarNum)
    TextView spreiBesarNum;

    @BindView(R.id.bandaCard)
    CardView bandanaCard;
    @BindView(R.id.topiCard)
    CardView topiCard;
    @BindView(R.id.maskerCard)
    CardView maskerCard;
    @BindView(R.id.kuplukCard)
    CardView kuplukCard;
    @BindView(R.id.krudungCard)
    CardView krudungCard;
    @BindView(R.id.peciCard)
    CardView peciCard;
    @BindView(R.id.kaosCard)
    CardView kaosCard;
    @BindView(R.id.kaosDalamCard)
    CardView koasDalamCard;
    @BindView(R.id.kemejaCard)
    CardView kemejaCard;
    @BindView(R.id.bajuMuslimCard)
    CardView bajuMuslimCard;
    @BindView(R.id.jaketCard)
    CardView jaketCard;
    @BindView(R.id.sweterCard)
    CardView sweterCard;
    @BindView(R.id.gamisCard)
    CardView gamisCard;
    @BindView(R.id.handukCard)
    CardView handukCard;
    @BindView(R.id.sarungTanganCard)
    CardView sarungTanganCard;
    @BindView(R.id.sapuTanganCard)
    CardView sapuTanganCard;
    @BindView(R.id.celanaCard)
    CardView celanaCard;
    @BindView(R.id.celanaDalamCard)
    CardView celanaDalamCard;
    @BindView(R.id.celanaPendekCard)
    CardView celanaPendekCard;
    @BindView(R.id.sarungCard)
    CardView sarungCard;
    @BindView(R.id.celanaOlahRagaCard)
    CardView celanaOlahRagaCard;
    @BindView(R.id.rokCard)
    CardView rokCard;
    @BindView(R.id.celanaLevisCard)
    CardView celanaLevisCard;
    @BindView(R.id.kaosKakiCard)
    CardView kaosKakiCard;
    @BindView(R.id.jasAlmamaterCard)
    CardView jasAlmamaterCard;
    @BindView(R.id.jasCard)
    CardView jasCard;
    @BindView(R.id.selimutKecilCard)
    CardView selimutKecilCard;
    @BindView(R.id.selimutBesarCard)
    CardView selimutBesarCard;
    @BindView(R.id.bagCoverCard)
    CardView bagCoverCard;
    @BindView(R.id.gordengKecilCard)
    CardView gordengKecilCard;
    @BindView(R.id.gordengBesarCard)
    CardView gordengBesarCard;
    @BindView(R.id.sepatuCard)
    CardView sepatuCard;
    @BindView(R.id.bantalCard)
    CardView bantalCard;
    @BindView(R.id.tasKecilCard)
    CardView tasKecilCard;
    @BindView(R.id.tasBesarCard)
    CardView tasBesarCard;
    @BindView(R.id.spreiKecilCard)
    CardView spreiKecilCard;
    @BindView(R.id.spreiBesarCard)
    CardView spreiBesarCard;

    @BindView(R.id.askAdminBtn)
    Button askAdminBtn;
    @BindView(R.id.order_detail_progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        mOrderDetailPresenterMvp = new OrderDetailPresenter(this);
        mOrderDetailPresenterMvp.onCreate();

        ButterKnife.bind(this);

        Intent orderDetailIntent = getIntent();
        String order_id = orderDetailIntent.getStringExtra("id");

        Toast.makeText(OrderDetailActivity.this, "order id " + order_id, Toast.LENGTH_LONG).show();

        mOrderDetailPresenterMvp.getOrderData(order_id);

        setSupportActionBar(mDetailOrderToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle("ORDER DETAIL");
        mDetailOrderToolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        mDetailOrderToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        mOrderDetailPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.askAdminBtn)
    public void askAdminBtnOnClick() {
        openWhatsApp();
    }

    private void openWhatsApp() {
        String smsNumber = "6282301198226"; // E164 format without '+' sign
//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//        sendIntent.setType("text/plain");
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
//        sendIntent.setPackage("com.whatsapp");
////        if (intent.resolveActivity(getActivity().getPackageManager()) == null) {
////            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
////            return;
////        }
//        startActivity(sendIntent);

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            waIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void hideProgress(){
        progressBar.setVisibility(View.GONE);
    }

    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void setAskAdminBtnEnable(){
        askAdminBtn.setEnabled(true);
    }

    public void setAskAdminBtnDisable(){
        askAdminBtn.setEnabled(false);
    }

    private void sendWhatsapp(String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    public void setTglPesan(String data) {
        tglPesan.setText(data);
    }

    public void setTglSelesai(String data) {
        tglSelesai.setText(data);
    }

    public void setLaundryType(String data) {
        laundryType.setText(data);
    }

    public void setLaundryWeight(String data) {
        laundryWeight.setText(data);
    }

    public void setLaundryPrice(String data) {
        laundryPrice.setText(data);
    }

    public void setLaundryOriginalPrice(String data) {
        originalPrice.setText(data);
    }

    public void setLaundryDiskon(String data) {
        laundryDiskon.setText(data);
    }

    public void setAcceptIndicatorCheck() {
        acceptIndicator.setBackgroundResource(R.drawable.circle_view_background);
        acceptLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
    }

    public void setAcceptIndicatorUnCheck() {
        acceptIndicator.setBackgroundResource(R.drawable.circle_view_border);
        acceptLine.setBackgroundResource(R.drawable.ractangle_view_putih);
    }

    public void setProsesIndicatorCheck() {
        prosesIndicator.setBackgroundResource(R.drawable.circle_view_background);
        prosesLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
    }

    public void setProsesIndicatorUnCheck() {
        prosesIndicator.setBackgroundResource(R.drawable.circle_view_border);
        prosesLine.setBackgroundResource(R.drawable.ractangle_view_putih);
    }

    public void setDoneIndicatorCheck() {
        doneIndicator.setBackgroundResource(R.drawable.circle_view_background);
        doneLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
    }

    public void setDoneIndicatorUnCheck() {
        doneIndicator.setBackgroundResource(R.drawable.circle_view_border);
        doneLine.setBackgroundResource(R.drawable.ractangle_view_putih);
    }

    public void setPaidIndicatorCheck() {
        paidIndicator.setBackgroundResource(R.drawable.circle_view_background);
        paidLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
    }

    public void setPaidIndicatorUnCheck() {
        paidIndicator.setBackgroundResource(R.drawable.circle_view_border);
        paidLine.setBackgroundResource(R.drawable.ractangle_view_putih);
    }

    public void setDeliveredIndicatorCheck() {
        deliveredIndicator.setBackgroundResource(R.drawable.circle_view_background);
    }

    public void setDeliveredIndicatorUnCheck() {
        deliveredIndicator.setBackgroundResource(R.drawable.circle_view_border);
    }

    public void setBandanaNumTxt(String data) {
        bandanaNum.setText(data);
    }

    public void setBandanaCardGone() {
        bandanaCard.setVisibility(View.GONE);
    }

    public void setBandanaCardVisible() {
        bandanaCard.setVisibility(View.VISIBLE);
    }

    public void setTopiNumTxt(String data) {
        topiNum.setText(data);
    }

    public void setTopiCardGone() {
        topiCard.setVisibility(View.GONE);
    }

    public void setTopiCardVisible() {
        topiCard.setVisibility(View.VISIBLE);
    }

    public void setMaskerNumTxt(String data) {
        maskerNum.setText(data);
    }

    public void setMaskerCardGone() {
        maskerCard.setVisibility(View.GONE);
    }

    public void setMaskerCardVisible() {
        maskerCard.setVisibility(View.VISIBLE);
    }

    public void setKuplukNumTxt(String data) {
        kuplukNum.setText(data);
    }

    public void setKuplukCardGone() {
        kuplukCard.setVisibility(View.GONE);
    }

    public void setKuplukCardVisible() {
        kuplukCard.setVisibility(View.VISIBLE);
    }

    public void setKrudungNumTxt(String data) {
        krudungNum.setText(data);
    }

    public void setKrudungCardGone() {
        krudungCard.setVisibility(View.GONE);
    }

    public void setKrudungCardVisible() {
        krudungCard.setVisibility(View.VISIBLE);
    }

    public void setPeciNumTxt(String data) {
        peciNum.setText(data);
    }

    public void setPeciCardGone() {
        peciCard.setVisibility(View.GONE);
    }

    public void setPeciCardVisible() {
        peciCard.setVisibility(View.VISIBLE);
    }

    public void setKaosNumTxt(String data) {
        kaosNum.setText(data);
    }

    public void setKaosCardGone() {
        kaosCard.setVisibility(View.GONE);
    }

    public void setKaosCardVisible() {
        kaosCard.setVisibility(View.VISIBLE);
    }

    public void setKaosDalamNumTxt(String data) {
        koasDalamNum.setText(data);
    }

    public void setKaosDalamCardGone() {
        koasDalamCard.setVisibility(View.GONE);
    }

    public void setKaosDalamCardVisible() {
        koasDalamCard.setVisibility(View.VISIBLE);
    }

    public void setKemejaNumTxt(String data) {
        kemejaNum.setText(data);
    }

    public void setKemajaCardGone() {
        kemejaCard.setVisibility(View.GONE);
    }

    public void setKemejaCardVisible() {
        kemejaCard.setVisibility(View.VISIBLE);
    }

    public void setBajuMuslimNumTxt(String data) {
        bajuMuslimNum.setText(data);
    }

    public void setBajuMuslimCardGone() {
        bajuMuslimCard.setVisibility(View.GONE);
    }

    public void setBajuMuslimCardVisible() {
        bajuMuslimCard.setVisibility(View.VISIBLE);
    }

    public void setJaketNumTxt(String data) {
        jaketNum.setText(data);
    }

    public void setJaketCardGone() {
        jaketCard.setVisibility(View.GONE);
    }

    public void setJaketCardVisible() {
        jaketCard.setVisibility(View.VISIBLE);
    }

    public void setSweterNumTxt(String data) {
        sweterNum.setText(data);
    }

    public void setSweterCardGone() {
        sweterCard.setVisibility(View.GONE);
    }

    public void setSweterCardVisible() {
        sweterCard.setVisibility(View.VISIBLE);
    }

    public void setGamisNumTxt(String data) {
        gamisNum.setText(data);
    }

    public void setGamisCardGone() {
        gamisCard.setVisibility(View.GONE);
    }

    public void setGamisCardVisible() {
        gamisCard.setVisibility(View.VISIBLE);
    }

    public void setHandukNumTxt(String data) {
        handukNum.setText(data);
    }

    public void setHandukCardGone() {
        handukCard.setVisibility(View.GONE);
    }

    public void setHandukCardVisible() {
        handukCard.setVisibility(View.VISIBLE);
    }

    public void setSarungTanganNumTxt(String data) {
        sarungTanganNum.setText(data);
    }

    public void setSarungTanganCardGone() {
        sarungTanganCard.setVisibility(View.GONE);
    }

    public void setSarungTanganCardVisible() {
        sarungTanganCard.setVisibility(View.VISIBLE);
    }

    public void setSapuTanganNumTxt(String data) {
        sapuTanganNum.setText(data);
    }

    public void setSapuTanganCardGone() {
        sapuTanganCard.setVisibility(View.GONE);
    }

    public void setSapuTanganCardVisible() {
        sapuTanganCard.setVisibility(View.VISIBLE);
    }

    public void setCelanaNumTxt(String data) {
        celanaNum.setText(data);
    }

    public void setCelanaCardGone() {
        celanaCard.setVisibility(View.GONE);
    }

    public void setCelanaCardVisible() {
        celanaCard.setVisibility(View.VISIBLE);
    }

    public void setCelanaDalamNumTxt(String data) {
        celanaDalamNum.setText(data);
    }

    public void setCelanaDalamCardGone() {
        celanaDalamCard.setVisibility(View.GONE);
    }

    public void setCelanaDalamCardVisible() {
        celanaDalamCard.setVisibility(View.VISIBLE);
    }

    public void setCelanaPendekNumTxt(String data) {
        celanaPendekNum.setText(data);
    }

    public void setCelanaPendekCardGone() {
        celanaPendekCard.setVisibility(View.GONE);
    }

    public void setCelanaPendekCardVisible() {
        celanaPendekCard.setVisibility(View.VISIBLE);
    }

    public void setSarungNumTxt(String data) {
        sarungNum.setText(data);
    }

    public void setSarungCardGone() {
        sarungCard.setVisibility(View.GONE);
    }

    public void setSarungCardVisible() {
        sarungCard.setVisibility(View.VISIBLE);
    }

    public void setCelanaOlahragaNumTxt(String data) {
        celanaOlahRagaNum.setText(data);
    }

    public void setCelanaOlahragaCardGone() {
        celanaOlahRagaCard.setVisibility(View.GONE);
    }

    public void setCelanaOlahragaCardVisible() {
        celanaOlahRagaCard.setVisibility(View.VISIBLE);
    }

    public void setRokNumTxt(String data) {
        rokNum.setText(data);
    }

    public void setRokCardGone() {
        rokCard.setVisibility(View.GONE);
    }

    public void setRokCardVisible() {
        rokCard.setVisibility(View.VISIBLE);
    }

    public void setCelanaLevisNumTxt(String data) {
        celanaLevisNum.setText(data);
    }

    public void setCelanaLevisCardGone() {
        celanaLevisCard.setVisibility(View.GONE);
    }

    public void setCelanaLevisCardVisible() {
        celanaLevisCard.setVisibility(View.VISIBLE);
    }

    public void setKaosKakiNumTxt(String data) {
        kaosKakiNum.setText(data);
    }

    public void setKaosKakiCardGone() {
        kaosKakiCard.setVisibility(View.GONE);
    }

    public void setKoasKakiCardVisible() {
        kaosKakiCard.setVisibility(View.VISIBLE);
    }

    public void setJasAlmamaterNumTxt(String data) {
        jasAlmamaterNum.setText(data);
    }

    public void setJasAlmamaterCardGone() {
        jasAlmamaterCard.setVisibility(View.GONE);
    }

    public void setJasAlmamaterCardVisible() {
        jasAlmamaterCard.setVisibility(View.VISIBLE);
    }

    public void setJasNumTxt(String data) {
        jasNum.setText(data);
    }

    public void setJasCardGone() {
        jasCard.setVisibility(View.GONE);
    }

    public void setJasCardVisible() {
        jasCard.setVisibility(View.VISIBLE);
    }

    public void setSelimutKecilNumTxt(String data) {
        selimutKecilNum.setText(data);
    }

    public void setSelimutKecilCardGone() {
        selimutKecilCard.setVisibility(View.GONE);
    }

    public void setSelimutKecilCardVisible() {
        selimutKecilCard.setVisibility(View.VISIBLE);
    }

    public void setSelimutBesarNumTxt(String data) {
        selimutBesarNum.setText(data);
    }

    public void setSelimutBesarCardGone() {
        selimutBesarCard.setVisibility(View.GONE);
    }

    public void setSelimutBesarCardVisible() {
        selimutKecilCard.setVisibility(View.VISIBLE);
    }

    public void setBagCoverNumTxt(String data) {
        bagCoverNum.setText(data);
    }

    public void setBagCoverCardGone() {
        bagCoverCard.setVisibility(View.GONE);
    }

    public void setBagCoverCardVisible() {
        bagCoverCard.setVisibility(View.VISIBLE);
    }

    public void setGordengKecilNumTxt(String data) {
        gordengKecilNum.setText(data);
    }

    public void setGordengKecilCardGone() {
        gordengKecilCard.setVisibility(View.GONE);
    }

    public void setGordengKecilCardVisible() {
        gordengKecilCard.setVisibility(View.VISIBLE);
    }

    public void setGordengBesarNumTxt(String data) {
        gordengBesarNum.setText(data);
    }

    public void setGordengBesarCardGone() {
        gordengBesarCard.setVisibility(View.GONE);
    }

    public void setGordengBesarCardVisible() {
        gordengBesarCard.setVisibility(View.VISIBLE);
    }

    public void setSepatuNumTxt(String data) {
        sepatuNum.setText(data);
    }

    public void setSepatuCardGone() {
        sepatuCard.setVisibility(View.GONE);
    }

    public void setSepatuCardVisible() {
        sepatuCard.setVisibility(View.VISIBLE);
    }

    public void setBantalNumTxt(String data) {
        bantalNum.setText(data);
    }

    public void setBantalCardGone() {
        bantalCard.setVisibility(View.GONE);
    }

    public void setBantalCardVisible() {
        bantalCard.setVisibility(View.VISIBLE);
    }

    public void setTaskecilNumTxt(String data) {
        tasKecilNum.setText(data);
    }

    public void setTasKecilCardGone() {
        tasKecilCard.setVisibility(View.GONE);
    }

    public void setTasKecilCardVisible() {
        tasKecilCard.setVisibility(View.VISIBLE);
    }

    public void setTasBesarNumTxt(String data) {
        tasBesarNum.setText(data);
    }

    public void setTasBesarCardGone() {
        tasBesarCard.setVisibility(View.GONE);
    }

    public void setTasBesarCardVisible() {
        tasBesarCard.setVisibility(View.VISIBLE);
    }

    public void setSreiKecilNumTxt(String data) {
        spreiKecilNum.setText(data);
    }

    public void setSpreiKecilCardGone() {
        spreiKecilCard.setVisibility(View.GONE);
    }

    public void setSpreiKecilCardVisible() {
        spreiKecilCard.setVisibility(View.VISIBLE);
    }

    public void setSpreiBesarNumTxt(String data) {
        spreiBesarNum.setText(data);
    }

    public void setSpreiBesarCardGone() {
        spreiBesarCard.setVisibility(View.GONE);
    }

    public void setSpreiBesarCardVisible() {
        spreiBesarCard.setVisibility(View.VISIBLE);
    }
}
