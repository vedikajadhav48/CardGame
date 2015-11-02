package com.game.vedikajadhav.cardgame;

/**
 * Created by Vedika Jadhav on 3/28/2015.
 */
public class BlankImageWithFaceImage {
    private int blankImageId;
    private int faceImageId;

   public BlankImageWithFaceImage(int blankId, int faceId) {
        blankImageId = blankId;
        faceImageId = faceId;
    }

    public int getBlankImageId() {
        return blankImageId;
    }

    public int getFaceImageId() {
        return faceImageId;
    }

    public void setBlankImageId(int bId) {
        this.blankImageId = bId;
    }

    public void setFaceImageId(int fId) {
        this.faceImageId = fId;
    }
}
