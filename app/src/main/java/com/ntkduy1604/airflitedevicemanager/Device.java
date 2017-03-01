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
    private String mId, mName, mSerialno, mActivedate;
//    private String mModel, mTagid, mUserid, mComid;     //Scalable data
    // Miwok icon imaging
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // Constructor without image
    public Device(String vId,
                  String vName,
                  String vSerialno,
//                String vUserid,
//                String vComid,
//                String vModel,
//                String vTagid,
                  String vActivedate
                ) {
        mId = vId;
        mName = vName;
        mSerialno = vSerialno;
        mActivedate = vActivedate;
//        mUserid = vUserid;
//        mComid = vComid;
//        mModel = vModel;
//        mTagid = vTagid;
    }

    // Constructor with image
    public Device(String vId,
                  String vName,
                  String vSerialno,
                  String vActivedate,
//                String vUserid,
//                String vComid,
//                String vModel,
//                String vTagid,
                  int imageResourceId
                ) {
        mId = vId;
        mName = vName;
        mSerialno = vSerialno;
        mActivedate = vActivedate;
//        mUserid = vUserid;
//        mComid = vComid;
//        mModel = vModel;
//        mTagid = vTagid;
        mImageResourceId = imageResourceId;
    }

    public String getId()           {        return mId;                    }
    public String getName()         {        return mName;                  }
    public String getSerialno()     {        return mSerialno;              }
    public String getActivedate()   {        return mActivedate;            }
//    public String getUserid()       {        return mUserid;                }
//    public String getComid()        {        return mComid;                 }
//    public String getModel()        {        return mModel;                 }
//    public String getTagid()        {        return mTagid;                 }

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
