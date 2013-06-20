package com.capsule.android.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.capsule.android.rest.api.UserClient;
import com.capsule.android.rest.extend.MyClientHttpRequestInterceptor;

public class TestActivity extends Activity {

	private EditText editURL = null;
	private TextView textConsole = null;

	private final RestTemplate template = new RestTemplate(true);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		editURL = (EditText) findViewById(R.id.editURL);
		textConsole = (TextView) findViewById(R.id.textConsole);

		template.getMessageConverters().add(new GsonHttpMessageConverter());

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new MyClientHttpRequestInterceptor());
		template.setInterceptors(interceptors);

	}

	public String getURL() {
		return editURL.getText().toString();
	}

	public void log(String msg) {

		if (msg == null) {
			textConsole.setText("msg is null");
			return;
		}

		textConsole.setText(msg);
	}

	public void click(View target) {
		login();
	}

	public void login() {
		new DownloadFilesTask().execute();
	}

	private class DownloadFilesTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... arg0) {
			UserClient client = new UserClient(template);
			// User user = client.register("18888888888", "sadgi93");
			// int rslt = client.login(user.get_id(), "sadgi93");
			client.loginByNumber("18888888888", "sadgi93");
			return client.getCurrentUser().get_id();
			// int rslt = client.logout();
			// return String.valueOf(rslt);

		}

		@Override
		protected void onPostExecute(String result) {
			log(result);
		}

	}
}
