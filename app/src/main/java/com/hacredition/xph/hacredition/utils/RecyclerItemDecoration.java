package com.hacredition.xph.hacredition.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pc on 2017/1/19.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    int topBottom;
    int leftright;
    private Drawable mDivider;

    public RecyclerItemDecoration(int topBottom,int leftright,int mColor){
        this.topBottom = DimenUtil.dp2px(topBottom);
        this.leftright = DimenUtil.dp2px(leftright);
        if(mColor!=0){
            mDivider = new ColorDrawable(mColor);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        LinearLayoutManager layoutManager = (LinearLayoutManager)parent.getLayoutManager();
        if(mDivider==null||layoutManager.getChildCount()==0){
            return;
        }
        int left;
        int right;
        int top;
        int bottom;

        final int childCount = parent.getChildCount();
        if(layoutManager.getOrientation()==RecyclerView.VERTICAL){
            for(int i=0;i<childCount-1;i++){
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
                //分割线居中
                float center = (layoutManager.getBottomDecorationHeight(child)-topBottom)/2;
                left = layoutManager.getLeftDecorationWidth(child);
                right = parent.getWidth() - layoutManager.getRightDecorationWidth(child);
                top = (int) (child.getBottom() + params.bottomMargin + center);
                bottom = top + topBottom;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        if(layoutManager.getOrientation()==RecyclerView.VERTICAL){
            if(parent.getChildAdapterPosition(view)==layoutManager.getItemCount()-1){
                outRect.bottom = topBottom;
            }
            outRect.top = topBottom;
            outRect.left = leftright;
            outRect.right = leftright;
        }
    }

}
