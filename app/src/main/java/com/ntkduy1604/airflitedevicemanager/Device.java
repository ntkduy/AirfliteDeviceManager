package com.ntkduy1604.airflitedevicemanager;

/**
 * Created by NTKDUY on 2/26/2017
 * for PIGGY HOUSE
 * you can contact me at: ntkduy1604@gmail.com
 */

/**
 * {@link Device} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Miwok translation for that word and an image
 */
public class Device {
    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    // Default translation for the word
    private String mId, mName, mModel, mSerialno, mActivedate, mTagid;
//    private String mModel,  mUserid, mComid;     //Scalable data
    // Miwok icon imaging
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // Constructor
    public Device(String vId,
                  String vName,
                  String vModel,
                  String vSerialno,
                  String vActivedate
                ) {
        mId = vId;
        mName = vName;
        mModel = vModel;
        mSerialno = vSerialno;
        mActivedate = vActivedate;
    }

    public Device(String vId,
                  String vName,
                  String vModel,
                  String vSerialno,
                  String vTagid,
                  String vActivedate
    ) {
        mId = vId;
        mName = vName;
        mModel = vModel;
        mSerialno = vSerialno;
        mActivedate = vActivedate;
        mTagid = vTagid;
    }

    // Constructor with image
    public Device(String vId,
                  String vName,
                  String vModel,
                  String vSerialno,
                  String vActivedate,
                  int imageResourceId
                ) {
        mId = vId;
        mName = vName;
        mModel = vModel;
        mSerialno = vSerialno;
        mActivedate = vActivedate;
        mImageResourceId = imageResourceId;
    }

    public String getId()           {        return mId;                    }
    public String getName()         {        return mName;                  }
    public String getModel()        {        return mModel;                 }
    public String getSerialno()     {        return mSerialno;              }
    public String getActivedate()   {        return mActivedate;            }
    public String getTagid()        {        return mTagid;                 }

    // Get the image resource ID
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
