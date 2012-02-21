package com.nextpression.veneer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.google.common.annotations.VisibleForTesting;
import com.nextpression.veneer.domain.XmlVeneer;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.CycleStrategy;
import org.simpleframework.xml.strategy.Strategy;

public class XmlResourceManager implements ResourceManager {

    private XmlVeneer veneer;

    @Override
    public int color(String name) {
        return 0;
    }

    @Override
    public GradientDrawable gradient(String name) {
        return veneer.gradientColor(name);
    }

    @Override
    public Drawable icon(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String string(String name) {
        return veneer.string(name);
    }

    @VisibleForTesting
    void buildVeneer(String xmlString) throws Exception {
        Strategy strategy = new CycleStrategy("id", "ref");
        Serializer serializer = new Persister(strategy);
        veneer = serializer.read(XmlVeneer.class, xmlString);
    }

}
