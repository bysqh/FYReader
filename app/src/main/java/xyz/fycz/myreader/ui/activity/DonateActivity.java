package xyz.fycz.myreader.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.weaction.ddsdk.ad.DdSdkFlowAd;
import com.weaction.ddsdk.ad.DdSdkInterAd;
import com.weaction.ddsdk.ad.DdSdkRewardAd;

import java.util.List;

import io.reactivex.annotations.NonNull;
import xyz.fycz.myreader.R;
import xyz.fycz.myreader.application.App;
import xyz.fycz.myreader.base.BaseActivity;
import xyz.fycz.myreader.base.observer.MySingleObserver;
import xyz.fycz.myreader.common.URLCONST;
import xyz.fycz.myreader.databinding.ActivityDonateBinding;
import xyz.fycz.myreader.ui.dialog.MyAlertDialog;
import xyz.fycz.myreader.util.SharedPreUtils;
import xyz.fycz.myreader.util.ToastUtils;
import xyz.fycz.myreader.util.utils.AdUtils;

/**
 * @author fengyue
 * @date 2021/4/23 21:23
 */
public class DonateActivity extends BaseActivity<ActivityDonateBinding> {

    private static final String TAG = DonateActivity.class.getSimpleName();

    @Override
    protected void bindView() {
        binding = ActivityDonateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void setUpToolbar(Toolbar toolbar) {
        super.setUpToolbar(toolbar);
        setStatusBarColor(R.color.colorPrimary, true);
        getSupportActionBar().setTitle(getString(R.string.support_author));
    }

    @Override
    protected void initWidget() {
        AdUtils.checkHasAd(true, false).subscribe(new MySingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    initAd();
                }
            }
        });
    }

    private void initAd() {
        binding.llAdSupport.setVisibility(View.VISIBLE);
        AdUtils.getFlowAd(this, 1, view -> binding.llAdSupport.addView(view, 2), null);
    }

    @Override
    protected void initClick() {
        binding.llWxZsm.setOnClickListener(v -> goDonate(URLCONST.WX_ZSM));
        binding.llZfbSkm.setOnClickListener(v -> goDonate(URLCONST.ZFB_SKM));
        binding.llQqSkm.setOnClickListener(v -> goDonate(URLCONST.QQ_SKM));
        binding.rlThanks.setOnClickListener(v ->
                MyAlertDialog.showFullWebViewDia(this, URLCONST.THANKS_URL,
                        false, null));

        binding.llRewardedVideo.setOnClickListener(v -> AdUtils.showRewardVideoAd(this, null));

        binding.llInterAd.setOnClickListener(v -> AdUtils.showInterAd(this, null));
    }

    private void goDonate(String address) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(address));
            startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showError(e.getLocalizedMessage());
        }
    }
}
