package com.example.vedikajadhav.cardgame;

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

    // references to our images
/*    private BlankImageWithFaceImage[] imageIds = {
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.club2),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.club8),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.diamond6),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.diamond9),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.diamond_king),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.heart_king),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.heart_jack),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.heart_queen),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.heart3),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.spade4),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.spade_king),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.spade9),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.club2),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.club8),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.diamond6),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.diamond9),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.diamond_king),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.heart_king),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.heart_jack),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.heart_queen),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.heart3),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.spade4),
            new BlankImageWithFaceImage(R.drawable.images,
                    R.drawable.spade_king),
            new BlankImageWithFaceImage(R.drawable.images, R.drawable.spade9) }*/;
}
