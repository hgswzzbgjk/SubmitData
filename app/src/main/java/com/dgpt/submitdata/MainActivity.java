package com.dgpt.submitdata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
	private EditText et_username;
	private EditText et_password;
	private ListView lv;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		et_password = (EditText) findViewById(R.id.et_password);
		et_username = (EditText) findViewById(R.id.et_username);
		lv=(ListView)findViewById(R.id.lv);

	}
	//HttpURLConnection GET方式
	public void getclick(View view) {
		//拿到用户输入的用户名
		final String username = et_username.getText().toString().trim();
		//拿到密码
		final String password = et_password.getText().toString().trim();
		new Thread() {
			public void run() {
				//调用LoginService里面的方法访问服务器  并拿到服务器返回的信息
				final String result = LoginUtils.loginByGet(username,
						password);
				if (result != null) {
					//UI线程更改界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
						}
					});
				} else {
					// 请求失败  UI线程弹出toast
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "请求失败...", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			};
		}.start();
	}
	//HttpURLConnection POST方式
	public void postclick(View view) {
		//首先获取界面用户输入的用户名和密码
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		new Thread() {//开启子线程访问网络
			public void run() {
				//调用LoginService里面的方法访问网络
				final String result = LoginUtils.loginByPost(username,
						password);
				if (result != null) {
					//ui线程更改界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
						}
					});
				} else {
					// 请求失败,使用UI线程更改UI界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "请求失败...", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			};
		}.start();
	}
	//HttpURLConnection POST JSON方式
	public void jsonclick(View view) {
		//首先获取界面用户输入的用户名和密码
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("username", username);
		jsonObject.addProperty("password",password);

		final String jsonstr=jsonObject.toString();
		Log.v("MainActivityjsonstr", jsonstr);
		new Thread() {//开启子线程访问网络
			public void run() {
				//调用LoginService里面的方法访问网络
				final String result = LoginUtils.loginByJson(jsonstr);
				if (result != null) {
					//ui线程更改界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
							Log.i("MainActivityresult", result);
							JsonObject returnData=new JsonParser().parse(result).getAsJsonObject();
							Log.i("MainActivity",returnData.get("result").toString());
						}
					});
				} else {
					// 请求失败,使用UI线程更改UI界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "请求失败...", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			};
		}.start();
	}
	//HttpURLConnection POST x-www-form-urlencoded方式
	public void wwwclick(View view) {
		//首先获取界面用户输入的用户名和密码
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();

		//参数
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", username);
		params.put("password", password);
		final String wwwstr=LoginUtils.getRequestData(params, "utf-8").toString();
		Log.v("MainActivityjsonstr", wwwstr);
		new Thread() {//开启子线程访问网络
			public void run() {
				//调用LoginService里面的方法访问网络
				final String result = LoginUtils.loginByWWW(wwwstr);
				if (result != null) {
					//ui线程更改界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
							Log.i("MainActivityresult", result);
							JsonObject returnData=new JsonParser().parse(result).getAsJsonObject();
							Log.i("MainActivity",returnData.get("result").toString());
						}
					});
				} else {
					// 请求失败,使用UI线程更改UI界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "请求失败...", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			};
		}.start();
	}


	public void getalluser(View view) {
		new Thread() {//开启子线程访问网络
			public void run() {
				//调用LoginService里面的方法访问网络
				final String allUser = LoginUtils.getAllUser();
				if (allUser != null) {
					//ui线程更改界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, allUser, Toast.LENGTH_SHORT).show();
							Log.i("MainActivityresult", allUser);
							Gson gson = new Gson();
							JsonParser jsonParser = new JsonParser();
							JsonArray jsonElements = jsonParser.parse(allUser).getAsJsonArray();//获取JsonArray对象
							List<UserInfo> userInfos =new ArrayList<UserInfo>();
							for (JsonElement bean : jsonElements) {
								UserInfo user = gson.fromJson(bean, UserInfo.class);//解析
								userInfos.add(user);
							}
							MyAdpter adpter=new MyAdpter(MainActivity.this, userInfos);
							lv.setAdapter(adpter);

						}
					});
				} else {
					// 请求失败,使用UI线程更改UI界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "请求失败...", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			};
		}.start();
	}

	//HttpURLConnection POST 获取所有用户
	public void getallusernew(View view) {
		//首先获取界面用户输入的用户名和密码
		new Thread() {//开启子线程访问网络
			public void run() {
				//调用LoginService里面的方法访问网络
				final String allUserNew = LoginUtils.getAllUserNew();
				if (allUserNew != null) {
					//ui线程更改界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, allUserNew, Toast.LENGTH_SHORT).show();
							Log.i("MainActivityresult", allUserNew);
							Gson gson = new Gson();
							Result result = gson.fromJson(allUserNew, Result.class);
							List<UserInfo> userInfos =  result.getData();
							MyAdpter adpter=new MyAdpter(MainActivity.this, userInfos);
							lv.setAdapter(adpter);
						}
					});
				} else {
					// 请求失败,使用UI线程更改UI界面
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "请求失败...", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			};
		}.start();
	}

}

