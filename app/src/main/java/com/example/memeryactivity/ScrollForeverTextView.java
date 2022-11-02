package com.example.memeryactivity;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;
/**
 * 
* @ClassName: ScrollForeverTextView 
* @Description: TODO(始终获取焦点的textview,为了跑马灯效果) 
* @author 
* @date 2016年7月21日 上午10:36:32 
*
 */
public class ScrollForeverTextView extends TextView {
	public ScrollForeverTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ScrollForeverTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ScrollForeverTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	@ExportedProperty(category = "focus")
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return true;// 重点
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		// TODO Auto-generated method stub
		super.onFocusChanged(true, direction, previouslyFocusedRect);// 重点
	}

	public void setUnderLine(boolean isEnable) {
		if (isEnable) {
			getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
			getPaint().setAntiAlias(true);
		}else {
			getPaint().setFlags(0);
			getPaint().setAntiAlias(false);
		}
	}
}
