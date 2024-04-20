package model.shapes;

import java.awt.Color;

public class Brush {
    // private Color brushColor = Color.black;
    // private int thickness = 2;
    // private boolean selected = false;
    private int StrokeSize = 0;

    // public Brush(int thickness, Color brushColor){
    //     this.brushColor = brushColor;
    //     this.thickness = thickness;
    // }
    
    // public boolean getSelected(){
    //     return selected;
    // }

    // public void setSelected(boolean selected){
    //     this.selected = selected;
    // }

    public int getStrokeSize() {
        return StrokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.StrokeSize = strokeSize;
    }
}
