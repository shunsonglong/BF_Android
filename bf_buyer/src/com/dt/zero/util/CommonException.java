package com.dt.zero.util;

import android.content.res.Resources;
import android.text.TextUtils;

import com.dt.zero.R;
import com.dt.zero.app.AppMgr;

public class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String error;
	private int error_code;

	public CommonException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonException(String message, Throwable cause) {
		 error = message;
	}

	public CommonException(String message) {
		error = message;
	}

	public CommonException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getError() {

		String result;

		if (!TextUtils.isEmpty(error)) {
			result = error;
		} else {

			String name = "code" + error_code;
			int i = AppMgr
					.getInstance()
					.getResources()
					.getIdentifier(name, "string",
							AppMgr.getInstance().getPackageName());

			try {
				result = AppMgr.getInstance().getString(i);

			} catch (Resources.NotFoundException e) {

				result = AppMgr.getInstance().getString(
						R.string.unknown_error_code)
						+ error_code;
			}
		}

		return result;
	}
	
	public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getError_code() {
        return error_code;
    }

}
