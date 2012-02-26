package com.nextpression.veneer.domain;

import android.R;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import com.google.common.collect.Lists;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowBitmapDrawable;
import com.xtremelabs.robolectric.shadows.ShadowStateListDrawable;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ImageTest {

    @Test
    public void testDrawableReturnsBitmapDrawableIfOneItemInList() {
        Image image = new Image("star", Lists.newArrayList("media/star.png"));

        Drawable drawable = image.drawable();

        assertThat(drawable, instanceOf(BitmapDrawable.class));
        assertThat(((ShadowBitmapDrawable) shadowOf(drawable)).getPath(), is("media/star.png"));
    }

    @Test
    public void testDrawableReturnsStateListDrawableIfTwoItemsInList() {
        Image image = new Image("star", Lists.newArrayList("/media/star.png", "/media/star_gray.png"));

        Drawable drawable = image.drawable();
        assertThat(drawable, instanceOf(StateListDrawable.class));
        ShadowStateListDrawable shadow = (ShadowStateListDrawable) shadowOf(drawable);

        Drawable pressed = shadow.getDrawableForState(new int[]{R.attr.state_pressed});
        assertNotNull(pressed);
        assertThat(((ShadowBitmapDrawable) shadowOf(pressed)).getPath(), is("/media/star_gray.png"));

        Drawable normal = shadow.getDrawableForState(StateSet.WILD_CARD);
        assertNotNull(normal);
        assertThat(((ShadowBitmapDrawable) shadowOf(normal)).getPath(), is("/media/star.png"));
    }
}
