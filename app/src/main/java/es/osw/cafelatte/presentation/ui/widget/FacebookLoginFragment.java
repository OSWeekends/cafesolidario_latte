package es.osw.cafelatte.presentation.ui.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import es.osw.cafelatte.R;
import es.osw.cafelatte.presentation.ui.BaseFragment;
import java.util.Arrays;

public class FacebookLoginFragment extends BaseFragment {
  private static final String[] FACEBOOK_PERMISSIONS = new String[] { "public_profile", "email" };
  private final LoginManager mFacebookLoginManager = LoginManager.getInstance();

  private CallbackManager mCallbackManager;
  private OnFacebookLoginListener mListener;

  public void setOnFacebookLoginListener(OnFacebookLoginListener listener) {
    this.mListener = listener;
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_facebook_login, container, false);
  }

  @Override public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.setOnClickListener(v -> {
      mListener.onFacebookLoginStart();
      mFacebookLoginManager.logInWithReadPermissions(FacebookLoginFragment.this,
          Arrays.asList(FACEBOOK_PERMISSIONS));
    });
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    this.initialize();
  }

  private void initialize() {
    mCallbackManager = CallbackManager.Factory.create();
    mFacebookLoginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
      @Override public void onSuccess(LoginResult loginResult) {
        FacebookLoginFragment.this.mListener.onFacebookLoginSuccess(
            loginResult.getAccessToken().getToken());
      }

      @Override public void onCancel() {
        FacebookLoginFragment.this.mListener.onFacebookLoginCancel();
      }

      @Override public void onError(FacebookException error) {
        FacebookLoginFragment.this.mListener.onFacebookLoginError();
      }
    });
  }

  public interface OnFacebookLoginListener {

    void onFacebookLoginStart();

    void onFacebookLoginSuccess(String token);

    void onFacebookLoginCancel();

    void onFacebookLoginError();
  }
}
