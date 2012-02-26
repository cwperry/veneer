package com.nextpression.veneer.domain;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.util.Entry;

import java.util.List;

@Root
public class Image implements Entry {

    @Attribute(name = "name")
    private String name;

    @ElementList(inline = true, entry = "file")
    private List<String> fileList;

    public Image(@Attribute(name = "name") String name,
                 @ElementList(inline = true, entry = "file") List<String> fileList) {
        this.name = name;
        this.fileList = fileList;
    }

    @Override
    public String getName() {
        return name;
    }


    public Drawable drawable() {
        if (fileList.size() > 1) {
            StateListDrawable drawable = new StateListDrawable();
            drawable.addState(new int[]{android.R.attr.state_pressed},
                    BitmapDrawable.createFromPath(fileList.get(1)));
            drawable.addState(StateSet.WILD_CARD, BitmapDrawable.createFromPath(fileList.get(0)));
            return drawable;
        }
        return BitmapDrawable.createFromPath(fileList.get(0));
    }
}
