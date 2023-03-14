package FlaNium.WinAPI.enums;

import FlaNium.WinAPI.exceptions.FlaNiumDriverException;

public enum BasePoint {

    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    CENTER,
    CENTER_TOP,
    CENTER_BOTTOM,
    CENTER_LEFT,
    CENTER_RIGHT,
    ZERO_POINT;

    public int getXCoordinate(int xTopLeftCoordinate, int width){
       switch (this){
           case TOP_LEFT:
           case BOTTOM_LEFT:
           case CENTER_LEFT:
           case ZERO_POINT:
               return xTopLeftCoordinate;
           case TOP_RIGHT:
           case BOTTOM_RIGHT:
           case CENTER_RIGHT:
               return xTopLeftCoordinate + width;
           case CENTER:
           case CENTER_TOP:
           case CENTER_BOTTOM:
               return xTopLeftCoordinate + width/2;
           default: throw new FlaNiumDriverException("Wrong BasePoint");
       }
    }


    public int getYCoordinate(int yTopLeftCoordinate, int height){
        switch (this){
            case TOP_LEFT:
            case TOP_RIGHT:
            case CENTER_TOP:
            case ZERO_POINT:
                return yTopLeftCoordinate;
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
            case CENTER_BOTTOM:
                return yTopLeftCoordinate + height;
            case CENTER_LEFT:
            case CENTER_RIGHT:
            case CENTER:
                return yTopLeftCoordinate + height/2;
            default: throw new FlaNiumDriverException("Wrong BasePoint");
        }
    }
}
