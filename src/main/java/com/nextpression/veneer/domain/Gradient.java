package com.nextpression.veneer.domain;

import android.graphics.drawable.GradientDrawable;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.nextpression.veneer.util.ColorUtils;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.util.Entry;

import java.util.List;

//http://developer.android.com/reference/android/graphics/drawable/GradientDrawable.html
/*
    <gradient id="gradientName" orientation="BOTTOM_TOP">
        <color>#FF012853</color>
        <color>#FF708f96</color>
    </gradient>
 */
@Root
public class Gradient implements Entry {

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "orientation")
    private String orientation;

    @ElementList(inline = true, entry = "color")
    private List<String> colors;

    public Gradient(@Attribute(name = "name") String name,
                    @Attribute(name = "orientation") String orientation,
                    @ElementList(inline = true, entry = "color") List<String> colors) {
        this.name = name;
        this.orientation = orientation;
        this.colors = colors;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getOrientation() {
        return orientation;
    }

    public List<String> getColors() {
        return colors;
    }

    public GradientDrawable drawableFromGradient() {
        return new GradientDrawable(determineOrientation(), buildColors());
    }

    private int[] buildColors() {
        List<Integer> concreteColors = Lists.newArrayList();
        for (String colorString : colors) {
            concreteColors.add(ColorUtils.parseColor(colorString));
        }
        return Ints.toArray(concreteColors);
    }

    private GradientDrawable.Orientation determineOrientation() {
        return GradientDrawable.Orientation.valueOf(orientation);
    }

    //315 = TL_BR
}
