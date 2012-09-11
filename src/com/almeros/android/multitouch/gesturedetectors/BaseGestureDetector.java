package com.almeros.android.multitouch.gesturedetectors;

import android.content.Context;
import android.view.MotionEvent;

/**
 * @author Almer Thie (code.almeros.com)
 */
public abstract class BaseGestureDetector {
    protected final Context mContext;
    protected boolean mGestureInProgress;

    protected MotionEvent mPrevEvent;
    protected MotionEvent mCurrEvent;
    
    protected float mCurrPressure;
    protected float mPrevPressure;
    protected long mTimeDelta;
    
    
	/**
	 * This value is the threshold ratio between the previous combined pressure
	 * and the current combined pressure. When pressure decreases rapidly
	 * between events the position values can often be imprecise, as it usually
	 * indicates that the user is in the process of lifting a pointer off of the
	 * device. This value was tuned experimentally.
	 */
    protected static final float PRESSURE_THRESHOLD = 0.67f;

    
    public BaseGestureDetector(Context context) {
    	mContext = context; 	
    }
    
	/**
	 * All gesture detectors need to be called through this method to be able to
	 * detect gestures. This method delegates work to handler methods
	 * (handleStartProgressEvent, handleInProgressEvent) implemented in
	 * extending classes.
	 * 
	 * @param event
	 * @return
	 */
    public boolean onTouchEvent(MotionEvent event){
    	final int actionCode = event.getAction() & MotionEvent.ACTION_MASK;
    	if (!mGestureInProgress) {
    		handleStartProgressEvent(actionCode, event);
    	} else {
    		handleInProgressEvent(actionCode, event);
    	}
    	return true;
    }
    
    /**
	 * Called when the current event occurred when NO gesture is in progress
	 * yet. The handling in this implementation may set the gesture in progress
	 * (via mGestureInProgress) or out of progress
	 * @param actionCode
	 * @param event
	 */
    protected abstract void handleStartProgressEvent(int actionCode, MotionEvent event);
    
	/**
	 * Called when the current event occurred when a gesture IS in progress. The
	 * handling in this implementation may set the gesture out of progress (via
	 * mGestureInProgress).
	 * @param action
	 * @param event
	 */
    protected abstract void handleInProgressEvent(int actionCode, MotionEvent event);
    
    
    protected void updateStateByEvent(MotionEvent curr){
    	final MotionEvent prev = mPrevEvent;
    	
    	// Reset mCurrEvent
        if (mCurrEvent != null) {
            mCurrEvent.recycle();
            mCurrEvent = null;
        }
        mCurrEvent = MotionEvent.obtain(curr);
        
        
        // Delta time
        mTimeDelta = curr.getEventTime() - prev.getEventTime();

        // Pressure
        mCurrPressure = curr.getPressure(curr.getActionIndex());
        mPrevPressure = prev.getPressure(prev.getActionIndex());
    }
    
    protected void resetState() {
        if (mPrevEvent != null) {
            mPrevEvent.recycle();
            mPrevEvent = null;
        }
        if (mCurrEvent != null) {
            mCurrEvent.recycle();
            mCurrEvent = null;
        }
        mGestureInProgress = false;
    }


    /**
     * Returns {@code true} if a gesture is currently in progress.
     * @return {@code true} if a gesture is currently in progress, {@code false} otherwise.
     */
    public boolean isInProgress() {
        return mGestureInProgress;
    }

	/**
	 * Return the time difference in milliseconds between the previous accepted
	 * GestureDetector event and the current GestureDetector event.
	 * 
	 * @return Time difference since the last move event in milliseconds.
	 */
	public long getTimeDelta() {
		return mTimeDelta;
	}

	/**
	 * Return the event time of the current GestureDetector event being
	 * processed.
	 * 
	 * @return Current GestureDetector event time in milliseconds.
	 */
	public long getEventTime() {
		return mCurrEvent.getEventTime();
	}
   
}
